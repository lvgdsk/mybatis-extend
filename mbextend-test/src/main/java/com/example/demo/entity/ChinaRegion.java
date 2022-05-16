package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lvqi
 * @since 2022-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("china_region")
public class ChinaRegion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("pid")
    private Integer pid;

    @TableField("region_name")
    private String regionName;

    @TableField("type")
    private Integer type;

    @TableField("post_code")
    private String postCode;

    @TableField("region_code")
    private String regionCode;

    @TableField("longitude")
    private String longitude;

    @TableField("latitude")
    private String latitude;
}
