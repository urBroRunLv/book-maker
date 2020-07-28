package edu.njucm.book.user.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Objects;

/**
 * @author lvrongwang
 * @since 2020/2/29 18:10
 */
public abstract class BaseController {

    private static final String REDIRECT = "redirect:";
    private static final String FORWARD = "forward:";
    private static final String PIC_CONTENT_TYPE = "image/jpg";
    public static final String OUTPUT_FLASH_MAP_ATTRIBUTE = DispatcherServlet.class.getName() + ".OUTPUT_FLASH_MAP";
    public static final String VO = "VO";

    /**
     * 重定向 redirect
     *
     * @param url 目标url
     * @return
     */
    public String redirect(String url) {
        if (StringUtils.isEmpty(url) || url.startsWith(REDIRECT)) {
            return url;
        }
        return REDIRECT + url;
    }

    /**
     * 转发 forward
     *
     * @param url 目标url
     * @return
     */
    public String forward(String url) {
        if (StringUtils.isEmpty(url) || url.startsWith(FORWARD)) {
            return url;
        }
        return FORWARD + url;
    }

    protected <T> T setModel(Model model, String key, T value, T defaultValue) {
        if (Objects.nonNull(value)) {
            model.addAttribute(key, value);
            return value;
        }
        if (Objects.nonNull(defaultValue)) {
            model.addAttribute(key, defaultValue);
            return defaultValue;
        }
        return null;
    }
}
