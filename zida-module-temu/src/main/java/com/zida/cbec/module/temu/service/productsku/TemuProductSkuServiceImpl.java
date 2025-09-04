package com.zida.cbec.module.temu.service.productsku;

import cn.hutool.core.collection.CollUtil;
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

}