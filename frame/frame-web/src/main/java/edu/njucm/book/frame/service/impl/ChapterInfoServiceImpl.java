package edu.njucm.book.frame.service.impl;

import edu.njucm.book.frame.dao.IChapterInfoDao;
import edu.njucm.book.frame.domain.ChapterInfo;
import edu.njucm.book.frame.service.IChapterInfoService;

import edu.njucm.book.user.domain.UserChapterMap;
import edu.njucm.book.user.service.IUserBookMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author lvrongwang
 * @since 2020/2/29 20:08
 */
@Service
public class ChapterInfoServiceImpl implements IChapterInfoService {

    @Autowired
    private IChapterInfoDao chapterInfoDao;
    @Autowired
    private IUserBookMapService userBookMapService;

    @Transactional
    @Override
    public boolean saveChapterInfo(ChapterInfo chapterInfo, Long userId) {
        if (Objects.isNull(chapterInfo)) {
            return false;
        }
        return chapterInfoDao.save(chapterInfo) > 0
                && userBookMapService.addChapterRec(new UserChapterMap(chapterInfoDao.getId(), userId));
    }

    @Override
    public boolean updateChapterInfo(ChapterInfo chapterInfo) {
        if (Objects.isNull(chapterInfo)) {
            return false;
        }
        ChapterInfo chapterInfoFromDb = getChapterInfoByChapterId(chapterInfo.getChapterId());
        chapterInfo.setBookId(chapterInfoFromDb.getBookId());
        return chapterInfoDao.update(chapterInfo) > 0;
    }

    @Override
    public boolean deleteChapterInfoByChapterId(Long chapterId) {
        if (Objects.isNull(chapterId)) {
            return false;
        }
        return chapterInfoDao.delete(chapterId) > 0;
    }

    @Override
    public ChapterInfo getChapterInfoByChapterId(Long chapterId) {
        if (Objects.isNull(chapterId)) {
            return null;
        }
        return chapterInfoDao.getChapterInfoByChapterId(chapterId);
    }

    @Override
    public List<ChapterInfo> listChapterInfoByBookId(Long bookId) {
        if (Objects.isNull(bookId)) {
            return null;
        }
        return chapterInfoDao.listChapterInfoByBookId(bookId);
    }
}
