package edu.njucm.book.frame.domain.chat;

import java.util.Date;

/**
 * 讨论区
 *
 * @author lvrongwang
 * @since 2020/4/18 11:04
 */
public class ChatInfo {

    /**
     * 主键，讨论号
     */
    private Long chatId;
    /**
     * 讨论章节id
     */
    private Long chapterId;
    /**
     * 关联的讨论id
     */
    private Long linkChatId;
    /**
     * 发表的用户id
     */
    private Long userId;
    /**
     * 内容，最多100字
     */
    private String content;
    /**
     * 点赞数
     */
    private Long topNum;
    /**
     * 添加时间
     */
    private Date addTime;

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public Long getLinkChatId() {
        return linkChatId;
    }

    public void setLinkChatId(Long linkChatId) {
        this.linkChatId = linkChatId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Long getTopNum() {
        return topNum;
    }

    public void setTopNum(Long topNum) {
        this.topNum = topNum;
    }

    @Override
    public String toString() {
        return "ChatInfo{" +
                "chatId=" + chatId +
                ", chapterId=" + chapterId +
                ", linkChatId=" + linkChatId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", topNum=" + topNum +
                ", addTime=" + addTime +
                '}';
    }
}
