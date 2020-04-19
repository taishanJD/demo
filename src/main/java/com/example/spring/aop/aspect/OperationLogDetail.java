package com.example.spring.aop.aspect;

import java.lang.annotation.*;

@Documented
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface OperationLogDetail {

    //方法描述
    String detail() default "";
}
