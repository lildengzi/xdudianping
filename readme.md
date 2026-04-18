# 西电食堂点评系统

这是一个基于 Spring Boot + MyBatis 的餐饮点评平台示例，包含：用户、商户、管理员三种身份功能，支持菜品评价、店铺评价、收藏、笔记、排行榜、特价预告等核心业务。

![Java](https://img.shields.io/badge/Java-17+-ED8B00?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-2.7+-6DB33F?logo=spring-boot&logoColor=white)
![MyBatis](https://img.shields.io/badge/MyBatis-3.5+-red)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql&logoColor=white)
![JMeter](https://img.shields.io/badge/JMeter-5.5-D22128?logo=apache-jmeter&logoColor=white)
![Project Management](https://img.shields.io/badge/Project_Management-Agile-blue)
![Testing](https://img.shields.io/badge/Testing-BlackBox%2FWhiteBox%2FPerformance-green)

---

## 🚀 一键启动

项目目录：`dianping`

1. 进入项目目录：

```bash
cd dianping
```

2. 使用 Maven 启动：

```bash
mvn spring-boot:run
```

3. 或打包运行：

```bash
mvn clean package
java -jar target/*.jar
```

4. 默认访问：

- 后台管理：`http://localhost:8080/admin`（视实际前端路径可调整）
- 前端页面：`http://localhost:8080/` 

> 日志输出：`SENS started at http://localhost:<port>`

---

## 🏷 关键入口

- 主启动类：`src/main/java/com/example/sens/Application.java`
- Mapper 扫描：`com.example.sens.mapper`
- 包扫描：`com.example.sens` 和 `com.liuyanzhao.yztool`

---

## 📦 目录说明

- `src/main/java`: 后端业务逻辑和控制器
- `src/main/resources/mapper`: MyBatis XML 映射文件
- `src/main/resources/static`: 前端静态资源（css/js/images）
- `src/main/resources/templates`: Thymeleaf/HTML 页面
- `src/main/resources/application.properties` / `application.yaml`: 配置文件

---

## 🧩 功能列表

### 用户
- 菜品信息浏览
- 个人中心
- “店铺评分”与“菜品评分”
- 收藏夹与笔记
- 点评榜单：
  - 最受欢迎店铺
  - 菜品排行榜
  - 环境/服务/口味排名
  - 新品推荐

### 商户
- 商铺信息管理
- 菜品管理
- 评价管理
- 菜品预告（特价/优惠套餐）

### 管理员
- 商铺管理
- 优惠管理
- 审核管理
- 营销管理（推荐菜品、优质窗口）
- 榜单管理（商家榜、菜品榜、好评榜、热门榜、口味榜、环境榜、服务榜）

---

## 🗄 数据库设计

### store（店铺）
- 店铺名称
- 店铺照片
- 店铺地址
- 责任人姓名
- 责任人电话
- 负责人商家ID

### dish（菜品）
- 菜品名称
- 描述
- 所属店铺ID
- 图片

### store_comment（店铺评价）
- 用户ID
- 店铺ID
- 评价内容
- 总评分
- 环境评分
- 口味评分
- 服务评分
- 创建时间

### dish_comment（菜品评价）
- 用户ID
- 菜品ID
- 评价内容
- 菜品评分
- 创建时间

### mark（收藏）
- 用户ID
- 类型
- 业务ID

### note（笔记）
- 用户ID
- 内容
- 创建时间

### dish_notice（菜品预告）
- 店铺ID
- 菜品名称
- 图片
- 分类名称

---

## 🔧 常见问题

- 端口设置：`application.properties/application.yaml` 中 `server.port`
- 数据库连接：`spring.datasource.url` 等配置
- 日志：采用 slf4j + logback（可在 `src/main/resources` 下修改）

---
