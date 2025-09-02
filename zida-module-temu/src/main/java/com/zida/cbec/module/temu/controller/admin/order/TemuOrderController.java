package com.zida.cbec.module.temu.controller.admin.order;

import com.zida.cbec.module.temu.controller.temu.resp.OrderDetail;
import com.zida.cbec.module.temu.controller.temu.resp.OrderList;
import com.zida.cbec.module.temu.controller.temu.resp.ShippingInfo;
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

import com.zida.cbec.module.temu.controller.admin.order.vo.*;
import com.zida.cbec.module.temu.dal.dataobject.order.TemuOrderDO;
import com.zida.cbec.module.temu.service.order.TemuOrderService;

@Tag(name = "管理后台 - Temu订单")
@RestController
@RequestMapping("/temu/order")
@Validated
public class TemuOrderController {

    @Resource
    private TemuOrderService orderService;

    @PostMapping("/create")
    @Operation(summary = "创建Temu订单")
    @PreAuthorize("@ss.hasPermission('temu:order:create')")
    public CommonResult<Long> createOrder(@Valid @RequestBody TemuOrderSaveReqVO createReqVO) {
        return success(orderService.createOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新Temu订单")
    @PreAuthorize("@ss.hasPermission('temu:order:update')")
    public CommonResult<Boolean> updateOrder(@Valid @RequestBody TemuOrderSaveReqVO updateReqVO) {
        orderService.updateOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除Temu订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('temu:order:delete')")
    public CommonResult<Boolean> deleteOrder(@RequestParam("id") Long id) {
        orderService.deleteOrder(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除Temu订单")
                @PreAuthorize("@ss.hasPermission('temu:order:delete')")
    public CommonResult<Boolean> deleteOrderList(@RequestParam("ids") List<Long> ids) {
        orderService.deleteOrderListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得Temu订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('temu:order:query')")
    public CommonResult<TemuOrderRespVO> getOrder(@RequestParam("id") Long id) {
        TemuOrderDO order = orderService.getOrder(id);
        return success(BeanUtils.toBean(order, TemuOrderRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得Temu订单分页")
    @PreAuthorize("@ss.hasPermission('temu:order:query')")
    public CommonResult<PageResult<TemuOrderRespVO>> getOrderPage(@Valid TemuOrderPageReqVO pageReqVO) {
        PageResult<TemuOrderDO> pageResult = orderService.getOrderPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TemuOrderRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出Temu订单 Excel")
    @PreAuthorize("@ss.hasPermission('temu:order:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOrderExcel(@Valid TemuOrderPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TemuOrderDO> list = orderService.getOrderPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "Temu订单.xls", "数据", TemuOrderRespVO.class,
                        BeanUtils.toBean(list, TemuOrderRespVO.class));
    }

    @PostMapping("/sync-all")
    @Operation(summary = "同步所有店铺订单")
    @PreAuthorize("@ss.hasPermission('temu:order:update')")
    public CommonResult<Boolean> syncAllOrders() {
        try {
            Boolean result = orderService.syncAllStoreOrders();
            return success(result);
        } catch (Exception e) {
            throw new RuntimeException("同步所有店铺订单失败: " + e.getMessage());
        }
    }

    @PostMapping("/sync-shipping-info")
    @Operation(summary = "同步订单配送信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('temu:order:update')")
    public CommonResult<ShippingInfo> syncOrderShippingInfo(
            @RequestParam("id") Long id) {
        try {
            ShippingInfo response = orderService.syncOrderShippingInfo(id);
            return success(response);
        } catch (Exception e) {
            throw new RuntimeException("同步订单配送信息失败: " + e.getMessage(), e);
        }
    }


    @PostMapping("/sync-order-detail")
    @Operation(summary = "同步订单详情")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('temu:order:update')")
    public CommonResult<OrderDetail> syncOrderDetail(
            @RequestParam("id") Long id) {
        try {
            OrderDetail response = orderService.syncOrderDetail(id);
            return success(response);
        } catch (Exception e) {
            throw new RuntimeException("同步订单详情失败: " + e.getMessage(), e);
        }
    }
}