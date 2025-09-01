package com.zida.cbec.module.infra.api.logger;

import com.zida.cbec.framework.common.biz.infra.logger.ApiAccessLogCommonApi;
import com.zida.cbec.framework.common.biz.infra.logger.dto.ApiAccessLogCreateReqDTO;
import com.zida.cbec.module.infra.service.logger.ApiAccessLogService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * API 访问日志的 API 实现类
 *
 * @author 自达源码
 */
@Service
@Validated
public class ApiAccessLogApiImpl implements ApiAccessLogCommonApi {

    @Resource
    private ApiAccessLogService apiAccessLogService;

    @Override
    public void createApiAccessLog(ApiAccessLogCreateReqDTO createDTO) {
        apiAccessLogService.createApiAccessLog(createDTO);
    }

}
