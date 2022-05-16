package com.mbextend;

import com.mbextend.enums.ConditionCombineType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 11:08
 */
public class ConditionExpr{
    private String expression;
    private final List<Object> params;
    private boolean isNeedBracket;

    ConditionExpr(String conditionExpr,List<Object> params) {
        this.expression = conditionExpr;
        this.params = params;
    }

    /** 字符串区分大小写 */
    public ConditionExpr binary(){
        if(this.expression.startsWith("not")){
            this.expression = this.expression.replace("not","not binary");
        }else{
            this.expression = "binary " + this.expression;
        }
        return this;
    }

    /** 条件取反 */
    public ConditionExpr not(){
        expression = "not "+expression;
        return this;
    }

    /** 以与逻辑来结合两个条件表达式，生成一个新的条件表达式 */
    public ConditionExpr and(ConditionExpr sqlCondition){
        return combineCondition(ConditionCombineType.AND,sqlCondition);
    }

    /** 以或逻辑来结合两个条件表达式，生成一个新的条件表达式 */
    public ConditionExpr or(ConditionExpr sqlCondition){
        return combineCondition(ConditionCombineType.OR,sqlCondition);
    }

    /** 以异或逻辑来结合两个条件表达式，生成一个新的条件表达式 */
    public ConditionExpr xor(ConditionExpr sqlCondition){
        return combineCondition(ConditionCombineType.XOR,sqlCondition);
    }

    private ConditionExpr combineCondition(ConditionCombineType combineType, ConditionExpr sqlCondition){
        String condition;
        boolean isNeedBracket = true;
        switch (combineType){
            case AND:
                condition = this.getBracketExpression()+" and "+sqlCondition.getBracketExpression();
                isNeedBracket = false;
                break;
            case OR:
                condition = this.expression+" or "+sqlCondition.expression;
                break;
            case XOR:
                condition = this.expression+" xor "+sqlCondition.expression;
                break;
            default:
                throw new IllegalArgumentException("不支持的条件结合类型");
        }
        List<Object> params = new ArrayList<>(this.params.size()+sqlCondition.params.size());
        params.addAll(this.params);
        params.addAll(sqlCondition.params);
        ConditionExpr expr = new ConditionExpr(condition, params);
        expr.isNeedBracket = isNeedBracket;
        return expr;
    }


    String getExpression() {
        return expression;
    }

    String getBracketExpression(){
        if(isNeedBracket){
            return "("+expression+")";
        }else{
            return expression;
        }
    }

    List<Object> getParams() {
        return params;
    }

    void changeTableAlias(String tableAlias){
        expression = expression.replaceAll("t0",tableAlias);
    }
}
