package edu.njucm.book.frame.domain;

import java.util.Date;

/**
 * 参考文献信息
 *
 * @author lvrongwang
 * @since 2020/2/29 20:18
 */
public class ReferenceInfo {

    /**
     * 文献id
     */
    private Long referenceId;
    /**
     * 所属书本id
     */
    private Long bookId;
    /**
     * 文献作者
     */
    private String authorName;
    /**
     * 文献名
     */
    private String referenceName;
    /**
     * 文献类型： 专著[M]，论文集[C]，报纸文章[N]，期刊文章[J]，学位论文[D]，报告[R]，标准[S]，专利[P]，论文集中的析出文献[A]
     */
    private String referenceType;
    /**
     * 出版地
     */
    private String publishPlace;
    /**
     * 出版人
     */
    private String publishName;
    /**
     * 出版年
     */
    private String publishYear;
    /**
     * 起止页码
     */
    private String startEndPage;
    /**
     * 添加时间
     */
    private Date addTime;

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public String getPublishPlace() {
        return publishPlace;
    }

    public void setPublishPlace(String publishPlace) {
        this.publishPlace = publishPlace;
    }

    public String getPublishName() {
        return publishName;
    }

    public void setPublishName(String publishName) {
        this.publishName = publishName;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public String getStartEndPage() {
        return startEndPage;
    }

    public void setStartEndPage(String startEndPage) {
        this.startEndPage = startEndPage;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "ReferenceInfo{" + "referenceId=" + referenceId + ", bookId=" + bookId + ", authorName='" + authorName
                + '\'' + ", referenceName='" + referenceName + '\'' + ", referenceType='" + referenceType + '\'' + '\''
                + ", publishPlace='" + publishPlace + '\'' + ", publishName='" + publishName + '\'' + ", publishYear='"
                + publishYear + '\'' + ", startEndPage='" + startEndPage + '\'' + ", addTime=" + addTime + '}';
    }
}
