package edu.njucm.book.frame.service;

import edu.njucm.book.frame.domain.ChapterInfo;

import java.util.List;

/**
 * @author lvrongwang
 * @since 2020/2/29 18:19
 */
public interface IChapterInfoService {

    /**
     * 存储章节信息
     *
     * @param chapterInfo
     * @param userId
     * @return
     */
    boolean saveChapterInfo(ChapterInfo chapterInfo,Long userId);

    /**
     * 更新章节信息
     *
     * @param chapterInfo
     * @return
     */
    boolean updateChapterInfo(ChapterInfo chapterInfo);

    /**
     * 删除章节信息
     *
     * @param chapterId
     * @return
     */
    boolean deleteChapterInfoByChapterId(Long chapterId);

    /**
     * 根据id获取章节信息
     *
     * @param chapterId
     * @return
     */
    ChapterInfo getChapterInfoByChapterId(Long chapterId);

    /**
     * 根据bookId获取所有章节信息
     *
     * @param bookId
     * @return
     */
    List<ChapterInfo> listChapterInfoByBookId(Long bookId);
}
