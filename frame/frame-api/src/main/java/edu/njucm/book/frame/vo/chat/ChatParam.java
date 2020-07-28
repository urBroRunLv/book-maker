package edu.njucm.book.frame.vo.chat;

/**
 * @author lvrongwang
 * @since 2020/5/12 16:46
 */
public class ChatParam {

    private Long chapterId;
    private String text;
    private Long linkChatId;

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getLinkChatId() {
        return linkChatId;
    }

    public void setLinkChatId(Long linkChatId) {
        this.linkChatId = linkChatId;
    }

    @Override
    public String toString() {
        return "ChatParam{" +
                "chapterId=" + chapterId +
                ", text='" + text + '\'' +
                ", linkChatId=" + linkChatId +
                '}';
    }
}
