package com.zida.cbec.module.temu.controller.admin.productspu;

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

import com.zida.cbec.module.temu.controller.admin.productspu.vo.*;
import com.zida.cbec.module.temu.dal.dataobject.productspu.TemuProductSpuDO;
import com.zida.cbec.module.temu.service.productspu.TemuProductSpuService;

@Tag(name = "管理后台 - Temu SPU")
@RestController
@RequestMapping("/temu/product-spu")
@Validated
public class TemuProductSpuController {

    @Resource
    private TemuProductSpuService productSpuService;

    @PostMapping("/create")
    @Operation(summary = "创建Temu SPU")
    @PreAuthorize("@ss.hasPermission('temu:product-spu:create')")
    public CommonResult<Long> createProductSpu(@Valid @RequestBody TemuProductSpuSaveReqVO createReqVO) {
        return success(productSpuService.createProductSpu(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新Temu SPU")
    @PreAuthorize("@ss.hasPermission('temu:product-spu:update')")
    public CommonResult<Boolean> updateProductSpu(@Valid @RequestBody TemuProductSpuSaveReqVO updateReqVO) {
        productSpuService.updateProductSpu(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除Temu SPU")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('temu:product-spu:delete')")
    public CommonResult<Boolean> deleteProductSpu(@RequestParam("id") Long id) {
        productSpuService.deleteProductSpu(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除Temu SPU")
                @PreAuthorize("@ss.hasPermission('temu:product-spu:delete')")
    public CommonResult<Boolean> deleteProductSpuList(@RequestParam("ids") List<Long> ids) {
        productSpuService.deleteProductSpuListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得Temu SPU")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('temu:product-spu:query')")
    public CommonResult<TemuProductSpuRespVO> getProductSpu(@RequestParam("id") Long id) {
        TemuProductSpuDO productSpu = productSpuService.getProductSpu(id);
        return success(BeanUtils.toBean(productSpu, TemuProductSpuRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得Temu SPU分页")
    @PreAuthorize("@ss.hasPermission('temu:product-spu:query')")
    public CommonResult<PageResult<TemuProductSpuRespVO>> getProductSpuPage(@Valid TemuProductSpuPageReqVO pageReqVO) {
        PageResult<TemuProductSpuDO> pageResult = productSpuService.getProductSpuPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TemuProductSpuRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出Temu SPU Excel")
    @PreAuthorize("@ss.hasPermission('temu:product-spu:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportProductSpuExcel(@Valid TemuProductSpuPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TemuProductSpuDO> list = productSpuService.getProductSpuPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "Temu SPU.xls", "数据", TemuProductSpuRespVO.class,
                        BeanUtils.toBean(list, TemuProductSpuRespVO.class));
    }

}