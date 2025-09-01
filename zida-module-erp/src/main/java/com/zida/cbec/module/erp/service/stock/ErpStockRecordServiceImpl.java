package com.zida.cbec.module.erp.service.stock;

import com.zida.cbec.framework.common.pojo.PageResult;
import com.zida.cbec.framework.common.util.object.BeanUtils;
import com.zida.cbec.module.erp.controller.admin.stock.vo.record.ErpStockRecordPageReqVO;
import com.zida.cbec.module.erp.dal.dataobject.stock.ErpStockRecordDO;
import com.zida.cbec.module.erp.dal.mysql.stock.ErpStockRecordMapper;
import com.zida.cbec.module.erp.service.stock.bo.ErpStockRecordCreateReqBO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

/**
 * ERP 产品库存明细 Service 实现类
 *
 * @author 自达源码
 */
@Service
@Validated
public class ErpStockRecordServiceImpl implements ErpStockRecordService {

    @Resource
    private ErpStockRecordMapper stockRecordMapper;

    @Resource
    private ErpStockService stockService;

    @Override
    public ErpStockRecordDO getStockRecord(Long id) {
        return stockRecordMapper.selectById(id);
    }

    @Override
    public PageResult<ErpStockRecordDO> getStockRecordPage(ErpStockRecordPageReqVO pageReqVO) {
        return stockRecordMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createStockRecord(ErpStockRecordCreateReqBO createReqBO) {
        // 1. 更新库存
        BigDecimal totalCount = stockService.updateStockCountIncrement(
                createReqBO.getProductId(), createReqBO.getWarehouseId(), createReqBO.getCount());
        // 2. 创建库存明细
        ErpStockRecordDO stockRecord = BeanUtils.toBean(createReqBO, ErpStockRecordDO.class)
                .setTotalCount(totalCount);
        stockRecordMapper.insert(stockRecord);
    }

}