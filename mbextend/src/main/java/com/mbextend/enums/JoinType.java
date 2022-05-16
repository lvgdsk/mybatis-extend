package com.mbextend.enums;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/1/25 9:58
 * 表关联类型
 */
public enum JoinType {
    INNER_JOIN("inner join"),
    LEFT_JOIN("left join"),
    RIGHT_JOIN("right join");

    private final String value;

    public String value() {
        return value;
    }

    JoinType(String value) {
        this.value = value;
    }
}
