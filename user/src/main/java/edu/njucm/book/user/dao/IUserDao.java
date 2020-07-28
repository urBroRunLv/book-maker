package edu.njucm.book.user.dao;

import edu.njucm.book.user.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lvrongwang
 * @since 2020/4/12/ 15:41
 */
public interface IUserDao {

    int add(@Param("user") User user);

    int update(@Param("user") User user);

    int delete(@Param("userId") Long userId);

    User getByUserId(@Param("userId") Long userId);

    User getByUserPhone(@Param("userPhone") String userPhone);

    List<User> listChildUserByUserId(@Param("userId") Long userId);

    List<User> listUserByUserType(@Param("userType") Short userType);
}
