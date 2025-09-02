package com.zida.cbec.module.ebay.controller.admin.store.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - eBay OAuth 刷新令牌请求 VO")
@Data
public class EbayStoreRefreshTokenReqVO {

    @Schema(description = "店铺编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "店铺编号不能为空")
    private Long storeId;

} 