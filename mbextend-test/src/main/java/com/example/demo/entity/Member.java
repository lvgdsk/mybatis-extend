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
@EqualsAndHashCode(callSuper = false)
@TableName("member")
@ToString
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String username;

    @TableField("phone")
    private String phone;

    @TableField("birthday")
    private Date birthday;

    @TableField("gender")
    private String gender;

    @TableField("money")
    private BigDecimal money;

    @TableField("address_code")
    private String addressCode;

    @TableField("address_name")
    private String addressName;

    @TableField(exist = false)
    private List<Order> orders;

    @TableField(exist = false)
    private Integer year;

    @TableField(exist = false)
    private Integer rankLevel;
}
