package edu.njucm.book.user.web.util;

/**
 * 登录url控制
 *
 * @author lvrongwang
 * @since 2020/4/16 15:27
 */
public interface ILoginUrlControl {

    /**
     * 是否需要登录
     *
     * @param url
     * @return
     */
    boolean needLogin(String url);
}
