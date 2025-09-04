package com.zida.cbec.module.temu.service.productspu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zida.cbec.framework.security.core.util.SecurityFrameworkUtils;
import com.zida.cbec.module.temu.controller.temu.client.TemuClient;
import com.zida.cbec.module.temu.controller.temu.resp.product.GoodsList;
import com.zida.cbec.module.temu.dal.dataobject.store.TemuStoreDO;
import com.zida.cbec.module.temu.framework.temu.config.TemuProperties;
import com.zida.cbec.module.temu.service.store.TemuStoreService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.*;
import com.zida.cbec.module.temu.controller.admin.productspu.vo.*;
import com.zida.cbec.module.temu.dal.dataobject.productspu.TemuProductSpuDO;
import com.zida.cbec.framework.common.pojo.PageResult;
import com.zida.cbec.framework.common.util.object.BeanUtils;

import com.zida.cbec.module.temu.dal.mysql.productspu.TemuProductSpuMapper;

import static com.zida.cbec.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zida.cbec.framework.common.util.collection.CollectionUtils.convertList;
import static com.zida.cbec.module.temu.enums.ErrorCodeConstants.*;

/**
 * Temu SPU Service 实现类
 *
 * @author 自达源码
 */
@Service
@Validated
public class TemuProductSpuServiceImpl implements TemuProductSpuService {

    @Resource
    private TemuProductSpuMapper productSpuMapper;

    @Resource
    private TemuStoreService temuStoreService;

    @Resource
    private TemuProperties temuProperties;

    @Override
    public Long createProductSpu(TemuProductSpuSaveReqVO createReqVO) {
        // 插入
        TemuProductSpuDO productSpu = BeanUtils.toBean(createReqVO, TemuProductSpuDO.class);
        productSpuMapper.insert(productSpu);

        // 返回
        return productSpu.getId();
    }

    @Override
    public void updateProductSpu(TemuProductSpuSaveReqVO updateReqVO) {
        // 校验存在
        validateProductSpuExists(updateReqVO.getId());
        // 更新
        TemuProductSpuDO updateObj = BeanUtils.toBean(updateReqVO, TemuProductSpuDO.class);
        productSpuMapper.updateById(updateObj);
    }

    @Override
    public void deleteProductSpu(Long id) {
        // 校验存在
        validateProductSpuExists(id);
        // 删除
        productSpuMapper.deleteById(id);
    }

    @Override
        public void deleteProductSpuListByIds(List<Long> ids) {
        // 删除
        productSpuMapper.deleteByIds(ids);
        }


    private void validateProductSpuExists(Long id) {
        if (productSpuMapper.selectById(id) == null) {
            throw exception(PRODUCT_SPU_NOT_EXISTS);
        }
    }

    @Override
    public TemuProductSpuDO getProductSpu(Long id) {
        return productSpuMapper.selectById(id);
    }

    @Override
    public PageResult<TemuProductSpuDO> getProductSpuPage(TemuProductSpuPageReqVO pageReqVO) {
        return productSpuMapper.selectPage(pageReqVO);
    }

    @Override
    public Boolean syncAllStoreProducts() {
        try {
            // 获取当前登录用户ID
            Long userId = SecurityFrameworkUtils.getLoginUserId();
            System.out.println("当前登录用户ID: " + userId);

            // 获取当前用户的所有店铺
            List<TemuStoreDO> stores = temuStoreService.getStoresByUserId(String.valueOf(userId));

            int successCount = 0;
            int failCount = 0;

            // 遍历所有店铺并同步商品
            for (TemuStoreDO store : stores) {
                try {
                    syncStoreProducts(store.getId());
                    successCount++;
                    System.out.println("店铺 " + store.getId() + "(" + store.getStoreName() + ") 商品同步成功");
                } catch (Exception e) {
                    // 记录单个店铺同步失败的日志，但继续同步其他店铺
                    System.err.println("店铺 " + store.getId() + "(" + store.getStoreName() + ") 商品同步失败: " + e.getMessage());
                    failCount++;
                }
            }

            System.out.println("商品同步完成: 成功 " + successCount + " 个店铺，失败 " + failCount + " 个店铺");
            return true;
        } catch (Exception e) {
            throw new RuntimeException("同步所有店铺商品失败: " + e.getMessage(), e);
        }
    }

