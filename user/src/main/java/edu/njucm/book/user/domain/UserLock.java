package edu.njucm.book.user.domain;

import java.util.Date;

/**
 * @author lvrongwang
 * @since 2020/6/2 13:32
 */
public class UserLock {

    private Long recId;
    private Long userId;
    private Date failTime;

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getFailTime() {
        return failTime;
    }

    public void setFailTime(Date failTime) {
        this.failTime = failTime;
    }

    @Override
    public String toString() {
        return "UserLock{" +
                "recId=" + recId +
                ", userId=" + userId +
                ", failTime=" + failTime +
                '}';
    }
}
