package edu.njucm.book.common.util;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import edu.njucm.book.common.constant.Constants;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

/**
 * cookie处理工具类
 *
 * @author lvrongwang
 * @since 2020/3/21 12:05
 */
public class CookieUtils {

    private static final Logger logger = LoggerFactory.getLogger(CookieUtils.class);

    /**
     * Cookie Key
     */
    private static final String TOKEN = "token";
    private static final String LAST_REQUEST_TIME = "lastRequestTime";
    /**
     * Cookie Constants
     */
    private static final String ALL_PATH = "/";
    public static final String ALL_DOMAIN = "book.com";


    public static final int DEFAULT_COOKIE_EXPIRE_TIME = 99999999;

    /**
     * 获取Cookie
     * token
     *
     * @param request
     * @return
     */
    public static String getTokenCookie(HttpServletRequest request) {
        return getCookieValue(request, TOKEN);
    }

    /**
     * 获取Cookie
     * token
     *
     * @param request
     * @return
     */
    public static String getLastRequestTimeCookie(HttpServletRequest request) {
        return getCookieValue(request, LAST_REQUEST_TIME);
    }

    /**
     * 添加cookie
     * 登录token
     *
     * @param response
     * @param token
     */
    public static void addToken(HttpServletResponse response, String token) {
        addCookie(response, TOKEN, token, ALL_DOMAIN, DEFAULT_COOKIE_EXPIRE_TIME);
    }

    /**
     * 添加cookie
     * 最后发送请求时间
     *
     * @param response
     * @param date
     */
    public static void addLastRequestTime(HttpServletResponse response, Date date) {
        addCookie(response, LAST_REQUEST_TIME, DateUtils.formatTime(date), ALL_DOMAIN, DEFAULT_COOKIE_EXPIRE_TIME);
    }

    /**
     * 清理cookie
     * 登录token
     *
     * @param response
     */
    public static void clearLogonToken(HttpServletResponse response) {
        cleanCookie(response, TOKEN, ALL_DOMAIN);
    }

    /**
     * 清理cookie
     * 最后发送请求时间
     *
     * @param response
     */
    public static void clearLastRequestTime(HttpServletResponse response) {
        cleanCookie(response, LAST_REQUEST_TIME, ALL_DOMAIN);
    }

    /**
     * 获得cookie的Value值
     *
     * @param request
     * @param name
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        if (cookie != null) {
            try {
                return URLDecoder.decode(cookie.getValue(), Constants.UTF_8);
            } catch (Exception e) {
                logger.error("getCookieValue error!", e);
                return "";
            }
        }
        return null;
    }

    /**
     * 获取cookie
     *
     * @param request
     * @param name
     * @return
     */
    private static Cookie getCookie(HttpServletRequest request, String name) {
        Assert.notNull(request);
        Cookie[] cookies = request.getCookies();
        if ((cookies != null) && (cookies.length > 0)) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * 添加cookie
     *
     * @param response
     * @param name
     * @param value
     * @param expiry
     * @return
     */
    public static Cookie addCookie(HttpServletResponse response, String name, String value, String domain, Integer expiry) {
        try {
            Cookie cookie = new Cookie(name, value);
            cookie.setPath(ALL_PATH);
            cookie.setDomain(isNotBlank(domain) ? domain : ALL_DOMAIN);
            try {
                URLEncoder.encode(value, Constants.UTF_8);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            cookie.setMaxAge(expiry);
            response.addCookie(cookie);
            return cookie;
        } catch (Exception e) {
            logger.error("addCookie error!", e);
            return new Cookie(name, StringUtils.EMPTY);
        }
    }

    /**
     * 清理cookie
     *
     * @param response
     * @param name
     */
    public static void cleanCookie(HttpServletResponse response, String name, String domain) {
        try {
            addCookie(response, name, Constants.EMPTY, domain, 0);
        } catch (Exception e) {
            logger.error("cleanCookie error!", e);
        }
    }
}
