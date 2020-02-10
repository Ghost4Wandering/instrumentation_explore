# Instrumentation

## 简介

Java Instrumentation指的是可以用独立于应用程序之外的代理（agent）程序, 主要用来来监测和协助运行在JVM上的应用程序。
这种监测和协助包括但不限于获取JVM运行时状态，替换和修改类定义等。 
实现上，提供一套代理机制，支持独立于JVM应用程序之外的程序以代理的方式连接和访问JVM。

Instrumentation 的最大作用就是类定义的动态改变和操作。
实现更为灵活的运行时虚拟机监控和 Java 类操作了。

下面是三种相应实现的方式：

### premain方式
代理必须满足以下条件：
1. MANIFAST 文件中包含Premain-Class属性，属性的值为代理类全名
2. 代理类必须实现 public static premain 方法

调用的大致的流程如下：
![image](https://raw.githubusercontent.com/Ghost4Wandering/j.icon/master/instrumentation/premain_2.png)


### agentmain方式 (java 6后增加)
实现可以提供在JVM启动之后再启动代理的机制。代理如何启动的细节特定于实现，通常应用程序已经启动，并且它的main方法已经被调用。
如果实现支持在JVM启动后启动代理，代理必须满足以下条件：
1. MANIFAST 文件中包含Agent-Class属性，属性的值为代理类全名
2. 代理类必须实现 public static agentmain 方法

调用的大概的流程如下：
![image](https://raw.githubusercontent.com/Ghost4Wandering/j.icon/master/instrumentation/agent_main_3.png)

### 本地方法的Instrumentation (java 6后增加)
在 java 6 中，新的 Native Instrumentation 提出了一个新的 native code 的解析方式，作为原有的 native method 的解析方式的一个补充。
这就是在的 java.lang.instrument 包里，添加了对 native 代码的 instrument 方式 —— 设置 prefix。

是用本地方法instrumentation代理必须满足以下条件：
1. MANIFAST 文件中Can-Set-Native-Method-Prefix 设置为true
2. 使用premain/agentmain

使用本地方法的Instrumentation的大概流程：
对于某一个 package 内的一个 class 当中的一个 native method 来说，首先，假设我们对这个函数的 transformer 设置了 native 的 prefix“another”，它将这个函数接口解释成

由 Java 的函数接口
``native void method()``
和上述 prefix"another"，去寻找本地代码中的函数

``void Java_package_class_another_method(jclass theClass, jobject thiz);``

一旦可以找到，那么调用这个函数，整个解析过程就结束了；如果没有找到，那么虚拟机将会做进一步的解析工作。我们将利用 Java native 接口最基本的解析方式 , 去找本地代码中的函数 :
``void Java_package_class_method(jclass theClass, jobject thiz);``

## [Instrumentation API](https://docs.oracle.com/javase/8/docs/api/java/lang/instrument/Instrumentation.html)

## 其他一些说明文档 (Java 8)

[instrumention package (Oracle desc) ](https://docs.oracle.com/javase/8/docs/api/java/lang/instrument/package-summary.html)

[ClassFileTransformer API (Oracle API)](https://docs.oracle.com/javase/8/docs/api/java/lang/instrument/ClassFileTransformer.html)

[Instrumentation API (Oracle API)](https://docs.oracle.com/javase/8/docs/api/java/lang/instrument/Instrumentation.html)
