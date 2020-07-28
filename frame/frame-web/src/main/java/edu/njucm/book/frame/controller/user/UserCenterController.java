package edu.njucm.book.frame.controller.user;

import edu.njucm.book.common.constant.IPageConstant;
import edu.njucm.book.frame.domain.ajax.BaseAjaxResult;
import edu.njucm.book.user.controller.BaseController;
import edu.njucm.book.user.domain.User;
import edu.njucm.book.user.service.IUserService;
import edu.njucm.book.user.util.LoginUtils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static edu.njucm.book.frame.domain.ajax.BaseAjaxResult.failResult;
import static edu.njucm.book.frame.domain.ajax.BaseAjaxResult.successResult;
import static edu.njucm.book.frame.vo.user.UserVO.tran2UserVO;

/**
 * 账户中心
 *
 * @author lvrongwang
 * @since 2020/4/16 16:05
 */
@Controller
@RequestMapping("user")
public class UserCenterController extends BaseController implements IPageConstant {

    @Autowired
    private IUserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String userCenterHomePage(Model model, HttpServletRequest request) {
        User user = userService.getByUserPhone(LoginUtils.getLoginUserToken(request).getUserPhone());
        model.addAttribute("vo", tran2UserVO(user));
        return "user/home";
    }
}
