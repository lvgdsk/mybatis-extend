package com.mbextend;

import java.util.List;

/**
 * 表抽象接口，有两类表：1：真实的表，2：子查询
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 10:42
 */
public abstract class SqlTaBle {
    /**
     * 获取表名
     */
    protected abstract String getTableName();

    /**
     * 获取表别名
     */
    protected abstract String getTableAlias();

    /**
     * 获取所有表列
     */
    protected abstract List<QColumn> getQColumns();

    protected abstract void setTableAlias(String tableAlias);
}
