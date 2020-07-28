package edu.njucm.book.chart.dao;

import edu.njucm.book.chart.entity.PicContent;

/**
 * (PicContent)数据库访问层
 *
 * @author makejava
 * @since 2020-03-25 15:54:28
 */
public interface PicContentMapper{
    void addPicContent(PicContent picContent);
    PicContent findById(Long id);
    Integer insertPicture(byte[] content);
}