package edu.njucm.book.frame.domain;

import edu.njucm.book.common.constant.StatusConstant;

import java.util.Date;

/**
 * 书本信息
 *
 * @author lvrongwang
 * @since 2020/2/29 19:17
 */
public class BookInfo {

    /**
     * 书本id
     */
    private Long bookId;
    /**
     * 书本名
     */
    private String bookName;
    /**
     * 书本状态：0-正常，1-删除
     */
    private Short bookStatus;
    /**
     * 书本封面
     */
    private Long bookPic;
    /**
     * 创作时间
     */
    private Date addTime;

    public BookInfo withStatusDeleted() {
        this.bookStatus = StatusConstant.STATUS_DELETE;
        return this;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Short getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(Short bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Long getBookPic() {
        return bookPic;
    }

    public void setBookPic(Long bookPic) {
        this.bookPic = bookPic;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookStatus=" + bookStatus +
                ", bookPic=" + bookPic +
                ", addTime=" + addTime +
                '}';
    }
}
