package edu.njucm.book.user.service;

import edu.njucm.book.user.domain.User;
import edu.njucm.book.user.vo.RegisterVO;

import java.util.Date;
import java.util.List;

/**
 * @author lvrongwang
 * @since 2020/4/12 16:12
 */
public interface IUserService {

    boolean add(User user);

    boolean update(User user);

    boolean delete(Long userId);

    /**
     * 用户注册
     *
     * @param registerVO
     * @return
     */
    Long userRegister(RegisterVO registerVO);

    /**
     * 根据用户id获取用户信息
     *
     * @param userId
     * @return
     */
    User getByUserId(Long userId);

    /**
     * 根据手机号获取用户信息（手机号作为用户唯一标识）
     *
     * @param userPhone
     * @return
     */
    User getByUserPhone(String userPhone);

    /**
     * 获取某个用户的权限用户，子用户（包含自己）
     *
     * @param userId
     * @return
     */
    List<User> listChildUserByUserId(Long userId);

    /**
     * 根据用户身份获取用户
     * 
     * @param userType
     * @return
     */
    List<User> listUserByUserType(Short userType);

    /**
     * 登录失败操作
     * 
     * @param userId
     * @return
     */
    boolean doAfterLoginFail(Long userId);

    /**
     * 判断是否锁定
     * 
     * @param userId
     * @return
     */
    boolean isLockUser(Long userId);

    /**
     * 解除锁定
     * 
     * @param userId
     * @return
     */
    boolean unlockUser(Long userId);

    Date getLockTime(Long userId);
}
