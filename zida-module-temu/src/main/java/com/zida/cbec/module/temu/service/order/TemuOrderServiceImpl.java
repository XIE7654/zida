package com.zida.cbec.module.temu.service.order;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zida.cbec.framework.security.core.util.SecurityFrameworkUtils;
import com.zida.cbec.module.temu.controller.temu.client.TemuClient;
import com.zida.cbec.module.temu.controller.temu.resp.OrderDetail;
import com.zida.cbec.module.temu.controller.temu.resp.OrderList;
import com.zida.cbec.module.temu.controller.temu.resp.ShippingInfo;
import com.zida.cbec.module.temu.dal.dataobject.store.TemuStoreDO;
import com.zida.cbec.module.temu.framework.factory.TemuClientFactory;
import com.zida.cbec.module.temu.framework.temu.config.TemuProperties;
import com.zida.cbec.module.temu.service.store.TemuStoreService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.zida.cbec.module.temu.controller.admin.order.vo.*;
import com.zida.cbec.module.temu.dal.dataobject.order.TemuOrderDO;
import com.zida.cbec.framework.common.pojo.PageResult;
import com.zida.cbec.framework.common.pojo.PageParam;
import com.zida.cbec.framework.common.util.object.BeanUtils;

import com.zida.cbec.module.temu.dal.mysql.order.TemuOrderMapper;

import static com.zida.cbec.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zida.cbec.framework.common.util.collection.CollectionUtils.convertList;
import static com.zida.cbec.framework.common.util.collection.CollectionUtils.diffList;
import static com.zida.cbec.module.temu.enums.ErrorCodeConstants.*;

/**
 * Temu订单 Service 实现类
 *
 * @author 自达源码
 */
@Service
@Validated
public class TemuOrderServiceImpl implements TemuOrderService {

    @Resource
    private TemuOrderMapper orderMapper;

    @Resource
    private TemuStoreService temuStoreService;

    @Resource
    private TemuClientFactory temuClientFactory;

    @Override
    public Long createOrder(TemuOrderSaveReqVO createReqVO) {
        // 插入
        TemuOrderDO order = BeanUtils.toBean(createReqVO, TemuOrderDO.class);
        orderMapper.insert(order);

        // 返回
        return order.getId();
    }

