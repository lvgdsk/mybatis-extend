package com.mbextend;

import com.mbextend.enums.ExprEnum;
import com.mbextend.enums.SqlOperator;
import com.mbextend.enums.TimeField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 表达式构建类
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/18 15:23
 */
public class SqlExprBuilder {
    /** 参数解析 */
    private static String[] parseParam(List<Object> sqlParams,Object ... params){
        String[] actParams = new String[params.length];
        int index = 0;
        for (Object param : params) {
            if(param==null){
                actParams[index] = "";
            }else if(param instanceof SqlExpr) {
                SqlExpr sqlExpr = (SqlExpr)param;
                sqlParams.addAll(sqlExpr.getParams());
                actParams[index] = sqlExpr.getQualifyExpr();
            }else if(param instanceof ConditionExpr){
                ConditionExpr expr = (ConditionExpr)param;
                sqlParams.addAll(expr.getParams());
                actParams[index] = expr.getExpression();
            } else if(param instanceof TimeField) {
                actParams[index] = ((TimeField)param).name();
            }else if(param instanceof Collection){
                Collection<?> collection = (Collection<?>) param;
                sqlParams.addAll(collection);
                actParams[index] = collection.stream().map(e->"${param}").collect(Collectors.joining(","));
            }else{
                sqlParams.add(param);
                actParams[index] = "${param}";
            }
            index++;
        }
        return actParams;
    }

    /** 构建函数、算式表达式 */
    public static ArithFuncExpr build(ExprEnum exprEnum, Object ... params) {
        String expression = exprEnum.expr();
        List<Object> sqlParams = new ArrayList<>(params.length);
        if (params.length > 0) {
            String[] actParams = parseParam(sqlParams,params);
            if (exprEnum == ExprEnum.CASE_SWITCH || exprEnum == ExprEnum.CASE_CONDITION) {
                int starIndex = 0;
                if (exprEnum == ExprEnum.CASE_SWITCH) {
                    expression = expression.replace("${field}", actParams[0]);
                    starIndex++;
                }
                StringBuilder builder = new StringBuilder();
                String template = "when %s then %s ";
                int endIndex = params.length-1;
                if( (params.length - starIndex)%2 ==1 ){
                    endIndex--;
                }
                for (int i = starIndex; i <= endIndex; i += 2) {
                    builder.append(String.format(template, actParams[i], actParams[i + 1]));
                }
                if(endIndex!=params.length-1){
                    builder.append("else ").append(actParams[params.length - 1]);
                }
                expression = expression.replace("${}", builder);
            }
            else {
                if (exprEnum.count() > 0) {
                    expression = String.format(expression, (Object[])actParams);
                }
                if (expression.contains("${}")) {
                    StringBuilder builder = new StringBuilder();
                    for (int i = exprEnum.count(); i < actParams.length; i++) {
                        builder.append(actParams[i]).append(",");
                    }
                    builder.deleteCharAt(builder.length() - 1);
                    expression = expression.replace("${}", builder);
                }
            }
        }
        return new ArithFuncExpr(expression, sqlParams);
    }

    /** 构建窗口函数表达式 */
    public static ArithFuncExpr buildWindowFunctionExpr(ExprEnum exprEnum,
                                                        List<GroupOrderExpr> partitions,
                                                        List<GroupOrderExpr> orders,
                                                        Object ... params){
        List<Object> sqlParams = new ArrayList<>(6);
        String expression = exprEnum.expr();
        if(exprEnum.count()>0 && params.length>0){
            String[] actParams = parseParam(sqlParams,params);
            expression = String.format(expression,(Object[])actParams);
        }
        String groupBy = "partition by ";
        String orderBy = "order by ";
        if(partitions!=null && !partitions.isEmpty()){
            groupBy += partitions.stream().map(expr->{
                sqlParams.add(expr.getParams());
                return expr.getExpression();
            }).collect(Collectors.joining(","));
        }else{
            groupBy = "";
        }
        if(orders!=null && !orders.isEmpty()){
            orderBy += orders.stream().map(expr->{
                sqlParams.add(expr.getParams());
                return expr.getExpression();
            }).collect(Collectors.joining(","));
        }else{
            orderBy = "";
        }
        expression = expression.replace("${group}",groupBy);
        expression = expression.replace("${order}",orderBy);
        return new ArithFuncExpr(expression, sqlParams);
    }

