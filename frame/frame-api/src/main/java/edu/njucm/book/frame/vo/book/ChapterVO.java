package edu.njucm.book.frame.vo.book;

import com.google.common.collect.Lists;
import edu.njucm.book.common.util.ContextUtils;
import edu.njucm.book.common.util.DateUtils;
import edu.njucm.book.frame.domain.BookInfo;
import edu.njucm.book.frame.domain.ChapterInfo;
import edu.njucm.book.frame.domain.ContentInfo;
import edu.njucm.book.frame.service.IBookInfoService;
import edu.njucm.book.frame.service.IChapterInfoService;
import edu.njucm.book.frame.service.IChatInfoService;
import edu.njucm.book.frame.service.IContentInfoService;
import edu.njucm.book.frame.vo.chat.ChatVO;
import edu.njucm.book.user.service.IUserBookMapService;

import java.util.Date;
import java.util.List;

import static edu.njucm.book.common.util.ContextUtils.getContext;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * @author lvrongwang
 * @since 2020/4/19 15:53
 */
public class ChapterVO extends BaseVO {

    /**
     * 章节id
     */
    private Long chapterId;
    /**
     * 所属书本id
     */
    private Long bookId;
    /**
     * 书本名
     */
    private String bookName;
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
     * 可编辑标志：true-可，1-不可
     */
    private boolean editFlag;
    /**
     * 内容list
     */
    private List<ContentVO> contentVOs;
    /**
     * 讨论list
     */
    private List<ChatVO> chatVOs;
    /**
     * 添加时间
     */
    private String addTime;

    public static ChapterVO tran2ChapterVO(Long userId, ChapterInfo info) {
        ChapterVO vo = new ChapterVO();
        if (nonNull(info)) {
            vo.setChapterId(info.getChapterId());
            vo.setChapterNo(info.getChapterNo());
            vo.setChapterName(info.getChapterName());
            vo.setChatFlag(info.getChatFlag());
            vo.setEditFlag(
                    getContext().getBean(IUserBookMapService.class).isUserCanEditChapter(userId, info.getChapterId()));
            vo.setBookId(info.getBookId());
            BookInfo bookInfo = getContext().getBean(IBookInfoService.class).getBookInfoByBookId(info.getBookId());
            if (nonNull(bookInfo)) {
                vo.setBookName(bookInfo.getBookName());
            }
            vo.setContentVOs(ContentVO.tran2ContentVOList(
                    getContext().getBean(IContentInfoService.class).listContentInfoByChapterId(info.getChapterId())));
            vo.setChatVOs(ChatVO.tran2ChatVOs(userId,
                    getContext().getBean(IChatInfoService.class).listByChapterId(info.getChapterId())));
            vo.setAddTime(DateUtils.formatTime(info.getAddTime()));
        }
        return vo;
    }

    public static List<ChapterVO> tran2ChapterVOList(Long userId, List<ChapterInfo> chapterInfos) {
        if (isNull(chapterInfos) || chapterInfos.isEmpty()) {
            return Lists.newArrayList();
        }
        List<ChapterVO> chapterVOs = Lists.newArrayList();
        for (ChapterInfo chapterInfo : chapterInfos) {
            chapterVOs.add(tran2ChapterVO(userId, chapterInfo));
        }
        return chapterVOs;
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

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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

    public boolean isEditFlag() {
        return editFlag;
    }

    public void setEditFlag(boolean editFlag) {
        this.editFlag = editFlag;
    }

    public List<ContentVO> getContentVOs() {
        return contentVOs;
    }

    public void setContentVOs(List<ContentVO> contentVOs) {
        this.contentVOs = contentVOs;
    }

    public List<ChatVO> getChatVOs() {
        return chatVOs;
    }

    public void setChatVOs(List<ChatVO> chatVOs) {
        this.chatVOs = chatVOs;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "ChapterVO{" + "chapterId=" + chapterId + ", bookId=" + bookId + ", bookName='" + bookName + '\''
                + ", chapterName='" + chapterName + '\'' + ", chapterNo=" + chapterNo + ", chatFlag=" + chatFlag
                + ", editFlag=" + editFlag + ", contentVOs=" + contentVOs + ", chatVOs=" + chatVOs + ", addTime='"
                + addTime + '\'' + '}';
    }
}
