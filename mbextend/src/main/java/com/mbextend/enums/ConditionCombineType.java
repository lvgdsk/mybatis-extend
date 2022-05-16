package com.mbextend.enums;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/18 14:37
 * 逻辑关系
 */
public enum ConditionCombineType {
    AND(" and "),
    OR(" or "),
    XOR(" xor ");
    private final String symbol;

    public String getSymbol(){
        return symbol;
    }

    ConditionCombineType(String symbol) {
        this.symbol = symbol;
    }
}
