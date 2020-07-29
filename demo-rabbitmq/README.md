
### 一、RabbitMQ工作模型
<img src="https://upload-images.jianshu.io/upload_images/17039633-b0adf1dfade2f122.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp" alt="rabbit架构" style="zoom:50%;" />

### 二、常见的几种exchange类型

#### 1.direct 

可以理解为点对点消息，消息发布与消费不经过exchanger；生产者直接将消息通过defaultExchanger，发布至指定队列；消费者直接从队列中读取消息。

<img src="./src/main/resources/images/direct_mode.jpg" alt="direct mode" style="zoom: 33%;" />

在这种模式下，exchange参数不填或填”“，使用默认的exchanger.

#### 2.workQueue 

direct模式的升级版本，多个消费着从队列中读，有公平分发，多劳多得两种模式，要看消费端的具体实现。

<img src="./src/main/resources/images/work_queue.jpg" alt="work queue" style="zoom: 33%;" />

在这种模式下，exchange参数不填或填”“，使用默认的exchanger.

#### 3.fanout  广播

<img src="./src/main/resources/images/fanout_mode.jpg" alt="work queue" style="zoom: 50%;" />



在这种模式下，routingKey不填，没有意思，主要使用exchange。 



#### 4.routing   发布订阅/静态路由

 

<img src="./src/main/resources/images/routing_mode.jpg" alt="work queue" style="zoom: 50%;" />

#### 5.topic  订阅模式/动态路由

<img src="./src/main/resources/images/topic_mode.jpg" alt="work queue" style="zoom: 50%;" />

> *代表一个单词
>
> #代表0到多个单词

***


### 三、springboot中使用RabbitMQ

#### 1.引入maven依赖

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```
 这个starter依赖包含spring-amqp与spring-rabbit两个模块

#### 2.配置文件

```properties
spring.rabbitmq.host=192.168.85.4
spring.rabbitmq.port=5672
spring.rabbitmq.username=admin
spring.rabbitmq.password=secret
//也可以这样配置,使用的是amqp协议中的443端口
//spring.rabbitmq.addresses=amqp://admin:secret@localhost

//失败重试策略,默认是关闭的
spring.rabbitmq.template.retry.enabled=true
spring.rabbitmq.template.retry.initial-interval=2s

```
#### 3.**RabbitTemplate** or **AmqpTemplate**

amqpTemplate是基于amqp协议的，同样的还有amqpAdmin；虽然spring都会为我们自动装配这些对象；但是推荐基于amqp进行注入；这样做可以与amqp解耦，后续如果换掉AMQP底层实现，就不需重构代码了。

#### 4.组件抽象

spring amqp在*org.springframework.amqp.core*包中为我们提供了对amqp协议中各组件与对象的抽象。

* Message MessageBuilder

* MessageProperties MessagePropertiesBuilder

  ```java
  MessageProperties props = MessagePropertiesBuilder.newInstance()
      .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
      .setMessageId("123")
      .setHeader("bar", "baz")
      .build();
  Message message = MessageBuilder.withBody("foo".getBytes())
      .andProperties(props)
      .build();
  ```

  

* Exchange 

* Queue

  ```java
  public Queue(String name, boolean durable, boolean exclusive, boolean autoDelete,
  			@Nullable Map<String, Object> arguments)
  ```

* Binding    BindingBuilder 

  ```java
  //DirectExchange  
  new Binding(someQueue, someDirectExchange, "foo.bar");
  //TopicExchange 
  new Binding(someQueue, someTopicExchange, "foo.*");
  //FanoutExchange 
  new Binding(someQueue, someFanoutExchange);
  
  Binding b = BindingBuilder.bind(someQueue).to(someTopicExchange).with("foo.*");
  ```

* Container 

  The container is an example of a “lifecycle” component. It provides methods for starting and stopping. When configuring the container, you essentially bridge the gap between an AMQP Queue and the MessageListener instance. 

  





#### 5.

#### 注：

> * springboot中，exchange、queue这两个组件都只能是消费者声明创建，即：生产者可以随便向任何queue和exchange中发消息，但是如果没有消费者主动创建对应的组件，消息会被server忽略掉，管理界面也看不到任何组件的信息。




****
### 四、RabbitMQ安装过程(基于centos7) [参考](https://www.cnblogs.com/fengyumeng/p/11133924.html)
> 此次安装的版本为 rabbitMQ-3.8.5; erlang 23.0
RabbitMq基于erlang开发，所以需要先安装erlang环境。[erlang官网](https://www.erlang.org/downloads)  

1. 安装erlang
```
wget http://erlang.org/download/otp_src_23.0.tar.gz
tar -xf otp_src_23.0.tar.gz
cd otp_src_23.0
./configure --prefix=/home/liuyf/rabbitmq/erlang 
make install
```
注:如果安装过程中遇到报错如下:可以忽略  
![报错](https://img2018.cnblogs.com/blog/1181622/201907/1181622-20190704173838160-1881201010.png)

2. 配置erlang环境变量
```
vi ~/.bashrc

追加:
ERLANG_HOME=/home/liuyf/rabbitmq/erlang
export PATH="$PATH:$ERLANG_HOME/bin"

source ~/.bashrc
```
3. 安装rabbitmq
```
//进入https://github.com/rabbitmq/rabbitmq-server/releases/tag/v3.8.5 找到安装包

xz -d rabbitmq-server-generic-unix-3.8.5.tar.xz   //解压  没有xz的话先安装 yum install -y xz
tar -xf rabbitmq-server-generic-unix-3.8.5.tar  //需要解压两次


//配置环境变量
vi ~/.bashrc
//追加
Rabbit_Home=/home/liuyf/rabbitmq/rabbitmq_server-3.8.5
export PATH="$PATH:$Rabbit_Home/sbin"

source ~/.bashrc

```
4. RabbitMQ相关指令
```
//启动
rabbitmq-server -detached
//停止
rabbitmqctl stop
//状态
rabbitmqctl status
//开启web插件
rabbitmq-plugins enable rabbitmq_management
//web地址默认为：http://192.168.85.4:15672/     默认账号密码：guest guest（这个账号只允许本机访问）

//增加用户
rabbitmqctl add_user liuyf 123456
//配置权限
rabbitmqctl set_permissions -p "/" liuyf ".*" ".*" ".*"
rabbitmqctl set_user_tags liuyf administrator

```

