package org.iyo.codegen.context;

import javax.annotation.processing.ProcessingEnvironment;

public final class ProcessingEnvironmentHolder {

  public static final ThreadLocal<ProcessingEnvironment> environment = new ThreadLocal<>();


  public static void setEnvironment(ProcessingEnvironment pe){
    environment.set(pe);
  }

  public static ProcessingEnvironment getEnvironment(){
    return environment.get();
  }


}
