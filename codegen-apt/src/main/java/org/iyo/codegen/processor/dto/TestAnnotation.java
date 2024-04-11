package org.iyo.codegen.processor.dto;

import java.lang.annotation.*;

/**
 * @Author:iyo
 * @Date:2024/4/10 8:04
 * @Description: 测试注解处理器能否处理该注解
 *   测试结果：扫描到的注解是注解处理器中获取到的注解 如GenDto
 *     是DtoCodeGenProcessor的getAnnotation() {return GenDto.class;}中返回的
 *   TestAnnotation和 TestAnnotationNotUse均没有被注解处理器处理
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestAnnotation {
}
