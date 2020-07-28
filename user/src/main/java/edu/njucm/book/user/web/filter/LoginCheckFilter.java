package edu.njucm.book.user.web.filter;

import edu.njucm.book.common.constant.IPageConstant;
import edu.njucm.book.common.util.ContextUtils;
import edu.njucm.book.user.domain.User;
import edu.njucm.book.user.service.IUserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static edu.njucm.book.user.util.LoginUtils.*;

/**
 * 登录检查过滤器
 *
 * @author lvrongwang
 * @since 2020/3/21 11:59
 */
public class LoginCheckFilter implements Filter, IPageConstant {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = String.valueOf(request.getRequestURL());
        if (isLoginSuccess(request)) {
            String uri = request.getRequestURI();
            if (!canAccessPage(uri, request)) {
                response.sendRedirect(ERROR_403);
            }
        }
        else {
            String requestUrl = request.getRequestURI();
            if (needLogin(requestUrl)) {
                logout(response);
                response.sendRedirect(loginUrlWithNextPage(url));
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
