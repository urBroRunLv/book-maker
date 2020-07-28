package edu.njucm.book.chart.dao;

import edu.njucm.book.chart.entity.ChartVO;
import edu.njucm.book.chart.entity.ChartInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (ChartInfo)数据库访问层
 *
 * @author makejava
 * @since 2020-03-25 15:31:49
 */
public interface ChartInfoMapper{
    List<ChartInfo> findAll(@Param("start") Integer start, @Param("rows")Integer rows);

    Integer count();

    List<ChartInfo> findByPosition(ChartInfo chartInfo);

    void addChartInfo(ChartInfo chartInfo);

    void deleteByIds(List<Long> ids);

    void updateById(ChartVO chartDTO);
}