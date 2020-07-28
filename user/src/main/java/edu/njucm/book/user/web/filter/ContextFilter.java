package edu.njucm.book.user.web.filter;

import edu.njucm.book.common.util.CookieUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author lvrongwang
 * @since 2020/5/8 20:54
 */
public class ContextFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        initContext(request, response);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private static void initContext(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.addLastRequestTime(response, new Date());
    }
}
