# YiBu
一个学习练手的项目
### 技术框架
SpringBoot 
SpringCloud
###服务
|项目名|端口号|简介
| ---- | ----|---|
gateway|10086|网管
auth|8010|认证授权
user|8080|用户
goods|8090|商品

注册中心采用nacos，因为服务器的原因没有实现config配置

网关采用gateway

-- 服务监控采用Spring Boot Admin，接入官网转发并实现在线日志查看
