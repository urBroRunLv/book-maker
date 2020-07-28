package edu.njucm.book.chart.service.impl;

import edu.njucm.book.chart.entity.ChartVO;
import edu.njucm.book.chart.entity.ChartInfo;
import edu.njucm.book.chart.entity.PicContent;
import edu.njucm.book.chart.entity.TabContent;
import edu.njucm.book.chart.dao.ChartInfoMapper;
import edu.njucm.book.chart.service.ChartInfoService;
import edu.njucm.book.chart.service.PicContentService;
import edu.njucm.book.chart.service.TabContentService;
import edu.njucm.book.chart.utils.ExcelUtil;
import edu.njucm.book.chart.utils.JudgeFileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ChartInfo 业务逻辑层实现类
 *
 * @author makejava
 * @since 2020-03-25 15:31:49
 */
@Service
public class ChartInfoServiceImpl implements ChartInfoService {
    @Autowired
    ChartInfoMapper chartInfoMapper;

    @Autowired
    PicContentService picContentService;

    @Autowired
    TabContentService tabContentService;

    public List<ChartInfo> findAll(Integer page, Integer limit){
        return chartInfoMapper.findAll((page-1)*limit,limit);
    }

    public Integer count(){
        return chartInfoMapper.count();
    }

    public List<ChartInfo> findByPosition(ChartInfo chartInfo){
        return chartInfoMapper.findByPosition(chartInfo);
    }

    public void addChartInfo(ChartInfo chartInfo, MultipartFile file) throws Exception{

        //设置文件名
        String fileName=file.getOriginalFilename();
        chartInfo.setChartName(fileName);

        //判断 上传文件类型 并设置data_type
        chartInfo.setDataType(JudgeFileType.judge(file.getContentType()));

        //处理这种情况：前端知识点、习题、段落，为null，会传入undefined（是否还有其他处理方式）
        if("undefined".equals(chartInfo.getExercise()))
            chartInfo.setExercise(null);
        if("undefined".equals(chartInfo.getParagraph()))
            chartInfo.setParagraph(null);
        if("undefined".equals(chartInfo.getKnowledge()))
            chartInfo.setKnowledge(null);
        //插入后，获得id
        chartInfoMapper.addChartInfo(chartInfo);

        //根据不同的data_type调用不同的service
        //data_type=1调用picContentService,插入图片的二进制
        if(chartInfo.getDataType()==1){
            byte[] content=file.getBytes();
            picContentService.addPicContent(new PicContent(chartInfo.getChartId(),content));
        }
        //data_type=2，调用tabContentService，插入excel的html形式
        if(chartInfo.getDataType()==2){
            //TODO：异常处理
            String content= ExcelUtil.excelToHtml(file.getInputStream());
            tabContentService.addTabContent(new TabContent(chartInfo.getChartId(),content));
        }
    }

    @Override
    public void addChartInfo(ChartInfo chartInfo) {
        chartInfoMapper.addChartInfo(chartInfo);
    }

    public void deleteByIds(List<Long> ids){
        chartInfoMapper.deleteByIds(ids);
    }
    //更新使用联表更新，所以使用VO存一起
    public void updateById(ChartVO chartVO, MultipartFile file)throws Exception{
        String fileName=file.getOriginalFilename();
        Object content=null;
        if(chartVO.getDataType()==1){
            //byte[]
            content=file.getBytes();
        }
        //data_type=2，调用tabContentService，插入excel的html形式
        if(chartVO.getDataType()==2){
            //TODO：异常处理
            //String
            content= ExcelUtil.excelToHtml(file.getInputStream());
        }
        chartVO.setContent(content);
        chartVO.setChartName(fileName);
        chartInfoMapper.updateById(chartVO);
    }
}