package edu.njucm.book.user.vo;

/**
 * @author lvrongwang
 * @since 2020/4/13 20:34
 */
public class LoginVO {

    /**
     * 用户名或电话
     */
    private String userPhone;
    /**
     * 密码
     */
    private String userPassword;
    private String nextPage;
    private String errorMsg;

    public LoginVO withErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "LoginVO{" +
                "userPhone='" + userPhone + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", nextPage='" + nextPage + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
