package edu.njucm.book.chart.service;

import edu.njucm.book.chart.entity.PicContent;

/**
 * PicContent业务逻辑层
 *
 * @author makejava
 * @since 2020-03-25 15:54:28
 */
public interface PicContentService{
    void addPicContent(PicContent picContent);
    PicContent findById(Long id);
    Long insertPicture(String base64);
}