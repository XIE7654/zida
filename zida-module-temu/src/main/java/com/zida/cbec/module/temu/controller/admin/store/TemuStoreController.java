package com.zida.cbec.module.temu.controller.admin.store;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import com.zida.cbec.framework.common.pojo.PageParam;
import com.zida.cbec.framework.common.pojo.PageResult;
import com.zida.cbec.framework.common.pojo.CommonResult;
import com.zida.cbec.framework.common.util.object.BeanUtils;
import static com.zida.cbec.framework.common.pojo.CommonResult.success;

import com.zida.cbec.framework.excel.core.util.ExcelUtils;

import com.zida.cbec.framework.apilog.core.annotation.ApiAccessLog;
import static com.zida.cbec.framework.apilog.core.enums.OperateTypeEnum.*;

import com.zida.cbec.module.temu.controller.admin.store.vo.*;
import com.zida.cbec.module.temu.dal.dataobject.store.TemuStoreDO;
import com.zida.cbec.module.temu.service.store.TemuStoreService;

@Tag(name = "管理后台 - Temu 店铺授权信息")
@RestController
@RequestMapping("/temu/store")
@Validated
public class TemuStoreController {

    @Resource
    private TemuStoreService storeService;

    @PostMapping("/create")
    @Operation(summary = "创建Temu 店铺授权信息")
    @PreAuthorize("@ss.hasPermission('temu:store:create')")
    public CommonResult<Long> createStore(@Valid @RequestBody TemuStoreSaveReqVO createReqVO) {
        return success(storeService.createStore(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新Temu 店铺授权信息")
    @PreAuthorize("@ss.hasPermission('temu:store:update')")
    public CommonResult<Boolean> updateStore(@Valid @RequestBody TemuStoreSaveReqVO updateReqVO) {
        storeService.updateStore(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除Temu 店铺授权信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('temu:store:delete')")
    public CommonResult<Boolean> deleteStore(@RequestParam("id") Long id) {
        storeService.deleteStore(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除Temu 店铺授权信息")
                @PreAuthorize("@ss.hasPermission('temu:store:delete')")
    public CommonResult<Boolean> deleteStoreList(@RequestParam("ids") List<Long> ids) {
        storeService.deleteStoreListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得Temu 店铺授权信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('temu:store:query')")
    public CommonResult<TemuStoreRespVO> getStore(@RequestParam("id") Long id) {
        TemuStoreDO store = storeService.getStore(id);
        return success(BeanUtils.toBean(store, TemuStoreRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得Temu 店铺授权信息分页")
    @PreAuthorize("@ss.hasPermission('temu:store:query')")
    public CommonResult<PageResult<TemuStoreRespVO>> getStorePage(@Valid TemuStorePageReqVO pageReqVO) {
        PageResult<TemuStoreDO> pageResult = storeService.getStorePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TemuStoreRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出Temu 店铺授权信息 Excel")
    @PreAuthorize("@ss.hasPermission('temu:store:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStoreExcel(@Valid TemuStorePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TemuStoreDO> list = storeService.getStorePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "Temu 店铺授权信息.xls", "数据", TemuStoreRespVO.class,
                        BeanUtils.toBean(list, TemuStoreRespVO.class));
    }

}