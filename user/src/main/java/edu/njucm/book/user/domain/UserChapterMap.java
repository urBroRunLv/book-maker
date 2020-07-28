package edu.njucm.book.user.domain;

import java.util.Date;

/**
 * @author lvrongwang
 * @since 2020/5/3 23:36
 */
public class UserChapterMap {

    public UserChapterMap(Long chapterId, Long userId) {
        this.chapterId = chapterId;
        this.userId = userId;
    }

    public UserChapterMap() {
    }

    private Long recId;
    private Long chapterId;
    private Long userId;
    private Short deleteFlag;
    private Date addTime;

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
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
        return "UserChapterMap{" + "recId=" + recId + ", chapterId=" + chapterId + ", userId=" + userId
                + ", deleteFlag=" + deleteFlag + ", addTime=" + addTime + '}';
    }
}
