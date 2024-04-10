package org.iyo.codegen.processor.dto;

import com.google.auto.service.AutoService;
import org.iyo.codegen.processor.BaseCodeGenProcessor;
import org.iyo.codegen.spi.CodeGenProcessor;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Set;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import lombok.Data;

/**
 * @Author:iyo
 * @Date:2024/4/10 8:09
 * @Description:
 */
@AutoService(value = CodeGenProcessor.class)
public class DtoCodeGenProcessor extends BaseCodeGenProcessor{

    public static final String SUFFIX = "DTO";

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return GenDto.class;
    }

    @Override
    public String generatePackage(TypeElement typeElement) {
        return typeElement.getAnnotation(GenDto.class).pkgName();
    }

    @Override
    protected void generateClass(TypeElement typeElement, RoundEnvironment roundEnvironment) {
        Set<VariableElement> fields = findFields(typeElement,
                ve -> Objects.isNull(ve.getAnnotation(IgnoreDto.class)));
        String className = typeElement.getSimpleName() + SUFFIX;
        Builder builder = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Schema.class)
                .addAnnotation(Data.class);
        addSetterAndGetterMethod(builder,fields);
        builder.addMethod(MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PROTECTED)
                .build());
        genJavaSourceFile(generatePackage(typeElement),
                typeElement.getAnnotation(GenDto.class).srcPath(), builder);
    }
}
