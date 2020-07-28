package edu.njucm.book.user.dao;

import edu.njucm.book.user.domain.UserBookMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lvrongwang
 * @since 2020/5/3 23:37
 */
public interface IUserBookMapDao {

    int add(@Param("map") UserBookMap map);

    int delete(@Param("recId") Long recId);

    int deleteByBookId(@Param("bookId") Long bookId);

    List<UserBookMap> listByUserId(@Param("userId") Long userId);

    List<UserBookMap> listByBookId(@Param("bookId") Long bookId);

    int countByUserIdAndBookId(@Param("userId") Long userId, @Param("bookId") Long bookId);
}
