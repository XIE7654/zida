package com.zida.cbec.module.temu.dal.dataobject.productsku;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.zida.cbec.framework.mybatis.core.dataobject.BaseDO;

/**
 * Temu SKU明细 DO
 *
 * @author 自达源码
 */
@TableName("temu_product_sku")
@KeySequence("temu_product_sku_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemuProductSkuDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 店铺id
     */
    private Long storeId;
    /**
     * SKU唯一ID
     */
    private Long skuId;
    /**
     * 所属商品ID
     */
    private Long goodsId;
    /**
     * 所属商品名称
     */
    private String goodsName;
    /**
     * 平台SKU编码
     */
    private String skuSn;
    /**
     * SKU规格名称
     */
    private String specName;
    /**
     * 货币类型
     */
    private String currency;
    /**
     * SKU售价
     */
    private BigDecimal price;
    /**
     * 零售价金额
     */
    private BigDecimal retailPriceAmount;
    /**
     * 零售价货币
     */
    private String retailPriceCurrency;
    /**
     * SKU单独库存
     */
    private Long stock;
    /**
     * SKU缩略图URL
     */
    private String thumbUrl;
    /**
     * SKU创建时间
     */
    private Long crtTime;
    /**
     * SKU状态变更时间
     */
    private String skuStatusChangeTime;
    /**
     * SKU状态
     */
    private Integer status4vo;
    /**
     * SKU子状态
     */
    private Integer subStatus4vo;
    /**
     * SKU展示子状态
     */
    private Integer skuShowSubStatus4vo;
    /**
     * 所属商品是否在售
     */
    private Integer goodsIsOnSale;
    /**
     * 低流量标签
     */
    private Integer lowTrafficTag;
    /**
     * 流量受限标签
     */
    private Integer restrictedTrafficTag;
    /**
     * SKU重量
     */
    private BigDecimal weight;
    /**
     * 重量单位
     */
    private String weightUnit;
    /**
     * SKU长度
     */
    private BigDecimal length;
    /**
     * SKU宽度
     */
    private BigDecimal width;
    /**
     * SKU高度
     */
    private BigDecimal height;
    /**
     * 体积单位
     */
    private String volumeUnit;


}