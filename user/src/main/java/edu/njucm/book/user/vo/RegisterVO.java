package edu.njucm.book.user.vo;

/**
 * @author lvrongwang
 * @since 2020/4/11 22:51
 */
public class RegisterVO {

    public RegisterVO(String userName, String userPhone, String userPassword, String verifyPassword, String errorMsg) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userPassword = userPassword;
        this.verifyPassword = verifyPassword;
        this.errorMsg = errorMsg;
    }

    public RegisterVO() {
    }

    private String userName;
    private String userPhone;
    private String userPassword;
    private String verifyPassword;
    private String errorMsg;

    public RegisterVO withErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "RegisterVO{" + "userName='" + userName + '\'' + ", userPhone='" + userPhone + '\'' + ", userPassword='"
                + userPassword + '\'' + ", verifyPassword='" + verifyPassword + '\'' + ", errorMsg='" + errorMsg + '\''
                + '}';
    }
}
