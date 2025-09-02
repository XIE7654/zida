package com.zida.cbec.module.temu.controller.temu.api;


import com.zida.cbec.module.temu.controller.temu.client.BaseTemuApi;
import com.zida.cbec.module.temu.controller.temu.client.TemuClient;
import com.zida.cbec.module.temu.controller.temu.resp.AccessTokenInfo;

import java.util.Map;

/**
 * 认证相关接口类
 */
public class Auth extends BaseTemuApi {

    public Auth(TemuClient client) {
        super(client);
    }

    /**
     * 查询当前授权 token 的 API 权限列表
     * 返回 AccessTokenInfo，结构与官方接口文档一致
     */
    public AccessTokenInfo getAccessTokenInfo() {
        // 构建基础参数
        Map<String, Object> params = buildBaseParams("bg.open.accesstoken.info.get");

        // 执行API调用
        return executeApiCall(params, AccessTokenInfo.class);
    }
}
