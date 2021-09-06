# 电圈(完善中......)



基于MVVM框架，由Kotlin+Jetpack实现的一个综合性电子数码产品交易Android应用

电圈App是新品交易、二手交易、产品测评与鉴别、互动图片社区于一体的综合性电子数码交易平台，在传统电商模式的基础上添加“鉴别测评服务”、“二手交易服务”，推出售前“先鉴别，再发货”，售后“测评性能，二手转卖”的购物流程，创造了体系完整的电子数码商品交易圈



本项目采用模块化的方式构建整个应用，现有模块：login(登录模块)、mine(个人模块)、home(首页模块)、store(商店模块)、deal(交易模块)、survey(测评模块)、service(服务模块)、common(公共模块) 

使用阿里ARouter实现模块之间的通信

依赖注入框架使用的是koin



## Common

- 公共基础类的构建
- 网络框架的构建
- 一些通用的类
- ......

## Service

依赖Common模块，被其它所有模块依赖，负责其它模块和Common模块之间的通信



## Login

当前login界面效果

<img src=https://user-images.githubusercontent.com/68262407/131501108-cdbe378c-d5b7-427e-b676-436941b35d8e.jpg width=247 height=506 />

已实现登录功能，支持用户名密码登录，尚未实现注册与其它方式登录



## Mine

当前mine界面效果

在未登录状态点击登录/注册跳转登录界面进行登录

<img src=https://user-images.githubusercontent.com/68262407/132229413-944c30bf-c83d-416b-ac12-fbe0a8bf2ec9.jpg width=247 height=506 />


登录成功后获取用户名称，显示退出登录按钮
<img src=https://user-images.githubusercontent.com/68262407/132229592-1c332612-cf0b-47b9-a3ed-a95a3f2db99b.jpg width=247 height=506 />

其它功能待完善......

## Store

当前store页面效果

<img src=https://user-images.githubusercontent.com/68262407/132229611-195e00de-68a8-4dc7-a41c-f2d03e64ada1.jpg width=247 height=506 />

已实现商品展示，点击每个item进入商品详情页

商品详情页如下图，已实现加入购物车按钮逻辑


<img src=https://user-images.githubusercontent.com/68262407/132229636-6129d19d-105f-47a6-9bed-3102b2414fc5.jpg width=247 height=506 />



点击商品列表界面的购物篮进入购物篮界面

购物篮已实现单选、全选、增加商品数量、删除商品、清空商品、显示选中金额总数
<img src=https://user-images.githubusercontent.com/68262407/132229650-9b7186ab-8980-4445-9979-543701114837.jpg width=247 height=506 />

## Home

当前home页面效果


广告轮播、热门品牌、热门商品、推荐商品

点击商品item进入商品详情


<img src=https://user-images.githubusercontent.com/68262407/132229675-1b474b6f-c014-4730-ad1d-dfd55a88814c.jpg width=247 height=506 />



## Deal

待实现......

## Survey

待实现......




