### 从springboot集成redis开始; 测试在springboot中如何使用redis

### springboot如何集成redis  
参考: [springboot-data-redis-guide](https://spring.io/guides/gs/messaging-redis/)

### springboot中如何使用redis做缓存
1. 在pom文件中添加依赖  
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
```
2. 在任意一个configuration类上加 **@EnableCaching**注解，开启spring对缓存的管理。  
3. 配置CacheManager，如果不配置的话，springboot默认的使用的jvm级的本地缓存，使用concurrentHashMap实现的。





### springboot cache相关注解
* @EnanbleCaching  
告诉spring开启缓存，与Configuration注解配合使用。
* @Cacheable   
注解在方法上，指出方法的返回值将被缓存，后续对此方法相同的key调用将直接返回缓存，而不再执行方法；注：需要在注解中通过key生命此次查询的唯一标识方式；
并且此方法支持同步锁（具体要看选择的缓存manager是否支持），通过注解内的sync方法来声明。
* @CacheEvit  
驱逐缓存，在方法执行结束后删除缓存；如果方法执行异常，不删除。
* @CachePut  
更新缓存，通过方法的返回值来告诉spring将缓存更新为什么；方法必须有返回值！
* @Caching  
一些业务方法比较复杂，可能不止更新一个缓存，可以通过caching注解，在其中组合使用多个 cacheable，cachePut，cacheEvit
* @CacheConfig  
可以用在类上，为当前类的所有方法的注解指定 cachename，keyGenerator等...
> **注：** 
> * 上面几个注解中的value,可以理解为缓存的前缀，通过注解中的key，可以唯一标识一个请求缓存。
> * 请不要在方法上同时使用CacheEvit与CachePut注解。
