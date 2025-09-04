package com.zida.cbec.module.temu.enums;

import com.zida.cbec.framework.common.exception.ErrorCode;

/**
 * Temu 错误码 Core 枚举类
 *
 * Temu 系统，使用 1-100-000-000 段
 */
public interface ErrorCodeConstants {


    // ========== Temu 店铺授权信息 ==========
    ErrorCode STORE_NOT_EXISTS = new ErrorCode(1_100_000_000, "Temu 店铺授权信息不存在");

    ErrorCode ORDER_NOT_EXISTS = new ErrorCode(1_100_001_000, "Temu订单不存在");

    ErrorCode PRODUCT_SPU_NOT_EXISTS = new ErrorCode(1_100_002_000, "Temu SPU不存在");

    ErrorCode PRODUCT_SKU_NOT_EXISTS = new ErrorCode(1_100_003_000, "Temu SKU明细不存在");
}
