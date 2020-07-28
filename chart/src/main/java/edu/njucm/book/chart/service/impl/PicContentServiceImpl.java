package edu.njucm.book.chart.service.impl;

import edu.njucm.book.chart.entity.ChartInfo;
import edu.njucm.book.chart.entity.PicContent;
import edu.njucm.book.chart.dao.PicContentMapper;
import edu.njucm.book.chart.service.ChartInfoService;
import edu.njucm.book.chart.service.PicContentService;
import edu.njucm.book.common.util.PicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

/**
 * PicContent 业务逻辑层实现类
 *
 * @author makejava
 * @since 2020-03-25 15:54:28
 */
@Service
public class PicContentServiceImpl implements PicContentService {

    @Autowired
    PicContentMapper picContentMapper;

    @Autowired
    ChartInfoService chartInfoService;

    @Override
    public void addPicContent(PicContent picContent) {
        picContentMapper.addPicContent(picContent);
    }

    @Override
    public PicContent findById(Long id) {
        return picContentMapper.findById(id);
    }

    @Override
    public Long insertPicture(String base64) {
        if (isNull(base64)) {
            return null;
        }
        byte[] data = PicUtils.getPicByteByBase64(base64);
        //造一条chartInfo  具体数据无意义
        ChartInfo chartInfo = new ChartInfo();
        chartInfo.setChartName("book");
        chartInfoService.addChartInfo(chartInfo);
        addPicContent(new PicContent(chartInfo.getChartId(), data));
        return chartInfo.getChartId();
    }
}