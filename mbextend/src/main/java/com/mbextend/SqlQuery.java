package com.mbextend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 代表一条查询sql语句
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 11:06
 */
public class SqlQuery extends SqlTaBle{
    private final String cteStatement;
    // sql语句
    private final String sqlStatement;
    private String tableAlias = "t0";
    // 是否是cte查询子句
    private boolean isCte;
    private boolean isRecursive;
    private List<SqlExpr> queryColumns;
    private List<QColumn> columns;
    private final List<Object> params;
    private final List<Object> cteParams;
    private List<Object> sqlParams;

    SqlQuery(String sqlStatement,String cteStatement,List<Object> params,List<Object> cteParams,List<SqlExpr> queryColumns) {
        this.sqlStatement = sqlStatement;
        this.cteStatement = cteStatement;
        this.params = params;
        this.cteParams = cteParams;
        this.queryColumns = queryColumns;
    }

    @Override
    protected String getTableName() {
        if(isCte){
            return String.valueOf(tableAlias);
        }
        return "(" + sqlStatement + ")";
    }

    @Override
    protected String getTableAlias() {
        return String.valueOf(tableAlias);
    }

    protected void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    @Override
    protected List<QColumn> getQColumns() {
        return columns;
    }

    /** 获取子查询列 */
    public QColumn column(SqlExpr sqlExpr){
        changeColumn();
        Optional<QColumn> first = columns.stream().filter(c -> c.isTheSame(sqlExpr)).findFirst();
        if(first.isPresent()){
            return first.get();
        }
        throw new RuntimeException(String.format("子查询内不存在%s查询列", sqlExpr.getQueryColumnName()));
    }

    /** 构建递归cte查询，mysql8才支持 */
    public SqlQuery unionRecursiveCte(SqlQuery query){
        if(!this.isCte){
            throw new RuntimeException("不是CTE查询");
        }
        String sqlStatement = this.sqlStatement + " union " + query.sqlStatement;
        List<Object> params = new ArrayList<>(this.params.size()+query.params.size());
        params.addAll(this.params);
        params.addAll(query.params);
        SqlQuery sqlQuery = new SqlQuery(sqlStatement, null, params, Collections.emptyList(), this.queryColumns);
        sqlQuery.isCte = true;
        sqlQuery.isRecursive = true;
        sqlQuery.tableAlias = this.tableAlias;
        return sqlQuery;
    }

    /** 结果集并集，去重 */
    public SqlQuery union(SqlQuery other){
        return union(other,false);
    }
    /** 结果集并集，不去重 */
    public SqlQuery unionAll(SqlQuery other){
        return union(other,true);
    }

    private SqlQuery union(SqlQuery other,boolean isAll){
        String operator = isAll?" union all ":" union ";
        String sqlStatement = this.sqlStatement + operator + other.sqlStatement;
        List<Object> params = new ArrayList<>(this.params.size()+other.params.size());
        params.addAll(this.params);
        params.addAll(other.params);
        List<Object> cteParams = new ArrayList<>(16);
        String cteStatement = null;
        if(this.cteStatement!=null){
            cteStatement = this.cteStatement;
            cteParams.add(this.cteParams);
        }
        if(other.cteStatement!=null){
            if(cteStatement==null){
                cteStatement = "";
            }else{
                cteStatement += ",";
            }
            cteStatement += other.cteStatement;
            cteParams.add(other.cteParams);
        }
        return new SqlQuery(sqlStatement ,cteStatement, params,cteParams, this.queryColumns);
    }

    /** 当前查询是否为cte查询 */
    public void setIsCte(){
        this.isCte = true;
    }

    boolean isCte() {
        return isCte;
    }

    String getSqlStatement() {
        return "("+sqlStatement+")";
    }

    List<Object> getParams() {
        return params;
    }

    String getFinalSqlStatement(){
        if(cteStatement!=null){
            return "with "+cteStatement + sqlStatement;
        }else{
            return sqlStatement;
        }
    }

    boolean isRecursive() {
        return isRecursive;
    }

    List<Object> getSqlParams() {
        if(sqlParams==null) {
            sqlParams = new ArrayList<>(this.params.size() + this.cteParams.size());
            sqlParams.addAll(this.cteParams);
            sqlParams.addAll(this.params);
        }
        return sqlParams;
    }

    void changeColumn() {
        if(columns==null) {
            columns = queryColumns.stream().map(c -> c.getReplication(this)).collect(Collectors.toList());
        }
    }
}
