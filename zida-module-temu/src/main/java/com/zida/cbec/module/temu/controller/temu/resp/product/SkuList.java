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
        private Pagination pagination;
        private List<SkuInfo> skuList;
    }

    @Data
    public static class Pagination {
        private String nextToken;
        private String previousToken;
        private Long total;
    }

    @Data
    public static class SkuInfo {
        private String skuId;
        private String goodsId;
        private String goodsName;
        private String thumbUrl;
        private String specName;
        private String outSkuSn;
        private String outGoodsSn;
        private Long goodsCreateTime;
        private Long skuStatusChangeTime;
        private String skuStatus;
        private String skuSubStatus;
        private Integer catType;
        private String catId;
        private VolumeInfo volumeInfo;
        private WeightInfo weightInfo;
        private List<SpecInfo> specList;
        private Integer lowTrafficTag;
        private Integer restrictedTrafficTag;
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
