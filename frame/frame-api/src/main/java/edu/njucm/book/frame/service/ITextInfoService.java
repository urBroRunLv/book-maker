package edu.njucm.book.frame.service;

import edu.njucm.book.frame.domain.TextInfo;

/**
 * @author lvrongwang
 * @since 2020/2/29 18:19
 */
public interface ITextInfoService {

    /**
     * 存储文字信息
     *
     * @param text
     * @return
     */
    Long saveTextInfo(String text);

    /**
     * 更新文字信息
     *
     * @param textInfo
     * @return
     */
    boolean updateTextInfo(TextInfo textInfo);

    /**
     * 删除文字信息
     *
     * @param textId
     * @return
     */
    boolean deleteTextInfoByTextId(Long textId);

    /**
     * 根据id获取文字内容
     *
     * @param textId
     * @return
     */
    String getTextDetailByTextId(Long textId);
}
