package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author lvqi
 * @since 2022-05-05
 */
@Data
@TableName("`order`")
@ToString
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.NONE)
    private String id;

    @TableField("create_time")
    private Date createTime;

    @TableField("member_id")
    private Long memberId;

    @TableField("total_price")
    private BigDecimal totalPrice;

    /**
     *
     * 1:新建，2：已付款，3：运输中，4：派送中，5：已签收，6：确认收货，7：退货中，8：退货完成
     */
    @TableField("status")
    private Integer status;

    @TableField("address_name")
    private String addressName;

    @TableField("address_code")
    private String addressCode;

    @TableField(exist = false)
    private List<OrderItem> orderItems;
}
