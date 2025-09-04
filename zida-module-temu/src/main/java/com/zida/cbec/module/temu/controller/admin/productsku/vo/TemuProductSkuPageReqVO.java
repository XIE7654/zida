package com.zida.cbec.module.temu.controller.admin.productsku.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.zida.cbec.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.zida.cbec.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - Temu SKU明细分页 Request VO")
@Data
public class TemuProductSkuPageReqVO extends PageParam {

    @Schema(description = "店铺id", example = "22690")
    private Long storeId;

    @Schema(description = "SKU唯一ID", example = "2314")
    private Long skuId;

    @Schema(description = "所属商品ID", example = "22628")
    private Long goodsId;

    @Schema(description = "所属商品名称", example = "李四")
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
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Long[] crtTime;

    @Schema(description = "SKU状态变更时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private String[] skuStatusChangeTime;

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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}