    /**
     * 同步单个店铺的商品数据
     * @param storeId 店铺ID
     */
    public void syncStoreProducts(Long storeId) {
        // 实现商品同步逻辑
        // 1. 获取店铺信息和访问令牌
        TemuStoreDO store = temuStoreService.getStore(storeId);
        String accessToken = store.getAccessToken();

        // 2. 创建TemuClient实例
        TemuClient temuClient = new TemuClient(temuProperties, accessToken);

        // 3. 创建Product API实例

        // 4. 设置请求参数
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("pageNo", 1); // 每页最大100条
        requestParams.put("pageSize", 100); // 从第一页开始
        requestParams.put("goodsSearchType", 1); // 从第一页开始

        boolean hasMore = true;
        int pageNumber = 1;

        // 5. 循环获取所有页面的商品数据
        while (hasMore) {
            requestParams.put("pageNo", pageNumber);

            // 6. 调用商品列表接口
            GoodsList response = temuClient.product.getLocalGoodsList(requestParams);

            System.out.println("Temu商品同步响应信息 - 第" + pageNumber + "页: " + response);

            // 7. 检查响应是否成功
            if (response != null && response.isSuccess()) {
                // 8. 处理商品数据
                if (response.getResult() != null && response.getResult().getGoodsList() != null) {
                    processProductData(response.getResult().getGoodsList(), storeId);

                    // 9. 检查是否还有更多数据
                    int totalItems = Math.toIntExact(response.getResult().getGoodsList() != null ? response.getResult().getTotal() : 0);
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
                throw new RuntimeException("获取商品列表失败: " + (response != null ? response.getErrorMsg() : "未知错误"));
            }
        }
    }

    /**
     * 处理商品数据
     * @param pageItems 页面商品项列表
     * @param storeId 店铺ID
     */
    private void processProductData(List<GoodsList.GoodsInfo> pageItems, Long storeId) {
        if (pageItems == null || pageItems.isEmpty()) {
            return;
        }

        for (GoodsList.GoodsInfo pageItem : pageItems) {
            // 处理每个商品数据
            // 1. 转换为DO对象
            // 2. 保存或更新到数据库
            try {
                // 将商品信息转换为DO对象
                TemuProductSpuDO productSpuDO = convertToProductSpuDO(pageItem, storeId);

                // 保存或更新到数据库
                saveOrUpdateProductSpu(productSpuDO);
            } catch (Exception e) {
                System.err.println("处理商品数据失败: " + pageItem.getGoodsId() + ", 错误: " + e.getMessage());
            }
        }
    }

    /**
     * 将商品信息转换为DO对象
     * @param goodsInfo 商品信息
     * @param storeId 店铺ID
     * @return TemuProductSpuDO对象
     */
    private TemuProductSpuDO convertToProductSpuDO(GoodsList.GoodsInfo goodsInfo, Long storeId) {
        TemuProductSpuDO productSpuDO = new TemuProductSpuDO();

        // 基本信息映射
        productSpuDO.setGoodsId(goodsInfo.getGoodsId());
        productSpuDO.setGoodsName(goodsInfo.getGoodsName());
        productSpuDO.setOutGoodsSn(goodsInfo.getOutGoodsSn());
        productSpuDO.setCatId(goodsInfo.getCatId());
        productSpuDO.setCurrency(goodsInfo.getCurrency());

        // 价格信息映射
        Long marketPriceObj = goodsInfo.getMarketPrice();
        // 错误：左侧是Long（包装类型），右侧三元表达式返回long（基本类型），类型不兼容
        Long marketPrice = (marketPriceObj != null) ? marketPriceObj : 0L;
        productSpuDO.setMarketPrice(marketPrice);
        productSpuDO.setPrice(goodsInfo.getRetailPrice().getAmount());
        productSpuDO.setRetailPriceAmount(goodsInfo.getRetailPrice().getAmount());
        productSpuDO.setRetailPriceCurrency(goodsInfo.getRetailPrice().getCurrency());
        BigDecimal listPriceAmount = getPriceAmount(goodsInfo.getListPrice());
        String listPriceCurrency = getPriceCurrency(goodsInfo.getListPrice(), "USD");

        productSpuDO.setListPriceAmount(listPriceAmount);
        productSpuDO.setListPriceCurrency(listPriceCurrency);

        // 库存信息
        productSpuDO.setQuantity(Long.valueOf(goodsInfo.getQuantity()));

        // 图片信息
        productSpuDO.setThumbUrl(goodsInfo.getThumbUrl());

        // 时间信息
        productSpuDO.setCrtTime(goodsInfo.getCrtTime());
        productSpuDO.setGoodsStatusChangeTime(goodsInfo.getGoodsStatusChangeTime());

        // 状态信息
        productSpuDO.setGoodsShowSubStatus(goodsInfo.getGoodsShowSubStatus());
        productSpuDO.setStatus4vo(goodsInfo.getStatus4VO());
        productSpuDO.setSubStatus4vo(goodsInfo.getSubStatus4VO());
        productSpuDO.setLowTrafficTag(goodsInfo.getLowTrafficTag());
        productSpuDO.setRestrictedTrafficTag(goodsInfo.getRestrictedTrafficTag());

        // 其他信息
        productSpuDO.setCostTemplateId(goodsInfo.getCostTemplateId());
        Integer shipmentLimitSecondObj = goodsInfo.getShipmentLimitSecond();
        Integer shipmentLimitSecond = Math.toIntExact((shipmentLimitSecondObj != null) ? shipmentLimitSecondObj : 0L);
        productSpuDO.setShipmentLimitSecond(shipmentLimitSecond);
        productSpuDO.setTrademarkId(goodsInfo.getTrademarkId());
        productSpuDO.setBrandId(goodsInfo.getBrandId());
        productSpuDO.setSpecName(goodsInfo.getSpecName());

        return productSpuDO;
    }
    /**
     * 安全获取价格金额
     */
    public static BigDecimal getPriceAmount(GoodsList.PriceInfo priceInfo) {
        if (priceInfo != null && priceInfo.getAmount() != null) {
            return priceInfo.getAmount();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 安全获取货币类型
     */
    public static String getPriceCurrency(GoodsList.PriceInfo priceInfo, String defaultCurrency) {
        if (priceInfo != null && priceInfo.getCurrency() != null) {
            return priceInfo.getCurrency();
        }
        return defaultCurrency;
    }
    /**
     * 保存或更新商品SPU信息
     * @param productSpuDO 商品SPU DO对象
     */
    private void saveOrUpdateProductSpu(TemuProductSpuDO productSpuDO) {
        // 根据goodsId检查商品是否已存在
        QueryWrapper<TemuProductSpuDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id", productSpuDO.getGoodsId());

        TemuProductSpuDO existingProduct = productSpuMapper.selectOne(queryWrapper);

        if (existingProduct != null) {
            // 更新现有商品
            productSpuDO.setId(existingProduct.getId());
            productSpuMapper.updateById(productSpuDO);
            System.out.println("更新商品: " + productSpuDO.getGoodsName());
        } else {
            // 插入新商品
            productSpuMapper.insert(productSpuDO);
            System.out.println("新增商品: " + productSpuDO.getGoodsName());
        }
    }
}