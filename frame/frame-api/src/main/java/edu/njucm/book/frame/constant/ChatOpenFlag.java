package edu.njucm.book.frame.constant;

/**
 * @author lvrongwang
 * @since 2020/5/24 12:44
 */
public enum ChatOpenFlag {

    CLOSE("0", "关闭"),
    OPEN("1", "开放");

    ChatOpenFlag(String flag, String desc) {
        this.flag = Short.valueOf(flag);
        this.desc = desc;
    }

    private Short flag;
    private String desc;

    public Short getFlag() {
        return flag;
    }

    public String getDesc() {
        return desc;
    }
}
