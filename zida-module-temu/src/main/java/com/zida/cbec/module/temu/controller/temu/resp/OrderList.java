package com.zida.cbec.module.temu.controller.temu.resp;

import lombok.Data;

import java.util.List;

/**
 * 订单列表响应类
 */
@Data
public class OrderList {
    private boolean success;
    private Integer errorCode;
    private String errorMsg;
    private Result result;

    @Data
    public static class Result {
        private List<PageItem> pageItems;
        private Integer totalItemNum;
    }

    @Data
    public static class PageItem {
        private ParentOrderMap parentOrderMap;
        private List<Order> orderList;
    }

    @Data
    public static class ParentOrderMap {
        private String parentOrderSn;
        private Integer parentOrderStatus;
        private Long parentOrderTime;
        private Long expectShipLatestTime;
        private Long parentOrderPendingFinishTime;
        private Long latestDeliveryTime;
        private Long parentShippingTime;
        private Long siteId;
        private Long regionId;
        private List<ParentOrderLabel> parentOrderLabel;
        private List<String> fulfillmentWarning;
        private Boolean hasShippingFee;
        private Long updateTime;
        private Integer shippingMethod;
        private String orderPaymentType;
        private List<String> batchOrderNumberList;
    }

    @Data
    public static class ParentOrderLabel {
        private String name;
        private Integer value;
    }

    @Data
    public static class Order {
        private String orderSn;
        private Integer quantity;
        private Integer canceledQuantityBeforeShipment;
        private Integer originalOrderQuantity;
        private Long goodsId;
        private Long skuId;
        private String spec;
        private String originalSpecName;
        private String thumbUrl;
        private String goodsName;
        private String originalGoodsName;
        private Integer orderStatus;
        private List<Product> productList;
        private List<OrderLabel> orderLabel;
        private List<String> fulfillmentWarning;
        private String fulfillmentType;
        private String inventoryDeductionWarehouseId;
        private String inventoryDeductionWarehouseName;
        private String orderPaymentType;
        private Boolean isCancelledDuringPending;
    }

    @Data
    public static class Product {
        private Long productId;
        private Long productSkuId;
        private Long soldFactor;
        private String extCode;
    }

    @Data
    public static class OrderLabel {
        private String name;
        private Integer value;
    }
}
