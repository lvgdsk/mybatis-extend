package com.example.demo;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Member;
import com.example.demo.entity.Order;
import com.example.demo.entity.ProductCategory;
import com.example.demo.mapper.MemberMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.mapper.ProductCategoryMapper;
import com.example.demo.tablemap.QMember;
import com.example.demo.tablemap.QOrder;
import com.example.demo.tablemap.QOrderItem;
import com.example.demo.tablemap.QProductCategory;
import com.mbextend.ExprUtil;
import com.mbextend.SqlBuilder;
import com.mbextend.SqlQuery;
import com.mbextend.SqlUpdate;
import com.mbextend.enums.TimeField;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/5/6 14:46
 */
@SpringBootTest
public class MbextendTest {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Test
    void testSimplyQuery1(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
//                .select(qMember)
//                .select(qMember.username, qMember.money)
                .where(qMember.username.eq("user-0"))
                .build();
        Member member = memberMapper.selectOne(sqlQuery);
        System.out.println(JSON.toJSONString(member));
    }

    @Test
    void testSimplyQuery2(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .where(qMember.username.in(Arrays.asList("user-0","user-1")))
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        System.out.println(JSON.toJSONString(members));
    }

    @Test
    void testSimplyQuery3(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .where(qMember.birthday.between(
                        DateUtil.parse("2008-08-00"),
                        DateUtil.parse("2008-09-00")))
                .orderBy(qMember.birthday.orderAsc())
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        members.forEach(m-> System.out.println(JSON.toJSONString(m)));
    }

    @Test
    void testSimplyQuery4(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .where(qMember.username.startWith("user-100"))
//                .where(qMember.username.endWith("user-100"))
//                .where(qMember.username.contain("user-100"))
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        members.forEach(m-> System.out.println(JSON.toJSONString(m)));
    }

    @Test
    void testDistinct(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .select(qMember.gender)
                .distinct()
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        members.forEach(m-> System.out.println(JSON.toJSONString(m)));
    }

    @Test
    void testBinary(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .where(qMember.username.eq("USER-0").binary())
                .build();
        Member member = memberMapper.selectOne(sqlQuery);
        System.out.println(JSON.toJSONString(member));
    }

    @Test
    void testNot(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .where(qMember.username.ne("user-0").not())
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        members.forEach(m-> System.out.println(JSON.toJSONString(m)));
    }

    @Test
    void testCondition(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .where(qMember.gender.eq("F")
                        .or(qMember.gender.eq("M")
                                .or(qMember.birthday.eq(new Date()))),
                        qMember.username.eq("user-0"))
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        members.forEach(m-> System.out.println(JSON.toJSONString(m)));
    }

    @Test
    void testGroupQuery(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .select(qMember.birthday,ExprUtil.count(1))
                .where(qMember.birthday.between(
                        DateUtil.parse("2008-08-00"),
                        DateUtil.parse("2008-09-00")))
                // 分组升序
                .groupBy(qMember.birthday.groupAsc())
                // 分组过滤
                .having(qMember.birthday.ne(DateUtil.parse("2008-08-00")))
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        members.forEach(m-> System.out.println(JSON.toJSONString(m)));
    }

    @Test
    void testJoinTableQuery(){
        QMember qMember = new QMember();
        QOrder qOrder = new QOrder("ord_");
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .innerJoin(qOrder,qMember.id.eq(qOrder.memberId))
                .select(qMember.id,qMember.username,qOrder.id,qOrder.createTime,qOrder.status)
                .where(qMember.username.eq("user-0"))
                .build();
        Member member = memberMapper.selectOne(sqlQuery);
        if(member!=null && member.getOrders()!=null) {
            member.getOrders().forEach(o -> System.out.println(JSON.toJSONString(o)));
        }
    }

    @Test
    void testSubQuery(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery1 = SqlBuilder.query(qMember)
                .select(qMember.id)
                .where(qMember.username.eq("user-1"))
                .build();

        QOrder qOrder = new QOrder();
        SqlQuery sqlQuery2 = SqlBuilder.query(qOrder)
                .select(qOrder.id, qOrder.createTime,qOrder.status)
                .where(qOrder.memberId.eq(sqlQuery1))
                .build();
        List<Order> orders = orderMapper.select(sqlQuery2);
        orders.forEach(o -> System.out.println(JSON.toJSONString(o)));
    }

