### 代码生成器

基本原理是使用注解处理器`AbstractProcessor`在编译期生成代码。

引入的依赖有：

-  `AutoService` ：这是一个[注释处理器库](https://baeldung.xiaocaicai.com/java-annotation-processing-builder)，可以帮助我们生成[Java服务提供商接口](https://baeldung.xiaocaicai.com/java-spi)（SPI）配置文件.
- `javapoet` ：is a Java API for generating `.java` source files [=>](https://github.com/square/javapoet).  



![说明](D:\projectJ\iyo-codegen\images\process.png)   







