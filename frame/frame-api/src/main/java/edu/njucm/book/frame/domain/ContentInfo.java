package edu.njucm.book.frame.domain;

import edu.njucm.book.common.constant.StatusConstant;

import java.util.Date;

/**
 * 书本内容信息
 *
 * @author lvrongwang
 * @since 2020/2/29 19:25
 */
public class ContentInfo {

    /**
     * 内容id
     */
    private Long contentId;
    /**
     * 所属章节id
     */
    private Long chapterId;
    /**
     * 文字id
     */
    private Long textId;
    /**
     * 图片id
     */
    private Long picId;
    /**
     * 标题
     */
    private String contentName;
    /**
     * 内容类型：1-自然段（文字），2-图片，3-图表，4-知识点
     */
    private Short contentType;
    /**
     * 内容状态：0-正常，1-删除
     */
    private Short contentStatus;
    /**
     * 添加时间
     */
    private Date addTime;

    public ContentInfo withStatusDeleted() {
        this.contentStatus = StatusConstant.STATUS_DELETE;
        return this;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public Long getTextId() {
        return textId;
    }

    public void setTextId(Long textId) {
        this.textId = textId;
    }

    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public Short getContentType() {
        return contentType;
    }

    public void setContentType(Short contentType) {
        this.contentType = contentType;
    }

    public Short getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus(Short contentStatus) {
        this.contentStatus = contentStatus;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "ContentInfo{" +
                "contentId=" + contentId +
                ", chapterId=" + chapterId +
                ", textId=" + textId +
                ", picId=" + picId +
                ", contentName='" + contentName + '\'' +
                ", contentType=" + contentType +
                ", contentStatus=" + contentStatus +
                ", addTime=" + addTime +
                '}';
    }
}