    @Test
    void testSubQuery1(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery1 = SqlBuilder.query(qMember)
                .select(qMember.id)
                .where(qMember.username.in(Arrays.asList("user-3","user-4")))
                .build();

        QOrder qOrder = new QOrder();
        SqlQuery sqlQuery2 = SqlBuilder.query(qOrder)
                .select(qOrder.id, qOrder.createTime)
                .where(qOrder.memberId.in(sqlQuery1))
                .build();
        List<Order> orders = orderMapper.select(sqlQuery2);
        orders.forEach(o -> System.out.println(JSON.toJSONString(o)));
    }

    @Test
    void testSubQuery2(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery1 = SqlBuilder.query(qMember)
                .select(qMember.id)
                .where(qMember.username.in(Arrays.asList("user-3","user-4")))
                .build();

        QOrder qOrder = new QOrder();
        SqlQuery sqlQuery2 = SqlBuilder.query(qOrder)
                // join子查询，通过column方法来指定子查询内的查询字段。
                .innerJoin(sqlQuery1,sqlQuery1.column(qMember.id).eq(qOrder.memberId))
                .select(qOrder.id, qOrder.createTime)
                .build();
        List<Order> orders = orderMapper.select(sqlQuery2);
        orders.forEach(o -> System.out.println(JSON.toJSONString(o)));
    }

    @Test
    void testUnionQuery(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery1 = SqlBuilder.query(qMember)
                .where(qMember.username.eq("user-5"))
                .build();

        SqlQuery sqlQuery2 = SqlBuilder.query(qMember)
                .where(qMember.username.eq("user-6"))
                .build();

        List<Member> members = memberMapper.select(sqlQuery1.union(sqlQuery2));
        members.forEach(m -> System.out.println(JSON.toJSONString(m)));
    }


    @Test
    void testIfFunc(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .select(ExprUtil.ifExpr(qMember.gender.eq("F"),"女人","男人")
                        .alias(qMember.gender),qMember.username)
                .where(qMember.username.eq("user-7"))
                .build();
        Member member = memberMapper.selectOne(sqlQuery);
        System.out.println(JSON.toJSONString(member));
    }

    @Test
    void testExists(){
        QMember qMember = new QMember();

        SqlQuery query = SqlBuilder.query(qMember)
                .select(qMember.id)
                .where(qMember.username.eq("user-8"))
                .build();

        SqlQuery sqlQuery = SqlBuilder.query(null)
                .select(ExprUtil.ifExpr(ExprUtil.exists(query),true,false))
                .build();

        Boolean exists = memberMapper.exists(sqlQuery);
        System.out.println(exists);
    }

    /** 常量列 */
    @Test
    void testLiteral(){
        QMember qMember = new QMember();
        SqlQuery query = SqlBuilder.query(null)
                .select(ExprUtil.literal(1))
                .build();
        Integer integer = memberMapper.selectLiteral(query);
        System.out.println(integer);
    }

    @Test
    void testSwitchCaseFunc(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .select(ExprUtil.caseSwitch(qMember.gender,"F","女人","M","男人","不男不女")
                        .alias(qMember.gender),qMember.username)
                .page(2,5)
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        members.forEach(m -> System.out.println(JSON.toJSONString(m)));
    }

    @Test
    void testCaseFunc(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .select(ExprUtil.caseCondition(qMember.gender.eq("F"),"女人",
                                qMember.gender.eq("M"),"男人")
                        .alias(qMember.gender),qMember.username)
                .page(3,5)
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        members.forEach(m -> System.out.println(JSON.toJSONString(m)));
    }

    @Test
    void testConcatFunc(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .select(qMember.id,qMember.username)
                .where(ExprUtil.concat(qMember.username,"a").eq("user-0a"))
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        members.forEach(m -> System.out.println(JSON.toJSONString(m)));
    }

