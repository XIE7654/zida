package com.zida.cbec.module.ebay.service.oauth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.zida.cbec.framework.common.exception.util.ServiceExceptionUtil;
import com.zida.cbec.module.ebay.controller.admin.store.vo.EbayStoreOAuthTokenRespVO;
import com.zida.cbec.module.ebay.dal.dataobject.store.EbayStoreDO;
import com.zida.cbec.module.ebay.framework.web.config.EbayApiProperties;
import com.zida.cbec.module.ebay.service.store.EbayStoreService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Base64;

/**
 * eBay OAuth 服务实现类
 *
 * @author 云瑞源码
 */
@Service
@Slf4j
public class EbayOAuthServiceImpl implements EbayOAuthService {

    @Resource
    private EbayApiProperties ebayApiProperties;

    @Resource
    private EbayStoreService storeService;

    @Resource
    private RestTemplate restTemplate;

    @Override
    public EbayStoreOAuthTokenRespVO exchangeToken(String storeId, String code) {
        try {
            // 获取店铺信息
            EbayStoreDO store = storeService.getStore(Long.valueOf(storeId));
            if (store == null) {
                throw ServiceExceptionUtil.exception0(1001, "店铺不存在");
            }

            // 构建请求URL
            String tokenUrl = ebayApiProperties.getApiUrl() + "identity/v1/oauth2/token";;

            // 构建请求头
            HttpHeaders headers = buildHeaders();

            // 构建请求体
            String requestBody = buildRequestBody(code);

            // 发送HTTP请求
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, requestEntity, String.class);
            
            // 打印HTTP响应信息
            log.info("HTTP响应状态码: {}", response.getStatusCode());
            log.info("HTTP响应头: {}", response.getHeaders());
            log.info("HTTP响应体: {}", response.getBody());

            // 解析响应
            if (response.getStatusCode() == HttpStatus.OK) {
                EbayStoreOAuthTokenRespVO tokenResp = parseTokenResponse(response.getBody());
                
                // 更新店铺的令牌信息到数据库
                storeService.updateStoreOAuthTokens(
                    store.getId(),
                    tokenResp.getAccessToken(),
                    tokenResp.getRefreshToken(),
                    tokenResp.getExpiresIn(),
                    tokenResp.getRefreshTokenExpiresIn()
                );
                
                log.info("成功更新店铺 {} 的OAuth令牌信息", store.getId());
                return tokenResp;
            } else {
                log.error("eBay OAuth令牌交换失败，状态码: {}, 响应: {}", response.getStatusCode(), response.getBody());
                throw ServiceExceptionUtil.exception0(1002, "eBay OAuth令牌交换失败: {}", response.getStatusCode());
            }
            
        } catch (Exception e) {
            log.error("eBay OAuth令牌交换异常", e);
            throw ServiceExceptionUtil.exception0(1003, "eBay OAuth令牌交换异常: {}", e.getMessage());
        }
    }

    @Override
    public EbayStoreOAuthTokenRespVO refreshToken(String storeId) {
        try {
            // 获取店铺信息

            EbayStoreDO store = storeService.getStore(Long.valueOf(storeId));
            if (store == null) {
                throw ServiceExceptionUtil.exception0(1001, "店铺不存在");
            }

            // 检查店铺是否有刷新令牌
            if (store.getRefreshToken() == null || store.getRefreshToken().isEmpty()) {
                throw ServiceExceptionUtil.exception0(1005, "店铺没有可用的刷新令牌");
            }

            // 构建请求URL
            String tokenUrl = ebayApiProperties.getApiUrl() + "identity/v1/oauth2/token";

            // 构建请求头
            HttpHeaders headers = buildHeaders();

            // 构建刷新令牌请求体
            String requestBody = buildRefreshTokenRequestBody(store.getRefreshToken());

            // 发送HTTP请求
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, requestEntity, String.class);
            
            // 打印HTTP响应信息
            log.info("刷新令牌HTTP响应状态码: {}", response.getStatusCode());
            log.info("刷新令牌HTTP响应体: {}", response.getBody());

            // 解析响应
            if (response.getStatusCode() == HttpStatus.OK) {
                EbayStoreOAuthTokenRespVO tokenResp = parseRefreshTokenResponse(response.getBody());
                
                // 更新店铺的访问令牌信息到数据库（刷新令牌请求不返回新的刷新令牌）
                storeService.updateStoreAccessToken(
                    store.getId(),
                    tokenResp.getAccessToken(),
                    tokenResp.getExpiresIn()
                );
                
                log.info("成功刷新店铺 {} 的访问令牌", store.getId());
                return tokenResp;
            } else {
                log.error("eBay OAuth令牌刷新失败，状态码: {}, 响应: {}", response.getStatusCode(), response.getBody());
                throw ServiceExceptionUtil.exception0(1006, "eBay OAuth令牌刷新失败: {}", response.getStatusCode());
            }
            
        } catch (Exception e) {
            log.error("eBay OAuth令牌刷新异常", e);
            throw ServiceExceptionUtil.exception0(1007, "eBay OAuth令牌刷新异常: {}", e.getMessage());
        }
    }

    /**
     * 构建请求头
     */
    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        
        // 生成Base64编码的认证信息
        String credentials = ebayApiProperties.getAppId() + ":" + ebayApiProperties.getCertId();
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        headers.set("Authorization", "Basic " + encodedCredentials);
        
        return headers;
    }

    /**
     * 构建请求体
     */
    private String buildRequestBody(String code) {
        return UriComponentsBuilder.newInstance()
                .queryParam("grant_type", "authorization_code")
                .queryParam("code", code)
                .queryParam("redirect_uri", ebayApiProperties.getRuName())
                .build()
                .toUriString()
                .substring(1); // 移除开头的问号
    }

    /**
     * 解析令牌响应
     */
    private EbayStoreOAuthTokenRespVO parseTokenResponse(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            EbayStoreOAuthTokenRespVO tokenResp = new EbayStoreOAuthTokenRespVO();
            tokenResp.setAccessToken(jsonNode.get("access_token").asText());
            tokenResp.setExpiresIn(jsonNode.get("expires_in").asInt());
            tokenResp.setRefreshToken(jsonNode.get("refresh_token").asText());
            tokenResp.setRefreshTokenExpiresIn(jsonNode.get("refresh_token_expires_in").asInt());
            tokenResp.setTokenType(jsonNode.get("token_type").asText());
            
            // 计算过期时间
            LocalDateTime now = LocalDateTime.now();
            tokenResp.setAccessTokenExpireTime(now.plusSeconds(tokenResp.getExpiresIn()));
            tokenResp.setRefreshTokenExpireTime(now.plusSeconds(tokenResp.getRefreshTokenExpiresIn()));
            
            return tokenResp;
            
        } catch (Exception e) {
            log.error("解析eBay OAuth令牌响应失败", e);
            throw ServiceExceptionUtil.exception0(1004, "解析eBay OAuth令牌响应失败: {}", e.getMessage());
        }
    }

    /**
     * 构建刷新令牌请求体
     */
    private String buildRefreshTokenRequestBody(String refreshToken) {
        return UriComponentsBuilder.newInstance()
                .queryParam("grant_type", "refresh_token")
                .queryParam("refresh_token", refreshToken)
                .queryParam("scope", String.join(" ", ebayApiProperties.getScopes()))
                .build()
                .toUriString()
                .substring(1); // 移除开头的问号
    }

    /**
     * 解析刷新令牌响应
     */
    private EbayStoreOAuthTokenRespVO parseRefreshTokenResponse(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            EbayStoreOAuthTokenRespVO tokenResp = new EbayStoreOAuthTokenRespVO();
            tokenResp.setAccessToken(jsonNode.get("access_token").asText());
            tokenResp.setExpiresIn(jsonNode.get("expires_in").asInt());
            tokenResp.setTokenType(jsonNode.get("token_type").asText());
            
            // 刷新令牌请求不返回新的刷新令牌，保持原有的
            // 计算新的过期时间
            LocalDateTime now = LocalDateTime.now();
            tokenResp.setAccessTokenExpireTime(now.plusSeconds(tokenResp.getExpiresIn()));
            
            return tokenResp;
            
        } catch (Exception e) {
            log.error("解析eBay OAuth刷新令牌响应失败", e);
            throw ServiceExceptionUtil.exception0(1008, "解析eBay OAuth刷新令牌响应失败: {}", e.getMessage());
        }
    }
} 