package edu.njucm.book.chart.controller;

import edu.njucm.book.chart.entity.ChartVO;
import edu.njucm.book.chart.entity.ChartInfo;
import edu.njucm.book.chart.service.ChartInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (ChartInfo)控制层
 *
 * @author makejava
 * @since 2020-03-25 15:31:49
 */
@RestController
@RequestMapping("/chart")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ChartInfoController {
    @Autowired
    private ChartInfoService chartInfoService;

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public Map<String,Object> findAll(Integer page, Integer limit){
        //分页查询
        List<ChartInfo> pictures=chartInfoService.findAll(page,limit);
        Map<String,Object> map=new HashMap<>();
        map.put("data",pictures);
        map.put("code","0");
        map.put("msg", "");
        //总条数
        map.put("count",chartInfoService.count());
        return map;

    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public  Map<String,Object> upload (ChartInfo chartInfo,MultipartFile file){

        Map<String,Object> map=new HashMap<>();
        try{
            chartInfoService.addChartInfo(chartInfo,file);
            map.put("code","0");
        }catch (Exception e){
            map.put("code","1");
        }
        map.put("id","");
        map.put("msg","");
        return map;

    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Map<String,Object> updateById(ChartVO chartVO, MultipartFile file){
        Map<String,Object> map=new HashMap<>();
        try{
            chartInfoService.updateById(chartVO,file);
            map.put("code","0");
        }
        catch (Exception e){
            e.printStackTrace();
            map.put("code","1");
        }
        map.put("data","");
        map.put("msg", "");
        return map;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public void deleteById (@RequestBody String json) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> map = mapper.readValue(json, HashMap.class);
        List<Long> ids=(List<Long>)map.get("ids");
        chartInfoService.deleteByIds(ids);
    }

    @RequestMapping(value = "/find",method = RequestMethod.POST)
    public void findByPosition(ChartInfo picture){
        System.out.println(chartInfoService.findByPosition(picture));
    }
}