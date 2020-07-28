package edu.njucm.book.frame.service;

import edu.njucm.book.frame.domain.BookInfo;
import edu.njucm.book.frame.vo.AllVO;

import java.util.List;

/**
 * @author lvrongwang
 * @since 2020/2/29 18:19
 */
public interface IBookInfoService {

    /**
     * 事务控制 前台存储书本并绑定
     *
     * @param bookInfo
     * @param userId
     * @return
     */
    boolean addBook(BookInfo bookInfo, Long userId);

    /**
     * 存储书本信息 不要调用
     *
     * @param bookInfo
     * @return
     */
    Long saveBookInfo(BookInfo bookInfo);

    /**
     * 更新书本信息
     *
     * @param bookInfo
     * @return
     */
    boolean updateBookInfo(BookInfo bookInfo);

    /**
     * 事务控制 删除书本+删除绑定
     * 
     * @param bookId
     * @return
     */
    boolean deleteBook(Long bookId);

    /**
     * 删除书本信息
     *
     * @param bookId
     * @return
     */
    boolean deleteBookInfoByBookId(Long bookId);

    /**
     * 根据id获取书本信息
     *
     * @param bookId
     * @return
     */
    BookInfo getBookInfoByBookId(Long bookId);

    /**
     * 根据用户id列表获取所做书本列表
     *
     * @param userIds
     * @return
     */
    List<BookInfo> listBookInfoByUserIds(List<Long> userIds);

    /**
     * 根据用户id获取所有权限书本列表
     *
     * @param userId
     * @return
     */
    List<BookInfo> listBookInfoByUserId(Long userId);

    /**
     * 根据参数获取书本列表
     *
     * @param bookInfo
     * @return
     */
    List<BookInfo> listBookInfoBySearchParam(BookInfo bookInfo);

    /**
     * 根据bookId获取全书信息
     * 
     * @param bookId
     * @return
     */
    AllVO getAllVOByBookId(Long userId, Long bookId);
}
