package edu.njucm.book.frame.vo.question;

import static java.util.Objects.isNull;

import edu.njucm.book.frame.constant.QuestionTypeEnum;

/**
 * @author lvrongwang
 * @since 2020/5/12 15:02
 */
public class QuestionVO {

    private Long questionId;
    private Short type;
    private Long contentId;
    private String question;
    private String answer;
    private String base64Pic;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getBase64Pic() {
        return base64Pic;
    }

    public void setBase64Pic(String base64Pic) {
        this.base64Pic = base64Pic;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isSelect() {
        if (isNull(this.type)){
            return false;
        }
        return QuestionTypeEnum.SELECT.getType().equals(this.type);
    }

    public boolean isBlank() {
        if (isNull(this.type)){
            return false;
        }
        return QuestionTypeEnum.BLANK.getType().equals(this.type);
    }

    public boolean isSaq() {
        if (isNull(this.type)){
            return false;
        }
        return QuestionTypeEnum.SAQ.getType().equals(this.type);
    }
}
