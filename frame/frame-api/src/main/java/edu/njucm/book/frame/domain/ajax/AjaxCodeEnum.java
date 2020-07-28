package edu.njucm.book.frame.domain.ajax;

/**
 * @author lvrongwang
 * @since 2020/4/17 10:15
 */
public enum AjaxCodeEnum {

    SUCCESS("10001"),
    ERROR("10002");

    private String code;

    AjaxCodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