    /** 构建条表达式 */
    public static ConditionExpr buildConditionExpr(SqlExpr sqlExpr, SqlOperator sqlOperator, Object param){
        List<Object> params = new ArrayList<>(6);

        StringBuilder builder = new StringBuilder();
        if(sqlExpr!=null){
            params.addAll(sqlExpr.getParams());
            builder.append(sqlExpr.getQualifyExpr()).append(" ");
        }

        String strParam = null;
        switch (sqlOperator.dist()){
            case 2:
                if (param instanceof SqlExpr) {
                    SqlExpr expr = (SqlExpr) param;
                    params.addAll(expr.getParams());
                    strParam = expr.getQualifyExpr();
                } else if (param instanceof SqlQuery) {
                    SqlQuery sqlQuery = (SqlQuery) param;
                    params.addAll(sqlQuery.getParams());
                    strParam = sqlQuery.getSqlStatement();
                }
                if(strParam==null){
                    params.add(param);
                    strParam = "${param}";
                }
                break;
            case 3:
                switch (sqlOperator){
                    case SW:
                    case NSW:
                        if(param instanceof String){
                            params.add(param+"%");
                        }else{
                            ArithFuncExpr concat = ExprUtil.concat(param, "%");
                            strParam = concat.getExpression();
                            params.addAll(concat.getParams());
                        }
                        break;
                    case EW:
                    case NEW:
                        if(param instanceof String){
                            params.add("%"+param);
                        }else{
                            ArithFuncExpr concat = ExprUtil.concat("%", param);
                            strParam = concat.getExpression();
                            params.addAll(concat.getParams());
                        }
                        break;
                    case CT:
                    case NCT:
                        if(param instanceof String){
                            params.add("%"+param+"%");
                        }else{
                            ArithFuncExpr concat = ExprUtil.concat("%",param,"%");
                            strParam = concat.getExpression();
                            params.addAll(concat.getParams());
                        }
                        break;
                }
                if(strParam==null){
                    strParam = "${param}";
                }
                break;
            case 4:
                if(param instanceof Collection){
                    Collection<?> collection = (Collection<?>) param;
                    params.addAll(collection);
                    strParam = collection.stream().map(e->"${param}").collect(Collectors.joining(","));
                    strParam = "(" + strParam + ")";
                }else if(param instanceof SqlQuery){
                    SqlQuery sqlQuery = (SqlQuery)param;
                    params.addAll(sqlQuery.getParams());
                    strParam = sqlQuery.getSqlStatement();
                }else{
                    throw new IllegalArgumentException("in条件参数类型错误");
                }
                break;
            case 5:
                if(!(param instanceof Collection)){
                    throw new IllegalArgumentException("between 或 not between 的参数必须是集合");
                }
                params.addAll((Collection<?>)param);
                strParam = "${param} and ${param}";
        }
        if(sqlOperator.dist()!=1) {
            assert strParam != null;
            builder.append(sqlOperator.operator().replace("{}", strParam));
        }else{
            builder.append(sqlOperator.operator());
        }
        return new ConditionExpr(builder.toString(),params);
    }

    /** 构建update的赋值set表达式 */
    public static String buildSetExpr(QColumn qColumn, Object param, List<Object> params){
        StringBuilder builder = new StringBuilder(qColumn.getQualifyExpr());
        builder.append("=");
        String strParam;
        if(param instanceof SqlExpr){
            params.addAll(((SqlExpr)param).getParams());
            strParam = ((SqlExpr)param).getQualifyExpr();
        }else{
            params.add(param);
            strParam = "${param}";
        }
        builder.append(strParam);
        return builder.toString();
    }
}
