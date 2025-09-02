package com.zida.cbec.module.ebay.controller.admin.store.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.zida.cbec.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.zida.cbec.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - eBay店铺分页 Request VO")
@Data
public class EbayStorePageReqVO extends PageParam {

    @Schema(description = "自定义店铺名称", example = "王五")
    private String shopName;

    @Schema(description = "eBay登录账号", example = "538")
    private String ebayAccount;

    @Schema(description = "所属站点（美国/英国/德国等）")
    private String site;

    @Schema(description = "是否保持零库存在线（1=是，0=否）")
    private Boolean keepZeroStockOnline;

    @Schema(description = "授权类型（API授权/手动授权等）", example = "2")
    private String authorizationType;

    @Schema(description = "到期时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] apiKeyExpireTime;

    @Schema(description = "商品刊登额度上限")
    private Integer listingQuota;

    @Schema(description = "是否启用eBay管理支付（1=启用，0=未启用）")
    private Boolean isManagedPayment;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "令牌")
    private String accessToken;

    @Schema(description = "令牌过期时间")
    private LocalDateTime accessTokenExpiresIn;

    @Schema(description = "刷新令牌")
    private String refreshToken;

    @Schema(description = "刷新令牌过期时间")
    private LocalDateTime refreshTokenExpiresIn;

}