package com.zida.cbec.module.ebay.controller.admin;

import com.zida.cbec.framework.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zida.cbec.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - Test")
@RestController
@RequestMapping("/ebay/test")
@Validated
public class EbayTestController {

    // 这个构造方法，只是方便大家，验证 Controller 有生效
    public EbayTestController() {
        System.out.println(getClass() + "生效啦！！！");
    }

    @GetMapping("/get")
    @Operation(summary = "获取 test 信息")
    public CommonResult<String> get() {
        return success("true");
    }

}
