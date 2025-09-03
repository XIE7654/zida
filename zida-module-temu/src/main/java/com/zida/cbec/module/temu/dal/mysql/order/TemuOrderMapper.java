package com.zida.cbec.module.temu.dal.mysql.order;

import java.util.*;

import com.zida.cbec.framework.common.pojo.PageResult;
import com.zida.cbec.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zida.cbec.framework.mybatis.core.mapper.BaseMapperX;
import com.zida.cbec.module.temu.dal.dataobject.order.TemuOrderDO;
import org.apache.ibatis.annotations.Mapper;
import com.zida.cbec.module.temu.controller.admin.order.vo.*;

/**
 * Temu订单 Mapper
 *
 * @author 自达源码
 */
@Mapper
public interface TemuOrderMapper extends BaseMapperX<TemuOrderDO> {

    default PageResult<TemuOrderDO> selectPage(TemuOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TemuOrderDO>()
                .eqIfPresent(TemuOrderDO::getStoreId, reqVO.getStoreId())
                .eqIfPresent(TemuOrderDO::getParentOrderSn, reqVO.getParentOrderSn())
                .eqIfPresent(TemuOrderDO::getParentOrderStatus, reqVO.getParentOrderStatus())
                .betweenIfPresent(TemuOrderDO::getParentOrderTime, reqVO.getParentOrderTime())
                .betweenIfPresent(TemuOrderDO::getExpectShipLatestTime, reqVO.getExpectShipLatestTime())
                .betweenIfPresent(TemuOrderDO::getParentOrderPendingFinishTime, reqVO.getParentOrderPendingFinishTime())
                .betweenIfPresent(TemuOrderDO::getLatestDeliveryTime, reqVO.getLatestDeliveryTime())
                .betweenIfPresent(TemuOrderDO::getParentShippingTime, reqVO.getParentShippingTime())
                .eqIfPresent(TemuOrderDO::getSiteId, reqVO.getSiteId())
                .eqIfPresent(TemuOrderDO::getRegionId, reqVO.getRegionId())
                .eqIfPresent(TemuOrderDO::getParentOrderLabel, reqVO.getParentOrderLabel())
                .eqIfPresent(TemuOrderDO::getFulfillmentWarning, reqVO.getFulfillmentWarning())
                .eqIfPresent(TemuOrderDO::getHasShippingFee, reqVO.getHasShippingFee())
                .eqIfPresent(TemuOrderDO::getShippingMethod, reqVO.getShippingMethod())
                .eqIfPresent(TemuOrderDO::getOrderPaymentType, reqVO.getOrderPaymentType())
                .eqIfPresent(TemuOrderDO::getBatchOrderNumberList, reqVO.getBatchOrderNumberList())
                .eqIfPresent(TemuOrderDO::getOrderList, reqVO.getOrderList())
                .betweenIfPresent(TemuOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TemuOrderDO::getId));
    }

}