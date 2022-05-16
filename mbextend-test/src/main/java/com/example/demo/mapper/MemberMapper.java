package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.Member;
import com.mbextend.BaseSqlProvider;
import com.mbextend.SqlQuery;
import com.mbextend.SqlUpdate;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lvqi
 * @since 2022-05-05
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {
    @SelectProvider(type = BaseSqlProvider.class, method = "select")
    @ResultMap("BaseResultMap")
    List<Member> select(@Param("sqlQuery")SqlQuery sqlQuery);

    @SelectProvider(type = BaseSqlProvider.class, method = "select")
    @ResultMap("BaseResultMap")
    Member selectOne(@Param("sqlQuery") SqlQuery sqlQuery);

    @SelectProvider(type = BaseSqlProvider.class, method = "select")
    @ResultMap("BaseResultMap")
    IPage<Member> selectPage(IPage<Member> page, @Param("sqlQuery")SqlQuery sqlQuery);

    @SelectProvider(type = BaseSqlProvider.class, method = "select")
    @ResultType(Boolean.class)
    Boolean exists(@Param("sqlQuery")SqlQuery sqlQuery);

    @SelectProvider(type = BaseSqlProvider.class, method = "select")
    @ResultType(Integer.class)
    Integer selectLiteral(@Param("sqlQuery")SqlQuery sqlQuery);

    @UpdateProvider(type = BaseSqlProvider.class, method = "update")
    @ResultType(Integer.class)
    Integer update(@Param("sqlUpdate")SqlUpdate sqlUpdate);
}
