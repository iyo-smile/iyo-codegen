package org.iyo.codegen.processor;

import com.google.auto.service.AutoService;
import org.iyo.codegen.context.ProcessingEnvironmentHolder;
import org.iyo.codegen.registry.CodeGenProcessorRegistry;
import org.iyo.codegen.spi.CodeGenProcessor;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic.Kind;

@AutoService(value = Processor.class)
public class Only4PlayCodeGenProcessor extends AbstractProcessor {

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    annotations.stream().forEach(an -> {
      System.out.println("Only4PlayCodeGenProcessor.process:" + an.getQualifiedName());
      Set<? extends Element> typeElements = roundEnv.getElementsAnnotatedWith(an);
      Set<TypeElement> types = ElementFilter.typesIn(typeElements);
      for (TypeElement typeElement : types){
        System.out.println("Only4PlayCodeGenProcessor.typeElement:"+typeElement.getQualifiedName());
        CodeGenProcessor codeGenProcessor = CodeGenProcessorRegistry.find(
            an.getQualifiedName().toString());
        try {
          codeGenProcessor.generate(typeElement,roundEnv);
        } catch (Exception e) {
          ProcessingEnvironmentHolder.getEnvironment().getMessager().printMessage(Kind.ERROR,"代码生成异常:" + e.getMessage());
        }
      }

    });
    return false;
  }

  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    ProcessingEnvironmentHolder.setEnvironment(processingEnv);
    CodeGenProcessorRegistry.initProcessors();
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return CodeGenProcessorRegistry.getSupportedAnnotations();
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }
}
