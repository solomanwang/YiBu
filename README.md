# 项目简介
一个学习练手的项目,这版使用Spring Cloud，spring Cloud Gateway，spring Security，jwt实现了
对资源的动态控制访问，auth负责认证，gateway作为资源服务器
### 技术框架
- SpringBoot 
- SpringCloud
- mybatis-plus
###服务
|项目名|端口号|简介
| ---- | ----|---|
gateway|10086|网关，统一的资源服务器，对访问进行控制
auth|8010|认证授权
user|8080|用户
goods|8090|商品
admin|10010|管理员

注册中心采用nacos，因为服务器的原因没有实现config配置
