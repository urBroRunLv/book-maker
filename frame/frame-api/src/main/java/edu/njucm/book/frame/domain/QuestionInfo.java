package edu.njucm.book.frame.domain;

import edu.njucm.book.common.constant.StatusConstant;

import java.util.Date;

/**
 * 习题信息
 *
 * @author lvrongwang
 * @since 2020/2/29 20:22
 */
public class QuestionInfo {

    /**
     * 习题id
     */
    private Long questionId;
    /**
     * 关联知识点id
     */
    private Long contentId;
    /**
     * 题型：1-选择题，2-填空题，3-简答题
     */
    private Short questionType;
    /**
     * 题干文字id
     */
    private Long questionTextId;
    /**
     * 答案文字id
     */
    private Long answerTextId;
    /**
     * 图片id
     */
    private Long picId;
    /**
     * 习题状态：0-正常，1-删除
     */
    private Short questionStatus;
    /**
     * 添加时间
     */
    private Date addTime;

    public QuestionInfo withStatusDeleted() {
        this.questionStatus = StatusConstant.STATUS_DELETE;
        return this;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Short getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Short questionType) {
        this.questionType = questionType;
    }

    public Long getQuestionTextId() {
        return questionTextId;
    }

    public void setQuestionTextId(Long questionTextId) {
        this.questionTextId = questionTextId;
    }

    public Long getAnswerTextId() {
        return answerTextId;
    }

    public void setAnswerTextId(Long answerTextId) {
        this.answerTextId = answerTextId;
    }

    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }

    public Short getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(Short questionStatus) {
        this.questionStatus = questionStatus;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "QuestionInfo{" +
                "questionId=" + questionId +
                ", contentId=" + contentId +
                ", questionType=" + questionType +
                ", questionTextId=" + questionTextId +
                ", answerTextId=" + answerTextId +
                ", picId=" + picId +
                ", questionStatus=" + questionStatus +
                ", addTime=" + addTime +
                '}';
    }
}
