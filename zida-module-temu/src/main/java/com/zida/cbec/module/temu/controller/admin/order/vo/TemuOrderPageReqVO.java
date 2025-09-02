package com.zida.cbec.module.temu.controller.admin.order.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.zida.cbec.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.zida.cbec.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - Temu订单分页 Request VO")
@Data
public class TemuOrderPageReqVO extends PageParam {

    @Schema(description = "店铺id", example = "22690")
    private Long shopId;

    @Schema(description = "订单号")
    private String parentOrderSn;

    @Schema(description = "订单状态", example = "1")
    private Integer parentOrderStatus;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Long[] parentOrderTime;

    @Schema(description = "最晚发货时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Long[] expectShipLatestTime;

    @Schema(description = "待处理状态结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Long[] parentOrderPendingFinishTime;

    @Schema(description = "最晚送达时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Long[] latestDeliveryTime;

    @Schema(description = "订单发货时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Long[] parentShippingTime;

    @Schema(description = "站点ID", example = "17273")
    private Long siteId;

    @Schema(description = "地区ID", example = "21289")
    private Long regionId;

    @Schema(description = "订单状态标签")
    private String parentOrderLabel;

    @Schema(description = "履约提示列表")
    private String fulfillmentWarning;

    @Schema(description = "用户实际支付运费是否为0")
    private Boolean hasShippingFee;

    @Schema(description = "配送渠道类型")
    private Integer shippingMethod;

    @Schema(description = "订单支付类型", example = "1")
    private String orderPaymentType;

    @Schema(description = "批次订单号列表")
    private String batchOrderNumberList;

    @Schema(description = "子订单信息列表")
    private String orderList;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}