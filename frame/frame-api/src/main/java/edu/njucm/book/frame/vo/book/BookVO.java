package edu.njucm.book.frame.vo.book;

import edu.njucm.book.chart.entity.PicContent;
import edu.njucm.book.chart.service.PicContentService;
import edu.njucm.book.common.util.ContextUtils;
import edu.njucm.book.common.util.DateUtils;
import edu.njucm.book.frame.domain.BookInfo;

import static edu.njucm.book.common.util.PicUtils.getPicBase64Str;
import static java.util.Objects.nonNull;

/**
 * @author lvrongwang
 * @since 2020/4/16 18:39
 */
public class BookVO extends BaseVO {

    /**
     * 书本id
     */
    private Long bookId;
    /**
     * 书本名
     */
    private String bookName;
    /**
     * 书本图片 base64码
     */
    private String bookPic;
    /**
     * 创建时间
     */
    private String addTime;

    public static BookVO tran2BookVO(BookInfo info) {
        BookVO vo = new BookVO();
        if (nonNull(info)) {
            vo.setBookId(info.getBookId());
            vo.setBookName(info.getBookName());
            PicContent content = ContextUtils.getContext().getBean(PicContentService.class).findById(info.getBookPic());
            vo.setBookPic(getPicBase64Str(content.getContent()));
            vo.setAddTime(DateUtils.formatTime(info.getAddTime()));
        }
        return vo;
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

    public String getBookPic() {
        return bookPic;
    }

    public void setBookPic(String bookPic) {
        this.bookPic = bookPic;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "BookVO{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookPic='" + bookPic + '\'' +
                ", addTime='" + addTime + '\'' +
                "} " + super.toString();
    }
}
