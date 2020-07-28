package edu.njucm.book.frame.dao;

import edu.njucm.book.frame.domain.ChapterInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @author lvrongwang
 * @since 2020/2/29 18:20
 */
public interface IChapterInfoDao {

    int save(@Param("chapterInfo") ChapterInfo chapterInfo);

    Long getId();

    int update(@Param("chapterInfo") ChapterInfo chapterInfo);

    int delete(@Param("chapterId") Long chapterId);

    ChapterInfo getChapterInfoByChapterId(@Param("chapterId") Long chapterId);

    List<ChapterInfo> listChapterInfoByBookId(@Param("bookId") Long bookId);
}
