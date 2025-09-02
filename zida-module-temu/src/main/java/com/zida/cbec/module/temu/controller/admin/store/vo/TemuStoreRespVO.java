package com.zida.cbec.module.temu.controller.admin.store.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - Temu 店铺授权信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TemuStoreRespVO {

    @Schema(description = "自增主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "22243")
    @ExcelProperty("自增主键")
    private Long id;

    @Schema(description = "店铺类型：1-全托管店铺，2-半托管店铺，3-本土店铺", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("店铺类型：1-全托管店铺，2-半托管店铺，3-本土店铺")
    private Integer storeType;

    @Schema(description = "自定义店铺名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("自定义店铺名称")
    private String storeName;

    @Schema(description = "产品库存 Token")
    @ExcelProperty("产品库存 Token")
    private String productStockToken;

    @Schema(description = "合规 API Token")
    @ExcelProperty("合规 API Token")
    private String complianceApiToken;

    @Schema(description = "美国订单发货 Token")
    @ExcelProperty("美国订单发货 Token")
    private String orderShippingTokenUs;

    @Schema(description = "欧区订单发货 Token")
    @ExcelProperty("欧区订单发货 Token")
    private String orderShippingTokenEu;

    @Schema(description = "全球订单发货 Token")
    @ExcelProperty("全球订单发货 Token")
    private String orderShippingTokenGlobal;

    @Schema(description = "授权token")
    @ExcelProperty("授权token")
    private String accessToken;

    @Schema(description = "店铺币种，如 CNY、USD(香港主体店铺) 等")
    @ExcelProperty("店铺币种，如 CNY、USD(香港主体店铺) 等")
    private String storeCurrency;

    @Schema(description = "店铺站点（本土店铺用，如美国、法国等）")
    @ExcelProperty("店铺站点（本土店铺用，如美国、法国等）")
    private String storeSite;

    @Schema(description = "授权状态：0-未授权，1-已授权，2-已过期，3-已取消", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("授权状态：0-未授权，1-已授权，2-已过期，3-已取消")
    private Integer authStatus;

    @Schema(description = "授权时间")
    @ExcelProperty("授权时间")
    private LocalDateTime authTime;

    @Schema(description = "API权限列表，JSON格式存储")
    @ExcelProperty("API权限列表，JSON格式存储")
    private String apiScopeList;

    @Schema(description = "应用订阅事件列表，JSON格式存储")
    @ExcelProperty("应用订阅事件列表，JSON格式存储")
    private String appSubscribeEventCodeList;

    @Schema(description = "应用订阅状态：0-未订阅，1-已订阅", example = "1")
    @ExcelProperty("应用订阅状态：0-未订阅，1-已订阅")
    private Integer appSubscribeStatus;

    @Schema(description = "授权事件代码列表，JSON格式存储")
    @ExcelProperty("授权事件代码列表，JSON格式存储")
    private String authEventCodeList;

    @Schema(description = "过期时间戳，如：1765634102")
    @ExcelProperty("过期时间戳，如：1765634102")
    private Long expiredTime;

    @Schema(description = "商城ID，如：1024", example = "10775")
    @ExcelProperty("商城ID，如：1024")
    private Long mallId;

    @Schema(description = "地区id", example = "4390")
    @ExcelProperty("地区id")
    private Integer regionId;

    @Schema(description = "商店类型", example = "1")
    @ExcelProperty("商店类型")
    private Integer mallType;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}