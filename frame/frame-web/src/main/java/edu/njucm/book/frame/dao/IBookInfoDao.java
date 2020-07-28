package edu.njucm.book.frame.dao;

import edu.njucm.book.frame.domain.BookInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lvrongwang
 * @since 2020/2/29 18:20
 */
public interface IBookInfoDao {

    int save(@Param("bookInfo") BookInfo bookInfo);

    Long getId();

    int update(@Param("bookInfo") BookInfo bookInfo);

    int delete(@Param("bookId") Long bookId);

    BookInfo getBookInfoByBookId(@Param("bookId") Long bookId);

    List<BookInfo> listBookInfoByUserId(@Param("userId") Long userId);

    List<BookInfo> listBookInfoBySearchParam(BookInfo bookInfo);
}
