package edu.njucm.book.chart.service.impl;

import edu.njucm.book.chart.entity.TabContent;
import edu.njucm.book.chart.dao.TabContentMapper;
import edu.njucm.book.chart.service.TabContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TabContent 业务逻辑层实现类
 *
 * @author huanghao
 * @since 2020-03-30 14:29:44
 */
@Service
public class TabContentServiceImpl implements TabContentService {
    @Autowired
    TabContentMapper tabContentMapper;

    @Override
    public void addTabContent(TabContent tabContent) {
        tabContentMapper.addTabContent(tabContent);
    }

    @Override
    public TabContent findById(Long id) {
        return tabContentMapper.findById(id);
    }
}