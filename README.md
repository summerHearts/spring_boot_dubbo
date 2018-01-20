##1、Dubbo简介
   - 一个分布式服务治理框架。当垂直应用越来越多，应用之间交互不可避免，将核心业务抽取出来，作为独立的服务，逐渐形成稳定的服务中心，使前端应用能更快速的响应多变的市场需求。 此时，用于提高业务复用及整合的分布式服务框架(RPC)是关键。

  -  当项目庞大比并且服务需要多次重复性的调用时，就需要一个框架来治理，`dubbo`可以做到的效果就是通多xml文件配置，达到一次提供，到处调用的效果，并且和可以对服务的提供者和消费者进行管理；就是将提供服务的Api打包到服务器，同时注册到注册中心（`zookeeper`）,需要调用此服务的只需依赖服务器上的`jar`包，配置消费者服务即可调用Api。


![](http://upload-images.jianshu.io/upload_images/325120-a76d04b992a90da5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/800)

- `provider` 服务的提供方
 - `consumer` 服务的消费方
 - `registry`     注册中心（可以对提供方和消费方统一管理）
 - `monitor`  统计中心
 -  `container` 运行容器

  - 调用关系说明:
     -  服务容器负责启动，加载，运行服务提供者。
     -   服务提供者在启动时，向注册中心注册自己提供的服务。
     -   服务消费者在启动时，向注册中心订阅自己所需的服务。
     -   注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于长连接推送变更数据给消费者。
     -   服务消费者，从提供者地址列表中，基于软负载均衡算法，选一台提供者进行调用，如果调用失败，再选另一台调用。
     -   服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心。

##2、如何集成Dubbo到Spring boot 中。

![](http://upload-images.jianshu.io/upload_images/325120-6bb53ee3c00d7c2b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/800)

  - 一 、首先创建服务的提供者
     一般的我们都会将API单独抽离出来作为单独的 jar.这里我们也采用这种方式。
    - 1、在Dubbo-api工程中，仅存放接口文件，并在pom.xml文件中定义它的父工程。

     ![](http://upload-images.jianshu.io/upload_images/325120-4fc7b2ae0eb1e513.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/800)

    - 2、在Dubbo-provider项目中引入dubbo和dubbo-api文件。
     ![](http://upload-images.jianshu.io/upload_images/325120-189732968beac436.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/800)
       
    - 3、Dubbo服务生产者文件配置。
         simple-dubbo-provider.xml

       ![](http://upload-images.jianshu.io/upload_images/325120-b9a12c3d7446a6b2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/800)

     - 4、实现接口定义方法。提供相应的服务返回数据。

     ![](http://upload-images.jianshu.io/upload_images/325120-feb2c715c81825a7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/800)


-  二、创建服务的消费者

   - 1、构建Dubbo-consumer消费者，配置pom.xml文件，引入依赖

     ![](http://upload-images.jianshu.io/upload_images/325120-7ff40b9d2b21f65d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/800)

    - 2、spring-boot-dubbo.xml文件配置
      
     ![](http://upload-images.jianshu.io/upload_images/325120-f65540ca3907e2fe.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/800)

   - 3、消费服务响应的方法
   
     ![](http://upload-images.jianshu.io/upload_images/325120-cefac7545153740e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/800)

  
- 三、启动服务提供者和服务消费者。访问 `127.0.0.1:8080/index` 结果如下

   ![](http://upload-images.jianshu.io/upload_images/325120-ec3d6525fd79e874.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/800)


## 3、总结

   ![](http://upload-images.jianshu.io/upload_images/325120-f6456d0d1993997e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/800)
   -  通过提供方提供服务到注册中心，消费方通过配置到注册中心上取到该服务，再进行消费（调用），即可达到不同项目之间的相互调用，也验证了分布式管理的意义，此处只是一个简单的小例子，在实际项目运用中，这种模式加上这个框架的好处会更加明显。


 
  


     

         
   
        