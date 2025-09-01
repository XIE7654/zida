package com.zida.cbec.framework.desensitize.core.handler;

import com.zida.cbec.framework.desensitize.core.DesensitizeTest;
import com.zida.cbec.framework.desensitize.core.base.handler.DesensitizationHandler;
import com.zida.cbec.framework.desensitize.core.annotation.Address;

/**
 * {@link Address} 的脱敏处理器
 *
 * 用于 {@link DesensitizeTest} 测试使用
 */
public class AddressHandler implements DesensitizationHandler<Address> {

    @Override
    public String desensitize(String origin, Address annotation) {
        return origin + annotation.replacer();
    }

}