    @Test
    void testDateAdd(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .select(qMember.username,qMember.birthday)
                .where(ExprUtil.dateAdd(qMember.birthday, 5, TimeField.DAY)
                        .eq(DateUtil.parse("2008-8-15")))
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        members.forEach(m -> System.out.println(JSON.toJSONString(m)));
    }

    @Test
    void testOverFunc(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .select(qMember.username, ExprUtil.year(qMember.birthday).alias("year"),qMember.money,
                        ExprUtil.overRank(
                        Collections.singletonList(ExprUtil.year(qMember.birthday).groupAsc()),
                        Collections.singletonList(qMember.money.orderDesc())).alias("rankLevel")
                        )
                .where(qMember.birthday.between(
                        DateUtil.parse("2008-8-1"),
                        DateUtil.parse("2009-8-1")))
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        members.forEach(m -> System.out.println(JSON.toJSONString(m)));
    }


    @Test
    void testETCQuery(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .select(qMember.id,qMember.username)
                .where(qMember.username.eq("user-9"))
                .build();
        sqlQuery.setIsCte();
        SqlQuery etcQuery = SqlBuilder.query(sqlQuery).build();
        List<Member> members = memberMapper.select(etcQuery);
        members.forEach(m -> System.out.println(JSON.toJSONString(m)));
    }

    @Test
    void testRecursiveETCQuery(){
        QProductCategory qpCategory = new QProductCategory();
        SqlQuery sqlQuery1 = SqlBuilder.query(qpCategory)
                .where(qpCategory.name.eq("服装")).build();
        sqlQuery1.setIsCte();

        SqlQuery sqlQuery2 = SqlBuilder.query(qpCategory)
                .innerJoin(sqlQuery1,qpCategory.parentId.eq(sqlQuery1.column(qpCategory.id)))
                .select(qpCategory)
                .build();

        SqlQuery sqlQuery3 = sqlQuery1.unionRecursiveCte(sqlQuery2);
        List<ProductCategory> categories = productCategoryMapper.select(SqlBuilder.query(sqlQuery3).build());
        categories.forEach(pc -> System.out.println(JSON.toJSONString(pc)));
    }

    @Test
    void testPage(){
        QMember qMember = new QMember();
        SqlQuery query = SqlBuilder.query(qMember)
                .select(qMember.username, qMember.birthday)
                .where(qMember.birthday.between(
                        DateUtil.parse("2008-8-1"),
                        DateUtil.parse("2009-8-1")))
                .page(1,5)
                .build();
        List<Member> members = memberMapper.select(query);
        members.forEach(m -> System.out.println(JSON.toJSONString(m)));
    }

    @Test
    void testPage1(){
        QMember qMember = new QMember();
        SqlQuery query = SqlBuilder.query(qMember)
                .select(qMember.username, qMember.birthday)
                .where(qMember.birthday.between(
                        DateUtil.parse("2008-8-1"),
                        DateUtil.parse("2009-8-1")))
                .build();
        IPage<Member> page = new Page<>(1,5);
        IPage<Member> members = memberMapper.selectPage(page,query);
        members.getRecords().forEach(m -> System.out.println(JSON.toJSONString(m)));
    }

    @Test
    void testUpdate(){
        QOrder qOrder = new QOrder();
        QOrderItem qItem = new QOrderItem();
        SqlUpdate sqlUpdate = SqlBuilder.update(qOrder)
                .innerJoin(qItem, qItem.orderId.eq(qOrder.id))
                .where(qOrder.id.eq("1525754268026703886"))
                .set(qItem.price, ExprUtil.mul(qItem.price, 0.8))
                .set(qOrder.totalPrice, ExprUtil.mul(qOrder.totalPrice, 0.8))
                .build();
        Integer count = orderMapper.update(sqlUpdate);
        System.out.println(count);
    }

    @Test
    void getOrderInfo(){
        QOrder qOrder = new QOrder();
        QOrderItem qItem = new QOrderItem("item_");
        SqlQuery sqlQuery = SqlBuilder.query(qOrder)
                .innerJoin(qItem, qItem.orderId.eq(qOrder.id))
                .where(qOrder.id.eq("1525754268026703886"))
                .build();
        Order order = orderMapper.selectOne(sqlQuery);
        System.out.println(JSON.toJSONString(order));
    }
}