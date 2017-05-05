# H-Express Android App

![Platform](https://img.shields.io/badge/platform-Android-blue.svg)![Release](https://img.shields.io/badge/debug-1.0.0-orange.svg)![Gradle](https://img.shields.io/badge/gradle-2.0.0-blue.svg)[![Packagist](https://img.shields.io/packagist/l/doctrine/orm.svg?style=plastic)]()

![这里写图片描述](http://img.blog.csdn.net/20170504170212518?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjUzNDgzMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

[H-Express  APK](https://github.com/qht1003077897/H-Express/blob/master/apk/app-debug.apk)

----------


download这个项目，你可以学到：

 1. EventBus
 2. OkHttp
 3. butterknife
 4. Glide图片加载
 5. ZXing二维码扫描
 6. 白天/夜间模式切换
 7. 第三方QQ登录、分享
 8. 安卓爬虫
 9. RecyclerView使用
 10. BaseActivity、BaseFragment、BaseAdapter
 11. LitePal数据库使用



----------

 - **H-Express** is an express delivery tracking app , built on OkHttp、EventBus、LitePal、Glide and ZXing.
 - H-Express 是一款快递追踪 APP，基于OkHttp、EventBus、LitePal、Glide、RecyclerView、ZXing而构建。


 - Clone the Repository: 
 - 克隆仓库：


```
git clone https://github.com/qht1003077897/H-Express.git
```

 - Suggestion: It is better for you to update your Android Studio to
   version 2.0 when you open this project.
 - 建议：运行此项目最好使用 Android Studio2.0 以上版本。

### To-dos

 - This project is still in progress. Here are the some features that I
   will finish in the future. 
 - 这个项目还在开发当中，下面的条目是我以后的完善计划。
 
 √ 完成客服模块。
 √ 加入网点地图模式。
 √ 界面美化、按钮样式统一。
 √ .....

### Help Me Improve This App

- As you can see at the screenshots, some images in H-Express app are not suitable. So If you want to improve the translation of H-Express, please email me.


- 从截图中可以看到，H-Express 并不是很完善，因此如果你有更好的建议或者想帮助我一起完善 H-Express，请 Email 我。

----------


![这里写图片描述](http://img.blog.csdn.net/20170427215859131?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjUzNDgzMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)![这里写图片描述](http://img.blog.csdn.net/20170427220250668?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjUzNDgzMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


----------


![这里写图片描述](http://img.blog.csdn.net/20170427220309106?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjUzNDgzMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)![这里写图片描述](http://img.blog.csdn.net/20170504170118094?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjUzNDgzMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


----------


![这里写图片描述](http://img.blog.csdn.net/20170504170133439?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjUzNDgzMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)![这里写图片描述](http://img.blog.csdn.net/20170504170200690?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjUzNDgzMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


 
----------
 

2017-05-03
----------

 1. 完成“寄快递”部分功能（数据来自快递100 H5 解析，包括网点信息、时效天数）。
 2. 完成“添加常用地址”功能。
 3. 编写“关于”界面。

 
----------
2017-04-27
----------

 1. 完成订单模块(包括已完成、已签收、未签收订单查看、编辑、单选、多选、全选、删除功能，包括手工设置订单状态为 已/未 签收)
 2. 增加 夜间/白天 主题切换
 3. 加入第三方(QQ)登录、退出登录、分享功能
 4. 可清除缓存
 5. 实时天气温度信息

 
----------


2017-04-18
----------

 1. 实现订单详情页面
 2. 时间轴式订单信息
 3. RecyclerView添加BaseAdapter
 4. 添加menu菜单样式
 5. 使用litepal数据库存储订单信息
 6. 首页添加weelview选择快递公司

 
----------
 
 
2017-04-14
----------

 1. 首页Fragment订单查询输入
 2. 订单页面viewpage+fragment 

 
----------
 
 
2017-04-11
----------

 1. BaseFragment 
 2. 微信底部菜单栏  
 3. 无数据情况下的EmptyView 

 
----------
 
 
2017-04-05
----------

 1. OkHttp 3.3.1(引用鸿洋的一个OkhttpUtil) 
 2. EventBus 3.0  
 3. Json解析 
 4.  ButterKnife8.5.1


----------


2017-03-26
----------

 1. 透明状态栏 
 2. 生命周期监控 
 3. 顶部统一的ToolBar
 
 
 
 -------
 尊重原创，请勿用作商业用途.
 ---

 
 
 License
-------

     MIT License
     
    Copyright (c) 2016-2018  qht1003077897

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.


---
 
 

