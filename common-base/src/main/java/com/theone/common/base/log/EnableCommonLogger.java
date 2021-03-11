package com.theone.common.base.log;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(HttpRequestLogRegistry.class)
@Inherited
public @interface EnableCommonLogger {
}
