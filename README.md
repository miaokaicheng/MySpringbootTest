# MySpringbootTest，自学spring的项目
## 地址 
  - 项目根：<http://localhost:8081>
  - druid监控地址：<http://localhost:8081/druid>
    - 账号密码在DruidMinotorConfig中配置的，这里使用了admin

## 2021/12/10
- 初始化项目
- 集成多数据源，含mysql，oracle，pg和gp（本地没装所以没写调用的mapper）
- 集成redis，同时支持单机和集群
- 集成mybatis-plus

## 2021/12/24
- 添加Druid监控目前只配置了Mysql，因为用的最多

## 2021/12/27
- 添加注解记录日志
- 规范部分编码

## 2021/12/28
- 添加Junit测试类,移除原先的TestController那一套
- 修复Druid的慢SQL，spring监控配置不生效问题
- 修复实体类接收Date格式解析异常问题（之后可以添加参数校验功能）
- 移除无用配置











还在学习中，慢慢完善。。。