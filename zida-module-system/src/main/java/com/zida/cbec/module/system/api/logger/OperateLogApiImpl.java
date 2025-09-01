package com.zida.cbec.module.system.api.logger;

import com.zida.cbec.framework.common.pojo.PageResult;
import com.zida.cbec.framework.common.util.object.BeanUtils;
import com.zida.cbec.framework.common.biz.system.logger.dto.OperateLogCreateReqDTO;
import com.zida.cbec.module.system.api.logger.dto.OperateLogPageReqDTO;
import com.zida.cbec.module.system.api.logger.dto.OperateLogRespDTO;
import com.zida.cbec.module.system.dal.dataobject.logger.OperateLogDO;
import com.zida.cbec.module.system.service.logger.OperateLogService;
import com.fhs.core.trans.anno.TransMethodResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 操作日志 API 实现类
 *
 * @author 自达源码
 */
@Service
@Validated
public class OperateLogApiImpl implements OperateLogApi {

    @Resource
    private OperateLogService operateLogService;

    @Override
    public void createOperateLog(OperateLogCreateReqDTO createReqDTO) {
        operateLogService.createOperateLog(createReqDTO);
    }

    @Override
    @TransMethodResult
    public PageResult<OperateLogRespDTO> getOperateLogPage(OperateLogPageReqDTO pageReqDTO) {
        PageResult<OperateLogDO> operateLogPage = operateLogService.getOperateLogPage(pageReqDTO);
        return BeanUtils.toBean(operateLogPage, OperateLogRespDTO.class);
    }

}
