package com.zida.cbec.module.temu.service.productspu;

import java.util.*;
import jakarta.validation.*;
import com.zida.cbec.module.temu.controller.admin.productspu.vo.*;
import com.zida.cbec.module.temu.dal.dataobject.productspu.TemuProductSpuDO;
import com.zida.cbec.framework.common.pojo.PageResult;
import com.zida.cbec.framework.common.pojo.PageParam;

/**
 * Temu SPU Service 接口
 *
 * @author 自达源码
 */
public interface TemuProductSpuService {

    /**
     * 创建Temu SPU
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProductSpu(@Valid TemuProductSpuSaveReqVO createReqVO);

    /**
     * 更新Temu SPU
     *
     * @param updateReqVO 更新信息
     */
    void updateProductSpu(@Valid TemuProductSpuSaveReqVO updateReqVO);

    /**
     * 删除Temu SPU
     *
     * @param id 编号
     */
    void deleteProductSpu(Long id);

    /**
    * 批量删除Temu SPU
    *
    * @param ids 编号
    */
    void deleteProductSpuListByIds(List<Long> ids);

    /**
     * 获得Temu SPU
     *
     * @param id 编号
     * @return Temu SPU
     */
    TemuProductSpuDO getProductSpu(Long id);

    /**
     * 获得Temu SPU分页
     *
     * @param pageReqVO 分页查询
     * @return Temu SPU分页
     */
    PageResult<TemuProductSpuDO> getProductSpuPage(TemuProductSpuPageReqVO pageReqVO);

}