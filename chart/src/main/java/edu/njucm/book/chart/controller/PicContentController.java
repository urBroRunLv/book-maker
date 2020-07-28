package edu.njucm.book.chart.controller;


import edu.njucm.book.chart.entity.PicContent;
import edu.njucm.book.chart.service.ChartInfoService;
import edu.njucm.book.chart.service.PicContentService;
import edu.njucm.book.chart.utils.GetIdFromJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pic")
@CrossOrigin(origins = "*",maxAge = 3600)
public class PicContentController {

    @Autowired
    private PicContentService picContentService;

    @Autowired
    ChartInfoService chartInfoService;

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public PicContent findById(@RequestBody String json){
        return picContentService.findById(GetIdFromJson.get(json));
    }

}
