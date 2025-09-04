package com.zida.cbec.module.temu.dal.mysql.productspu;

import java.util.*;

import com.zida.cbec.framework.common.pojo.PageResult;
import com.zida.cbec.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zida.cbec.framework.mybatis.core.mapper.BaseMapperX;
import com.zida.cbec.module.temu.dal.dataobject.order.TemuOrderDO;
import com.zida.cbec.module.temu.dal.dataobject.productspu.TemuProductSpuDO;
import org.apache.ibatis.annotations.Mapper;
import com.zida.cbec.module.temu.controller.admin.productspu.vo.*;

/**
 * Temu SPU Mapper
 *
 * @author 自达源码
 */
@Mapper
public interface TemuProductSpuMapper extends BaseMapperX<TemuProductSpuDO> {

    default PageResult<TemuProductSpuDO> selectPage(TemuProductSpuPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TemuProductSpuDO>()
                .eqIfPresent(TemuProductSpuDO::getStoreId, reqVO.getStoreId())
                .eqIfPresent(TemuProductSpuDO::getGoodsId, reqVO.getGoodsId())
                .likeIfPresent(TemuProductSpuDO::getGoodsName, reqVO.getGoodsName())
                .eqIfPresent(TemuProductSpuDO::getOutGoodsSn, reqVO.getOutGoodsSn())
                .eqIfPresent(TemuProductSpuDO::getCatId, reqVO.getCatId())
                .eqIfPresent(TemuProductSpuDO::getCurrency, reqVO.getCurrency())
                .eqIfPresent(TemuProductSpuDO::getMarketPrice, reqVO.getMarketPrice())
                .eqIfPresent(TemuProductSpuDO::getPrice, reqVO.getPrice())
                .eqIfPresent(TemuProductSpuDO::getRetailPriceAmount, reqVO.getRetailPriceAmount())
                .eqIfPresent(TemuProductSpuDO::getRetailPriceCurrency, reqVO.getRetailPriceCurrency())
                .eqIfPresent(TemuProductSpuDO::getListPriceAmount, reqVO.getListPriceAmount())
                .eqIfPresent(TemuProductSpuDO::getListPriceCurrency, reqVO.getListPriceCurrency())
                .eqIfPresent(TemuProductSpuDO::getQuantity, reqVO.getQuantity())
                .eqIfPresent(TemuProductSpuDO::getThumbUrl, reqVO.getThumbUrl())
                .betweenIfPresent(TemuProductSpuDO::getCrtTime, reqVO.getCrtTime())
                .betweenIfPresent(TemuProductSpuDO::getGoodsStatusChangeTime, reqVO.getGoodsStatusChangeTime())
                .eqIfPresent(TemuProductSpuDO::getGoodsShowSubStatus, reqVO.getGoodsShowSubStatus())
                .eqIfPresent(TemuProductSpuDO::getStatus4vo, reqVO.getStatus4vo())
                .eqIfPresent(TemuProductSpuDO::getSubStatus4vo, reqVO.getSubStatus4vo())
                .eqIfPresent(TemuProductSpuDO::getLowTrafficTag, reqVO.getLowTrafficTag())
                .eqIfPresent(TemuProductSpuDO::getRestrictedTrafficTag, reqVO.getRestrictedTrafficTag())
                .eqIfPresent(TemuProductSpuDO::getCostTemplateId, reqVO.getCostTemplateId())
                .eqIfPresent(TemuProductSpuDO::getShipmentLimitSecond, reqVO.getShipmentLimitSecond())
                .eqIfPresent(TemuProductSpuDO::getTrademarkId, reqVO.getTrademarkId())
                .eqIfPresent(TemuProductSpuDO::getBrandId, reqVO.getBrandId())
                .likeIfPresent(TemuProductSpuDO::getSpecName, reqVO.getSpecName())
                .betweenIfPresent(TemuProductSpuDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TemuProductSpuDO::getId));
    }

}