package com.zida.cbec.module.temu.controller.admin.productsku.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - Temu SKU明细 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TemuProductSkuRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "30311")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "店铺id", requiredMode = Schema.RequiredMode.REQUIRED, example = "22690")
    @ExcelProperty("店铺id")
    private Long storeId;

    @Schema(description = "SKU唯一ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2314")
    @ExcelProperty("SKU唯一ID")
    private Long skuId;

    @Schema(description = "所属商品ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22628")
    @ExcelProperty("所属商品ID")
    private Long goodsId;

    @Schema(description = "所属商品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("所属商品名称")
    private String goodsName;

    @Schema(description = "平台SKU编码")
    @ExcelProperty("平台SKU编码")
    private String skuSn;

    @Schema(description = "SKU规格名称", example = "张三")
    @ExcelProperty("SKU规格名称")
    private String specName;

    @Schema(description = "货币类型")
    @ExcelProperty("货币类型")
    private String currency;

    @Schema(description = "SKU售价", example = "32176")
    @ExcelProperty("SKU售价")
    private BigDecimal price;

    @Schema(description = "零售价金额")
    @ExcelProperty("零售价金额")
    private BigDecimal retailPriceAmount;

    @Schema(description = "零售价货币")
    @ExcelProperty("零售价货币")
    private String retailPriceCurrency;

    @Schema(description = "SKU单独库存")
    @ExcelProperty("SKU单独库存")
    private Long stock;

    @Schema(description = "SKU缩略图URL", example = "https://www.iocoder.cn")
    @ExcelProperty("SKU缩略图URL")
    private String thumbUrl;

    @Schema(description = "SKU创建时间")
    @ExcelProperty("SKU创建时间")
    private Long crtTime;

    @Schema(description = "SKU状态变更时间")
    @ExcelProperty("SKU状态变更时间")
    private String skuStatusChangeTime;

    @Schema(description = "SKU状态")
    @ExcelProperty("SKU状态")
    private Integer status4vo;

    @Schema(description = "SKU子状态")
    @ExcelProperty("SKU子状态")
    private Integer subStatus4vo;

    @Schema(description = "SKU展示子状态")
    @ExcelProperty("SKU展示子状态")
    private Integer skuShowSubStatus4vo;

    @Schema(description = "所属商品是否在售")
    @ExcelProperty("所属商品是否在售")
    private Integer goodsIsOnSale;

    @Schema(description = "低流量标签")
    @ExcelProperty("低流量标签")
    private Integer lowTrafficTag;

    @Schema(description = "流量受限标签")
    @ExcelProperty("流量受限标签")
    private Integer restrictedTrafficTag;

    @Schema(description = "SKU重量")
    @ExcelProperty("SKU重量")
    private BigDecimal weight;

    @Schema(description = "重量单位")
    @ExcelProperty("重量单位")
    private String weightUnit;

    @Schema(description = "SKU长度")
    @ExcelProperty("SKU长度")
    private BigDecimal length;

    @Schema(description = "SKU宽度")
    @ExcelProperty("SKU宽度")
    private BigDecimal width;

    @Schema(description = "SKU高度")
    @ExcelProperty("SKU高度")
    private BigDecimal height;

    @Schema(description = "体积单位")
    @ExcelProperty("体积单位")
    private String volumeUnit;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}