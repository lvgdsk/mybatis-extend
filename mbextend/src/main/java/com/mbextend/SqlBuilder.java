package com.mbextend;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/21 10:49
 */
public class SqlBuilder {

    /** 查询入口 */
    public static QueryBuilder query(SqlTaBle sqlTaBle) {return QueryBuilder.from(sqlTaBle);}

    /** 更新入口 */
    public static UpdateBuilder update(SqlTaBle sqlTaBle){
        return UpdateBuilder.update(sqlTaBle);
    }
}
