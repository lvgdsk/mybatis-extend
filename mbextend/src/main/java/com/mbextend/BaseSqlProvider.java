package com.mbextend;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/18 15:44
 */
public class BaseSqlProvider {

    /** 构建select sql语句 */
    public static String select(@Param("sqlQuery") SqlQuery sqlQuery){
        return fillParams(sqlQuery.getFinalSqlStatement(),sqlQuery.getSqlParams(),"sqlQuery");
    }

    /** 构建update sql语句 */
    public static String update(@Param("sqlUpdate")SqlUpdate sqlUpdate){
        return fillParams(sqlUpdate.getSqlStatement(),sqlUpdate.getSqlParams(),"sqlUpdate");
    }

    private static String fillParams(String sqlStatement,List<Object> params,String paramName){
        for (int i = 0; i <params.size(); i++) {
            sqlStatement = sqlStatement.replaceFirst("\\$\\{param}",String.format("#{%s.sqlParams[%d]}",paramName,i));
        }
        return sqlStatement;
    }
}
