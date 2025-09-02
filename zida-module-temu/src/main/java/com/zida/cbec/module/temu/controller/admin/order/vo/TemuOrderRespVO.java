package com.zida.cbec.module.temu.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - Temu订单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TemuOrderRespVO {

    @Schema(description = "主键自增ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27703")
    @ExcelProperty("主键自增ID")
    private Long id;

    @Schema(description = "店铺id", requiredMode = Schema.RequiredMode.REQUIRED, example = "22690")
    @ExcelProperty("店铺id")
    private Long shopId;

    @Schema(description = "订单号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("订单号")
    private String parentOrderSn;

    @Schema(description = "订单状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("订单状态")
    private Integer parentOrderStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private Long parentOrderTime;

    @Schema(description = "最晚发货时间")
    @ExcelProperty("最晚发货时间")
    private Long expectShipLatestTime;

    @Schema(description = "待处理状态结束时间")
    @ExcelProperty("待处理状态结束时间")
    private Long parentOrderPendingFinishTime;

    @Schema(description = "最晚送达时间")
    @ExcelProperty("最晚送达时间")
    private Long latestDeliveryTime;

    @Schema(description = "订单发货时间")
    @ExcelProperty("订单发货时间")
    private Long parentShippingTime;

    @Schema(description = "站点ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17273")
    @ExcelProperty("站点ID")
    private Long siteId;

    @Schema(description = "地区ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21289")
    @ExcelProperty("地区ID")
    private Long regionId;

    @Schema(description = "订单状态标签")
    @ExcelProperty("订单状态标签")
    private String parentOrderLabel;

    @Schema(description = "履约提示列表")
    @ExcelProperty("履约提示列表")
    private String fulfillmentWarning;

    @Schema(description = "用户实际支付运费是否为0")
    @ExcelProperty("用户实际支付运费是否为0")
    private Boolean hasShippingFee;

    @Schema(description = "配送渠道类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("配送渠道类型")
    private Integer shippingMethod;

    @Schema(description = "订单支付类型", example = "1")
    @ExcelProperty("订单支付类型")
    private String orderPaymentType;

    @Schema(description = "批次订单号列表")
    @ExcelProperty("批次订单号列表")
    private String batchOrderNumberList;

    @Schema(description = "子订单信息列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("子订单信息列表")
    private String orderList;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}