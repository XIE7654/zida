package com.zida.cbec.module.temu.service.productsku;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zida.cbec.framework.security.core.util.SecurityFrameworkUtils;
import com.zida.cbec.module.temu.controller.temu.client.TemuClient;
import com.zida.cbec.module.temu.controller.temu.resp.product.SkuList;
import com.zida.cbec.module.temu.dal.dataobject.store.TemuStoreDO;
import com.zida.cbec.module.temu.framework.temu.config.TemuProperties;
import com.zida.cbec.module.temu.service.store.TemuStoreService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.zida.cbec.module.temu.controller.admin.productsku.vo.*;
import com.zida.cbec.module.temu.dal.dataobject.productsku.TemuProductSkuDO;
import com.zida.cbec.framework.common.pojo.PageResult;
import com.zida.cbec.framework.common.pojo.PageParam;
import com.zida.cbec.framework.common.util.object.BeanUtils;

import com.zida.cbec.module.temu.dal.mysql.productsku.TemuProductSkuMapper;

import static com.zida.cbec.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zida.cbec.framework.common.util.collection.CollectionUtils.convertList;
import static com.zida.cbec.framework.common.util.collection.CollectionUtils.diffList;
import static com.zida.cbec.module.temu.enums.ErrorCodeConstants.*;

/**
 * Temu SKU明细 Service 实现类
 *
 * @author 自达源码
 */
@Service
@Validated
public class TemuProductSkuServiceImpl implements TemuProductSkuService {

    @Resource
    private TemuProductSkuMapper productSkuMapper;

    @Resource
    private TemuStoreService temuStoreService;

    @Resource
    private TemuProperties temuProperties;

    @Override
    public Long createProductSku(TemuProductSkuSaveReqVO createReqVO) {
        // 插入
        TemuProductSkuDO productSku = BeanUtils.toBean(createReqVO, TemuProductSkuDO.class);
        productSkuMapper.insert(productSku);

        // 返回
        return productSku.getId();
    }

    @Override
    public void updateProductSku(TemuProductSkuSaveReqVO updateReqVO) {
        // 校验存在
        validateProductSkuExists(updateReqVO.getId());
        // 更新
        TemuProductSkuDO updateObj = BeanUtils.toBean(updateReqVO, TemuProductSkuDO.class);
        productSkuMapper.updateById(updateObj);
    }

    @Override
    public void deleteProductSku(Long id) {
        // 校验存在
        validateProductSkuExists(id);
        // 删除
        productSkuMapper.deleteById(id);
    }

    @Override
        public void deleteProductSkuListByIds(List<Long> ids) {
        // 删除
        productSkuMapper.deleteByIds(ids);
        }


    private void validateProductSkuExists(Long id) {
        if (productSkuMapper.selectById(id) == null) {
            throw exception(PRODUCT_SKU_NOT_EXISTS);
        }
    }

    @Override
    public TemuProductSkuDO getProductSku(Long id) {
        return productSkuMapper.selectById(id);
    }

    @Override
    public PageResult<TemuProductSkuDO> getProductSkuPage(TemuProductSkuPageReqVO pageReqVO) {
        return productSkuMapper.selectPage(pageReqVO);
    }

    @Override
    public Boolean syncAllStoreSkus() {
        try {
            // 获取当前登录用户ID
            Long userId = SecurityFrameworkUtils.getLoginUserId();
            System.out.println("当前登录用户ID: " + userId);

            // 获取当前用户的所有店铺
            List<TemuStoreDO> stores = temuStoreService.getStoresByUserId(String.valueOf(userId));

            int successCount = 0;
            int failCount = 0;

            // 遍历所有店铺并同步SKU
            for (TemuStoreDO store : stores) {
                try {
                    syncStoreSkus(store.getId());
                    successCount++;
                    System.out.println("店铺 " + store.getId() + "(" + store.getStoreName() + ") SKU同步成功");
                } catch (Exception e) {
                    // 记录单个店铺同步失败的日志，但继续同步其他店铺
                    System.err.println("店铺 " + store.getId() + "(" + store.getStoreName() + ") SKU同步失败: " + e.getMessage());
                    failCount++;
                }
            }

            System.out.println("SKU同步完成: 成功 " + successCount + " 个店铺，失败 " + failCount + " 个店铺");
            return true;
        } catch (Exception e) {
            throw new RuntimeException("同步所有店铺SKU失败: " + e.getMessage(), e);
        }
    }

