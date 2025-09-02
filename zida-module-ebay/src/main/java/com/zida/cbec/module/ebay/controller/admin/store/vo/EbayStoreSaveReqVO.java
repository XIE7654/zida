package com.zida.cbec.module.ebay.controller.admin.store.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - eBay店铺新增/修改 Request VO")
@Data
public class EbayStoreSaveReqVO {

    @Schema(description = "自增主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "13192")
    private Long id;

    @Schema(description = "自定义店铺名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "自定义店铺名称不能为空")
    private String shopName;

    @Schema(description = "eBay登录账号", example = "538")
    private String ebayAccount;

    @Schema(description = "所属站点（美国/英国/德国等）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属站点（美国/英国/德国等）不能为空")
    private String site;

    @Schema(description = "是否保持零库存在线（1=是，0=否）")
    private Boolean keepZeroStockOnline;

    @Schema(description = "授权类型（API授权/手动授权等）", example = "2")
    private String authorizationType;

    @Schema(description = "到期时间")
    private LocalDateTime apiKeyExpireTime;

    @Schema(description = "商品刊登额度上限")
    private Integer listingQuota;

    @Schema(description = "是否启用eBay管理支付（1=启用，0=未启用）")
    private Boolean isManagedPayment;

    @Schema(description = "令牌")
    private String accessToken;

    @Schema(description = "令牌过期时间")
    private LocalDateTime accessTokenExpiresIn;

    @Schema(description = "刷新令牌")
    private String refreshToken;

    @Schema(description = "刷新令牌过期时间")
    private LocalDateTime refreshTokenExpiresIn;

}