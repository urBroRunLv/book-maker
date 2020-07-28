package edu.njucm.book.chart.service;

import edu.njucm.book.chart.entity.TabContent;

/**
 * TabContent业务逻辑层
 *
 * @author huanghao
 * @since 2020-03-30 14:29:44
 */
public interface TabContentService{
    void addTabContent(TabContent tabContent);
    TabContent findById(Long id);
}