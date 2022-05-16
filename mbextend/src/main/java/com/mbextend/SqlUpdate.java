package com.mbextend;

import java.util.List;

/**
 * 代表一个update语句
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/21 10:16
 */
public class SqlUpdate{
    private final String sqlStatement;
    private final List<Object> sqlParams;

    public SqlUpdate(String sqlStatement, List<Object> sqlParams) {
        this.sqlStatement = sqlStatement;
        this.sqlParams = sqlParams;
    }
    public String getSqlStatement() {
        return sqlStatement;
    }

    public List<Object> getSqlParams() {
        return sqlParams;
    }
}
