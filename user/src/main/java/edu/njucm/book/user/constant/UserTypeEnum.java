package edu.njucm.book.user.constant;

import static java.util.Objects.isNull;

/**
 * @author lvrongwang
 * @since 2020/4/12 16:41
 */
public enum UserTypeEnum {

    ADMIN("0", "系统管理员"),
    MANAGER("1", "主编"),
    EDITOR("2", "编委");

    UserTypeEnum(String type, String name) {
        this.type = Short.valueOf(type);
        this.name = name;
    }

    private Short type;
    private String name;

    public static String tran2Name(Short userType) {
        if (isNull(userType)) {
            return null;
        }
        if (userType.equals(ADMIN.getType())) {
            return ADMIN.getName();
        } else if (userType.equals(MANAGER.getType())) {
            return MANAGER.getName();
        } else if (userType.equals(EDITOR.getType())) {
            return EDITOR.getName();
        } else {
            return null;
        }
    }

    public Short getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
