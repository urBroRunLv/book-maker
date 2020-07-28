package edu.njucm.book.user.domain;

import static edu.njucm.book.user.constant.UserTypeEnum.ADMIN;
import static edu.njucm.book.user.constant.UserTypeEnum.MANAGER;

import java.util.Date;

/**
 * @author lvrongwang
 * @since 2020/4/11 22:45
 */
public class User {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String userPassword;
    /**
     * 手机号
     */
    private String userPhone;
    /**
     * 隶属用户id
     */
    private Long parentUserId;
    /**
     * 用户身份 0-系统管理员 1-主编 2-编委
     */
    private Short userType;
    /**
     * 添加时间
     */
    private Date addTime;

    public User withUserType(Short userType) {
        this.userType = userType;
        return this;
    }

    public User withName(String name) {
        this.userName = name;
        return this;
    }

    public User withNoParent() {
        this.parentUserId = 0L;
        return this;
    }

    public User withParent(Long userId) {
        this.parentUserId = userId;
        return this;
    }

    public boolean isManager() {
        return ADMIN.getType().equals(this.userType) || MANAGER.getType().equals(this.userType);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Long getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(Long parentUserId) {
        this.parentUserId = parentUserId;
    }

    public Short getUserType() {
        return userType;
    }

    public void setUserType(Short userType) {
        this.userType = userType;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName='" + userName + '\'' + ", userPassword='" + userPassword
                + '\'' + ", userPhone='" + userPhone + '\'' + ", parentUserId=" + parentUserId + ", userType="
                + userType + ", addTime=" + addTime + '}';
    }
}
