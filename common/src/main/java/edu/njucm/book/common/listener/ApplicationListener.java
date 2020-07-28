package edu.njucm.book.common.listener;

import edu.njucm.book.common.util.ContextUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author lvrongwang
 * @since 2020/4/12 15:25
 */
public class ApplicationListener extends ContextLoader implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            final ApplicationContext applicationContext = initWebApplicationContext(servletContextEvent.getServletContext());
            ContextUtils.setApplicationContext(applicationContext);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        closeWebApplicationContext(servletContextEvent.getServletContext());
    }
}
