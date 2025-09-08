package com.zida.cbec.module.temu.framework.factory;

import com.zida.cbec.module.temu.controller.temu.client.TemuClient;
import com.zida.cbec.module.temu.dal.dataobject.store.TemuStoreDO;
import com.zida.cbec.module.temu.framework.temu.config.TemuProperties;
import com.zida.cbec.module.temu.service.store.TemuStoreService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class TemuClientFactory {

    @Resource
    private TemuStoreService storeService;

    @Resource
    private TemuProperties temuProperties;

    public TemuClient createClient(Long storeId) {
        // 获取店铺信息
        TemuStoreDO store = storeService.getStore(storeId);

        // 根据店铺类型获取相应的配置
//        TemuProperties siteProperties = temuProperties.getSiteProperties(store.getStoreType());

//        String accessToken = store.getAccessToken();

        // 2. 创建TemuClient实例
//        TemuClient temuClient = new TemuClient(temuProperties, accessToken);
        // 创建TemuClient实例
        return new TemuClient(temuProperties, store.getAccessToken());
    }
}
