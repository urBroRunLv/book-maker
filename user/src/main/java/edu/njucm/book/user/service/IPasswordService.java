package edu.njucm.book.user.service;

/**
 * @author lvrongwang
 * @since 2020/4/13 21:13
 */
public interface IPasswordService {

    /**
     * 校验密码是否正确
     *
     * @param logonPassword
     * @param encryptedPassword
     * @return
     */
    boolean checkLogonPassword(String logonPassword, String encryptedPassword);

    /**
     * 校验用户密码是否正确
     *
     * @param logonPassword
     * @param userPhone
     * @return
     */
    boolean checkUserLogonPassword(String logonPassword, String userPhone);
}
