package com.zida.cbec.module.ebay.service.oauth;

import com.zida.cbec.module.ebay.controller.admin.store.vo.EbayStoreOAuthTokenRespVO;

/**
 * eBay OAuth 服务接口
 *
 * @author 云瑞源码
 */
public interface EbayOAuthService {

    /**
     * 使用授权码交换OAuth令牌
     *
     * @param storeId 店铺编号
     * @param code 授权码
     * @return OAuth令牌信息
     */
    EbayStoreOAuthTokenRespVO exchangeToken(String storeId, String code);

    /**
     * 使用刷新令牌刷新访问令牌
     *
     * @param storeId 店铺编号
     * @return 新的OAuth令牌信息
     */
    EbayStoreOAuthTokenRespVO refreshToken(String storeId);

} 