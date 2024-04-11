package org.iyo.codegen.processor.dto;

import java.lang.annotation.*;

/**
 * @Author:iyo
 * @Date:2024/4/12 6:33
 * @Description: 测试注解处理器能否处理该注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestAnnotationNotUse {
}
