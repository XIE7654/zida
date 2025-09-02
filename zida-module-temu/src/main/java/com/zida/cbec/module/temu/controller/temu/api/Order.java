package com.zida.cbec.module.temu.controller.temu.api;



import com.zida.cbec.module.temu.controller.temu.client.BaseTemuApi;
import com.zida.cbec.module.temu.controller.temu.client.TemuClient;
import com.zida.cbec.module.temu.controller.temu.resp.OrderDetail;
import com.zida.cbec.module.temu.controller.temu.resp.OrderList;
import com.zida.cbec.module.temu.controller.temu.resp.ShippingInfo;

import java.util.List;
import java.util.Map;

public class Order extends BaseTemuApi {
    public Order(TemuClient client) {
        super(client);
    }

    /**
     * 获取订单列表
     * @param requestParams 请求参数
     * @return 订单列表响应
     */
    public OrderList getOrderList(Map<String, Object> requestParams) {
        // 构建基础参数
        Map<String, Object> params = buildBaseParams("bg.order.list.v2.get");

        // 添加请求参数
        if (requestParams != null) {
            params.putAll(requestParams);
        }

        // 执行API调用
        return executeApiCall(params, OrderList.class);
    }

    /**
     * 获取订单详情
     * @param parentOrderSn 父订单号
     * @param fulfillmentTypeList 履货类型列表
     * @return 订单详情响应
     */
    public OrderDetail getOrderDetail(String parentOrderSn, List<String> fulfillmentTypeList) {
        // 构建基础参数
        Map<String, Object> params = buildBaseParams("bg.order.detail.v2.get");

        // 必需参数
        params.put("parentOrderSn", parentOrderSn);

        // 可选参数
        if (fulfillmentTypeList != null && !fulfillmentTypeList.isEmpty()) {
            params.put("fulfillmentTypeList", fulfillmentTypeList);
        }

        // 执行API调用
        return executeApiCall(params, OrderDetail.class);
    }

    /**
     * 获取订单配送信息
     * @param parentOrderSn 父订单号
     * @return 配送信息响应
     */
    public ShippingInfo getShippingInfo(String parentOrderSn) {
        // 构建基础参数
        Map<String, Object> params = buildBaseParams("bg.order.shippinginfo.v2.get");

        // 可选参数
        if (parentOrderSn != null && !parentOrderSn.isEmpty()) {
            params.put("parentOrderSn", parentOrderSn);
        }

        // 执行API调用
        return executeApiCall(params, ShippingInfo.class);
    }

}
