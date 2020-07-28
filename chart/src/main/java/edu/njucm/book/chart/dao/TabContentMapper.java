package edu.njucm.book.chart.dao;

import edu.njucm.book.chart.entity.TabContent;

/**
 * (TabContent)数据库访问层
 *
 * @author huanghao
 * @since 2020-03-30 14:29:44
 */
public interface TabContentMapper{
    void addTabContent(TabContent tabContent);
    TabContent findById(Long id);
}