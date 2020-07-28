package edu.njucm.book.user.controller;

import edu.njucm.book.common.constant.IPageConstant;
import edu.njucm.book.user.domain.User;
import edu.njucm.book.user.service.IPasswordService;
import edu.njucm.book.user.service.IUserService;
import edu.njucm.book.user.util.LoginUtils;
import edu.njucm.book.user.vo.LoginVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static edu.njucm.book.user.util.LoginUtils.LOGIN_URL;
import static edu.njucm.book.user.util.LoginUtils.createToken;
import static edu.njucm.book.user.util.LoginUtils.initLoginCookie;
import static edu.njucm.book.user.util.validator.LoginValidator.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNoneBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.IOException;

/**
 * 登录
 *
 * @author lvrongwang
 * @since 2020/3/21 15:53
 */
@Controller
@RequestMapping()
public class LoginController extends BaseController implements IPageConstant {

    @Autowired
    private IPasswordService passwordService;
    @Autowired
    private IUserService userService;

    /**
     * 登录首面
     *
     * @param model
     * @param nextPage
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model, String nextPage, HttpServletRequest request) {
        if (LoginUtils.isLoginSuccess(request)) {
            if (isNoneBlank(nextPage)) {
                return redirect(nextPage);
            }
            return "user/notice";
        }
        model.addAttribute("nextPage", nextPage);
        return "user/login";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String loginPageForHome(Model model, String nextPage, HttpServletRequest request) {
        return loginPage(model, nextPage, request);
    }

    /**
     * 登录表单提交
     *
     * @param loginVO
     * @param response
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(LoginVO loginVO, Model model, HttpServletResponse response) {
        return loginInfoCheck(model, loginVO, response, loginVO.getNextPage());
    }

    /**
     * 登录信息检查
     */
    private String loginInfoCheck(Model model, LoginVO loginVO, HttpServletResponse response, String nextPage) {
        if (isNotBlank(userLockValidate(loginVO).getErrorMsg())) {
            model.addAttribute(VO, loginVO);
            return "user/login";
        }
        User user = userService.getByUserPhone(loginVO.getUserPhone());
        if (isNull(user)) {
            loginVO.withErrorMsg(USER_NOT_EXIST);
            model.addAttribute(VO, loginVO);
            return "user/login";
        }
        loginVO = loginVOValidate(loginVO);
        if (isNotBlank(loginVO.getErrorMsg())) {
            userService.doAfterLoginFail(user.getUserId());
            model.addAttribute(VO, loginVO);
            return "user/login";
        }
        if (!passwordService.checkUserLogonPassword(loginVO.getUserPassword(), loginVO.getUserPhone())) {
            loginVO.withErrorMsg(USER_PASSWORD_ERROR);
            userService.doAfterLoginFail(user.getUserId());
            model.addAttribute(VO, loginVO);
            return "user/login";
        }
        initLoginCookie(response, createToken(loginVO.getUserPhone()));
        String loginNextPage = isNotBlank(nextPage) ? nextPage : USER_CENTER_PAGE;
        return redirect(loginNextPage);
    }

    /**
     * 登出
     *
     * @param response
     * @param nextPage
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logoutHandle(HttpServletResponse response, String nextPage) throws IOException {
        LoginUtils.logout(response);
        String logoutNextPage = isNotBlank(nextPage) ? nextPage : LOGIN_URL;
        response.sendRedirect(logoutNextPage);
    }
}
