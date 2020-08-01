### jpa 是什么、ORM又是什么

JPA全称，Java Persistence Api，jpa是一种规范； 

ORM 即 Object Relational Mapping  是通过使用描述对象和数据库之间映射的元数据，将面向对象语言程序中的对象自动持久化到关系数据库中。

### 引入oracle jar包依赖

1. 先查看oracle版本。

   ```sql
   select* from v$version;
   ```

2. 去[官网]('https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html')下载对应的依赖包，因为授权原因，maven公共仓库上是没有这个驱动包的。

   > ojdbc 后的数字是支持哪个java版本，ojdbc6 支持 java 6,7,8

3. 安装到本地仓库

   ```
   mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=12.9.9 -Dpackaging=jar -Dfile=ojdbc6.jar
   ```

4. 再引入依赖

   ```
   <dependency>
       <groupId>com.oracle</groupId>
       <artifactId>ojdbc6</artifactId>
       <version>12.9.9</version>
   </dependency>
   ```

### 引入dozer依赖

dozer是一个工具类，可以方便的在 PO，BO，DO，VO之间对象对象的转换，默认是根据字段名来匹配的。

```xml
<dependency>
    <groupId>net.sf.dozer</groupId>
    <artifactId>dozer</artifactId>
    <version>5.4.0</version>
</dependency>
```

其java配置类如下

```
@Bean
public DozerBeanMapper dozerBeanMapper(){
	return new DozerBeanMapper();
}
```



***

### Spring  Boot JPA 配置多数据源

在使用多数据源的情况下，就不能使用springboot提供的默认配置项目  spring.datasource.jdbc-url 等等类似的配置了。你必须手动的在配置文件中增加配置，然后在java中通过configuration+bean的方式，手动创建Datasource与TranscationMangager，对JPA来讲，还需要再手动的声明 EntityManagerFactory。

1. 相关依赖

   ```xml
   		<dependency>
   			<groupId>org.springframework.boot</groupId>
   			<artifactId>spring-boot-starter-data-jpa</artifactId>
   		</dependency>
   		<dependency>
   			<groupId>org.springframework.boot</groupId>
   			<artifactId>spring-boot-starter-jdbc</artifactId>
   		</dependency>
   		<dependency>
   			<groupId>mysql</groupId>
   			<artifactId>mysql-connector-java</artifactId>
   			<version>8.0.21</version>
   		</dependency>
   ```

2. 数据源配置

   ```yml
   spring:
     primary-datasource:
       #数据源a 配置
       jdbc-url: jdbc:mysql://localhost:3306/test_db_a?useUnicode=true&characterEncoding=UTF-8
       username: test_mysql_a
       password: 123456
       driver-class-name: com.mysql.cj.jdbc.Driver
       #数据源b配置
     secondary-datasource:
       jdbc-url: jdbc:mysql://localhost:3306/test_db_b?useUnicode=true&characterEncoding=UTF-8
       username: test_mysql_b
       password: 123456
       driver-class-name: com.mysql.cj.jdbc.Driver
   ```

3. 配置 两个DataSource  和 TransactionManager

   ```java
   @Configuration
   @EnableTransactionManagement
   public class DatasourceConfig {
   
       // 基础数据源   jdbcTemplate  transactionManager
       @ConfigurationProperties(prefix = "spring.primary-datasource")
       @Primary
       @Bean("primaryDatasource")
       public DataSource primaryDataSource() {
           return DataSourceBuilder.create().build();
   
       }
   
       @Bean("primaryJdbcTemplate")
       @Primary
       public JdbcTemplate wgglJdbcTemplate(DataSource dataSource) {
           return new JdbcTemplate(dataSource);
       }
   
       @Primary
       @Bean("primaryTranscationManager")
       public PlatformTransactionManager primaryTransactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
   
           JpaTransactionManager transactionManager
                   = new JpaTransactionManager();
           transactionManager.setEntityManagerFactory(
                   entityManagerFactory.getObject());
           return transactionManager;
       }
   
   
       // 第二套数据源   jdbcTemplate  transactionManager
   
   
       @ConfigurationProperties(prefix = "spring.secondary-datasource")
       @Bean("secondaryDatasource")
       public DataSource secondaryDataSource() {
           return DataSourceBuilder.create().build();
       }
   
   
       @Bean("secondaryJdbcTemplate")
       public JdbcTemplate cmktJdbcTemplate(@Qualifier("secondaryDatasource")
                                                    DataSource dataSource) {
           return new JdbcTemplate(dataSource);
       }
   
   
       @Bean("secondaryTranscationManager")
       public PlatformTransactionManager secondaryTransactionManager(@Qualifier("secondaryContainerEntiryManagerFactoryBean") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
   
           JpaTransactionManager transactionManager
                   = new JpaTransactionManager();
           transactionManager.setEntityManagerFactory(
                   entityManagerFactory.getObject());
           return transactionManager;
       }
   
   
   }
   ```

