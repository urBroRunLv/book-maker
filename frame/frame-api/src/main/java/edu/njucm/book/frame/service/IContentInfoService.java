package edu.njucm.book.frame.service;

import edu.njucm.book.frame.domain.ContentInfo;

import java.util.List;

/**
 * @author lvrongwang
 * @since 2020/2/29 18:19
 */
public interface IContentInfoService {

    /**
     * 存储内容信息
     *
     * @param contentInfo
     * @return
     */
    boolean saveContentInfo(ContentInfo contentInfo);

    /**
     * 更新内容信息
     *
     * @param contentInfo
     * @return
     */
    boolean updateContentInfo(ContentInfo contentInfo);

    /**
     * 删除内容信息
     *
     * @param contentId
     * @return
     */
    boolean deleteContentInfoByContentId(Long contentId);

    /**
     * 根据id获取内容信息
     *
     * @param contentId
     * @return
     */
    ContentInfo getContentInfoByContentId(Long contentId);

    /**
     * 根据章节id获取内容列表
     *
     * @param chapterId
     * @return
     */
    List<ContentInfo> listContentInfoByChapterId(Long chapterId);

    /**
     * 根据章节id获取内容id列表
     *
     * @param chapterId
     * @return
     */
    List<Long> listContentIdByChapterId(Long chapterId);
}
