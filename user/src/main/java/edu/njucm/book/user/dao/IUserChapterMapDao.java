package edu.njucm.book.user.dao;

import java.util.List;

import edu.njucm.book.user.domain.UserChapterMap;
import org.apache.ibatis.annotations.Param;

import edu.njucm.book.user.domain.UserBookMap;

/**
 * @author lvrongwang
 * @since 2020/5/3 23:37
 */
public interface IUserChapterMapDao {

    int add(@Param("map") UserChapterMap map);

    int delete(@Param("recId") Long recId);

    int deleteByChapterId(@Param("chapterId") Long chapterId);

    List<UserChapterMap> listByUserId(@Param("userId") Long userId);

    int countByUserIdAndChapterId(@Param("userId") Long userId, @Param("chapterId") Long chapterId);
}
