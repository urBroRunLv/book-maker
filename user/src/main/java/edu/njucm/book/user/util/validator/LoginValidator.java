package edu.njucm.book.user.util.validator;

import edu.njucm.book.common.util.ContextUtils;
import edu.njucm.book.common.util.DateUtils;
import edu.njucm.book.user.domain.User;
import edu.njucm.book.user.service.impl.UserServiceImpl;
import edu.njucm.book.user.vo.LoginVO;

import java.util.Objects;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author lvrongwang
 * @since 2020/4/13 20:40
 */
public class LoginValidator {

    private static final int USER_PHONE_LENGTH_LIMIT = 11;

    // private static final int LOCK_TIME = (30 * 60 * 1000);
    private static final int LOCK_TIME = (1);

    private static final Pattern USER_PASSWORD_TYPE = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]{8,20}$");

    private static final String LOGIN_INFO_NULL = "登录表单为空，请重填";
    private static final String USER_PHONE_NULL = "手机号为空，请重填";
    private static final String USER_PHONE_TOO_LONG = "手机号过长，请重填";
    private static final String USER_PHONE_NOT_EXIST = "手机号不存在，请换个手机号或<a href='http://user.book.com/register'>注册</a>";
    private static final String USER_PASSWORD_NULL = "密码为空，请重填";
    private static final String USER_PASSWORD_TYPE_ERROR = "密码格式错误，要求：1、密码必须由字母和数字组成，区分大小写；2、密码长度为8-18位";
    public static final String USER_PASSWORD_ERROR = "密码错误，请重新填写";
    public static final String USER_NOT_EXIST = "用户不存在";
    private static final String USER_LOCKED = "登录失败次数过多，用户被锁定，请30分钟后再试";

    /**
     * bean
     */
    private static UserServiceImpl userService;

    private static UserServiceImpl userService() {
        if (Objects.isNull(userService)) {
            userService = ContextUtils.getContext().getBean(UserServiceImpl.class);
        }
        return userService;
    }

    /**
     * 检查登录信息是否正确
     */
    public static LoginVO loginVOValidate(LoginVO loginVO) {
        if (isNull(loginVO)) {
            return new LoginVO().withErrorMsg(LOGIN_INFO_NULL);
        }
        userPhoneValidate(loginVO);
        userPasswordValidate(loginVO);
        return loginVO;
    }

    public static LoginVO userLockValidate(LoginVO loginVO) {
        User user = userService().getByUserPhone(loginVO.getUserPhone());
        if (nonNull(user)) {
            if (userService().isLockUser(user.getUserId())) {
                if ((System.currentTimeMillis() - userService().getLockTime(user.getUserId()).getTime()) < LOCK_TIME) {
                    loginVO.withErrorMsg(USER_LOCKED);
                }
                else {
                    userService().unlockUser(user.getUserId());
                }
            }
        }
        return loginVO;
    }

    private static void userPhoneValidate(LoginVO loginVO) {
        String userPhone = loginVO.getUserPhone();
        if (isBlank(userPhone)) {
            loginVO.withErrorMsg(USER_PHONE_NULL);
        }
        else if (USER_PHONE_LENGTH_LIMIT < userPhone.length()) {
            loginVO.withErrorMsg(USER_PHONE_TOO_LONG);
        }
    }

    private static void userPasswordValidate(LoginVO loginVO) {
        String userPassword = loginVO.getUserPassword();
        if (isBlank(userPassword)) {
            loginVO.withErrorMsg(USER_PASSWORD_NULL);
        }
        else if (!USER_PASSWORD_TYPE.matcher(userPassword).matches()) {
            loginVO.withErrorMsg(USER_PASSWORD_TYPE_ERROR);
        }
    }

    public static boolean isUserPhoneExist(LoginVO loginVO) {
        if (isNull(loginVO)) {
            loginVO = new LoginVO().withErrorMsg(LOGIN_INFO_NULL);
        }
        if (isNull(userService().getByUserPhone(loginVO.getUserPhone()))) {
            loginVO.withErrorMsg(USER_PHONE_NOT_EXIST);
            return true;
        }
        return false;
    }
}
