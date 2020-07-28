package edu.njucm.book.frame.domain;

import edu.njucm.book.frame.constant.ChatOpenFlag;
import edu.njucm.book.frame.domain.chat.ChatInfo;

import java.util.Date;

import static edu.njucm.book.frame.constant.ChatOpenFlag.CLOSE;
import static edu.njucm.book.frame.constant.ChatOpenFlag.OPEN;
import static java.util.Objects.isNull;

/**
 * 章节信息
 *
 * @author lvrongwang
 * @since 2020/2/29 19:22
 */
public class ChapterInfo {

    /**
     * 章节id
     */
    private Long chapterId;
    /**
     * 所属书本id
     */
    private Long bookId;
    /**
     * 章节名称
     */
    private String chapterName;
    /**
     * 章节号
     */
    private Long chapterNo;
    /**
     * 讨论区开放标志：0-关闭，1-开放
     */
    private Short chatFlag;
    /**
     * 添加时间
     */
    private Date addTime;

    public boolean canChat() {
        if (isNull(this.chatFlag)) {
            return false;
        }
        return this.chatFlag.equals(OPEN.getFlag());
    }

    public ChapterInfo withChatFlag(Short chatFlag) {
        this.chatFlag = chatFlag;
        return this;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public Long getChapterNo() {
        return chapterNo;
    }

    public void setChapterNo(Long chapterNo) {
        this.chapterNo = chapterNo;
    }

    public Short getChatFlag() {
        return chatFlag;
    }

    public void setChatFlag(Short chatFlag) {
        this.chatFlag = chatFlag;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "ChapterInfo{" + "chapterId=" + chapterId + ", bookId=" + bookId + ", chapterName='" + chapterName + '\''
                + ", chapterNo=" + chapterNo + ", chatFlag=" + chatFlag + ", addTime=" + addTime + '}';
    }
}
