package com.zida.cbec.module.temu.dal.mysql.store;

import java.util.*;

import com.zida.cbec.framework.common.pojo.PageResult;
import com.zida.cbec.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zida.cbec.framework.mybatis.core.mapper.BaseMapperX;
import com.zida.cbec.module.temu.dal.dataobject.store.TemuStoreDO;
import org.apache.ibatis.annotations.Mapper;
import com.zida.cbec.module.temu.controller.admin.store.vo.*;

/**
 * Temu 店铺授权信息 Mapper
 *
 * @author 自达源码
 */
@Mapper
public interface TemuStoreMapper extends BaseMapperX<TemuStoreDO> {

    default PageResult<TemuStoreDO> selectPage(TemuStorePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TemuStoreDO>()
                .eqIfPresent(TemuStoreDO::getStoreType, reqVO.getStoreType())
                .likeIfPresent(TemuStoreDO::getStoreName, reqVO.getStoreName())
                .eqIfPresent(TemuStoreDO::getProductStockToken, reqVO.getProductStockToken())
                .eqIfPresent(TemuStoreDO::getComplianceApiToken, reqVO.getComplianceApiToken())
                .eqIfPresent(TemuStoreDO::getOrderShippingTokenUs, reqVO.getOrderShippingTokenUs())
                .eqIfPresent(TemuStoreDO::getOrderShippingTokenEu, reqVO.getOrderShippingTokenEu())
                .eqIfPresent(TemuStoreDO::getOrderShippingTokenGlobal, reqVO.getOrderShippingTokenGlobal())
                .eqIfPresent(TemuStoreDO::getAccessToken, reqVO.getAccessToken())
                .eqIfPresent(TemuStoreDO::getStoreCurrency, reqVO.getStoreCurrency())
                .eqIfPresent(TemuStoreDO::getStoreSite, reqVO.getStoreSite())
                .eqIfPresent(TemuStoreDO::getAuthStatus, reqVO.getAuthStatus())
                .betweenIfPresent(TemuStoreDO::getAuthTime, reqVO.getAuthTime())
                .eqIfPresent(TemuStoreDO::getApiScopeList, reqVO.getApiScopeList())
                .eqIfPresent(TemuStoreDO::getAppSubscribeEventCodeList, reqVO.getAppSubscribeEventCodeList())
                .eqIfPresent(TemuStoreDO::getAppSubscribeStatus, reqVO.getAppSubscribeStatus())
                .eqIfPresent(TemuStoreDO::getAuthEventCodeList, reqVO.getAuthEventCodeList())
                .betweenIfPresent(TemuStoreDO::getExpiredTime, reqVO.getExpiredTime())
                .eqIfPresent(TemuStoreDO::getMallId, reqVO.getMallId())
                .eqIfPresent(TemuStoreDO::getRegionId, reqVO.getRegionId())
                .eqIfPresent(TemuStoreDO::getMallType, reqVO.getMallType())
                .betweenIfPresent(TemuStoreDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TemuStoreDO::getId));
    }

}