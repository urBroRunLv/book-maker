package edu.njucm.book.frame.service.impl;

import com.google.common.collect.Lists;
import edu.njucm.book.common.constant.Constants;
import edu.njucm.book.frame.dao.IBookInfoDao;
import edu.njucm.book.frame.domain.BookInfo;
import edu.njucm.book.frame.domain.ChapterInfo;
import edu.njucm.book.frame.domain.ReferenceInfo;
import edu.njucm.book.frame.service.IBookInfoService;
import edu.njucm.book.frame.service.IChapterInfoService;
import edu.njucm.book.frame.service.IChatInfoService;
import edu.njucm.book.frame.service.IReferenceInfoService;
import edu.njucm.book.frame.vo.AllVO;
import edu.njucm.book.frame.vo.book.BookVO;
import edu.njucm.book.frame.vo.book.ChapterVO;
import edu.njucm.book.frame.vo.book.ContentVO;
import edu.njucm.book.frame.vo.chat.ChatVO;
import edu.njucm.book.user.domain.User;
import edu.njucm.book.user.domain.UserBookMap;
import edu.njucm.book.user.service.IUserBookMapService;
import edu.njucm.book.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * @author lvrongwang
 * @since 2020/2/29 20:03
 */
@Service
public class BookInfoServiceImpl implements IBookInfoService {

    @Autowired
    private IBookInfoDao bookInfoDao;
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserBookMapService userBookMapService;
    @Autowired
    private IChapterInfoService chapterInfoService;
    @Autowired
    private IReferenceInfoService referenceInfoService;

    @Transactional
    @Override
    public boolean addBook(BookInfo bookInfo, Long userId) {
        if (isNull(bookInfo)) {
            return false;
        }
        bookInfo.setBookStatus(Constants.STATUS_NORMAL);
        UserBookMap map = new UserBookMap();
        map.setBookId(saveBookInfo(bookInfo));
        map.setUserId(userId);
        return userBookMapService.add(map);
    }

    @Override
    public Long saveBookInfo(BookInfo bookInfo) {
        if (isNull(bookInfo)) {
            return null;
        }
        if (bookInfoDao.save(bookInfo) > 0) {
            return bookInfoDao.getId();
        }
        return null;
    }

    @Override
    public boolean updateBookInfo(BookInfo bookInfo) {
        if (isNull(bookInfo)) {
            return false;
        }
        return bookInfoDao.update(bookInfo) > 0;
    }

    @Transactional
    @Override
    public boolean deleteBook(Long bookId) {
        if (isNull(bookId)) {
            return false;
        }
        return deleteBookInfoByBookId(bookId) && userBookMapService.deleteByBookId(bookId);
    }

    @Override
    public boolean deleteBookInfoByBookId(Long bookId) {
        if (isNull(bookId)) {
            return false;
        }
        BookInfo bookInfo = bookInfoDao.getBookInfoByBookId(bookId);
        if (Objects.nonNull(bookInfo)) {
            return bookInfoDao.update(bookInfo.withStatusDeleted()) > 0;
        }
        return false;
    }

    @Override
    public BookInfo getBookInfoByBookId(Long bookId) {
        if (isNull(bookId)) {
            return null;
        }
        return bookInfoDao.getBookInfoByBookId(bookId);
    }

    @Override
    public List<BookInfo> listBookInfoByUserIds(List<Long> userIds) {
        if (userIds.isEmpty()) {
            return Lists.newArrayList();
        }
        List<BookInfo> bookInfos = Lists.newArrayList();
        for (Long userId : userIds) {
            bookInfos.addAll(bookInfoDao.listBookInfoByUserId(userId));
        }
        return bookInfos;
    }

    @Override
    public List<BookInfo> listBookInfoByUserId(Long userId) {
        if (isNull(userId)) {
            return Lists.newArrayList();
        }
        return listBookInfoByUserIds(Lists.newArrayList(userId));
    }

    @Override
    public List<BookInfo> listBookInfoBySearchParam(BookInfo bookInfo) {
        if (isNull(bookInfo)) {
            return Lists.newArrayList();
        }
        return bookInfoDao.listBookInfoBySearchParam(bookInfo);
    }

    @Override
    public AllVO getAllVOByBookId(Long userId, Long bookId) {
        if (isNull(bookId)) {
            return null;
        }
        if (!userBookMapService.isUserAccess(userId, bookId)) {
            return null;
        }
        BookInfo bookInfo = getBookInfoByBookId(bookId);
        List<ChapterInfo> chapterInfos = chapterInfoService.listChapterInfoByBookId(bookId);
        List<ReferenceInfo> referenceInfos = referenceInfoService.listReferenceInfoByBookId(bookId);
        return new AllVO(userBookMapService.listAuthorByBookId(bookId), BookVO.tran2BookVO(bookInfo),
                ChapterVO.tran2ChapterVOList(userId, chapterInfos), referenceInfos);
    }
}
