# leyou
整个项目采用的是前后端分离开发模式。  - 前端分为两部分：    - 后台管理：主要面向的是数据管理人员，采用基于Vue的单页应用开发方式   - 门户系统：面向的是客户，门户采用的是Vue结合Nuxt实现服务端渲染方式  - 后端    后端采用基于SpringCloud的微服务架构，统一对外提供Rest风格接口，无论是后台管理还是门户系统都共享这些微服务接口，而微服务中通过JWT方式来识别用户身份，开放不同接口。 