package com.zida.cbec.module.temu.dal.mysql.productsku;

import java.util.*;

import com.zida.cbec.framework.common.pojo.PageResult;
import com.zida.cbec.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zida.cbec.framework.mybatis.core.mapper.BaseMapperX;
import com.zida.cbec.module.temu.dal.dataobject.productsku.TemuProductSkuDO;
import org.apache.ibatis.annotations.Mapper;
import com.zida.cbec.module.temu.controller.admin.productsku.vo.*;

/**
 * Temu SKU明细 Mapper
 *
 * @author 自达源码
 */
@Mapper
public interface TemuProductSkuMapper extends BaseMapperX<TemuProductSkuDO> {

    default PageResult<TemuProductSkuDO> selectPage(TemuProductSkuPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TemuProductSkuDO>()
                .eqIfPresent(TemuProductSkuDO::getSkuId, reqVO.getSkuId())
                .eqIfPresent(TemuProductSkuDO::getGoodsId, reqVO.getGoodsId())
                .likeIfPresent(TemuProductSkuDO::getGoodsName, reqVO.getGoodsName())
                .eqIfPresent(TemuProductSkuDO::getSkuSn, reqVO.getSkuSn())
                .likeIfPresent(TemuProductSkuDO::getSpecName, reqVO.getSpecName())
                .eqIfPresent(TemuProductSkuDO::getCurrency, reqVO.getCurrency())
                .eqIfPresent(TemuProductSkuDO::getPrice, reqVO.getPrice())
                .eqIfPresent(TemuProductSkuDO::getRetailPriceAmount, reqVO.getRetailPriceAmount())
                .eqIfPresent(TemuProductSkuDO::getRetailPriceCurrency, reqVO.getRetailPriceCurrency())
                .eqIfPresent(TemuProductSkuDO::getStock, reqVO.getStock())
                .eqIfPresent(TemuProductSkuDO::getThumbUrl, reqVO.getThumbUrl())
                .betweenIfPresent(TemuProductSkuDO::getCrtTime, reqVO.getCrtTime())
                .betweenIfPresent(TemuProductSkuDO::getSkuStatusChangeTime, reqVO.getSkuStatusChangeTime())
                .eqIfPresent(TemuProductSkuDO::getStatus4vo, reqVO.getStatus4vo())
                .eqIfPresent(TemuProductSkuDO::getSubStatus4vo, reqVO.getSubStatus4vo())
                .eqIfPresent(TemuProductSkuDO::getSkuShowSubStatus4vo, reqVO.getSkuShowSubStatus4vo())
                .eqIfPresent(TemuProductSkuDO::getGoodsIsOnSale, reqVO.getGoodsIsOnSale())
                .eqIfPresent(TemuProductSkuDO::getLowTrafficTag, reqVO.getLowTrafficTag())
                .eqIfPresent(TemuProductSkuDO::getRestrictedTrafficTag, reqVO.getRestrictedTrafficTag())
                .eqIfPresent(TemuProductSkuDO::getWeight, reqVO.getWeight())
                .eqIfPresent(TemuProductSkuDO::getWeightUnit, reqVO.getWeightUnit())
                .eqIfPresent(TemuProductSkuDO::getLength, reqVO.getLength())
                .eqIfPresent(TemuProductSkuDO::getWidth, reqVO.getWidth())
                .eqIfPresent(TemuProductSkuDO::getHeight, reqVO.getHeight())
                .eqIfPresent(TemuProductSkuDO::getVolumeUnit, reqVO.getVolumeUnit())
                .betweenIfPresent(TemuProductSkuDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TemuProductSkuDO::getId));
    }

}