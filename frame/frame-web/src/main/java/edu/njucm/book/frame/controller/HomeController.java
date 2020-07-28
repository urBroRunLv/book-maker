package edu.njucm.book.frame.controller;

import edu.njucm.book.common.constant.IPageConstant;
import edu.njucm.book.user.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 系统主页
 *
 * @author lvrongwang
 * @create 2019/12/27 16:49
 */
@Controller
@RequestMapping
public class HomeController extends BaseController implements IPageConstant {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * 主页
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index/index";
    }
}
