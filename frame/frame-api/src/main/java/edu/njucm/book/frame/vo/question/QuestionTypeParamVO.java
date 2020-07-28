package edu.njucm.book.frame.vo.question;

/**
 * 参数设置页页面信息对照vo
 *
 * @author lvrongwang
 * @since 2020/4/17 13:36
 */
public class QuestionTypeParamVO {

    public QuestionTypeParamVO(Long chapterId, int selectAmount, int blankAmount, int saqAmount) {
        this.chapterId = chapterId;
        this.selectAmount = selectAmount;
        this.blankAmount = blankAmount;
        this.saqAmount = saqAmount;
    }

    public QuestionTypeParamVO() {
    }

    /**
     * 章节id
     */
    private Long chapterId;
    /**
     * 选择题题目数
     */
    private int selectAmount;
    /**
     * 填空题题目数
     */
    private int blankAmount;
    /**
     * 简答题题目数
     */
    private int saqAmount;

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public int getSelectAmount() {
        return selectAmount;
    }

    public void setSelectAmount(int selectAmount) {
        this.selectAmount = selectAmount;
    }

    public int getBlankAmount() {
        return blankAmount;
    }

    public void setBlankAmount(int blankAmount) {
        this.blankAmount = blankAmount;
    }

    public int getSaqAmount() {
        return saqAmount;
    }

    public void setSaqAmount(int saqAmount) {
        this.saqAmount = saqAmount;
    }

    @Override
    public String toString() {
        return "QuestionTypeParamVO{" +
                "chapterId=" + chapterId +
                ", selectAmount='" + selectAmount + '\'' +
                ", blankAmount='" + blankAmount + '\'' +
                ", saqAmount='" + saqAmount + '\'' +
                '}';
    }
}