    @Override
    public void updateOrder(TemuOrderSaveReqVO updateReqVO) {
        // 校验存在
        validateOrderExists(updateReqVO.getId());
        // 更新
        TemuOrderDO updateObj = BeanUtils.toBean(updateReqVO, TemuOrderDO.class);
        orderMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrder(Long id) {
        // 校验存在
        validateOrderExists(id);
        // 删除
        orderMapper.deleteById(id);
    }

    @Override
        public void deleteOrderListByIds(List<Long> ids) {
        // 删除
        orderMapper.deleteByIds(ids);
        }


    private void validateOrderExists(Long id) {
        if (orderMapper.selectById(id) == null) {
            throw exception(ORDER_NOT_EXISTS);
        }
    }

    @Override
    public TemuOrderDO getOrder(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public PageResult<TemuOrderDO> getOrderPage(TemuOrderPageReqVO pageReqVO) {
        return orderMapper.selectPage(pageReqVO);
    }

    @Override
    public void syncStoreOrders(Long storeId) {
        // 实现订单同步逻辑
        // 1. 获取店铺信息和访问令牌
//        TemuStoreDO store = temuStoreService.getStore(storeId);
//        String accessToken = store.getAccessToken();
        // 2. 调用TEMU API获取订单列表
        try {
            // 2. 创建TemuClient实例
//            TemuClient temuClient = new TemuClient(temuProperties, accessToken);
            TemuClient temuClient = temuClientFactory.createClient(storeId);

            // 3. 计算最近3天的时间范围
            long currentTime = System.currentTimeMillis() / 1000;
            long threeDaysAgo = currentTime - (3 * 24 * 60 * 60);

            // 4. 设置请求参数
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put("updateAtStart", threeDaysAgo);
            requestParams.put("updateAtEnd", currentTime);
            requestParams.put("pageSize", 100); // 每页最大100条
            requestParams.put("pageNumber", 1); // 从第一页开始

            boolean hasMore = true;
            int pageNumber = 1;

            // 5. 循环获取所有页面的订单数据
            while (hasMore) {
                requestParams.put("pageNumber", pageNumber);

                // 6. 调用订单同步接口
                OrderList response = temuClient.order.getOrderList(requestParams);

                System.out.println("Temu订单同步响应信息 - 第" + pageNumber + "页: " + response);

                // 7. 检查响应是否成功
                if (response != null && response.isSuccess()) {
                    // 8. 处理订单数据
                    if (response.getResult() != null && response.getResult().getPageItems() != null) {
                        processOrderData(response.getResult().getPageItems(), storeId);

                        // 9. 检查是否还有更多数据
                        int totalItems = response.getResult().getTotalItemNum() != null ? response.getResult().getTotalItemNum() : 0;
                        int pageSize = 100;
                        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

                        if (pageNumber >= totalPages) {
                            hasMore = false;
                        } else {
                            pageNumber++;
                        }
                    } else {
                        hasMore = false;
                    }
                } else {
                    throw new RuntimeException("获取订单列表失败: " + (response != null ? response.getErrorMsg() : "未知错误"));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("订单同步失败: " + e.getMessage(), e);
        }
    }

    @Override
    public Boolean syncAllStoreOrders() {
        try {
            // 获取当前登录用户ID（根据您的安全框架实现）
            // 例如使用 Spring Security:
            Long userId = SecurityFrameworkUtils.getLoginUserId();
            System.out.println("当前登录用户ID: " + userId);

            // 获取当前用户的所有店铺
            List<TemuStoreDO> stores = temuStoreService.getStoresByUserId(String.valueOf(userId)); //

            int successCount = 0;
            int failCount = 0;

            // 遍历所有店铺并同步订单
            for (TemuStoreDO store : stores) {
                try {
                    syncStoreOrders(store.getId());
                    successCount++;
                    System.out.println("店铺 " + store.getId() + "(" + store.getStoreName() + ") 订单同步成功");
                } catch (Exception e) {
                    // 记录单个店铺同步失败的日志，但继续同步其他店铺
                    System.err.println("店铺 " + store.getId() + "(" + store.getStoreName() + ") 订单同步失败: " + e.getMessage());
                    failCount++;
                }
            }

            System.out.println("订单同步完成: 成功 " + successCount + " 个店铺，失败 " + failCount + " 个店铺");
            return true;
        } catch (Exception e) {
            throw new RuntimeException("同步所有店铺订单失败: " + e.getMessage(), e);
        }
    }
    /**
     * 处理订单数据
     * @param pageItems 页面订单项列表
     * @param storeId 店铺ID
     */
    private void processOrderData(List<OrderList.PageItem> pageItems, Long storeId) {
        if (pageItems == null || pageItems.isEmpty()) {
            return;
        }

        for (OrderList.PageItem pageItem : pageItems) {
            // 处理父订单（每个PageItem对应一个父订单）
            TemuOrderDO temuOrderDO = convertToOrderDO(pageItem.getOrderList(), pageItem.getParentOrderMap(), storeId);
            saveOrUpdateOrder(temuOrderDO);
        }
    }

    /**
     * 转换订单数据为DO对象
     * @param orders 子订单信息
     * @param parentOrderMap 父订单信息
     * @param storeId 店铺ID
     * @return OrderDO对象
     */
    private TemuOrderDO convertToOrderDO(List<OrderList.Order> orders,
                                     OrderList.ParentOrderMap parentOrderMap,
                                     Long storeId) {
        TemuOrderDO orderDO = new TemuOrderDO();
        orderDO.setStoreId(storeId);
        // 父订单信息（当前OrderDO主要存储父订单信息）
        if (parentOrderMap != null) {
            orderDO.setParentOrderSn(parentOrderMap.getParentOrderSn());
            orderDO.setParentOrderStatus(parentOrderMap.getParentOrderStatus());
            orderDO.setParentOrderTime(parentOrderMap.getParentOrderTime());
            orderDO.setExpectShipLatestTime(parentOrderMap.getExpectShipLatestTime());
            orderDO.setParentOrderPendingFinishTime(parentOrderMap.getParentOrderPendingFinishTime());
            orderDO.setLatestDeliveryTime(parentOrderMap.getLatestDeliveryTime());
            orderDO.setParentShippingTime(parentOrderMap.getParentShippingTime());
            orderDO.setSiteId(parentOrderMap.getSiteId());
            orderDO.setRegionId(parentOrderMap.getRegionId());
            orderDO.setParentOrderLabel(parentOrderMap.getParentOrderLabel() != null ?
                    parentOrderMap.getParentOrderLabel().toString() : null);
            orderDO.setFulfillmentWarning(parentOrderMap.getFulfillmentWarning() != null ?
                    parentOrderMap.getFulfillmentWarning().toString() : null);
            orderDO.setHasShippingFee(parentOrderMap.getHasShippingFee());
            orderDO.setShippingMethod(parentOrderMap.getShippingMethod());
            orderDO.setOrderPaymentType(parentOrderMap.getOrderPaymentType());
            orderDO.setBatchOrderNumberList(parentOrderMap.getBatchOrderNumberList() != null ?
                    parentOrderMap.getBatchOrderNumberList().toString() : null);
        }

        // 子订单信息可以考虑序列化后存储到orderList字段中
        if (orders != null) {
            // 这里可以将子订单信息序列化为JSON字符串存储
            // 例如使用Jackson或Gson进行序列化
            try {
                // 使用Jackson序列化子订单信息为JSON字符串
                ObjectMapper objectMapper = new ObjectMapper();
                orderDO.setOrderList(objectMapper.writeValueAsString(orders));
            } catch (Exception e) {
                // 如果序列化失败，记录日志并设置为空字符串
                System.err.println("子订单信息序列化失败: " + e.getMessage());
                orderDO.setOrderList("{}");
            }
        } else {
            // 如果没有子订单信息，设置为空字符串而不是null
            orderDO.setOrderList("[]");
        }

        return orderDO;
    }

    /**
     * 保存或更新订单
     * @param orderDO 订单DO对象
     */
    private void saveOrUpdateOrder(TemuOrderDO orderDO) {
        // 检查订单是否已存在
        QueryWrapper<TemuOrderDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_order_sn", orderDO.getParentOrderSn());
        // 假设有软删除字段
        queryWrapper.eq("deleted", 0);

        TemuOrderDO existingOrder = orderMapper.selectOne(queryWrapper);

        if (existingOrder != null) {
            // 更新现有订单
            orderDO.setId(existingOrder.getId());
            orderMapper.updateById(orderDO);
        } else {
            // 插入新订单
            orderMapper.insert(orderDO);
        }
    }

    @Override
    public ShippingInfo syncOrderShippingInfo(Long id) {
        try {

            // 获取TemuClient实例
            TemuClient temuClient = getTemuClient(id);

            // 获取父订单号
            String parentOrderSn = getParentOrderSn(id);

            // 调用配送信息接口
            ShippingInfo response = temuClient.order.getShippingInfo(parentOrderSn);

            if (response != null && response.isSuccess()) {
                return response;
            } else {
                throw new RuntimeException("获取配送信息失败: " + (response != null ? response.getErrorMsg() : "未知错误"));
            }
        } catch (Exception e) {
            throw new RuntimeException("同步订单配送信息失败: " + e.getMessage(), e);
        }
    }

    /**
     * 获取TEMU客户端
     * @param orderId 订单ID
     * @return TemuClient实例
     */
    private TemuClient getTemuClient(Long orderId) {
        // 从数据库获取订单信息
        TemuOrderDO order = getOrder(orderId);
        if (order == null || order.getParentOrderSn() == null) {
            throw new RuntimeException("订单不存在或缺少父订单号");
        }

        Long storeId = order.getStoreId(); // 从订单中获取storeId

        // 获取店铺信息和访问令牌
//        TemuStoreDO store = temuStoreService.getStore(storeId);
//        String accessToken = store.getAccessToken();
        // 创建TemuClient实例
//        return new TemuClient(temuProperties, accessToken);
        return temuClientFactory.createClient(storeId);
    }
    /**
     * 获取订单的父订单号
     * @param orderId 订单ID
     * @return 父订单号
     */
    private String getParentOrderSn(Long orderId) {
        TemuOrderDO order = getOrder(orderId);
        if (order == null || order.getParentOrderSn() == null) {
            throw new RuntimeException("订单不存在或缺少父订单号");
        }
        return order.getParentOrderSn();
    }
    @Override
    public OrderDetail syncOrderDetail(Long id) {
        try {
            // 获取TemuClient实例
            TemuClient temuClient = getTemuClient(id);

            // 获取父订单号
            String parentOrderSn = getParentOrderSn(id);

            // 调用订单详情接口
            OrderDetail response = temuClient.order.getOrderDetail(parentOrderSn, null);
            System.out.println(response);
            System.out.println("dasdasdas");
            if (response != null && response.isSuccess()) {
                return response;
            } else {
                throw new RuntimeException("获取订单详情失败: " + (response != null ? response.getErrorMsg() : "未知错误"));
            }
        } catch (Exception e) {
            throw new RuntimeException("同步订单详情失败: " + e.getMessage(), e);
        }
    }
}