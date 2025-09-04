package com.zida.cbec.module.temu.controller.admin.productsku;

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

import com.zida.cbec.module.temu.controller.admin.productsku.vo.*;
import com.zida.cbec.module.temu.dal.dataobject.productsku.TemuProductSkuDO;
import com.zida.cbec.module.temu.service.productsku.TemuProductSkuService;

@Tag(name = "管理后台 - Temu SKU明细")
@RestController
@RequestMapping("/temu/product-sku")
@Validated
public class TemuProductSkuController {

    @Resource
    private TemuProductSkuService productSkuService;

    @PostMapping("/create")
    @Operation(summary = "创建Temu SKU明细")
    @PreAuthorize("@ss.hasPermission('temu:product-sku:create')")
    public CommonResult<Long> createProductSku(@Valid @RequestBody TemuProductSkuSaveReqVO createReqVO) {
        return success(productSkuService.createProductSku(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新Temu SKU明细")
    @PreAuthorize("@ss.hasPermission('temu:product-sku:update')")
    public CommonResult<Boolean> updateProductSku(@Valid @RequestBody TemuProductSkuSaveReqVO updateReqVO) {
        productSkuService.updateProductSku(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除Temu SKU明细")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('temu:product-sku:delete')")
    public CommonResult<Boolean> deleteProductSku(@RequestParam("id") Long id) {
        productSkuService.deleteProductSku(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除Temu SKU明细")
                @PreAuthorize("@ss.hasPermission('temu:product-sku:delete')")
    public CommonResult<Boolean> deleteProductSkuList(@RequestParam("ids") List<Long> ids) {
        productSkuService.deleteProductSkuListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得Temu SKU明细")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('temu:product-sku:query')")
    public CommonResult<TemuProductSkuRespVO> getProductSku(@RequestParam("id") Long id) {
        TemuProductSkuDO productSku = productSkuService.getProductSku(id);
        return success(BeanUtils.toBean(productSku, TemuProductSkuRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得Temu SKU明细分页")
    @PreAuthorize("@ss.hasPermission('temu:product-sku:query')")
    public CommonResult<PageResult<TemuProductSkuRespVO>> getProductSkuPage(@Valid TemuProductSkuPageReqVO pageReqVO) {
        PageResult<TemuProductSkuDO> pageResult = productSkuService.getProductSkuPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TemuProductSkuRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出Temu SKU明细 Excel")
    @PreAuthorize("@ss.hasPermission('temu:product-sku:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportProductSkuExcel(@Valid TemuProductSkuPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TemuProductSkuDO> list = productSkuService.getProductSkuPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "Temu SKU明细.xls", "数据", TemuProductSkuRespVO.class,
                        BeanUtils.toBean(list, TemuProductSkuRespVO.class));
    }

}