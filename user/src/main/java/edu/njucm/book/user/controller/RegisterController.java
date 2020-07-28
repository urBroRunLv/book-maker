package edu.njucm.book.user.controller;

import edu.njucm.book.user.service.IUserService;
import edu.njucm.book.user.util.LoginUtils;
import edu.njucm.book.user.vo.RegisterVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import static edu.njucm.book.user.util.validator.RegisterValidator.isUserPhoneExist;
import static edu.njucm.book.user.util.validator.RegisterValidator.registerVOValidate;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * @author lvrongwang
 * @since 2020/3/21 15:55
 */
@Controller
@RequestMapping()
public class RegisterController extends BaseController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(Model model, HttpServletRequest request) {
        if (LoginUtils.isLoginSuccess(request)) {
            return "user/notice";
        }
        setModel(model, VO, model.asMap().get(VO), new RegisterVO());
        return "user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerSubmit(RegisterVO registerVO, Model model, HttpServletRequest request) {
        if (LoginUtils.isLoginSuccess(request)) {
            return "user/notice";
        }
        registerVO = registerVOValidate(registerVO);
        model.addAttribute(VO, registerVO);
        if (isNotBlank(registerVO.getErrorMsg())) {
            return "user/register";
        }
        if (isUserPhoneExist(registerVO)) {
            return "user/register";
        }
        Long userId = userService.userRegister(registerVO);
        if (isNull(userId) || userId <= 0 || isEmpty(registerVO.getUserPhone())) {
            return redirect("/error/500");
        }
        return "user/register_success";
    }
}
