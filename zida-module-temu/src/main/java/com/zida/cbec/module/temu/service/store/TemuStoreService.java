package com.zida.cbec.module.temu.service.store;

import java.util.*;
import jakarta.validation.*;
import com.zida.cbec.module.temu.controller.admin.store.vo.*;
import com.zida.cbec.module.temu.dal.dataobject.store.TemuStoreDO;
import com.zida.cbec.framework.common.pojo.PageResult;
import com.zida.cbec.framework.common.pojo.PageParam;

/**
 * Temu 店铺授权信息 Service 接口
 *
 * @author 自达源码
 */
public interface TemuStoreService {

    /**
     * 创建Temu 店铺授权信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStore(@Valid TemuStoreSaveReqVO createReqVO);

    /**
     * 更新Temu 店铺授权信息
     *
     * @param updateReqVO 更新信息
     */
    void updateStore(@Valid TemuStoreSaveReqVO updateReqVO);

    /**
     * 删除Temu 店铺授权信息
     *
     * @param id 编号
     */
    void deleteStore(Long id);

    /**
    * 批量删除Temu 店铺授权信息
    *
    * @param ids 编号
    */
    void deleteStoreListByIds(List<Long> ids);

    /**
     * 获得Temu 店铺授权信息
     *
     * @param id 编号
     * @return Temu 店铺授权信息
     */
    TemuStoreDO getStore(Long id);

    /**
     * 获得Temu 店铺授权信息分页
     *
     * @param pageReqVO 分页查询
     * @return Temu 店铺授权信息分页
     */
    PageResult<TemuStoreDO> getStorePage(TemuStorePageReqVO pageReqVO);

}