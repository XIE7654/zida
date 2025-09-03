package com.zida.cbec.module.temu.dal.dataobject.order;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.zida.cbec.framework.mybatis.core.dataobject.BaseDO;

/**
 * Temu订单 DO
 *
 * @author 自达源码
 */
@TableName("temu_order")
@KeySequence("temu_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemuOrderDO extends BaseDO {

    /**
     * 主键自增ID
     */
    @TableId
    private Long id;
    /**
     * 店铺id
     */
    private Long storeId;
    /**
     * 订单号
     */
    private String parentOrderSn;
    /**
     * 订单状态
     */
    private Integer parentOrderStatus;
    /**
     * 创建时间
     */
    private Long parentOrderTime;
    /**
     * 最晚发货时间
     */
    private Long expectShipLatestTime;
    /**
     * 待处理状态结束时间
     */
    private Long parentOrderPendingFinishTime;
    /**
     * 最晚送达时间
     */
    private Long latestDeliveryTime;
    /**
     * 订单发货时间
     */
    private Long parentShippingTime;
    /**
     * 站点ID
     */
    private Long siteId;
    /**
     * 地区ID
     */
    private Long regionId;
    /**
     * 订单状态标签
     */
    private String parentOrderLabel;
    /**
     * 履约提示列表
     */
    private String fulfillmentWarning;
    /**
     * 用户实际支付运费是否为0
     */
    private Boolean hasShippingFee;
    /**
     * 配送渠道类型
     */
    private Integer shippingMethod;
    /**
     * 订单支付类型
     */
    private String orderPaymentType;
    /**
     * 批次订单号列表
     */
    private String batchOrderNumberList;
    /**
     * 子订单信息列表
     */
    private String orderList;


}