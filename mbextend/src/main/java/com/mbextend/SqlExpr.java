package com.mbextend;

import com.mbextend.enums.SqlOperator;

import java.util.Arrays;
import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 11:10
 */
public abstract class SqlExpr{

    /** 空值判断 */
    public ConditionExpr isNull(){
        return SqlExprBuilder.buildConditionExpr(this, SqlOperator.ISN,null);
    }
    /** 非空判断 */
    public ConditionExpr isNotNull(){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.ISNN,null);
    }
    /** 等值判断 */
    public ConditionExpr eq(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.EQ,param);
    }
    /** 不等判断 */
    public ConditionExpr ne(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.NE,param);
    }
    /** 大于判断 */
    public ConditionExpr gt(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.GT,param);
    }
    /** 小于判断 */
    public ConditionExpr lt(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.LT,param);
    }
    /** 大于或等于判断 */
    public ConditionExpr ge(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.GE,param);
    }
    /** 小于或等于判断 */
    public ConditionExpr le(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.LE,param);
    }
    /** 在...之内判断 */
    public ConditionExpr in(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.IN,param);
    }
    /** 不在...之内判断 */
    public ConditionExpr notIn(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.NIN,param);
    }
    /** 在...之间判断 */
    public ConditionExpr between(Object begin, Object end){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.BT, Arrays.asList(begin,end));
    }
    /** 不在...之间判断 */
    public ConditionExpr notBetween(Object begin, Object end){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.NBT,Arrays.asList(begin,end));
    }
    /** 字符串以什么开头判断 */
    public ConditionExpr startWith(Object param){
        checkLikeParam(param);
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.SW,param);
    }
    /** 字符串不以什么开头判断 */
    public ConditionExpr notStartWith(Object param){
        checkLikeParam(param);
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.NSW,param);
    }
    /** 字符串以什么结尾判断 */
    public ConditionExpr endWith(Object param){
        checkLikeParam(param);
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.EW,param);
    }
    /** 字符串不以什么结尾判断 */
    public ConditionExpr notEndWith(Object param){
        checkLikeParam(param);
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.NEW,param);
    }
    /** 字符串子串包含判断 */
    public ConditionExpr contain(Object param){
        checkLikeParam(param);
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.CT,param);
    }
    /** 字符串子串不包含判断 */
    public ConditionExpr notContain(Object param){
        checkLikeParam(param);
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.NCT,param);
    }
    /** like判断 */
    public ConditionExpr like(Object param){
        checkLikeParam(param);
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.LK,param);
    }
    /** not like判断 */
    public ConditionExpr notLike(Object param){
        checkLikeParam(param);
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.NLK,param);
    }
    /** 正则判断 */
    public ConditionExpr regexp(Object param){
        checkLikeParam(param);
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.REG,param);
    }
    /** 升序排序 */
    public GroupOrderExpr orderAsc(){
        return new GroupOrderExpr(getQualifyExpr(),getParams());
    }
    /** 降序排序 */
    public GroupOrderExpr orderDesc(){
        return new GroupOrderExpr(getQualifyExpr()+ " desc",getParams());
    }
    /** 分组升序 */
    public GroupOrderExpr groupAsc(){
        return new GroupOrderExpr(getQualifyExpr(),getParams());
    }
    /** 分组降序 */
    public GroupOrderExpr groupDesc(){
        return new GroupOrderExpr(getQualifyExpr()+ " desc",getParams());
    }

    void checkLikeParam(Object param){
        if(!(param instanceof QColumn || param instanceof String)){
            throw new IllegalArgumentException("不支持的参数类型");
        }
    }

    abstract String getColumnAlias();
    abstract QColumn getReplication(SqlTaBle taBle);
    abstract String getQueryColumnName();
    abstract List<Object> getParams();
    abstract String getQualifyExpr();
}
