package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author lvqi
 * @since 2022-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("order_item")
@ToString
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("product_id")
    private Long productId;

    @TableField("number")
    private Integer number;

    @TableField("price")
    private BigDecimal price;

    @TableField("order_id")
    private String orderId;

    @TableField(exist = false)
    private Product product;
}
