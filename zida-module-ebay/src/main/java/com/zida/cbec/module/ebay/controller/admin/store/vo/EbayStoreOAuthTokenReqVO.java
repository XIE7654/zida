package com.zida.cbec.module.ebay.controller.admin.store.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "管理后台 - eBay OAuth 授权码交换请求 VO")
@Data
public class EbayStoreOAuthTokenReqVO {

    @Schema(description = "店铺编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @jakarta.validation.constraints.NotNull(message = "店铺编号不能为空")
    private Long storeId;

    @Schema(description = "授权码", requiredMode = Schema.RequiredMode.REQUIRED, example = "v%5E1.1%23i%5E1%23f%5E...")
    @NotBlank(message = "授权码不能为空")
    private String code;

} 