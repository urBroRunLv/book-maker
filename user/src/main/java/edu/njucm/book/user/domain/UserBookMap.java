package edu.njucm.book.user.domain;

import java.util.Date;

/**
 * @author lvrongwang
 * @since 2020/5/3 23:36
 */
public class UserBookMap {

    public UserBookMap(Long bookId, Long userId) {
        this.bookId = bookId;
        this.userId = userId;
    }

    public UserBookMap() {
    }

    private Long recId;
    private Long bookId;
    private Long userId;
    private Short deleteFlag;
    private Date addTime;

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Short getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Short deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "UserBookMap{" + "recId=" + recId + ", bookId=" + bookId + ", userId=" + userId + ", deleteFlag="
                + deleteFlag + ", addTime=" + addTime + '}';
    }
}
