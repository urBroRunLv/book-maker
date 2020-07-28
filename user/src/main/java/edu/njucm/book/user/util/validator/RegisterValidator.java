package edu.njucm.book.user.util.validator;

import edu.njucm.book.common.util.ContextUtils;
import edu.njucm.book.user.service.impl.UserServiceImpl;
import edu.njucm.book.user.vo.RegisterVO;
import org.springframework.ui.Model;

import java.util.Objects;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author lvrongwang
 * @since 2020/4/11 23:10
 */
public class RegisterValidator {

    private static final int USER_NAME_LENGTH_LIMIT = 20;

    private static final Pattern USER_PHONE_TYPE =
            Pattern.compile("^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$");
    private static final Pattern USER_PASSWORD_TYPE = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]{8,20}$");

    private static final String REGISTER_INFO_NULL = "注册表单为空，请重填";
    private static final String USER_NAME_NULL = "用户名为空，请重填";
    private static final String USER_NAME_TOO_LONG = "用户名过长，请重填";
    private static final String USER_PASSWORD_NULL = "密码为空，请重填";
    private static final String USER_PASSWORD_TYPE_ERROR = "密码格式错误，要求：1、密码必须由字母和数字组成，区分大小写；2、密码长度为8-20位";
    private static final String USER_PASSWORD_NOT_SAME = "两次输入密码不一致";
    private static final String USER_PHONE_NULL = "手机号为空，请重填";
    private static final String USER_PHONE_TYPE_ERROR = "手机号格式错误，请重填";
    private static final String USER_PHONE_EXIST = "手机号已存在，请换手机号或<a href='http://user.book.com/login'>登录</a>";

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
     * 检查注册信息是否正确
     */
    public static RegisterVO registerVOValidate(RegisterVO registerVO) {
        if (isNull(registerVO)) {
            return new RegisterVO().withErrorMsg(REGISTER_INFO_NULL);
        }
        userNameValidate(registerVO);
        userPhoneValidate(registerVO);
        userPasswordValidate(registerVO);
        return registerVO;
    }

    private static void userNameValidate(RegisterVO registerVO) {
        String userName = registerVO.getUserName();
        if (isBlank(userName)) {
            registerVO.withErrorMsg(USER_NAME_NULL);
        }
        else if (USER_NAME_LENGTH_LIMIT < userName.length()) {
            registerVO.withErrorMsg(USER_NAME_TOO_LONG);
        }
    }

    private static void userPhoneValidate(RegisterVO registerVO) {
        String userPhone = registerVO.getUserPhone();
        if (isBlank(userPhone)) {
            registerVO.withErrorMsg(USER_PHONE_NULL);
        }
        else if (!USER_PHONE_TYPE.matcher(userPhone).matches()) {
            registerVO.withErrorMsg(USER_PHONE_TYPE_ERROR);
        }
    }

    private static void userPasswordValidate(RegisterVO registerVO) {
        String userPassword = registerVO.getUserPassword();
        String verifyPassword = registerVO.getVerifyPassword();
        if (isBlank(userPassword) || isBlank(verifyPassword)) {
            registerVO.withErrorMsg(USER_PASSWORD_NULL);
        }
        else if (!USER_PASSWORD_TYPE.matcher(userPassword).matches()
                || !USER_PASSWORD_TYPE.matcher(verifyPassword).matches()) {
            registerVO.withErrorMsg(USER_PASSWORD_TYPE_ERROR);
        }
        else if (!userPassword.equals(verifyPassword)) {
            registerVO.withErrorMsg(USER_PASSWORD_NOT_SAME);
        }
    }

    public static boolean isUserPhoneExist(RegisterVO registerVO) {
        if (isNull(registerVO)) {
            registerVO = new RegisterVO().withErrorMsg(REGISTER_INFO_NULL);
        }
        if (nonNull(userService().getByUserPhone(registerVO.getUserPhone()))) {
            registerVO.withErrorMsg(USER_PHONE_EXIST);
            return true;
        }
        return false;
    }
}
