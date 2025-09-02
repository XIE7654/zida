package com.zida.cbec.module.ebay.controller.admin.store.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - eBay店铺 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EbayStoreRespVO {

    @Schema(description = "自增主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "13192")
    @ExcelProperty("自增主键")
    private Long id;

    @Schema(description = "自定义店铺名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("自定义店铺名称")
    private String shopName;

    @Schema(description = "eBay登录账号", example = "538")
    @ExcelProperty("eBay登录账号")
    private String ebayAccount;

    @Schema(description = "所属站点（美国/英国/德国等）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属站点（美国/英国/德国等）")
    private String site;

    @Schema(description = "是否保持零库存在线（1=是，0=否）")
    @ExcelProperty("是否保持零库存在线（1=是，0=否）")
    private Boolean keepZeroStockOnline;

    @Schema(description = "授权类型（API授权/手动授权等）", example = "2")
    @ExcelProperty("授权类型（API授权/手动授权等）")
    private String authorizationType;

    @Schema(description = "到期时间")
    @ExcelProperty("到期时间")
    private LocalDateTime apiKeyExpireTime;

    @Schema(description = "商品刊登额度上限")
    @ExcelProperty("商品刊登额度上限")
    private Integer listingQuota;

    @Schema(description = "是否启用eBay管理支付（1=启用，0=未启用）")
    @ExcelProperty("是否启用eBay管理支付（1=启用，0=未启用）")
    private Boolean isManagedPayment;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "令牌")
    @ExcelProperty("令牌")
    private String accessToken;

    @Schema(description = "令牌过期时间")
    @ExcelProperty("令牌过期时间")
    private LocalDateTime accessTokenExpiresIn;

    @Schema(description = "刷新令牌")
    @ExcelProperty("刷新令牌")
    private String refreshToken;

    @Schema(description = "刷新令牌过期时间")
    @ExcelProperty("刷新令牌过期时间")
    private LocalDateTime refreshTokenExpiresIn;

}