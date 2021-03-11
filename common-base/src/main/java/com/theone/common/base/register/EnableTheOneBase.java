package com.theone.common.base.register;

import com.theone.common.base.advice.EnableStandardResponse;
import com.theone.common.base.log.EnableCommonLogger;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@EnableStandardResponse
@EnableCommonLogger
@Inherited
public @interface EnableTheOneBase {
}
