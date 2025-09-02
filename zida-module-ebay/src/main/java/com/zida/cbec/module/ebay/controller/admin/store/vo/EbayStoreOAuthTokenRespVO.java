package com.zida.cbec.module.ebay.controller.admin.store.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - eBay OAuth 令牌响应 VO")
@Data
public class EbayStoreOAuthTokenRespVO {

    @Schema(description = "访问令牌", example = "v^1.1#i^1#p^3#r^1...XzMjRV4xMjg0")
    private String accessToken;

    @Schema(description = "令牌过期时间（秒）", example = "7200")
    private Integer expiresIn;

    @Schema(description = "刷新令牌", example = "v^1.1#i^1#p^3#r^1...zYjRV4xMjg0")
    private String refreshToken;

    @Schema(description = "刷新令牌过期时间（秒）", example = "47304000")
    private Integer refreshTokenExpiresIn;

    @Schema(description = "令牌类型", example = "User Access Token")
    private String tokenType;

    @Schema(description = "令牌过期时间")
    private LocalDateTime accessTokenExpireTime;

    @Schema(description = "刷新令牌过期时间")
    private LocalDateTime refreshTokenExpireTime;

} 