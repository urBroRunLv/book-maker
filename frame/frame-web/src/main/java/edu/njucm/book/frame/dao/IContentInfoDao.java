package edu.njucm.book.frame.dao;

import edu.njucm.book.frame.domain.ContentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lvrongwang
 * @since 2020/2/29 18:20
 */
public interface IContentInfoDao {

    int save(ContentInfo contentInfo);

    int update(ContentInfo contentInfo);

    int delete(@Param("contentId") Long contentId);

    ContentInfo getContentInfoByContentId(@Param("contentId") Long contentId);

    List<ContentInfo> listContentInfoByChapterId(@Param("chapterId") Long chapterId);

    List<Long> listContentIdByChapterId(Long chapterId);
}
