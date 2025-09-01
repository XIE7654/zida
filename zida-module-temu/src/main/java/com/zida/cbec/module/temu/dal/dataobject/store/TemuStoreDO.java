package com.zida.cbec.module.temu.dal.dataobject.store;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.zida.cbec.framework.mybatis.core.dataobject.BaseDO;

/**
 * Temu 店铺授权信息 DO
 *
 * @author 自达源码
 */
@TableName("temu_store")
@KeySequence("temu_store_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemuStoreDO extends BaseDO {

    /**
     * 自增主键
     */
    @TableId
    private Long id;
    /**
     * 店铺类型：1-全托管店铺，2-半托管店铺，3-本土店铺
     */
    private Integer shopType;
    /**
     * 自定义店铺名称
     */
    private String shopName;
    /**
     * 产品库存 Token
     */
    private String productStockToken;
    /**
     * 合规 API Token
     */
    private String complianceApiToken;
    /**
     * 美国订单发货 Token
     */
    private String orderShippingTokenUs;
    /**
     * 欧区订单发货 Token
     */
    private String orderShippingTokenEu;
    /**
     * 全球订单发货 Token
     */
    private String orderShippingTokenGlobal;
    /**
     * 授权token
     */
    private String accessToken;
    /**
     * 店铺币种，如 CNY、USD(香港主体店铺) 等
     */
    private String shopCurrency;
    /**
     * 店铺站点（本土店铺用，如美国、法国等）
     */
    private String shopSite;
    /**
     * 授权状态：0-未授权，1-已授权，2-已过期，3-已取消
     */
    private Integer authStatus;
    /**
     * 授权时间
     */
    private LocalDateTime authTime;
    /**
     * API权限列表，JSON格式存储
     */
    private String apiScopeList;
    /**
     * 应用订阅事件列表，JSON格式存储
     */
    private String appSubscribeEventCodeList;
    /**
     * 应用订阅状态：0-未订阅，1-已订阅
     */
    private Integer appSubscribeStatus;
    /**
     * 授权事件代码列表，JSON格式存储
     */
    private String authEventCodeList;
    /**
     * 过期时间戳，如：1765634102
     */
    private Long expiredTime;
    /**
     * 商城ID，如：1024
     */
    private Long mallId;
    /**
     * 地区id
     */
    private Integer regionId;
    /**
     * 商店类型
     */
    private Integer mallType;


}