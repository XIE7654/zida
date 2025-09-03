package com.zida.cbec.module.temu.service.store;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zida.cbec.module.temu.controller.temu.resp.AccessTokenInfo;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import com.zida.cbec.module.temu.controller.admin.store.vo.*;
import com.zida.cbec.module.temu.dal.dataobject.store.TemuStoreDO;
import com.zida.cbec.framework.common.pojo.PageResult;
import com.zida.cbec.framework.common.pojo.PageParam;
import com.zida.cbec.framework.common.util.object.BeanUtils;

import com.zida.cbec.module.temu.dal.mysql.store.TemuStoreMapper;

import static com.zida.cbec.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zida.cbec.framework.common.util.collection.CollectionUtils.convertList;
import static com.zida.cbec.framework.common.util.collection.CollectionUtils.diffList;
import static com.zida.cbec.module.temu.enums.ErrorCodeConstants.*;

/**
 * Temu 店铺授权信息 Service 实现类
 *
 * @author 自达源码
 */
@Service
@Validated
public class TemuStoreServiceImpl implements TemuStoreService {

    @Resource
    private TemuStoreMapper storeMapper;

    @Override
    public Long createStore(TemuStoreSaveReqVO createReqVO) {
        // 插入
        TemuStoreDO store = BeanUtils.toBean(createReqVO, TemuStoreDO.class);
        storeMapper.insert(store);

        // 返回
        return store.getId();
    }

    @Override
    public void updateStore(TemuStoreSaveReqVO updateReqVO) {
        // 校验存在
        validateStoreExists(updateReqVO.getId());
        // 更新
        TemuStoreDO updateObj = BeanUtils.toBean(updateReqVO, TemuStoreDO.class);
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

    @Override
    public TemuStoreDO getStore(Long id) {
        return storeMapper.selectById(id);
    }

    @Override
    public PageResult<TemuStoreDO> getStorePage(TemuStorePageReqVO pageReqVO) {
        return storeMapper.selectPage(pageReqVO);
    }



    private void validateStoreExists(Long id) {
        if (storeMapper.selectById(id) == null) {
            throw exception(STORE_NOT_EXISTS);
        }
    }

    @Override
    public void updateStoreByAuthInfo(Long id, AccessTokenInfo response) {
        // 校验存在
        validateStoreExists(id);

        // 创建更新对象
        // 先从数据库中查询现有对象
        TemuStoreDO existingStore = storeMapper.selectById(id);

        // 更新需要修改的字段
        existingStore.setRegionId(response.getResult().getRegionId());
        existingStore.setMallType(response.getResult().getMallType());
        existingStore.setMallId(response.getResult().getMallId());
        existingStore.setExpiredTime(response.getResult().getExpiredTime());
        existingStore.setApiScopeList(response.getResult().getApiScopeList().toString());
        existingStore.setAppSubscribeEventCodeList(response.getResult().getAppSubscribeEventCodeList().toString());
        existingStore.setAuthEventCodeList(response.getResult().getAuthEventCodeList().toString());
        // 使用本地实际时间作为授权时间
        existingStore.setAuthTime(LocalDateTime.now());
        // 更新数据库
        storeMapper.updateById(existingStore);
    }

    @Override
    public List<TemuStoreDO> getStoresByUserId(String creator) {
        if (creator == null || creator.isEmpty()) {
            return new ArrayList<>();
        }

        // 使用 MyBatis Plus 的 QueryWrapper
        QueryWrapper<TemuStoreDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("creator", creator);
        queryWrapper.eq("deleted", 0); // 假设有软删除字段

        return storeMapper.selectList(queryWrapper);
    }
}