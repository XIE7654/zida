
package com.zida.cbec.module.temu.controller.temu.resp;

import lombok.Data;

import java.util.List;

/**
 * AccessToken信息响应类
 */
@Data
public class AccessTokenInfo {
    private boolean success;
    private Integer errorCode;
    private String errorMsg;
    private Result result;

    @Data
    public static class Result {
        private Long expiredTime;
        private Long mallId;
        private Integer mallType;
        private Integer regionId;
        private List<String> apiScopeList;
        private List<String> appSubscribeEventCodeList;
        private Integer appSubscribeStatus;
        private List<AuthEventCode> authEventCodeList;
    }

    @Data
    public static class AuthEventCode {
        private String eventCode;
        private Integer permitsStatus;
    }
}
