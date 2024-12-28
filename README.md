# wesmile-spring

#### 介绍

将[wesmile库](https://github.com/timbersea/wesmile) 集成到spring，更易于使用

#### 原理

与mybatis-spring的作用类似

#### 克隆并安装到本地仓库

```
git clone https://github.com/timbersea/wesmile-spring
cd  ./wesmile-spring
mvn clean install -DskipTests=true
```

#### 使用说明

- 添加依赖

```
<dependency>
  <groupId>com.an</groupId>
  <artifactId>wesmile-spring</artifactId>
  <version>1.0-SNAPSHOT</version>
 </dependency>
```

- 在spring的xml文件中添加bean的定义，如果不使用xml的方式，将bean用new的方式托管到spring ioc容器即可

```
<bean class="com.qian.wesmile.SmileConfigurer">
        <property name="appid" value="你的appid"/>
        <property name="appSecret" value="你的appsecret"/>
        <property name="domain" value="https://api.weixin.qq.com"/>
        <property name="scanPackage" value="com.qian"/><!--多个包以英文;分割-->
</bean>
```

- 自定义接口，根据微信开发平台扩展新的接口
  例如com.qian.wesmile.Datacube
- 在spring中使用注入的方式注入bean

```
@Autowire
Datacube datacue;

调用API
A a = new A();
Getusersummary getusersummary = datacube.getusersummary(a);
```

#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request