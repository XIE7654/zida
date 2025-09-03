package com.zida.cbec.module.temu.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - Temu订单新增/修改 Request VO")
@Data
public class TemuOrderSaveReqVO {

    @Schema(description = "主键自增ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27703")
    private Long id;

    @Schema(description = "店铺id", requiredMode = Schema.RequiredMode.REQUIRED, example = "22690")
    @NotNull(message = "店铺id不能为空")
    private Long storeId;

    @Schema(description = "订单号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "订单号不能为空")
    private String parentOrderSn;

    @Schema(description = "订单状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "订单状态不能为空")
    private Integer parentOrderStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间不能为空")
    private Long parentOrderTime;

    @Schema(description = "最晚发货时间")
    private Long expectShipLatestTime;

    @Schema(description = "待处理状态结束时间")
    private Long parentOrderPendingFinishTime;

    @Schema(description = "最晚送达时间")
    private Long latestDeliveryTime;

    @Schema(description = "订单发货时间")
    private Long parentShippingTime;

    @Schema(description = "站点ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17273")
    @NotNull(message = "站点ID不能为空")
    private Long siteId;

    @Schema(description = "地区ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21289")
    @NotNull(message = "地区ID不能为空")
    private Long regionId;

    @Schema(description = "订单状态标签")
    private String parentOrderLabel;

    @Schema(description = "履约提示列表")
    private String fulfillmentWarning;

    @Schema(description = "用户实际支付运费是否为0")
    private Boolean hasShippingFee;

    @Schema(description = "配送渠道类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "配送渠道类型不能为空")
    private Integer shippingMethod;

    @Schema(description = "订单支付类型", example = "1")
    private String orderPaymentType;

    @Schema(description = "批次订单号列表")
    private String batchOrderNumberList;

    @Schema(description = "子订单信息列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "子订单信息列表不能为空")
    private String orderList;

}