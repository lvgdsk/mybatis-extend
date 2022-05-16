package com.mbextend;

import java.util.List;

/**
 * 算术函数表达式
 * @author lvqi
 * @version 1.0.0
 * @since 2022/4/29 10:29
 */
public class ArithFuncExpr extends SqlExpr{
    private final List<Object> params;
    private final String expression;
    private String columnAlias;

    ArithFuncExpr(String expression, List<Object> params) {
        this.expression = expression;
        this.params = params;
    }

    List<Object> getParams() {
        return params;
    }

    String getColumnAlias() {
        return columnAlias;
    }

    /** 设置表达式别名 */
    public ArithFuncExpr alias(Object alias){
        if(alias instanceof QColumn){
            QColumn qColumn = (QColumn)alias;
            columnAlias = qColumn.getColumnAlias()!=null?qColumn.getColumnAlias():qColumn.getColumnName();
        }else if(alias instanceof String) {
            this.columnAlias = (String)alias;
        }else{
            throw new RuntimeException("不支持的别名类型");
        }
        return this;
    }

    @Override
    QColumn getReplication(SqlTaBle taBle) {
        if(columnAlias==null){
            throw new RuntimeException("表达式别名不能为空");
        }
        return new QColumn(taBle,columnAlias,null);
    }

    @Override
    String getQueryColumnName() {
        return columnAlias==null?expression:columnAlias;
    }

    String getExpression() {
        return expression;
    }

    @Override
    String getQualifyExpr() {
        return expression;
    }
}
