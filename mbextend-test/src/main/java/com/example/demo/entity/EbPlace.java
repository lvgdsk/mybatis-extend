package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 地点表
 * </p>
 *
 * @author lvqi
 * @since 2022-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("eb_place")
public class EbPlace implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 区域表主键
     */
    @TableId(value = "EBPL_ID", type = IdType.ID_WORKER_STR)
    private Long ebplId;

    /**
     * 地点级别
     */
    @TableField("EBPL_TYPE")
    private String ebplType;

    /**
     * 代码
     */
    @TableField("EBPL_CODE")
    private String ebplCode;

    /**
     * 快速录入码
     */
    @TableField("EBPL_QUICK_CODE")
    private String ebplQuickCode;

    /**
     * 中文名称
     */
    @TableField("EBPL_NAME_CN")
    private String ebplNameCn;

    /**
     * 英文名称
     */
    @TableField("EBPL_NAME_EN")
    private String ebplNameEn;

    /**
     * 邮编
     */
    @TableField("EBPL_POST_CODE")
    private String ebplPostCode;

    /**
     * 是否可用:0是启用/1是停用
     */
    @TableField("EBPL_IS_ABLE")
    private String ebplIsAble;

    /**
     * 预留字段
     */
    @TableField("EBPL_SUBSTR1")
    private String ebplSubstr1;

    /**
     * ediflag  edi是否处理同步 1 已处理，0或空未处理，2处理失败
     */
    @TableField("EBPL_SUBSTR2")
    private String ebplSubstr2;

    /**
     * 预留字段
     */
    @TableField("EBPL_SUBSTR3")
    private String ebplSubstr3;

    /**
     * 预留字段
     */
    @TableField("EBPL_SUBSTR4")
    private String ebplSubstr4;

    /**
     * 预留字段
     */
    @TableField("EBPL_SUBSTR5")
    private String ebplSubstr5;

    /**
     * 预留字段
     */
    @TableField("EBPL_SUBSTR6")
    private String ebplSubstr6;

    /**
     * 预留字段
     */
    @TableField("EBPL_SUBSTR8")
    private String ebplSubstr8;

    /**
     * 预留字段
     */
    @TableField("EBPL_SUBSTR7")
    private String ebplSubstr7;

    /**
     * 预留字段
     */
    @TableField("EBPL_SUBDATE1")
    private LocalDateTime ebplSubdate1;

    /**
     * 预留字段
     */
    @TableField("EBPL_SUBDATE2")
    private LocalDateTime ebplSubdate2;

    /**
     * 预留字段
     */
    @TableField("EBPL_SUBDATE5")
    private LocalDateTime ebplSubdate5;

    /**
     * 预留字段
     */
    @TableField("EBPL_SUBDATE4")
    private LocalDateTime ebplSubdate4;

    /**
     * 预留字段
     */
    @TableField("EBPL_SUBDATE3")
    private LocalDateTime ebplSubdate3;

    /**
     * 预留字段
     */
    @TableField("EBPL_SUBNUM1")
    private Long ebplSubnum1;

    /**
     * 预留字段
     */
    @TableField("EBPL_SUBNUM2")
    private Long ebplSubnum2;

    /**
     * 预留字段
     */
    @TableField("EBPL_SUBNUM3")
    private Long ebplSubnum3;

    /**
     * 版本号
     */
    @TableField("REC_VER")
    private Integer recVer;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 最后修改时间
     */
    @TableField("MODIFY_TIME")
    private LocalDateTime modifyTime;

    /**
     * 最后修改人
     */
    @TableField("MODIFIER")
    private String modifier;

    /**
     * 创建人
     */
    @TableField("CREATOR")
    private String creator;

    /**
     * 所属基地代码
     */
    @TableField("EBPL_STATION_CODE")
    private String ebplStationCode;

    /**
     * 所属基地
     */
    @TableField("EBPL_STATION_NAME")
    private String ebplStationName;

    /**
     * 上级地点code
     */
    @TableField("EBPL_PARENT_PM_CODE")
    private String ebplParentPmCode;

    /**
     * 地区区号
     */
    @TableField("EBPL_AREA_NUMBER")
    private String ebplAreaNumber;

    /**
     * 停用时间/替换时间
     */
    @TableField("EBPL_PUB_REPLACE_DATE")
    private LocalDateTime ebplPubReplaceDate;

    /**
     * 替换代码
     */
    @TableField("EBPL_PUB_REPLACE")
    private String ebplPubReplace;

    /**
     * 经度
     */
    @TableField("EBPL_LONGITUDE")
    private String ebplLongitude;

    /**
     * 纬度
     */
    @TableField("EBPL_LATITUDE")
    private String ebplLatitude;

    /**
     * 逻辑删除:0-正常,1-删除
     */
    @TableField("REC_STATUS")
    private Integer recStatus;

    /**
     * 国家编码
     */
    @TableField("COUNTRY_CODE")
    private String countryCode;

    /**
     * 自定义编码
     */
    @TableField("FD_CODE")
    private String fdCode;

    /**
     * 最后一次同步时间
     */
    @TableField("FD_SYNC_TIME")
    private LocalDateTime fdSyncTime;

    /**
     * 是否生效 0-不生效 1-生效
     */
    @TableField("FD_IS_AVAILABLE")
    private Integer fdIsAvailable;


}
