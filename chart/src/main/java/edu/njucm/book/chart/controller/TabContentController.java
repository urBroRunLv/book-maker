package edu.njucm.book.chart.controller;

import edu.njucm.book.chart.entity.TabContent;
import edu.njucm.book.chart.service.TabContentService;
import edu.njucm.book.chart.utils.GetIdFromJson;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (TabContent)控制层
 *
 * @author huanghao
 * @since 2020-03-30 14:29:44
 */
@RestController
@RequestMapping("/tab")
@CrossOrigin(origins = "*",maxAge = 3600)
public class TabContentController {

    @Autowired
    private TabContentService tabContentService;

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public TabContent findById(@RequestBody String json){
        return tabContentService.findById(GetIdFromJson.get(json));
    }
}