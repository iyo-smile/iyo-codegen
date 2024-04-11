package org.iyo.codegen.registry;

import com.google.common.collect.Maps;
import org.iyo.codegen.spi.CodeGenProcessor;
import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;

/**
 * @Author:iyo
 * @Date:2024/4/10 7:28
 * @Description: 通过SPI 加载所有的CodeGenProcessor 识别要处理的annotation标记类
 */
public class CodeGenProcessorRegistry {
    private static Map<String, ? extends CodeGenProcessor> PROCESSORS;


    private CodeGenProcessorRegistry() {
        throw new UnsupportedOperationException();
    }

    /**
     * 注解处理器要处理的注解集合
     *
     * @return
     */
    public static Set<String> getSupportedAnnotations() {
        return PROCESSORS.keySet();
    }

    public static CodeGenProcessor find(String annotationClassName) {
        return PROCESSORS.get(annotationClassName);
    }

    /**
     * spi 加载所有的processor
     *
     * @return
     */
    public static void initProcessors() {
        final Map<String, CodeGenProcessor> map = Maps.newLinkedHashMap();
        ServiceLoader<CodeGenProcessor> processors = ServiceLoader.load(CodeGenProcessor.class,CodeGenProcessor.class.getClassLoader());
        Iterator<CodeGenProcessor> iterator = processors.iterator();
        while (iterator.hasNext()) {
            CodeGenProcessor next = iterator.next();
            Class<? extends Annotation> annotation = next.getAnnotation();
            map.put(annotation.getName(), next);
            System.out.println("CodeGenProcessorRegistry spi: "+annotation.getName());
        }
        PROCESSORS = map;
    }

}
