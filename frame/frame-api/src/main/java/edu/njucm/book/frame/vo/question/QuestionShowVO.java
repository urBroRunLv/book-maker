package edu.njucm.book.frame.vo.question;

import com.google.common.collect.Lists;

import edu.njucm.book.chart.entity.PicContent;
import edu.njucm.book.chart.service.PicContentService;
import edu.njucm.book.common.util.DateUtils;
import edu.njucm.book.frame.domain.ContentInfo;
import edu.njucm.book.frame.domain.QuestionInfo;
import edu.njucm.book.frame.service.IContentInfoService;
import edu.njucm.book.frame.service.ITextInfoService;

import java.util.List;

import static edu.njucm.book.common.util.ContextUtils.getContext;
import static edu.njucm.book.common.util.PicUtils.getPicBase64Str;
import static edu.njucm.book.frame.constant.QuestionTypeEnum.tranId2Name;
import static java.util.Objects.nonNull;

/**
 * @author lvrongwang
 * @since 2020/5/16 16:57
 */
public class QuestionShowVO {

    public QuestionShowVO(QuestionInfo info) {
        this.questionId = info.getQuestionId();
        this.questionType = tranId2Name(info.getQuestionType());
        ContentInfo contentInfo =
                getContext().getBean(IContentInfoService.class).getContentInfoByContentId(info.getContentId());
        if (nonNull(contentInfo)) {
            this.contentName = contentInfo.getContentName();
        }
        this.contentId = info.getContentId();
        ITextInfoService textInfoService = getContext().getBean(ITextInfoService.class);
        if (nonNull(textInfoService)) {
            this.question = textInfoService.getTextDetailByTextId(info.getQuestionTextId());
            this.answer = textInfoService.getTextDetailByTextId(info.getAnswerTextId());
        }
        PicContent pic = getContext().getBean(PicContentService.class).findById(info.getPicId());
        if (nonNull(pic)) {
            this.base64Pic = getPicBase64Str(pic.getContent());
        }
        this.addTime = DateUtils.formatTime(info.getAddTime());
    }

    public static List<QuestionShowVO> tran2QuestionShowVOList(List<QuestionInfo> questionInfos) {
        List<QuestionShowVO> vos = Lists.newArrayList();
        if (nonNull(questionInfos) && !questionInfos.isEmpty()) {
            for (QuestionInfo questionInfo : questionInfos) {
                vos.add(new QuestionShowVO(questionInfo));
            }
        }
        return vos;
    }

    /**
     * id
     */
    private Long questionId;
    /**
     * 习题类型
     */
    private String questionType;
    /**
     * 关联知识点
     */
    private String contentName;
    private Long contentId;
    /**
     * 题干
     */
    private String question;
    /**
     * 答案
     */
    private String answer;
    /**
     * 图片
     */
    private String base64Pic;
    /**
     * 添加时间
     */
    private String addTime;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
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

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getBase64Pic() {
        return base64Pic;
    }

    public void setBase64Pic(String base64Pic) {
        this.base64Pic = base64Pic;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "QuestionShowVO{" + "questionId=" + questionId + ", questionType='" + questionType + '\''
                + ", contentName='" + contentName + '\'' + ", contentId=" + contentId + ", question='" + question + '\''
                + ", answer='" + answer + '\'' + ", base64Pic='" + base64Pic + '\'' + ", addTime='" + addTime + '\''
                + '}';
    }
}
