package edu.njucm.book.user.web.domain;

import edu.njucm.book.user.domain.User;

/**
 * 上下文信息
 *
 * @author lvrongwang
 * @since 2020/4/16 15:54
 */
public class Context {

    /**
     * 登录成功标记
     */
    private boolean loginFlag;
    /**
     * 登录token
     */
    private boolean loginToken;
    /**
     * 用户信息
     */
    private User user;

    public boolean isLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(boolean loginFlag) {
        this.loginFlag = loginFlag;
    }

    public boolean isLoginToken() {
        return loginToken;
    }

    public void setLoginToken(boolean loginToken) {
        this.loginToken = loginToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Context{" +
                "loginFlag=" + loginFlag +
                ", loginToken=" + loginToken +
                ", user=" + user +
                '}';
    }
}
