package com.theone.common.base.advice;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({HybridExceptionAdvice.class, StandardResponseAdvice.class})
@Inherited
public @interface EnableStandardResponse {
}
