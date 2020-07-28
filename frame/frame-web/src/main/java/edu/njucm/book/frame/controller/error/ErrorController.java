package edu.njucm.book.frame.controller.error;

import javax.servlet.http.HttpServletResponse;

import edu.njucm.book.common.constant.IPageConstant;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lvrongwang
 * @since 2020/1/4 15:26
 */
@Controller
@RequestMapping(value = "/error")
public class ErrorController implements IPageConstant {

    /**
     * 403.vm 页面
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "/403",method = RequestMethod.GET)
    public String page403(HttpServletResponse response) {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        return "error/403";
    }

    /**
     * 404 页面
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "/404",method = RequestMethod.GET)
    public String page404(HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return "error/404";
    }

    /**
     * 500页面
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "/500",method = RequestMethod.GET)
    public String page500(HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return "error/500";
    }
}
