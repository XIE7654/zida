package com.zida.cbec.module.ebay.service.store;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import com.zida.cbec.module.ebay.controller.admin.store.vo.*;
import com.zida.cbec.module.ebay.dal.dataobject.store.EbayStoreDO;
import com.zida.cbec.framework.common.pojo.PageResult;
import com.zida.cbec.framework.common.pojo.PageParam;
import com.zida.cbec.framework.common.util.object.BeanUtils;

import com.zida.cbec.module.ebay.dal.mysql.store.EbayStoreMapper;

import static com.zida.cbec.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zida.cbec.framework.common.util.collection.CollectionUtils.convertList;
import static com.zida.cbec.framework.common.util.collection.CollectionUtils.diffList;
import static com.zida.cbec.module.ebay.enums.ErrorCodeConstants.*;

/**
 * eBay店铺 Service 实现类
 *
 * @author 自达源码
 */
@Service
@Validated
public class EbayStoreServiceImpl implements EbayStoreService {

    @Resource
    private EbayStoreMapper storeMapper;

    @Override
    public Long createStore(EbayStoreSaveReqVO createReqVO) {
        // 插入
        EbayStoreDO store = BeanUtils.toBean(createReqVO, EbayStoreDO.class);
        storeMapper.insert(store);

        // 返回
        return store.getId();
    }

    @Override
    public void updateStore(EbayStoreSaveReqVO updateReqVO) {
        // 校验存在
        validateStoreExists(updateReqVO.getId());
        // 更新
        EbayStoreDO updateObj = BeanUtils.toBean(updateReqVO, EbayStoreDO.class);
        storeMapper.updateById(updateObj);
    }

    @Override
    public void deleteStore(Long id) {
        // 校验存在
        validateStoreExists(id);
        // 删除
        storeMapper.deleteById(id);
    }

    @Override
        public void deleteStoreListByIds(List<Long> ids) {
        // 删除
        storeMapper.deleteByIds(ids);
        }


    private void validateStoreExists(Long id) {
        if (storeMapper.selectById(id) == null) {
            throw exception(STORE_NOT_EXISTS);
        }
    }

    @Override
    public EbayStoreDO getStore(Long id) {
        return storeMapper.selectById(id);
    }

    @Override
    public PageResult<EbayStoreDO> getStorePage(EbayStorePageReqVO pageReqVO) {
        return storeMapper.selectPage(pageReqVO);
    }

    @Override
    public void updateStoreOAuthTokens(Long storeId, String accessToken, String refreshToken,
                                       Integer expiresIn, Integer refreshTokenExpiresIn) {
        // 校验店铺是否存在
        validateStoreExists(storeId);

        // 创建更新对象
        EbayStoreDO updateObj = new EbayStoreDO();
        updateObj.setId(storeId);
        updateObj.setAccessToken(accessToken);
        updateObj.setRefreshToken(refreshToken);
        updateObj.setAuthorizationType("API授权");

        // 计算过期时间
        LocalDateTime now = LocalDateTime.now();
        updateObj.setAccessTokenExpiresIn(now.plusSeconds(expiresIn));
        updateObj.setRefreshTokenExpiresIn(now.plusSeconds(refreshTokenExpiresIn));

        // 更新数据库
        storeMapper.updateById(updateObj);
    }

    @Override
    public void updateStoreAccessToken(Long storeId, String accessToken, Integer expiresIn) {
        // 校验店铺是否存在
        validateStoreExists(storeId);

        // 创建更新对象
        EbayStoreDO updateObj = new EbayStoreDO();
        updateObj.setId(storeId);
        updateObj.setAccessToken(accessToken);

        // 计算过期时间
        LocalDateTime now = LocalDateTime.now();
        updateObj.setAccessTokenExpiresIn(now.plusSeconds(expiresIn));

        // 更新数据库
        storeMapper.updateById(updateObj);
    }
}