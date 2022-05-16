package com.mbextend;

import com.mbextend.enums.JoinType;

/**
 * 查询、更新、删除的表
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 10:58
 */
public class QueryTable{
    private final SqlTaBle sqlTaBle;
    private final ConditionExpr joinCondition;
    private final JoinType joinType;

    QueryTable(SqlTaBle sqlTaBle) {
        this(sqlTaBle,null,null);
    }

    QueryTable(SqlTaBle sqlTaBle, JoinType joinType, ConditionExpr joinCondition) {
        this.sqlTaBle = sqlTaBle;
        this.joinType = joinType;
        this.joinCondition = joinCondition;
    }

    SqlTaBle getSqlTaBle() {
        return sqlTaBle;
    }

    ConditionExpr getJoinCondition() {
        return joinCondition;
    }

    JoinType getJoinType() {
        return joinType;
    }
}
