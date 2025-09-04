package com.zida.cbec.module.temu.controller.admin.productspu.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - Temu SPU Response VO")
@Data
@ExcelIgnoreUnannotated
public class TemuProductSpuRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "27764")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "商品唯一ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21385")
    @ExcelProperty("商品唯一ID")
    private Long goodsId;

    @Schema(description = "商品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("商品名称")
    private String goodsName;

    @Schema(description = "商家自定义商品编码")
    @ExcelProperty("商家自定义商品编码")
    private String outGoodsSn;

    @Schema(description = "商品所属类目ID", example = "29545")
    @ExcelProperty("商品所属类目ID")
    private Long catId;

    @Schema(description = "货币类型")
    @ExcelProperty("货币类型")
    private String currency;

    @Schema(description = "市场价格", example = "30916")
    @ExcelProperty("市场价格")
    private Long marketPrice;

    @Schema(description = "商品售价", example = "17781")
    @ExcelProperty("商品售价")
    private BigDecimal price;

    @Schema(description = "零售价金额")
    @ExcelProperty("零售价金额")
    private BigDecimal retailPriceAmount;

    @Schema(description = "零售价货币")
    @ExcelProperty("零售价货币")
    private String retailPriceCurrency;

    @Schema(description = "标价金额")
    @ExcelProperty("标价金额")
    private BigDecimal listPriceAmount;

    @Schema(description = "标价货币")
    @ExcelProperty("标价货币")
    private String listPriceCurrency;

    @Schema(description = "商品总库存")
    @ExcelProperty("商品总库存")
    private Long quantity;

    @Schema(description = "商品缩略图URL", example = "https://www.iocoder.cn")
    @ExcelProperty("商品缩略图URL")
    private String thumbUrl;

    @Schema(description = "商品创建时间")
    @ExcelProperty("商品创建时间")
    private Long crtTime;

    @Schema(description = "商品状态变更时间")
    @ExcelProperty("商品状态变更时间")
    private String goodsStatusChangeTime;

    @Schema(description = "商品展示子状态", example = "2")
    @ExcelProperty("商品展示子状态")
    private Integer goodsShowSubStatus;

    @Schema(description = "商品状态")
    @ExcelProperty("商品状态")
    private Integer status4vo;

    @Schema(description = "商品子状态")
    @ExcelProperty("商品子状态")
    private Integer subStatus4vo;

    @Schema(description = "低流量标签")
    @ExcelProperty("低流量标签")
    private Integer lowTrafficTag;

    @Schema(description = "流量受限标签")
    @ExcelProperty("流量受限标签")
    private Integer restrictedTrafficTag;

    @Schema(description = "成本模板ID", example = "22476")
    @ExcelProperty("成本模板ID")
    private String costTemplateId;

    @Schema(description = "发货时限")
    @ExcelProperty("发货时限")
    private Integer shipmentLimitSecond;

    @Schema(description = "商标ID", example = "11317")
    @ExcelProperty("商标ID")
    private Long trademarkId;

    @Schema(description = "品牌ID", example = "4498")
    @ExcelProperty("品牌ID")
    private Long brandId;

    @Schema(description = "商品规格名称", example = "李四")
    @ExcelProperty("商品规格名称")
    private String specName;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}