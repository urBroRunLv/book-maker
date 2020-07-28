package edu.njucm.book.frame.controller.user.manage;

import static edu.njucm.book.frame.domain.ajax.BaseAjaxResult.failResult;
import static edu.njucm.book.frame.domain.ajax.BaseAjaxResult.successResult;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import edu.njucm.book.common.constant.Constants;
import edu.njucm.book.common.constant.IPageConstant;
import edu.njucm.book.frame.domain.ajax.BaseAjaxResult;
import edu.njucm.book.user.constant.UserTypeEnum;
import edu.njucm.book.user.controller.BaseController;
import edu.njucm.book.user.domain.User;
import edu.njucm.book.user.service.IUserService;
import edu.njucm.book.user.util.LoginUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.njucm.book.user.vo.RegisterVO;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户后台管理
 *
 * @author lvrongwang
 * @since 2020/4/17 9:58
 */
@Controller
@RequestMapping("manage")
public class UserManagerController extends BaseController implements IPageConstant {

    @Autowired
    private IUserService userService;

    /**
     * 管理用户
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String manageUser(Model model, HttpServletRequest request) {
        User user = userService.getByUserPhone(LoginUtils.getLoginUserToken(request).getUserPhone());
        if (nonNull(user)) {
            if (user.getUserType().equals(UserTypeEnum.MANAGER.getType())) {
                if (BooleanUtils.isFalse(user.isManager())) {
                    return redirect(ERROR_403);
                }
                model.addAttribute("childUsers", userService.listChildUserByUserId(user.getUserId()));
                return "user/manage_user";
            }
        }
        return redirect(ERROR_403);
    }

    /**
     * 修改用户名
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/change-name", method = RequestMethod.POST)
    public BaseAjaxResult changeName(String name, Long id, HttpServletRequest request) {
        if (nonNull(name)) {
            User user = userService.getByUserPhone(LoginUtils.getLoginUserToken(request).getUserPhone());
            if (nonNull(user)) {
                Long userId = nonNull(id) ? id : user.getUserId();
                if (userService.update(user.withName(name))) {
                    return successResult();
                }
            }
        }
        return failResult();
    }

    /**
     * 新增绑定
     *
     * @param userIds
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/tying", method = RequestMethod.POST)
    public String tyingEditor(String userIds, HttpServletRequest request) {
        if (isNotBlank(userIds)) {
            User nowUser = userService.getByUserPhone(LoginUtils.getLoginUserToken(request).getUserPhone());
            Long nowUserId = nonNull(nowUser) ? nowUser.getUserId() : 0L;
            String[] userIdList = userIds.split(Constants.SEP_COMMA);
            for (String userId : userIdList) {
                User user = userService.getByUserId(Long.valueOf(userId));
                if (nonNull(user)) {
                    userService.update(user.withParent(nowUserId));
                }
            }
            return redirect("/manage/user");
        }
        return redirect(ERROR_403);
    }

    /**
     * 异步取消绑定
     *
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/untying", method = RequestMethod.POST)
    public BaseAjaxResult untyingEditor(Long userId) {
        if (nonNull(userId)) {
            User child = userService.getByUserId(userId);
            if (nonNull(child)) {
                if (userService.update(child.withNoParent())) {
                    return successResult();
                }
            }
        }
        return failResult();
    }

    /**
     * 批量导入编委
     */
    @RequestMapping(value = "/editor-register", method = RequestMethod.GET)
    public String editorRegister() {
        return "user/manage_editor_register";
    }

    /**
     * 批量导入编委
     * 
     * @param phones
     * @param response
     */
    @Transactional
    @RequestMapping(value = "/editor-register", method = RequestMethod.POST)
    public String editorRegisterSubmit(String phones, HttpServletResponse response) throws IOException {
        if (isNull(phones)) {
            return redirect("/manage/editor-register");
        }
        String[] phoneArray = phones.split(Constants.SEP_COMMA);
        try {
            for (String phone : phoneArray) {
                if (isNull(userService.userRegister(new RegisterVO("默认用户名", phone, phone, phone, null)))) {
                    PrintWriter out = response.getWriter();
                    out.print("<script>alert('add failed')</script>");
                    out.flush();
                    out.close();
                    return redirect("/manage/editor-register");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "user/success";
    }

    /**
     * 任命主编
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String appointManager(Model model) {
        model.addAttribute("managers", userService.listUserByUserType(UserTypeEnum.MANAGER.getType()));
        return "user/manage_manager";
    }

    /**
     * 任命主编
     *
     * @param userIds
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.POST)
    public String appointManagerSubmit(String userIds) {
        String[] userIdList = userIds.split(Constants.SEP_COMMA);
        for (String userId : userIdList) {
            User user = userService.getByUserId(Long.valueOf(userId));
            if (nonNull(user)) {
                userService.update(user.withUserType(UserTypeEnum.MANAGER.getType()));
            }
        }
        return redirect("/manage/manager");
    }
}
