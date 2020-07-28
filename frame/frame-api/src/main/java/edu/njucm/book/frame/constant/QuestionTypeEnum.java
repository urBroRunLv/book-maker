package edu.njucm.book.frame.constant;

import static edu.njucm.book.common.constant.Constants.EMPTY;
import static java.util.Objects.nonNull;

/**
 * @author lvrongwang
 * @since 2020/5/12 15:06
 */
public enum QuestionTypeEnum {

    SELECT("0", "选择题"),
    BLANK("1", "填空题"),
    SAQ("2", "简答题");

    QuestionTypeEnum(String type, String name) {
        this.type = Short.valueOf(type);
        this.name = name;
    }

    private Short type;
    private String name;

    public Short getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static String tranId2Name(Short type) {
        if (nonNull(type)) {
            for (QuestionTypeEnum e : QuestionTypeEnum.values()) {
                if (e.getType().equals(type)) {
                    return e.getName();
                }
            }
        }
        return EMPTY;
    }
}
