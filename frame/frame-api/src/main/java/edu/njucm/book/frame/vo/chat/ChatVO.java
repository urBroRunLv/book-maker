package edu.njucm.book.frame.vo.chat;

import com.google.common.collect.Lists;
import edu.njucm.book.common.util.ContextUtils;
import edu.njucm.book.common.util.DateUtils;
import edu.njucm.book.frame.domain.BookInfo;
import edu.njucm.book.frame.domain.ChapterInfo;
import edu.njucm.book.frame.domain.chat.ChatInfo;
import edu.njucm.book.frame.service.IBookInfoService;
import edu.njucm.book.frame.service.IChapterInfoService;
import edu.njucm.book.frame.service.IChatInfoService;
import edu.njucm.book.frame.service.IUserTopMapService;
import edu.njucm.book.user.domain.User;
import edu.njucm.book.user.service.IUserService;

import java.util.List;

import static edu.njucm.book.common.util.ContextUtils.getContext;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * 讨论区前端交互vo
 *
 * @author lvrongwang
 * @since 2020/4/18 11:03
 */
public class ChatVO {

    /**
     * 讨论id
     */
    private Long chatId;
    /**
     * 书本名
     */
    private String bookName;
    /**
     * 章节号
     */
    private String chapterNo;
    /**
     * 章节名
     */
    private String chapterName;
    /**
     * 评论作者
     */
    private String author;
    private Long authorId;
    /**
     * 回复评论id+回复作者名
     */
    private Long chatToId;
    private String chatTo;
    /**
     * 内容
     */
    private String content;
    /**
     * 添加时间
     */
    private String addTime;
    /**
     * 點贊數
     */
    private Long topNum;
    /**
     * 当前用户已点赞
     */
    private boolean hasTop;

    private static ChatVO tran2ChatVO(Long userId, ChatInfo chatInfo) {
        if (isNull(chatInfo)) {
            return null;
        }
        ChatVO chatVO = new ChatVO();
        chatVO.setChatId(chatInfo.getChatId());
        chatVO.setAddTime(DateUtils.formatTime(chatInfo.getAddTime()));
        chatVO.setTopNum(chatInfo.getTopNum());
        chatVO.setContent(chatInfo.getContent());
        chatVO.setChatToId(chatInfo.getChatId());
        User author = getContext().getBean(IUserService.class).getByUserId(chatInfo.getUserId());
        if (nonNull(author)) {
            chatVO.setAuthor(author.getUserName());
            chatVO.setAuthorId(author.getUserId());
        }
        ChatInfo linkChat = getContext().getBean(IChatInfoService.class).getByChatId(chatInfo.getLinkChatId());
        if (nonNull(linkChat)) {
            User chatTo = getContext().getBean(IUserService.class).getByUserId(linkChat.getUserId());
            if (nonNull(chatTo)) {
                chatVO.setChatTo(chatTo.getUserName());
            }
        }
        ChapterInfo chapterInfo =
                getContext().getBean(IChapterInfoService.class).getChapterInfoByChapterId(chatInfo.getChapterId());
        if (nonNull(chapterInfo)) {
            chatVO.setChapterNo(String.valueOf(chapterInfo.getChapterNo()));
            chatVO.setChapterName(chapterInfo.getChapterName());
            BookInfo bookInfo =
                    getContext().getBean(IBookInfoService.class).getBookInfoByBookId(chapterInfo.getBookId());
            if (nonNull(bookInfo)) {
                chatVO.setBookName(bookInfo.getBookName());
            }
        }
        chatVO.setHasTop(getContext().getBean(IUserTopMapService.class).hasTop(userId, chatInfo.getChatId()));
        return chatVO;
    }

    public static List<ChatVO>  tran2ChatVOs(Long userId, List<ChatInfo> chatInfos) {
        if (isNull(chatInfos) || chatInfos.isEmpty() || isNull(userId)) {
            return Lists.newArrayList();
        }
        List<ChatVO> chatVOs = Lists.newArrayList();
        for (ChatInfo chatInfo : chatInfos) {
            chatVOs.add(tran2ChatVO(userId, chatInfo));
        }
        return chatVOs;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getChapterNo() {
        return chapterNo;
    }

    public void setChapterNo(String chapterNo) {
        this.chapterNo = chapterNo;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getChatToId() {
        return chatToId;
    }

    public void setChatToId(Long chatToId) {
        this.chatToId = chatToId;
    }

    public String getChatTo() {
        return chatTo;
    }

    public void setChatTo(String chatTo) {
        this.chatTo = chatTo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public Long getTopNum() {
        return topNum;
    }

    public void setTopNum(Long topNum) {
        this.topNum = topNum;
    }

    public boolean isHasTop() {
        return hasTop;
    }

    public void setHasTop(boolean hasTop) {
        this.hasTop = hasTop;
    }

    @Override
    public String toString() {
        return "ChatVO{" +
                "chatId=" + chatId +
                ", bookName='" + bookName + '\'' +
                ", chapterNo='" + chapterNo + '\'' +
                ", chapterName='" + chapterName + '\'' +
                ", author='" + author + '\'' +
                ", authorId=" + authorId +
                ", chatToId=" + chatToId +
                ", chatTo='" + chatTo + '\'' +
                ", content='" + content + '\'' +
                ", addTime='" + addTime + '\'' +
                ", topNum=" + topNum +
                ", hasTop=" + hasTop +
                '}';
    }
}
