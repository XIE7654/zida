package com.zida.cbec.module.ebay.controller.admin.store;

import com.zida.cbec.module.ebay.framework.web.config.EbayApiProperties;
import com.zida.cbec.module.ebay.service.oauth.EbayOAuthService;
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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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

import com.zida.cbec.module.ebay.controller.admin.store.vo.*;
import com.zida.cbec.module.ebay.dal.dataobject.store.EbayStoreDO;
import com.zida.cbec.module.ebay.service.store.EbayStoreService;

@Tag(name = "管理后台 - eBay店铺")
@RestController
@RequestMapping("/ebay/store")
@Validated
public class EbayStoreController {

    @Resource
    private EbayStoreService storeService;

    @Resource
    private EbayOAuthService ebayOAuthService;

    @Resource
    private EbayApiProperties ebayApiProperties;

    @PostMapping("/create")
    @Operation(summary = "创建eBay店铺")
    @PreAuthorize("@ss.hasPermission('ebay:store:create')")
    public CommonResult<Long> createStore(@Valid @RequestBody EbayStoreSaveReqVO createReqVO) {
        return success(storeService.createStore(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新eBay店铺")
    @PreAuthorize("@ss.hasPermission('ebay:store:update')")
    public CommonResult<Boolean> updateStore(@Valid @RequestBody EbayStoreSaveReqVO updateReqVO) {
        storeService.updateStore(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除eBay店铺")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('ebay:store:delete')")
    public CommonResult<Boolean> deleteStore(@RequestParam("id") Long id) {
        storeService.deleteStore(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除eBay店铺")
                @PreAuthorize("@ss.hasPermission('ebay:store:delete')")
    public CommonResult<Boolean> deleteStoreList(@RequestParam("ids") List<Long> ids) {
        storeService.deleteStoreListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得eBay店铺")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('ebay:store:query')")
    public CommonResult<EbayStoreRespVO> getStore(@RequestParam("id") Long id) {
        EbayStoreDO store = storeService.getStore(id);
        return success(BeanUtils.toBean(store, EbayStoreRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得eBay店铺分页")
    @PreAuthorize("@ss.hasPermission('ebay:store:query')")
    public CommonResult<PageResult<EbayStoreRespVO>> getStorePage(@Valid EbayStorePageReqVO pageReqVO) {
        PageResult<EbayStoreDO> pageResult = storeService.getStorePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EbayStoreRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出eBay店铺 Excel")
    @PreAuthorize("@ss.hasPermission('ebay:store:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStoreExcel(@Valid EbayStorePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EbayStoreDO> list = storeService.getStorePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "eBay店铺.xls", "数据", EbayStoreRespVO.class,
                        BeanUtils.toBean(list, EbayStoreRespVO.class));
    }


    @GetMapping("/oauth/consent-url")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @Operation(summary = "生成 eBay OAuth 授权同意页 URL（后端从配置读取，无需入参）")
    @PreAuthorize("@ss.hasPermission('ebay:store:query')")
    public CommonResult<String> buildConsentUrl(@RequestParam("id") Long id) {
        String clientId = ebayApiProperties.getAppId();
        String ruName = ebayApiProperties.getRuName();
        EbayStoreDO store = storeService.getStore(id);
        String base = ebayApiProperties.getAuthUrl() + "oauth2/authorize";
        String scope = String.join(" ", ebayApiProperties.getScopes());
        String locale = store.getSite();

        String url = base + "?" +
                "client_id=" + encode(clientId) +
                "&redirect_uri=" + encode(ruName) +
                "&response_type=code" +
                "&scope=" + scope +
                "&state=" + encode(String.valueOf(id)) +
                "&locale=" + encode(locale);

        return success(url);
    }

    // 用户授权回调 (eBay 会把 code 返回到这里)
    @PostMapping("/oauth/exchange-token")
    @Operation(summary = "使用授权码交换eBay OAuth令牌")
    @PreAuthorize("@ss.hasPermission('ebay:store:update')")
    public CommonResult<EbayStoreOAuthTokenRespVO> exchangeOAuthToken(@Valid @RequestBody EbayStoreOAuthTokenReqVO reqVO) {
        EbayStoreOAuthTokenRespVO tokenInfo = ebayOAuthService.exchangeToken(String.valueOf(reqVO.getStoreId()), reqVO.getCode());
        return success(tokenInfo);
    }

    @PostMapping("/oauth/refresh-token")
    @Operation(summary = "使用刷新令牌刷新eBay OAuth访问令牌")
    @PreAuthorize("@ss.hasPermission('ebay:store:update')")
    public CommonResult<EbayStoreOAuthTokenRespVO> refreshOAuthToken(@Valid @RequestBody EbayStoreRefreshTokenReqVO reqVO) {
        EbayStoreOAuthTokenRespVO tokenInfo = ebayOAuthService.refreshToken(String.valueOf(reqVO.getStoreId()));
        return success(tokenInfo);
    }


    private static String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }


}