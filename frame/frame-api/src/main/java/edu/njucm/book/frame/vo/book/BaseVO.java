package edu.njucm.book.frame.vo.book;

/**
 * @author lvrongwang
 * @since 2020/4/23 16:48
 */
public class BaseVO {

    /**
     * 错误信息
     */
    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "BaseVO{" +
                "errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
