package com.zida.cbec.module.temu.controller.temu.api;



import com.zida.cbec.module.temu.controller.temu.client.BaseTemuApi;
import com.zida.cbec.module.temu.controller.temu.client.TemuClient;
import com.zida.cbec.module.temu.controller.temu.resp.product.GoodsList;
import com.zida.cbec.module.temu.controller.temu.resp.product.SkuList;

import java.util.Map;

/**
 * 产品相关接口类
 */
public class Product extends BaseTemuApi {
    public Product(TemuClient client) {
        super(client);
    }

    /**
     * 获取本地SKU列表
     * @param requestParams 请求参数
     * @return SKU列表响应
     */
    public SkuList getLocalSkuList(Map<String, Object> requestParams) {
        // 构建基础参数
        Map<String, Object> params = buildBaseParams("bg.local.goods.sku.list.query");

        // 添加请求参数
        if (requestParams != null) {
            params.putAll(requestParams);
        }

        // 执行API调用
        return executeApiCall(params, SkuList.class);
    }

    /**
     * 获取本地商品列表
     * @param requestParams 请求参数
     * @return 商品列表响应
     */
    public GoodsList getLocalGoodsList(Map<String, Object> requestParams) {
        // 构建基础参数
        Map<String, Object> params = buildBaseParams("bg.local.goods.list.query");

        // 添加请求参数
        if (requestParams != null) {
            params.putAll(requestParams);
        }

        // 执行API调用
        return executeApiCall(params, GoodsList.class);
    }
}
