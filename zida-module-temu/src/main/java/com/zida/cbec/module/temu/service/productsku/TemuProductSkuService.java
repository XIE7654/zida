package com.zida.cbec.module.temu.service.productsku;

import java.util.*;
import jakarta.validation.*;
import com.zida.cbec.module.temu.controller.admin.productsku.vo.*;
import com.zida.cbec.module.temu.dal.dataobject.productsku.TemuProductSkuDO;
import com.zida.cbec.framework.common.pojo.PageResult;
import com.zida.cbec.framework.common.pojo.PageParam;

/**
 * Temu SKU明细 Service 接口
 *
 * @author 自达源码
 */
public interface TemuProductSkuService {

    /**
     * 创建Temu SKU明细
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProductSku(@Valid TemuProductSkuSaveReqVO createReqVO);

    /**
     * 更新Temu SKU明细
     *
     * @param updateReqVO 更新信息
     */
    void updateProductSku(@Valid TemuProductSkuSaveReqVO updateReqVO);

    /**
     * 删除Temu SKU明细
     *
     * @param id 编号
     */
    void deleteProductSku(Long id);

    /**
    * 批量删除Temu SKU明细
    *
    * @param ids 编号
    */
    void deleteProductSkuListByIds(List<Long> ids);

    /**
     * 获得Temu SKU明细
     *
     * @param id 编号
     * @return Temu SKU明细
     */
    TemuProductSkuDO getProductSku(Long id);

    /**
     * 获得Temu SKU明细分页
     *
     * @param pageReqVO 分页查询
     * @return Temu SKU明细分页
     */
    PageResult<TemuProductSkuDO> getProductSkuPage(TemuProductSkuPageReqVO pageReqVO);

    /**
     * 同步所有店铺SKU
     *
     * @return 同步结果
     */
    Boolean syncAllStoreSkus();
}