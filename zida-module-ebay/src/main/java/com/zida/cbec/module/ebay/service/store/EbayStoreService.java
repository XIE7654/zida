package com.zida.cbec.module.ebay.service.store;

import java.util.*;
import jakarta.validation.*;
import com.zida.cbec.module.ebay.controller.admin.store.vo.*;
import com.zida.cbec.module.ebay.dal.dataobject.store.EbayStoreDO;
import com.zida.cbec.framework.common.pojo.PageResult;
import com.zida.cbec.framework.common.pojo.PageParam;

/**
 * eBay店铺 Service 接口
 *
 * @author 自达源码
 */
public interface EbayStoreService {

    /**
     * 创建eBay店铺
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStore(@Valid EbayStoreSaveReqVO createReqVO);

    /**
     * 更新eBay店铺
     *
     * @param updateReqVO 更新信息
     */
    void updateStore(@Valid EbayStoreSaveReqVO updateReqVO);

    /**
     * 删除eBay店铺
     *
     * @param id 编号
     */
    void deleteStore(Long id);

    /**
    * 批量删除eBay店铺
    *
    * @param ids 编号
    */
    void deleteStoreListByIds(List<Long> ids);

    /**
     * 获得eBay店铺
     *
     * @param id 编号
     * @return eBay店铺
     */
    EbayStoreDO getStore(Long id);

    /**
     * 获得eBay店铺分页
     *
     * @param pageReqVO 分页查询
     * @return eBay店铺分页
     */
    PageResult<EbayStoreDO> getStorePage(EbayStorePageReqVO pageReqVO);

    /**
     * 更新店铺的OAuth令牌信息
     *
     * @param storeId 店铺编号
     * @param accessToken 访问令牌
     * @param refreshToken 刷新令牌
     * @param expiresIn 过期时间（秒）
     * @param refreshTokenExpiresIn 刷新令牌过期时间（秒）
     */
    void updateStoreOAuthTokens(Long storeId, String accessToken, String refreshToken,
                                Integer expiresIn, Integer refreshTokenExpiresIn);

    /**
     * 更新店铺的访问令牌信息
     *
     * @param storeId 店铺编号
     * @param accessToken 访问令牌
     * @param expiresIn 过期时间（秒）
     */
    void updateStoreAccessToken(Long storeId, String accessToken, Integer expiresIn);

}