# MySpringbootTest，自学spring的项目
## 地址 
  - 项目根：<http://localhost:8081>
  - druid监控地址：<http://localhost:8081/druid>
    - 账号密码在DruidMinotorConfig中配置的，这里使用了admin
  - swagger地址：<http://localhost:8081/swagger-ui/index.html>
    - 听说更好看的UI：<http://localhost:8081/doc.htm>

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
- 集成Swagger2
- 移除无用配置

## 2021/12/29
- 添加Devtools热部署
- 添加发送邮件功能，使用thymeleaf模板

## 2021/12/30
- 添加Jsoup防御XSS攻击
- 添加参数检验功能
- 添加跨域处理
- 添加异步

## 2021/12/31
- 整合kafka（只是简单实现发送接收）

## 2022/01/12
- 整合websocket（没有写前端，直接使用postman测试，如果要写前端，可以引入SockJS库）
- 整合Shiro

## 2022/01/13
- 升级swagger到3.0版本（根据 [issue#1](https://github.com/miaokaicheng/MySpringbootTest/issues/1) 问题 2.10.0以下的版本有跨站脚本漏洞,因为是自己学习用的，确实没有注意安全这方面，感谢提醒。同时3.0版本还是有一些变化的，建议看下官方文档）
- 移除dev和prd分支，因为自己开发学习用的，只留一个管理代码就行
- shiro中使用redis缓存权限
- thymeleaf中使用Shiro标签



还在学习中，慢慢完善。。。