package com.zida.cbec.module.temu.controller.admin.productspu.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - Temu SPU新增/修改 Request VO")
@Data
public class TemuProductSpuSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "27764")
    private Long id;

    @Schema(description = "商品唯一ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21385")
    @NotNull(message = "商品唯一ID不能为空")
    private Long goodsId;

    @Schema(description = "商品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "商品名称不能为空")
    private String goodsName;

    @Schema(description = "商家自定义商品编码")
    private String outGoodsSn;

    @Schema(description = "商品所属类目ID", example = "29545")
    private Long catId;

    @Schema(description = "货币类型")
    private String currency;

    @Schema(description = "市场价格", example = "30916")
    private BigDecimal marketPrice;

    @Schema(description = "商品售价", example = "17781")
    private BigDecimal price;

    @Schema(description = "零售价金额")
    private BigDecimal retailPriceAmount;

    @Schema(description = "零售价货币")
    private String retailPriceCurrency;

    @Schema(description = "标价金额")
    private BigDecimal listPriceAmount;

    @Schema(description = "标价货币")
    private String listPriceCurrency;

    @Schema(description = "商品总库存")
    private Long quantity;

    @Schema(description = "商品缩略图URL", example = "https://www.iocoder.cn")
    private String thumbUrl;

    @Schema(description = "商品创建时间")
    private Long crtTime;

    @Schema(description = "商品状态变更时间")
    private String goodsStatusChangeTime;

    @Schema(description = "商品展示子状态", example = "2")
    private Integer goodsShowSubStatus;

    @Schema(description = "商品状态")
    private Integer status4vo;

    @Schema(description = "商品子状态")
    private Integer subStatus4vo;

    @Schema(description = "低流量标签")
    private Integer lowTrafficTag;

    @Schema(description = "流量受限标签")
    private Integer restrictedTrafficTag;

    @Schema(description = "成本模板ID", example = "22476")
    private String costTemplateId;

    @Schema(description = "发货时限")
    private Integer shipmentLimitSecond;

    @Schema(description = "商标ID", example = "11317")
    private Long trademarkId;

    @Schema(description = "品牌ID", example = "4498")
    private Long brandId;

    @Schema(description = "商品规格名称", example = "李四")
    private String specName;

}