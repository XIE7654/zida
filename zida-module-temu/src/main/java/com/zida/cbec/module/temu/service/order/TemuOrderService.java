package com.zida.cbec.module.temu.service.order;

import java.util.*;

import com.zida.cbec.module.temu.controller.temu.resp.OrderDetail;
import com.zida.cbec.module.temu.controller.temu.resp.OrderList;
import com.zida.cbec.module.temu.controller.temu.resp.ShippingInfo;
import jakarta.validation.*;
import com.zida.cbec.module.temu.controller.admin.order.vo.*;
import com.zida.cbec.module.temu.dal.dataobject.order.TemuOrderDO;
import com.zida.cbec.framework.common.pojo.PageResult;
import com.zida.cbec.framework.common.pojo.PageParam;

/**
 * Temu订单 Service 接口
 *
 * @author 自达源码
 */
public interface TemuOrderService {

    /**
     * 创建Temu订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrder(@Valid TemuOrderSaveReqVO createReqVO);

    /**
     * 更新Temu订单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrder(@Valid TemuOrderSaveReqVO updateReqVO);

    /**
     * 删除Temu订单
     *
     * @param id 编号
     */
    void deleteOrder(Long id);

    /**
    * 批量删除Temu订单
    *
    * @param ids 编号
    */
    void deleteOrderListByIds(List<Long> ids);

    /**
     * 获得Temu订单
     *
     * @param id 编号
     * @return Temu订单
     */
    TemuOrderDO getOrder(Long id);

    /**
     * 获得Temu订单分页
     *
     * @param pageReqVO 分页查询
     * @return Temu订单分页
     */
    PageResult<TemuOrderDO> getOrderPage(TemuOrderPageReqVO pageReqVO);

    void syncStoreOrders(Long storeId);

    /**
     * 同步所有店铺订单
     * @return 是否成功
     */
    Boolean syncAllStoreOrders();

    /**
     * 同步订单配送信息
     * @return 配送信息
     */
    ShippingInfo syncOrderShippingInfo(Long id);

    /**
     * 同步订单详情
     * @return 订单详情
     */
    OrderDetail syncOrderDetail(Long id);
}