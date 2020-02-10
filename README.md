# Instrumentation

##简介
Java Instrumentation指的是可以用独立于应用程序之外的代理（agent）程序来监测和协助运行在JVM上的应用程序。
这种监测和协助包括但不限于获取JVM运行时状态，替换和修改类定义等。 提供一套代理机制，
支持独立于JVM应用程序之外的程序以代理的方式连接和访问JVM。Instrumentation 的最大作用就是类定义的动态改变和操作。
实现更为灵活的运行时虚拟机监控和 Java 类操作了，提供了一种虚拟机级别支持的 AOP 实现方式。


### premain方式
JVM初始化完成后首先调用代理的premain函数，然后调用应用的main函数，premain方法必须返回后进程才能启动。
代理必须满足以下条件：
1. 清单文件包含Premain-Class属性，属性的值为代理类全名
2. 代理类必须实现 public static premain 方法

调用的大致的流程如下：
![image](https://raw.githubusercontent.com/Ghost4Wandering/j.icon/master/instrumentation/agentclass-2.png)


### agentmain方式 (java 6后增加)
实现可以提供在JVM启动之后再启动代理的机制。代理如何启动的细节特定于实现，通常应用程序已经启动，并且它的main方法已经被调用。
如果实现支持在JVM启动后启动代理，代理必须满足以下条件：
1. 清单文件包含Agent-Class属性，属性的值为代理类全名
2. 代理类必须实现 public static agentmain 方法

调用的大概的流程如下：
![image](https://raw.githubusercontent.com/Ghost4Wandering/j.icon/master/instrumentation/premain.png)

### 本地方法的Instrumentation (java 6后增加)
在 java 6 中，新的 Native Instrumentation 提出了一个新的 native code 的解析方式，作为原有的 native method 的解析方式的一个补充。
这就是在的 java.lang.instrument 包里，添加了对 native 代码的 instrument 方式 —— 设置 prefix。

## Instrumentation, ClassFileTransformer, ClassDefinition

## [Instrumentation API](https://docs.oracle.com/javase/8/docs/api/java/lang/instrument/Instrumentation.html)
* void addTransformer(ClassFileTransformer transformer, boolean canRetransform)

## 引用 (Java 8)

[instrumention package (Oracle desc) ](https://docs.oracle.com/javase/8/docs/api/java/lang/instrument/package-summary.html)

[ClassFileTransformer API (Oracle API)](https://docs.oracle.com/javase/8/docs/api/java/lang/instrument/ClassFileTransformer.html)

[Instrumentation API (Oracle API)](https://docs.oracle.com/javase/8/docs/api/java/lang/instrument/Instrumentation.html)