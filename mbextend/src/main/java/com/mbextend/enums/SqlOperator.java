package com.mbextend.enums;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/1/24 13:46
 * 比较运算符
 */
public enum SqlOperator {
    // 为null
    ISN(1,"is null"),
    // 不为null
    ISNN(1,"is not null"),
    // 等于
    EQ(2,"={}"),
    // 不等于
    NE(2,"!={}"),
    // 大于
    GT(2,">{}"),
    // 小于
    LT(2,"<{}"),
    // 大于或等于
    GE(2,">={}"),
    // 小于或等于
    LE(2,"<={}"),
    // 似
    LK(2,"like {}"),
    // 不似
    NLK(2,"not like {}"),
    // 正则匹配
    REG(2,"regexp {}"),
    // 存在
    ES(2,"exists {}"),
    // 不存在
    NES(2,"not exists {}"),
    // 以其开头
    SW(3,"like {}"),
    // 不以其开头
    NSW(3,"not like {}"),
    // 以其结尾
    EW(3,"like {}"),
    // 不以其结尾
    NEW(3,"not like {}"),
    // 包含
    CT(3,"like {}"),
    // 不包含
    NCT(3,"not like {}"),
    // 在其中
    IN(4,"in {}"),
    // 不在其中
    NIN(4,"not in {}"),
    // 在其间
    BT(5,"between {}"),
    // 不在其间
    NBT(5,"not between {}");

    private final Integer dist;
    private final String operator;

    SqlOperator(Integer dist, String operator){
        this.dist = dist;
        this.operator = operator;
    }

    public Integer dist(){return dist;}
    public String operator(){return operator;}
}
