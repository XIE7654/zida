package com.zida.cbec.module.temu.controller.temu.resp.product;

import lombok.Data;

import java.util.List;

/**
 * SKU列表响应类
 */
@Data
public class SkuList {
    private boolean success;
    private Integer errorCode;
    private String errorMsg;
    private Result result;

    @Data
    public static class Result {
        private Integer pageNo;
        private Long total;
        private List<SkuInfo> skuList;
    }

    @Data
    public static class SkuInfo {
        private String goodsName;
        private String specName;
        private String thumbUrl;
        private Long goodsId;
        private Long skuId;
        private String skuSn;
        private Integer stock;
        private String price;
        private RetailPrice retailPrice;
        private Long crtTime;
        private Integer status4VO;
        private Integer subStatus4VO;
        private Integer goodsIsOnSale;
        private String currency;
        private String skuStatusChangeTime;
        private VolumeInfo volumeInfo;
        private WeightInfo weightInfo;
        private Integer skuShowSubStatus4VO;
        private List<SpecInfo> specList;
        private Integer lowTrafficTag;
        private Integer restrictedTrafficTag;
    }

    @Data
    public static class RetailPrice {
        private String amount;
        private String currency;
    }

    @Data
    public static class VolumeInfo {
        private String length;
        private String width;
        private String height;
        private String unit;
    }

    @Data
    public static class WeightInfo {
        private String weight;
        private String unit;
    }

    @Data
    public static class SpecInfo {
        private Long specId;
        private Long parentSpecId;
    }
}
