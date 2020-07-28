package edu.njucm.book.user.service.impl;

import com.google.common.collect.Lists;
import edu.njucm.book.user.dao.IUserBookMapDao;
import edu.njucm.book.user.dao.IUserChapterMapDao;
import edu.njucm.book.user.domain.User;
import edu.njucm.book.user.domain.UserBookMap;
import edu.njucm.book.user.domain.UserChapterMap;
import edu.njucm.book.user.service.IUserBookMapService;
import edu.njucm.book.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * @author lvrongwang
 * @since 2020/5/3 23:49
 */
@Service
public class UserBookMapServiceImpl implements IUserBookMapService {

    @Autowired
    private IUserBookMapDao userBookMapDao;
    @Autowired
    private IUserChapterMapDao userChapterMapDao;
    @Autowired
    private IUserService userService;

    @Override
    public List<UserBookMap> listByUserId(Long userId) {
        if (isNull(userId)) {
            return Lists.newArrayList();
        }
        return userBookMapDao.listByUserId(userId);
    }

    @Override
    public List<UserBookMap> listByBookId(Long bookId) {
        if (isNull(bookId)) {
            return Lists.newArrayList();
        }
        return userBookMapDao.listByBookId(bookId);
    }

    @Override
    public List<String> listAuthorByBookId(Long bookId) {
        if (isNull(bookId)) {
            return Lists.newArrayList();
        }
        List<Long> userIds = Lists.newArrayList();
        List<String> authors = Lists.newArrayList();
        for (UserBookMap map : listByBookId(bookId)) {
            userIds.add(map.getUserId());
        }
        for (Long userId : userIds) {
            User author = userService.getByUserId(userId);
            if (nonNull(author)) {
                authors.add(author.getUserName());
            }
        }
        return authors;
    }

    @Override
    public boolean add(UserBookMap map) {
        if (isNull(map)) {
            return false;
        }
        return userBookMapDao.add(map) > 0;
    }

    @Override
    public boolean addChapterRec(UserChapterMap map) {
        if (isNull(map)) {
            return false;
        }
        return userChapterMapDao.add(map) > 0;
    }

    @Override
    public boolean delete(Long recId) {
        if (isNull(recId)) {
            return false;
        }
        return userBookMapDao.delete(recId) > 0;
    }

    @Override
    public boolean deleteByBookId(Long bookId) {
        if (isNull(bookId)) {
            return false;
        }
        return userBookMapDao.deleteByBookId(bookId) > 0;
    }

    @Override
    public boolean isUserAccess(Long userId, Long bookId) {
        if (isNull(userId) || isNull(bookId)) {
            return false;
        }
        return userBookMapDao.countByUserIdAndBookId(userId, bookId) > 0;
    }

    @Override
    public boolean isUserCanEditChapter(Long userId, Long chapterId) {
        if (isNull(userId) || isNull(chapterId)) {
            return false;
        }
        return userChapterMapDao.countByUserIdAndChapterId(userId, chapterId) > 0;
    }
}
