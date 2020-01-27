# Instrumentation

##简介
利用 Java 代码，即 java.lang.instrument 做动态 Instrumentation 是从 Java 5 开始添加的新特性，
用来监测和协助运行在 JVM 上的程序，甚至能够替换和修改某些类的定义，
实现更为灵活的运行时虚拟机监控和 Java 类操作了，提供了一种虚拟机级别支持的 AOP 实现方式。

## javaagent
java.lang.instrument 为javaagent 通过修改方法字节码的方式操作运行在JVM上的程序提供服务，因此介绍instrumentation需要先介绍javaagent
javaagent以JAR包的形式部署，JAR文件清单中的属性指定要加载的代理类，以启动代理。javaagent的启动方式有以下几种:

1. 通过在命令行指定参数启动 (permain)
2. JVM启动后启动。例如，提供一种工具，该工具可以依附到已运行的应用，并允许在已运行的应用内加载代理 (agentmain)
3. 应用一起打包为可执行文件

### 通过在命令行指定参数启动
启动javaagent 的命令行参数：-javaagent:<jarpath>[=<options>]

javaagent JAR文件清单必须包含 Premain-Class属性，属性的值为agent class的全路径名（包名+类名）。
代理类必须实现premain 方法，premain方法和main方法一样分别是代理和应用的入口点。
JVM初始化完成后首先调用代理的premain函数，然后调用应用的main函数，premain方法必须返回后进程才能启动。

### JVM启动后启动
实现可以提供在JVM启动之后再启动代理的机制。代理如何启动的细节特定于实现，通常应用程序已经启动，并且它的main方法已经被调用。
如果实现支持在JVM启动后启动代理，代理必须满足以下条件：
1. 清单文件包含Agent-Class属性，属性的值为代理类全名
2. 代理类必须实现 public static agentmain 方法

## Instrumentation API
* void addTransformer(ClassFileTransformer transformer, boolean canRetransform)

注册ClassFileTransformer实例，注册多个会按照注册顺序进行调用。
所有的类被加载完毕之后会调用ClassFileTransformer实例，相当于它们通过了redefineClasses方法进行重定义。
布尔值参数canRetransform决定这里被重定义的类是否能够通过retransformClasses方法进行回滚

* boolean removeTransformer(ClassFileTransformer transformer)

移除(反注册)ClassFileTransformer实例。

* boolean isRetransformClassesSupported()

返回当前JVM配置是否支持类重新转换的特性。

* void retransformClasses(Class<?>... classes) throws UnmodifiableClassException

已加载类进行重新转换的方法，重新转换的类会被回调到ClassFileTransformer的列表中进行处理，想深入理解建议阅读API注释。

* boolean isRedefineClassesSupported()

返回当前JVM配置是否支持重定义类（修改类的字节码）的特性。

* void redefineClasses(ClassDefinition... definitions) throws ClassNotFoundException, UnmodifiableClassException

重定义类，也就是对已经加载的类进行重定义，ClassDefinition类型的入参包括了对应的类型Class<?>对象和字节码文件对应的字节数组。
