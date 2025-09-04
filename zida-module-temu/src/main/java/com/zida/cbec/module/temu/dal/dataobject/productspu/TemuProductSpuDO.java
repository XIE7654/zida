package com.zida.cbec.module.temu.dal.dataobject.productspu;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.zida.cbec.framework.mybatis.core.dataobject.BaseDO;

/**
 * Temu SPU DO
 *
 * @author 自达源码
 */
@TableName("temu_product_spu")
@KeySequence("temu_product_spu_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemuProductSpuDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 商品唯一ID
     */
    private Long goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商家自定义商品编码
     */
    private String outGoodsSn;
    /**
     * 商品所属类目ID
     */
    private Long catId;
    /**
     * 货币类型
     */
    private String currency;
    /**
     * 市场价格
     */
    private BigDecimal marketPrice;
    /**
     * 商品售价
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
     * 标价金额
     */
    private BigDecimal listPriceAmount;
    /**
     * 标价货币
     */
    private String listPriceCurrency;
    /**
     * 商品总库存
     */
    private Long quantity;
    /**
     * 商品缩略图URL
     */
    private String thumbUrl;
    /**
     * 商品创建时间
     */
    private Long crtTime;
    /**
     * 商品状态变更时间
     */
    private String goodsStatusChangeTime;
    /**
     * 商品展示子状态
     */
    private Integer goodsShowSubStatus;
    /**
     * 商品状态
     */
    private Integer status4vo;
    /**
     * 商品子状态
     */
    private Integer subStatus4vo;
    /**
     * 低流量标签
     */
    private Integer lowTrafficTag;
    /**
     * 流量受限标签
     */
    private Integer restrictedTrafficTag;
    /**
     * 成本模板ID
     */
    private String costTemplateId;
    /**
     * 发货时限
     */
    private Integer shipmentLimitSecond;
    /**
     * 商标ID
     */
    private Long trademarkId;
    /**
     * 品牌ID
     */
    private Long brandId;
    /**
     * 商品规格名称
     */
    private String specName;


}