> **注意**：配置多个数据源时，必须将一个数据源设置为primary，否则spring会探测到多个数据源，启动会失败。



4. 分别配置EntityManager，注意，我们要为不同的Entity与Repository分包，然后在设置EntiryManager时根据包路径来指定数据源。

   ```java
   @Configuration
   @EnableJpaRepositories(
           basePackages = {"com.liuyf.demo.jpa.dao.primary", "com.liuyf.demo.jpa.entity.primary"},
           entityManagerFactoryRef = "primaryContainerEntiryManagerFactoryBean",
           transactionManagerRef = "primaryTranscationManager"
   )
   public class JPAConfigPrimary {
   
       public final static String[] primary_datasource_basePackages = {"com.liuyf.demo.jpa.dao.primary", "com.liuyf.demo.jpa.entity.primary"};
   
   
       @Bean("primaryContainerEntiryManagerFactoryBean")
       @Primary
       public LocalContainerEntityManagerFactoryBean productEntityManager(DataSource dataSource) {
   
   
           HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
   
   
           LocalContainerEntityManagerFactoryBean em  = new LocalContainerEntityManagerFactoryBean();
           em.setDataSource(dataSource);
           em.setPackagesToScan(primary_datasource_basePackages);
           em.setJpaVendorAdapter(vendorAdapter);
           em.setJpaProperties(additionalProperties());   //从spring 3.1 起，可以不用再通过 /META-INFO/perisients.xml 的方式配置 hibernate的配置了， 可以直接通过java编码来配置
   
   
           return em;
       }
   
       private Properties additionalProperties(){
           Properties properties = new Properties();
           properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
           properties.setProperty("hibernate.show_sql", "true");
           properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
           
           
           //配置根据entity生成建表脚本
           properties.setProperty("javax.persistence.schema-generation.create-source", "metadata");  //
           properties.setProperty("javax.persistence.schema-generation.scripts.action", "create");   //生成建表脚本
           properties.setProperty("javax.persistence.schema-generation.scripts.create-target", "../create.sql");  //脚本导出位置
           
           
           return properties;
       }
   }
   
   //另一套配置相似，只是关键字稍微有区别
   ```

### Spring Data JPA 自动生成数据库表

spring data jpa 提供了根据Entity创建表的能力，颠覆了传统的先创建表，然后根据表来创建实体的思想，使用hibernate一定要具备这种领域建模的思想。

spring data jpa 对auto-ddl提供了4中选项，配置方式参考前面代码中的additionalProperties()方法。

> * create  每次应用启动时都根据Entity删除表，然后再新建一遍，数据会丢失。
> * update  根据Entity更新表结构，但是数据不会丢失
> * create-drop  每次应用启动时新建表，应用停止的时候会把表删除
> * validate    启动时验证Entity与表结构是否一致，不一致会报错！

配置输出创建表的脚本也参照上面的代码即可



### JPA中的一些元素概念

#### Entity

#### EntityManager

#### EntityManagerFactory

#### Repository













@Column注解

```java
//@Column可以声明字段与数据库表之间的映射关系，默认的，使用字段名与表字段进行关联；如果实体字段与数据库字段有出入，可以在column中的name属性作声明。需要特别注意的是，column中有个columnDefination属性，通过这个属性，相当于在建表时直接在字段后面加了约束，可以声明字段类型，约束，注释等等；通过这种方式我们可以更加细粒度的控制数据库表结构。eg：
@Column(name = "last_update_time" , columnDefinition = " datetime(6) comment '最后更新时间' ")
	private Date lastUpdateTime;

//等价于
//create table xxx {
//    last_update_time datetime(6) comment '最后更新时间'
//};
```



















