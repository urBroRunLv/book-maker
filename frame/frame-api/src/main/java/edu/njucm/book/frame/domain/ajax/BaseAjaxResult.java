package edu.njucm.book.frame.domain.ajax;


import static edu.njucm.book.common.constant.Constants.AJAX_RETURN_SUCCESS;
import static edu.njucm.book.frame.domain.ajax.AjaxCodeEnum.ERROR;
import static edu.njucm.book.frame.domain.ajax.AjaxCodeEnum.SUCCESS;

/**
 * @author lvrongwang
 * @since 2020/4/17 10:08
 */
public class BaseAjaxResult<T> {

    /**
     * 异步请求状态码：10001-成功，10002-失败
     */
    private String code;
    /**
     * 业务状态码
     */
    private String status;
    /**
     * 异步请求返回值
     */
    private T data;

    public static <T> BaseAjaxResult<T> successResult() {
        BaseAjaxResult<T> result = new BaseAjaxResult<>();
        result.setCode(SUCCESS.getCode());
        result.setStatus(AJAX_RETURN_SUCCESS);
        result.setData(null);
        return result;
    }

    public static <T> BaseAjaxResult<T> successResult(T data) {
        BaseAjaxResult<T> result = new BaseAjaxResult<>();
        result.setCode(SUCCESS.getCode());
        result.setStatus(AJAX_RETURN_SUCCESS);
        result.setData(data);
        return result;
    }

    public static <T> BaseAjaxResult<T> failResult() {
        BaseAjaxResult<T> result = new BaseAjaxResult<>();
        result.setCode(SUCCESS.getCode());
        result.setStatus("99");
        result.setData(null);
        return result;
    }

    public static <T> BaseAjaxResult<T> failResult(T data) {
        BaseAjaxResult<T> result = new BaseAjaxResult<>();
        result.setCode(SUCCESS.getCode());
        result.setStatus("99");
        result.setData(data);
        return result;
    }

    public static <T> BaseAjaxResult<T> failResult(String status, T data) {
        BaseAjaxResult<T> result = new BaseAjaxResult<>();
        result.setCode(SUCCESS.getCode());
        result.setStatus(status);
        result.setData(data);
        return result;
    }

    public static <T> BaseAjaxResult<T> errorResult() {
        BaseAjaxResult<T> result = new BaseAjaxResult<>();
        result.setCode(ERROR.getCode());
        result.setStatus(null);
        result.setData(null);
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseAjaxResult{" +
                "code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
