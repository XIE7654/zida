package com.zida.cbec.module.temu.controller.admin.store.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - Temu 店铺授权信息新增/修改 Request VO")
@Data
public class TemuStoreSaveReqVO {

    @Schema(description = "自增主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "22243")
    private Long id;

    @Schema(description = "店铺类型：1-全托管店铺，2-半托管店铺，3-本土店铺", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "店铺类型：1-全托管店铺，2-半托管店铺，3-本土店铺不能为空")
    private Integer storeType;

    @Schema(description = "自定义店铺名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "自定义店铺名称不能为空")
    private String storeName;

    @Schema(description = "产品库存 Token")
    private String productStockToken;

    @Schema(description = "合规 API Token")
    private String complianceApiToken;

    @Schema(description = "美国订单发货 Token")
    private String orderShippingTokenUs;

    @Schema(description = "欧区订单发货 Token")
    private String orderShippingTokenEu;

    @Schema(description = "全球订单发货 Token")
    private String orderShippingTokenGlobal;

    @Schema(description = "授权token")
    private String accessToken;

    @Schema(description = "店铺币种，如 CNY、USD(香港主体店铺) 等")
    private String storeCurrency;

    @Schema(description = "店铺站点（本土店铺用，如美国、法国等）")
    private String storeSite;

    @Schema(description = "授权状态：0-未授权，1-已授权，2-已过期，3-已取消", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer authStatus;

    @Schema(description = "授权时间")
    private LocalDateTime authTime;

    @Schema(description = "API权限列表，JSON格式存储")
    private String apiScopeList;

    @Schema(description = "应用订阅事件列表，JSON格式存储")
    private String appSubscribeEventCodeList;

    @Schema(description = "应用订阅状态：0-未订阅，1-已订阅", example = "1")
    private Integer appSubscribeStatus;

    @Schema(description = "授权事件代码列表，JSON格式存储")
    private String authEventCodeList;

    @Schema(description = "过期时间戳，如：1765634102")
    private Long expiredTime;

    @Schema(description = "商城ID，如：1024", example = "10775")
    private Long mallId;

    @Schema(description = "地区id", example = "4390")
    private Integer regionId;

    @Schema(description = "商店类型", example = "1")
    private Integer mallType;

}