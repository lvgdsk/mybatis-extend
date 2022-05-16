package com.mbextend;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 代表一个表列
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 11:08
 */
public class QColumn extends SqlExpr {

    // 表字段对应的表
    private SqlTaBle taBle;
    // 表字段名
    private final String columnName;
    // 表字段别名
    private final String columnAlias;

    public QColumn(String columnName, String columnAlias) {
        this.columnName = columnName;
        this.columnAlias = columnAlias;
    }

    QColumn(SqlTaBle taBle, String columnName, String columnAlias) {
        this.taBle = taBle;
        this.columnName = columnName;
        this.columnAlias = columnAlias;
    }

    String getColumnName() {
        return columnName;
    }

    String getColumnAlias() {
        return columnAlias;
    }

    @Override
    QColumn getReplication(SqlTaBle taBle) {
        return new QColumn(taBle,columnName,columnAlias);
    }

    @Override
    String getQueryColumnName() {
        return columnAlias==null?columnName:columnAlias;
    }

    public void setTable(SqlTaBle taBle) {
        if(this.taBle==null) {
            this.taBle = taBle;
        }
    }

    boolean isTheSame(SqlExpr sqlExpr) {
        if(sqlExpr instanceof QColumn){
            QColumn qColumn = (QColumn)sqlExpr;
            return Objects.equals(columnName, qColumn.columnName) &&
                    Objects.equals(columnAlias, qColumn.columnAlias);
        }else if(sqlExpr instanceof ArithFuncExpr){
            return Objects.equals(columnName, sqlExpr.getColumnAlias());
        }
        return false;
    }

    @Override
    String getQualifyExpr() {
        return taBle.getTableAlias() + "." + columnName;
    }

    @Override
    List<Object> getParams() {
        return Collections.emptyList();
    }
}
