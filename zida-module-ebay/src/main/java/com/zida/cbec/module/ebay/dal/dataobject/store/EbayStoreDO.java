package com.zida.cbec.module.ebay.dal.dataobject.store;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.zida.cbec.framework.mybatis.core.dataobject.BaseDO;

/**
 * eBay店铺 DO
 *
 * @author 自达源码
 */
@TableName("ebay_store")
@KeySequence("ebay_store_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EbayStoreDO extends BaseDO {

    /**
     * 自增主键
     */
    @TableId
    private Long id;
    /**
     * 自定义店铺名称
     */
    private String shopName;
    /**
     * eBay登录账号
     */
    private String ebayAccount;
    /**
     * 所属站点（美国/英国/德国等）
     */
    private String site;
    /**
     * 是否保持零库存在线（1=是，0=否）
     */
    private Boolean keepZeroStockOnline;
    /**
     * 授权类型（API授权/手动授权等）
     */
    private String authorizationType;
    /**
     * 到期时间
     */
    private LocalDateTime apiKeyExpireTime;
    /**
     * 商品刊登额度上限
     */
    private Integer listingQuota;
    /**
     * 是否启用eBay管理支付（1=启用，0=未启用）
     */
    private Boolean isManagedPayment;
    /**
     * 令牌
     */
    private String accessToken;
    /**
     * 令牌过期时间
     */
    private LocalDateTime accessTokenExpiresIn;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 刷新令牌过期时间
     */
    private LocalDateTime refreshTokenExpiresIn;


}