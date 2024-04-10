package org.iyo.codegen.processor.dto;

import java.lang.annotation.*;

/**
 * @Author:iyo
 * @Date:2024/4/10 8:04
 * @Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GenDto {
    String pkgName();

    String srcPath() default "src/main/java";

    boolean overrideSource() default false;

}
