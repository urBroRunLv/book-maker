package edu.njucm.book.user.domain;

import edu.njucm.book.common.util.DateUtils;

import java.util.Date;

/**
 * @author lvrongwang
 * @since 2020/4/16 16:17
 */
public class Token {

    /**
     * 登录时间
     */
    private String loginTime;
    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 获取格式化登录时间
     */
    public String getFormatLoginTime() {
        return DateUtils.formatTime(new Date(Long.parseLong(this.loginTime)));
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "Token{" +
                "loginTime=" + loginTime +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }
}