    /**
     * 同步单个店铺的SKU数据
     * @param storeId 店铺ID
     */
    public void syncStoreSkus(Long storeId) {
        // 实现SKU同步逻辑
        // 1. 获取店铺信息和访问令牌
        TemuStoreDO store = temuStoreService.getStore(storeId);
        String accessToken = store.getAccessToken();

        // 2. 创建TemuClient实例
        TemuClient temuClient = new TemuClient(temuProperties, accessToken);

        // 3. 设置请求参数
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("pageSize", 100); // 每页最大100条
        requestParams.put("pageNo", 1); // 从第一页开始
        requestParams.put("skuSearchType", 2);
        requestParams.put("skuStatusFilterType", 2);

        boolean hasMore = true;
        int pageNumber = 1;

        // 4. 循环获取所有页面的SKU数据
        while (hasMore) {
            requestParams.put("pageNo", pageNumber);

            // 5. 调用SKU列表接口
            SkuList response = temuClient.product.getLocalSkuList(requestParams);

            System.out.println("Temu SKU同步响应信息 - 第" + pageNumber + "页: " + response);

            // 6. 检查响应是否成功
            if (response != null && response.isSuccess()) {
                // 7. 处理SKU数据
                if (response.getResult() != null && response.getResult().getSkuList() != null) {
                    processSkuData(response.getResult().getSkuList(), storeId);

                    // 8. 检查是否还有更多数据
                    int totalItems = Math.toIntExact(response.getResult().getSkuList() != null ? response.getResult().getTotal() : 0);
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
                throw new RuntimeException("获取SKU列表失败: " + (response != null ? response.getErrorMsg() : "未知错误"));
            }
        }
    }

    /**
     * 处理SKU数据
     * @param skuList SKU列表
     * @param storeId 店铺ID
     */
    private void processSkuData(List<SkuList.SkuInfo> skuList, Long storeId) {
        if (skuList == null || skuList.isEmpty()) {
            return;
        }

        for (SkuList.SkuInfo skuInfo : skuList) {
            try {
                // 将SKU信息转换为DO对象
                TemuProductSkuDO productSkuDO = convertToProductSkuDO(skuInfo, storeId);

                // 保存或更新到数据库
                saveOrUpdateProductSku(productSkuDO);
            } catch (Exception e) {
                System.err.println("处理SKU数据失败: " + skuInfo.getSkuId() + ", 错误: " + e.getMessage());
            }
        }
    }

    /**
     * 将SKU信息转换为DO对象
     * @param skuInfo SKU信息
     * @param storeId 店铺ID
     * @return TemuProductSkuDO对象
     */
    private TemuProductSkuDO convertToProductSkuDO(SkuList.SkuInfo skuInfo, Long storeId) {
        TemuProductSkuDO productSkuDO = new TemuProductSkuDO();
        productSkuDO.setStoreId(storeId);

        // 基本信息映射
        productSkuDO.setSkuId(skuInfo.getSkuId());
        productSkuDO.setGoodsId(skuInfo.getGoodsId());
        productSkuDO.setGoodsId(skuInfo.getGoodsId());
        productSkuDO.setGoodsName(skuInfo.getGoodsName());
        productSkuDO.setThumbUrl(skuInfo.getThumbUrl());
        productSkuDO.setSpecName(skuInfo.getSpecName());
        productSkuDO.setCurrency(skuInfo.getCurrency());
        productSkuDO.setCrtTime(skuInfo.getCrtTime());
        productSkuDO.setSkuStatusChangeTime(skuInfo.getSkuStatusChangeTime());
        productSkuDO.setStatus4vo(skuInfo.getStatus4VO());
        productSkuDO.setSubStatus4vo(skuInfo.getSubStatus4VO());
        productSkuDO.setLowTrafficTag(skuInfo.getLowTrafficTag());
        productSkuDO.setRestrictedTrafficTag(skuInfo.getRestrictedTrafficTag());

        return productSkuDO;
    }

    /**
     * 保存或更新商品SKU信息
     * @param productSkuDO 商品SKU DO对象
     */
    private void saveOrUpdateProductSku(TemuProductSkuDO productSkuDO) {
        // 根据skuId检查SKU是否已存在
        QueryWrapper<TemuProductSkuDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sku_id", productSkuDO.getSkuId()); // 需要根据实际字段名调整

        TemuProductSkuDO existingProduct = productSkuMapper.selectOne(queryWrapper);

        if (existingProduct != null) {
            // 更新现有SKU
            productSkuDO.setId(existingProduct.getId());
            productSkuMapper.updateById(productSkuDO);
            System.out.println("更新SKU: " + productSkuDO.getSkuId());
        } else {
            // 插入新SKU
            productSkuMapper.insert(productSkuDO);
            System.out.println("新增SKU: " + productSkuDO.getSkuId());
        }
    }

}