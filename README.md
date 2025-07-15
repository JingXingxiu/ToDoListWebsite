# ToDoList - JavaWeb + Vue3 全栈项目

🚀 一个基于原生 JavaWeb（Servlet）和 Vue3 的前后端分离项目，采用 MVC 架构，包含 JWT 鉴权和跨域解决方案。适合学习全栈开发基础技术栈。

## 🌟 技术栈

| 层级       | 技术选型                                                                 |
|------------|-------------------------------------------------------------------------|
| **前端**   | Vue 3 + Pinia（状态管理） + vue-router + Axios + Vite（构建工具）          |
| **后端**   | JavaWeb（Servlet/Jakarta） + JWT + Jackson（JSON处理） + MySQL +HikariCP  |
| **架构**   | 前后端分离 + MVC 模式                                                     |
| **特色**   | JWT Token 鉴权、Filter 解决跨域、无 Spring 纯 Servlet 实现                 |

## 🛠️ 项目亮点

1. **认证安全**  
   - 基于 JWT 的 Token 无状态认证
   - 密码加密存储（示例代码见 `backend里的JwtUtil.java`）

2. **跨域方案**  
   - 通过自定义 Filter 实现 CORS 处理（见 `backend里的CorsFilter.java`）

3. **纯 Servlet 实现**  
   - 不依赖 Spring 框架，适合学习原生 JavaWeb 工作原理

## 部署方式

用VS code打开frontEnd, IDEA打开backEnd, 添加完各种依赖
Vue：vue-router pinia axios
Java:Jackson Jwt Servlet(jakarta) MySQL
修改后端的src下的resource里的config.properties里的user和password，填你自己的账号密码