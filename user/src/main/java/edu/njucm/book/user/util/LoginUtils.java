package edu.njucm.book.user.util;

import com.google.common.collect.Lists;
import edu.njucm.book.common.util.ContextUtils;
import edu.njucm.book.common.util.CookieUtils;
import edu.njucm.book.common.util.DateUtils;
import edu.njucm.book.common.util.EncryptUtils;
import edu.njucm.book.user.domain.Token;
import edu.njucm.book.user.domain.User;
import edu.njucm.book.user.service.impl.UserServiceImpl;
import edu.njucm.book.user.web.util.ILoginUrlControl;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static edu.njucm.book.common.constant.Constants.LOGIN_TOKEN_COMBINE_KEY;
import static edu.njucm.book.common.util.CookieUtils.getLastRequestTimeCookie;
import static edu.njucm.book.common.util.EncryptUtils.encrypt;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.*;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 登录处理工具类
 *
 * @author lvrongwang
 * @since 2020/3/21 12:05
 */
public class LoginUtils {

    public static String LOGIN_URL = "http://user.book.com/login";
    public static final String NEXT_PAGE = "?nextPage=";

    public static final Long LOGIN_EXPIRE_TIME = (long) (60 * 60 * 1000);
    public static List<String> managerCanAccessPage = Lists.newArrayList();

    static {
        managerCanAccessPage.add("^/manage/.*$");
    }

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
     * 判断用户是否登录成功
     *
     * @param request
     * @return
     */
    public static boolean isLoginSuccess(HttpServletRequest request) {
        Token token = getLoginUserToken(request);
        String lastRequestTime = getLastRequestTimeCookie(request);
        if (isNull(token) || isNull(lastRequestTime)) {
            return false;
        }
        if ((System.currentTimeMillis() - DateUtils.parseTime(lastRequestTime).getTime()) > LOGIN_EXPIRE_TIME) {
            return false;
        }
        if (isNull(userService().getByUserPhone(token.getUserPhone()))) {
            return false;
        }
        return true;
    }

    /**
     * 获取登录的用户token
     *
     * @param request
     * @return
     */
    public static String getLoginToken(HttpServletRequest request) {
        String encryptedToken = CookieUtils.getTokenCookie(request);
        if (StringUtils.isBlank(encryptedToken)) {
            return "";
        }
        return EncryptUtils.decrypt(encryptedToken);
    }

    /**
     * 获取登录用户User
     *
     * @param request
     * @return
     */
    public static User getLoginUser(HttpServletRequest request) {
        Token token = getLoginUserToken(request);
        if (nonNull(token)) {
            return userService().getByUserPhone(token.getUserPhone());
        }
        return null;
    }

    /**
     * 获取登录用户token信息
     *
     * @param request
     * @return
     */
    public static Token getLoginUserToken(HttpServletRequest request) {
        String tokenFormReq = getLoginToken(request);
        if (isBlank(tokenFormReq)) {
            return null;
        }
        String[] tokenArray = tokenFormReq.split("~!\\*!~");
        String loginTime = tokenArray[0];
        String userPhone = tokenArray[1];
        Token token = new Token();
        token.setLoginTime(loginTime);
        token.setUserPhone(userPhone);
        return token;
    }

    /**
     * 插入用户登录cookie token
     *
     * @param response
     * @param token
     */
    public static void initLoginCookie(HttpServletResponse response, String token) {
        if (nonNull(token)) {
            CookieUtils.addToken(response, encrypt(token));
        }
    }

    /**
     * 构建Token
     *
     * @param userPhone
     * @return
     */
    public static String createToken(String userPhone) {
        return isNotBlank(userPhone) ? System.currentTimeMillis() + LOGIN_TOKEN_COMBINE_KEY + userPhone : "";
    }

    /**
     * 获取带nextPage的登录页
     *
     * @param nextPage
     * @return
     */
    public static String loginUrlWithNextPage(String nextPage) {
        return isNotBlank(nextPage) ? LOGIN_URL + NEXT_PAGE + nextPage : LOGIN_URL;
    }

    /**
     * 登出清理cookie
     *
     * @param response
     */
    public static void logout(HttpServletResponse response) {
        CookieUtils.clearLogonToken(response);
    }

    /**
     * 判断请求是否需要登录
     *
     * @param url
     * @return
     */
    public static boolean needLogin(String url) {
        ILoginUrlControl loginUrlControl = ContextUtils.getContext().getBean(ILoginUrlControl.class);
        return loginUrlControl.needLogin(url);
    }

    /**
     * uri当前用户是否可登录
     * 
     * @param uri
     * @param request
     * @return
     */
    public static boolean canAccessPage(String uri, HttpServletRequest request) {
        User user = getLoginUser(request);
        if (isNull(user) || isNull(uri)) {
            return false;
        }
        if (!user.isManager()) {
            for (String manageCanAccessUri : managerCanAccessPage) {
                if (uri.matches(manageCanAccessUri)) {
                    return false;
                }
            }
        }
        return true;
    }
}
