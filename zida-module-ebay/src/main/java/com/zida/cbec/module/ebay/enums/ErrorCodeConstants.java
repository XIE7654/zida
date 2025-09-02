package com.zida.cbec.module.ebay.enums;

import com.zida.cbec.framework.common.exception.ErrorCode;

/**
 * Ebay 错误码 Core 枚举类
 *
 * Ebay 系统，使用 1-110-000-000 段
 */
public interface ErrorCodeConstants {


    // ========== Temu 店铺授权信息 ==========
    ErrorCode STORE_NOT_EXISTS = new ErrorCode(1_110_000_000, "eBay店铺不存在");

    ErrorCode ORDER_NOT_EXISTS = new ErrorCode(1_110_001_000, "eBay订单不存在");
}
