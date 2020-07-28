package edu.njucm.book.frame.vo.book;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import com.google.common.collect.Lists;
import edu.njucm.book.common.util.ContextUtils;
import edu.njucm.book.frame.domain.ContentInfo;
import edu.njucm.book.frame.service.ITextInfoService;

import java.util.Date;
import java.util.List;

/**
 * @author lvrongwang
 * @since 2020/4/23 16:46
 */
public class ContentVO extends BaseVO {

    /**
     * 内容id
     */
    private Long contentId;
    /**
     * 所属章节id
     */
    private Long chapterId;
    /**
     * 文字内容
     */
    private String text;
    /**
     * 图片base64
     */
    private Long picId;
    /**
     * base64图片
     */
    private String base64Pic;
    /**
     * 标题
     */
    private String contentName;
    /**
     * 内容类型：1-自然段（文字），2-图片，3-知识点
     */
    private Short contentType;
    /**
     * 状态
     */
    private Short contentStatus;
    /**
     * 添加时间
     */
    private Date addTime;

    public static ContentVO tran2ContentVO(ContentInfo info) {
        ContentVO vo = new ContentVO();
        if (nonNull(info)) {
            vo.setContentId(info.getContentId());
            vo.setChapterId(info.getChapterId());
            vo.setContentName(info.getContentName());
            vo.setContentType(info.getContentType());
            vo.setContentStatus(info.getContentStatus());
            vo.setPicId(info.getPicId());
            vo.setText(
                    ContextUtils.getContext().getBean(ITextInfoService.class).getTextDetailByTextId(info.getTextId()));
            vo.setAddTime(info.getAddTime());
        }
        return vo;
    }

    public static List<ContentVO> tran2ContentVOList(List<ContentInfo> contentInfos) {
        if (isNull(contentInfos) || contentInfos.isEmpty()) {
            return Lists.newArrayList();
        }
        List<ContentVO> contentVOs = Lists.newArrayList();
        for (ContentInfo contentInfo : contentInfos) {
            contentVOs.add(tran2ContentVO(contentInfo));
        }
        return contentVOs;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }

    public String getBase64Pic() {
        return base64Pic;
    }

    public void setBase64Pic(String base64Pic) {
        this.base64Pic = base64Pic;
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
        return "ContentVO{" + "contentId=" + contentId + ", chapterId=" + chapterId + ", text='" + text + '\''
                + ", picId=" + picId + ", base64Pic='" + base64Pic + '\'' + ", contentName='" + contentName + '\''
                + ", contentType=" + contentType + ", contentStatus=" + contentStatus + ", addTime=" + addTime + '}';
    }
}
