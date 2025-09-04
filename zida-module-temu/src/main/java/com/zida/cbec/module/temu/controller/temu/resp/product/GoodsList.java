package com.zida.cbec.module.temu.controller.temu.resp.product;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品列表响应类
 */
@Data
public class GoodsList {
    private boolean success;
    private Integer errorCode;
    private String errorMsg;
    private Result result;

    @Data
    public static class Result {
        private Integer pageNo;
        private Long total;
        private List<GoodsInfo> goodsList;
    }

    @Data
    public static class GoodsInfo {
        private Long goodsId;
        private String goodsName;
        private String specName;
        private String thumbUrl;
        private String outGoodsSn;
        private Integer status4VO;
        private Integer subStatus4VO;
        private String currency;
        private Long marketPrice;
        private PriceInfo listPrice;
        private String price;
        private PriceInfo retailPrice;
        private Integer quantity;
        private Long crtTime;
        private String goodsStatusChangeTime;
        private Long catId;
        private Long brandId;
        private Long trademarkId;
        private String costTemplateId;
        private Integer shipmentLimitSecond;
        private List<String> outSkuSnList;
        private List<Long> skuIdList;
        private List<SkuInfo> skuInfoList;
        private List<SpecInfo> specList;
        private Integer goodsShowSubStatus;
        private Integer lowTrafficTag;
        private Integer restrictedTrafficTag;
    }

    @Data
    public static class PriceInfo {
        private BigDecimal amount;
        private String currency;
    }

    @Data
    public static class SkuInfo {
        private Long skuId;
        private String skuSn;
        private List<SpecInfo> specList;
    }

    @Data
    public static class SpecInfo {
        private Long specId;
        private Long parentSpecId;
    }
}
