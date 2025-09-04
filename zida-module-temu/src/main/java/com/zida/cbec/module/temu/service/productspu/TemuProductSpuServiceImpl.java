package com.zida.cbec.module.temu.service.productspu;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.zida.cbec.module.temu.controller.admin.productspu.vo.*;
import com.zida.cbec.module.temu.dal.dataobject.productspu.TemuProductSpuDO;
import com.zida.cbec.framework.common.pojo.PageResult;
import com.zida.cbec.framework.common.pojo.PageParam;
import com.zida.cbec.framework.common.util.object.BeanUtils;

import com.zida.cbec.module.temu.dal.mysql.productspu.TemuProductSpuMapper;

import static com.zida.cbec.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zida.cbec.framework.common.util.collection.CollectionUtils.convertList;
import static com.zida.cbec.framework.common.util.collection.CollectionUtils.diffList;
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

}