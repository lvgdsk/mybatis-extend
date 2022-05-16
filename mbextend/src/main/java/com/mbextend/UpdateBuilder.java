package com.mbextend;

import com.mbextend.enums.JoinType;

import java.util.*;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/1/27 15:17
 * 定义更新语句
 */
public class UpdateBuilder {
    private final List<QueryTable> updateTables = new ArrayList<>(8);
    private Set<String> sqlSets;
    private List<ConditionExpr> whereConditions;
    List<Object> params = new ArrayList<>(16);
    private boolean hadEndFrom;
    private int tableAliasIndex = 0;

    private UpdateBuilder(SqlTaBle sqlTaBle) {
        if(sqlTaBle.getTableAlias().equals("t0")){
            if(sqlTaBle instanceof SqlQuery && ((SqlQuery)sqlTaBle).isCte()){
                sqlTaBle.setTableAlias("cte"+(++tableAliasIndex));
            }else {
                sqlTaBle.setTableAlias("t" + (++tableAliasIndex));
            }
        }
        updateTables.add(new QueryTable(sqlTaBle));
    }

    static UpdateBuilder update(SqlTaBle sqlTaBle){
        return new UpdateBuilder(sqlTaBle);
    }
    /** inner join */
    public UpdateBuilder innerJoin(SqlTaBle sqlTaBle, ConditionExpr joinCondition){
        return joinTable(sqlTaBle,JoinType.INNER_JOIN,joinCondition);
    }
    /** left join */
    public UpdateBuilder leftJoin(SqlTaBle sqlTaBle, ConditionExpr joinCondition){
        return joinTable(sqlTaBle,JoinType.LEFT_JOIN,joinCondition);
    }
    /** right join */
    public UpdateBuilder rightJoin(SqlTaBle sqlTaBle, ConditionExpr joinCondition){
        return joinTable(sqlTaBle, JoinType.RIGHT_JOIN,joinCondition);
    }

    private UpdateBuilder joinTable(SqlTaBle sqlTaBle, JoinType joinType, ConditionExpr joinCondition){
        if(hadEndFrom){
            throw new RuntimeException("join子句需紧随from子句后");
        }
        if(sqlTaBle.getTableAlias().equals("t0")){
            if(sqlTaBle instanceof SqlQuery && ((SqlQuery)sqlTaBle).isCte()){
                sqlTaBle.setTableAlias("cte"+(++tableAliasIndex));
            }else {
                sqlTaBle.setTableAlias("t" + (++tableAliasIndex));
            }
            joinCondition.changeTableAlias(sqlTaBle.getTableAlias());
        }
        this.updateTables.add(new QueryTable(sqlTaBle,joinType,joinCondition));
        return this;
    }
    /** 构建set子句 */
    public UpdateBuilder set(QColumn qColumn, Object value){
        if(sqlSets==null){
            sqlSets = new HashSet<>(10);
        }
        sqlSets.add(SqlExprBuilder.buildSetExpr(qColumn,value,this.params));
        hadEndFrom = true;
        return this;
    }
    /** 构建where条件表达式 */
    public UpdateBuilder where(ConditionExpr... sqlCondition){
        if(whereConditions==null) {
            whereConditions = new ArrayList<>(Arrays.asList(sqlCondition));
        }else{
            whereConditions.addAll(new ArrayList<>(Arrays.asList(sqlCondition)));
        }
        hadEndFrom = true;
        return this;
    }
    /** 构建sql语句 */
    public SqlUpdate build(){
        return SqlStatementBuilder.buildUpdate(this);
    }

    List<QueryTable> getUpdateTables() {
        return updateTables;
    }

    Set<String> getSqlSets() {
        return sqlSets;
    }

    List<ConditionExpr> getWhereConditions() {
        return whereConditions;
    }

    List<Object> getParams() {
        return params;
    }
}
