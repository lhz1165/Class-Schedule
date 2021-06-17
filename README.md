# 个人课程表管理
**项目介绍**：个人课程表管理工具，可以根据时间来修改自己的课表，新增自己的课程和教室相关信息，达到查阅课程信息的目的。

这个项目是纯前后端分离，没有模板引擎，交互完全采用ajax来传递json数据。

**项目使用的软件**（需要下载的在我的[download_web_site](https://github.com/lhz1165/download_web_site) 仓库去找下载链接）

1. idea2021
2. mysql8(没有8的修改下application.properties的驱动就好了)
3. mavne3.6

**使用框架**:

- 后端：springboot ，mybatisplus
- 前端：layui，jquery

**运行方式**：执行sql脚本，创建数据库和表，导入项目，配置maven信息，运行EsTestApplication的main方法，

在浏览器输入

http://localhost:8081/index.html



![1](/pic/1.png)





![2](/pic/2.png)

![3](/pic/3.png)



下面这是添加/修改课程，如果开始时间覆盖了那么直接修改这个课程，开始时间没覆盖则新增

![4](D:\lhz\myproject\es-test\pic\4.png)



删除某个课程

![5](/pic/5.png)
