## spring-validation以及spring单元测试的使用





###  1、validation

#### 相关依赖

在一个经典的spring-boot-web项目中，我们可以通过注解的方式来对方法的参数进行校验，但是前提，这个类必须是被spring管理的bean。

那么spring-boot-web项目中是如何实现使用注解对参数进行校验的呢，

1、spring-boot-starter-web.pom文件中添加了对spring-boot-starter-validation的依赖。

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-validation</artifactId>
  <version>2.2.6.RELEASE</version>
  <scope>compile</scope>
  <exclusions>
    <exclusion>
      <artifactId>tomcat-embed-el</artifactId>
      <groupId>org.apache.tomcat.embed</groupId>
    </exclusion>
  </exclusions>
</dependency>
```

2、然后在spring-boot-starter-validation中，其又依赖了hibernate的validation与jakarta的api。

```xml
<dependency>
    <groupId>jakarta.validation</groupId>
    <artifactId>jakarta.validation-api</artifactId>
    <version>2.0.2</version>
    <scope>compile</scope>
</dependency>
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>6.0.18.Final</version>
    <scope>compile</scope>
    <exclusions>
        <exclusion>
            <artifactId>validation-api</artifactId>
            <groupId>javax.validation</groupId>
        </exclusion>
    </exclusions>
</dependency>
```

3、hibernate pom文件中又添加了javax.validation的依赖。

```xml
<dependency>
    <groupId>javax.validation</groupId>
    <artifactId>validation-api</artifactId>
</dependency>
```



#### @valid  @validated区别

valid注解是JSR-303中的规范，提供级联验证的功能。

validated是spring的注解，不提供级联验证的功能，但是提供分组验证的功能。

> 使用校验注解时，记住以下几点即可：
>
> * @Valid 注解单独放在方法上，或者入参之前是没用的！正确的用法是注解在需要被级联验证的字段上，即注解在一个Bean的某个字段上，如果这个字段所对应的对象也有自己的校验规则，则会进行级联验证！总之，valid注解只有以下含义及用法：
>
>   * 用在方法入参的参数前：标注此字段需要验证！
>   * 用在一个字段中，标注此属性需要级联验证！
>
> * @Validated 注解单独放在方法上，或者放在参数之前也
>
>   * 用在方法入参的参数前：标注此字段需要验证，//只在controller层生效，在service层配合类上开启validated 也没用，不知道为啥
>* 不可用于字段上!
>   * 可以用在方法上，为方法指定注解验证组。
>   * 用在一个类或方法上，标注需要被代理，开启参数验证！ controller好像是默认开的，service层要开启参数校验必须加此参数。

综上：一般情况下：

* 字段上加valid注解，表示需要级联验证
* 入参上加valid注解，表示这个对象需要验证
* 类上加validated注解，表示这个类需要被代理，开启验证
* 入参如果是非对象参数，不需要加任何注解，直接加@empty @min等注解配合类或方法上添加@validated注解即可。



***

### 2、单元测试

1. 需要引入spring的相关依赖

```xml
<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scopte>test</scopte>
</dependency>
```

2. 平时做的主要是web项目，测试分两部分介绍，

   * 一部分是测试service层，dao层的方法，这种测试选用直接注入对象然后注入
   * 另一部分是Controller层的测试，一般选用MockMvc的方式。

   具体看代码：

   ```java
   @SpringBootTest
   @RunWith(SpringRunner.class)
   @Slf4j
   public class Tests {
   
       @Autowired
       private ValidationTestService validationTestService;
   
       @Autowired
       private WebApplicationContext webApplicationContext;
       private MockMvc mockMvc;
   
       @Before
       public void setUp() throws Exception {
           // 构造MockMvc
           mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
   
       }
   
   
       @Test
       public void testServiceLayer() {
           log.info("测试服务层");
           validationTestService.print("");
       }
   
       @Test
       public void testControllerLayer() throws Exception {
           log.info("测试服务层");
           mockMvc.perform(MockMvcRequestBuilders.post("/test1").accept(MediaType.APPLICATION_JSON).param("userId", "liuyf"))
                   .andDo(MockMvcResultHandlers.print())
                   .andExpect(MockMvcResultMatchers.status().isOk())
                   .andReturn();
           
       }
   }
   ```

   