package edu.njucm.book.user.dao;

import java.util.Date;

/**
 * @author lvrongwang
 * @since 2020/6/2 13:31
 */
public interface IUserLockDao {

    int add(Long userId);

    int delete(Long userId);

    int countByUserId(Long userId);

    Date getLockTime(Long userId);
}
