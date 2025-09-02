package com.zida.cbec.module.ebay.dal.mysql.store;

import java.util.*;

import com.zida.cbec.framework.common.pojo.PageResult;
import com.zida.cbec.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zida.cbec.framework.mybatis.core.mapper.BaseMapperX;
import com.zida.cbec.module.ebay.dal.dataobject.store.EbayStoreDO;
import org.apache.ibatis.annotations.Mapper;
import com.zida.cbec.module.ebay.controller.admin.store.vo.*;
import org.springframework.data.repository.query.Param;

/**
 * eBay店铺 Mapper
 *
 * @author 自达源码
 */
@Mapper
public interface EbayStoreMapper extends BaseMapperX<EbayStoreDO> {

    default PageResult<EbayStoreDO> selectPage(EbayStorePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EbayStoreDO>()
                .likeIfPresent(EbayStoreDO::getShopName, reqVO.getShopName())
                .eqIfPresent(EbayStoreDO::getEbayAccount, reqVO.getEbayAccount())
                .eqIfPresent(EbayStoreDO::getSite, reqVO.getSite())
                .eqIfPresent(EbayStoreDO::getKeepZeroStockOnline, reqVO.getKeepZeroStockOnline())
                .eqIfPresent(EbayStoreDO::getAuthorizationType, reqVO.getAuthorizationType())
                .betweenIfPresent(EbayStoreDO::getApiKeyExpireTime, reqVO.getApiKeyExpireTime())
                .eqIfPresent(EbayStoreDO::getListingQuota, reqVO.getListingQuota())
                .eqIfPresent(EbayStoreDO::getIsManagedPayment, reqVO.getIsManagedPayment())
                .betweenIfPresent(EbayStoreDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(EbayStoreDO::getAccessToken, reqVO.getAccessToken())
                .eqIfPresent(EbayStoreDO::getAccessTokenExpiresIn, reqVO.getAccessTokenExpiresIn())
                .eqIfPresent(EbayStoreDO::getRefreshToken, reqVO.getRefreshToken())
                .eqIfPresent(EbayStoreDO::getRefreshTokenExpiresIn, reqVO.getRefreshTokenExpiresIn())
                .orderByDesc(EbayStoreDO::getId));
    }


    /**
     * 根据用户ID查询店铺列表
     * @param creator 用户ID
     * @return 店铺列表
     */
    List<EbayStoreDO> selectListByUserId(@Param("creator") String creator);
}