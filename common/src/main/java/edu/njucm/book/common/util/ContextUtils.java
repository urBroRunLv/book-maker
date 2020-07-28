package edu.njucm.book.common.util;

import org.springframework.context.ApplicationContext;

/**
 * 获取bean的工具类
 *
 * @author lvrongwang
 * @since 2020/4/12 15:27
 */
public class ContextUtils {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getContext() {
        return applicationContext;
    }

    public static Object getBean(String beanId) {
        Object bean = applicationContext.getBean(beanId);
        if (bean == null)
            return null;
        return bean;
    }

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }
}
