package com.zida.cbec.module.infra.api.logger;

import com.zida.cbec.framework.common.biz.infra.logger.ApiErrorLogCommonApi;
import com.zida.cbec.framework.common.biz.infra.logger.dto.ApiErrorLogCreateReqDTO;
import com.zida.cbec.module.infra.service.logger.ApiErrorLogService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

/**
 * API 访问日志的 API 接口
 *
 * @author 自达源码
 */
@Service
@Validated
public class ApiErrorLogApiImpl implements ApiErrorLogCommonApi {

    @Resource
    private ApiErrorLogService apiErrorLogService;

    @Override
    public void createApiErrorLog(ApiErrorLogCreateReqDTO createDTO) {
        apiErrorLogService.createApiErrorLog(createDTO);
    }

}
