package edu.njucm.book.frame.constant;

/**
 * @author lvrongwang
 * @since 2020/5/27 0:16
 */
public enum ContentTypeEnum {

    TEXT("1", "文本"),
    PIC("2", "图片"),
    CHART("3", "图表"),
    KNOWLEDGE("4", "知识点");

    ContentTypeEnum(String type, String desc) {
        this.type = Short.valueOf(type);
        this.desc = desc;
    }

    private Short type;
    private String desc;

    public Short getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
