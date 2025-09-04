package com.zida.cbec.module.temu.controller.admin.productsku.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - Temu SKU明细新增/修改 Request VO")
@Data
public class TemuProductSkuSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "30311")
    private Long id;

    @Schema(description = "店铺id", requiredMode = Schema.RequiredMode.REQUIRED, example = "22690")
    @NotNull(message = "店铺id不能为空")
    private Long storeId;

    @Schema(description = "SKU唯一ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2314")
    @NotNull(message = "SKU唯一ID不能为空")
    private Long skuId;

    @Schema(description = "所属商品ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22628")
    @NotNull(message = "所属商品ID不能为空")
    private Long goodsId;

    @Schema(description = "所属商品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "所属商品名称不能为空")
    private String goodsName;

    @Schema(description = "平台SKU编码")
    private String skuSn;

    @Schema(description = "SKU规格名称", example = "张三")
    private String specName;

    @Schema(description = "货币类型")
    private String currency;

    @Schema(description = "SKU售价", example = "32176")
    private BigDecimal price;

    @Schema(description = "零售价金额")
    private BigDecimal retailPriceAmount;

    @Schema(description = "零售价货币")
    private String retailPriceCurrency;

    @Schema(description = "SKU单独库存")
    private Long stock;

    @Schema(description = "SKU缩略图URL", example = "https://www.iocoder.cn")
    private String thumbUrl;

    @Schema(description = "SKU创建时间")
    private Long crtTime;

    @Schema(description = "SKU状态变更时间")
    private String skuStatusChangeTime;

    @Schema(description = "SKU状态")
    private Integer status4vo;

    @Schema(description = "SKU子状态")
    private Integer subStatus4vo;

    @Schema(description = "SKU展示子状态")
    private Integer skuShowSubStatus4vo;

    @Schema(description = "所属商品是否在售")
    private Integer goodsIsOnSale;

    @Schema(description = "低流量标签")
    private Integer lowTrafficTag;

    @Schema(description = "流量受限标签")
    private Integer restrictedTrafficTag;

    @Schema(description = "SKU重量")
    private BigDecimal weight;

    @Schema(description = "重量单位")
    private String weightUnit;

    @Schema(description = "SKU长度")
    private BigDecimal length;

    @Schema(description = "SKU宽度")
    private BigDecimal width;

    @Schema(description = "SKU高度")
    private BigDecimal height;

    @Schema(description = "体积单位")
    private String volumeUnit;

}