package edu.njucm.book.frame.domain.chat;

import java.util.Date;

/**
 * @author lvrongwang
 * @since 2020/5/19 1:51
 */
public class UserTopMap {

    public UserTopMap(Long userId, Long chatId) {
        this.userId = userId;
        this.chatId = chatId;
    }

    private Long recId;
    private Long userId;
    private Long chatId;
    private Date addTime;

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

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "UserTopMap{" +
                "recId=" + recId +
                ", userId=" + userId +
                ", chatId=" + chatId +
                ", addTime=" + addTime +
                '}';
    }
}
