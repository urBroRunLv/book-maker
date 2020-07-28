package edu.njucm.book.frame.domain;

import java.util.Date;

/**
 * 文字信息
 *
 * @author lvrongwang
 * @since 2020/2/29 19:06
 */
public class TextInfo {

    /**
     * 文字id
     */
    private Long textId;
    /**
     * 文字信息
     */
    private String textDetail;
    /**
     * 添加时间
     */
    private Date addTime;

    public TextInfo withTextDetail(String text) {
        this.textDetail = text;
        return this;
    }

    public Long getTextId() {
        return textId;
    }

    public void setTextId(Long textId) {
        this.textId = textId;
    }

    public String getTextDetail() {
        return textDetail;
    }

    public void setTextDetail(String textDetail) {
        this.textDetail = textDetail;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "TextInfo{" +
                "textId=" + textId +
                ", textDetail='" + textDetail + '\'' +
                ", addTime=" + addTime +
                '}';
    }
}
