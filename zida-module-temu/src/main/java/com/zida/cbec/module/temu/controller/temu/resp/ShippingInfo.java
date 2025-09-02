package com.zida.cbec.module.temu.controller.temu.resp;

import lombok.Data;

/**
 * 订单配送信息响应类
 */
@Data
public class ShippingInfo {
    private boolean success;
    private Integer errorCode;
    private String errorMsg;
    private ShippingInfoResult result;

    @Data
    public static class ShippingInfoResult {
        private String receiptName;
        private String receiptAdditionalName;
        private String mobile;
        private String backupMobile;
        private String mail;
        private String taxCode;
        private String regionName1;
        private String regionName2;
        private String regionName3;
        private String regionName4;
        private String addressLine1;
        private String addressLine2;
        private String addressLine3;
        private String postCode;
        private String addressLineAll;
        private WarningInfo warning;
    }

    @Data
    public static class WarningInfo {
        private Boolean isRestriction;
        private Integer reason;
    }
}
