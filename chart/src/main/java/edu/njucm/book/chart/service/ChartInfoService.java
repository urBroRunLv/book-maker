package edu.njucm.book.chart.service;

import edu.njucm.book.chart.entity.ChartVO;
import edu.njucm.book.chart.entity.ChartInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ChartInfo业务逻辑层
 *
 * @author makejava
 * @since 2020-03-25 15:31:49
 */
public interface ChartInfoService {
    List<ChartInfo> findAll(Integer page, Integer limit);

    Integer count();

    List<ChartInfo> findByPosition(ChartInfo chartInfo);

    void addChartInfo(ChartInfo chartInfo, MultipartFile file)throws Exception;
    //配合base64存
    void addChartInfo(ChartInfo chartInfo);

    void deleteByIds(List<Long> ids);

    void updateById(ChartVO chartVO,MultipartFile file)throws Exception;
}