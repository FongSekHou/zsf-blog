<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/initialization.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/personal.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconfont.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.jpg" type="image/x-icon">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<style>
    html {
        height: 100%;
    }

    body {
        height: 100%;
    }
</style>
<body>
<div class="header">
    <div class="logo">
        <img src="${pageContext.request.contextPath}/img/logo.jpg">
    </div>
    <ul class="header-nav">
        <li class="nav-list">主页</li>
        <li class="nav-list">收藏</li>
        <li class="nav-list">消息通知</li>
        <li class="nav-list part"></li>
        <li class="nav-list"><a href="${pageContext.request.contextPath}/user/personal" style="color:#00cbba">个人中心</a></li>
        <li class="nav-list writing"><a href="${pageContext.request.contextPath}/blog/edit">写博客</a></li>
        <li class="quit"><a href="${pageContext.request.contextPath}/user/logout">退出</a></li>
    </ul>
</div>
<div class="container">
    <div class="content home-content">
        <div class="home-personal">
            <div class="home-personal-msg">
                <div class="home-personal-header">
                    <!-- <img src="../../img/logo.jpg">
                    <h1>zhu</h1> -->
                    <a href="${pageContext.request.contextPath}/user/login">
                        <div class="please-login">请先<span class="please-login-last">登录</span></div>
                    </a>
                </div>
                <div class="home-personal-footer">
                    <div class="article-msg">
                        <i class="iconfont icon-fabiao"></i>我发表的文章
                        <p>(0)</p>
                    </div>
                    <div class="article-msg">
                        <i class="iconfont icon-xin"></i>我喜欢的文章
                        <p>(0)</p>
                    </div>
                    <div class="article-msg">
                        <i class="iconfont icon-star"></i>我收藏的文章
                        <p>(0)</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="home-content-right">
            <div class="content-right-box">
                <div class="content-right-header">
                    <div class="layui-tab">
                        <div class="content-header-nav">
                            <ul class="layui-tab-title">
                                <li class="layui-this">网站设置</li>
                                <li>用户管理</li>
                                <li>权限分配</li>
                                <li>商品管理</li>
                                <li>订单管理</li>
                            </ul>
                            <div class="blog-search">
                                <form>
                                    <input type="text" class="search-input" placeholder="通过标题搜索改分类下的博客">
                                    <i class="search-icon iconfont icon-sousuokuang"></i>
                                    <button type="button" class="layui-btn layui-btn-primary blog-btn">搜索</button>
                                </form>
                            </div>
                        </div>
                        <div class="layui-tab-content">
                            <div class="layui-tab-item layui-show">
                                <div class="home-content-blog">
                                    <div class="home-blog-msg">
                                        <div class="home-blog-header">
                                            <div class="home-blog-title">TCP和UDP</div>
                                            <div class="home-blog-btn">
                                                <button class="gray">编辑</button>
                                                <button class="red">删除</button>
                                            </div>
                                        </div>
                                        <div class="home-blog-content">
                                            <div class="blog-msg">
                                                <ul>
                                                    <li class="nav-list home-blog-time">2019-10-1</li>
                                                    <li class="nav-list">喜欢&nbsp;16</li>
                                                    <li class="nav-list li-style">收藏&nbsp;16</li>
                                                    <li class="nav-list li-style">留言&nbsp;16</li>
                                                    <li class="nav-list li-style">浏览&nbsp;16</li>
                                                </ul>
                                                <div class="blog-content-main">
                                                    <div class="blog-text">
                                                        TCP（Transmission Control
                                                        Protocol传输控制协议）是一种面向连接的、可靠的、基于字节流的传输层通信协议，由IETF的RFC793定义。在简化的计算机网络OSI模型中，它完成第四层传输层所指定的功能，用户数据报协议（UDP）是同一层内另一个重要的传输协议。
                                                    </div>
                                                    <div class="blog-label">
                                                        <ul>
                                                            <li>编程语言</li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="home-blog-msg">
                                        <div class="home-blog-header">
                                            <div class="home-blog-title">TCP和UDP</div>
                                            <div class="home-blog-btn">
                                                <button class="gray">编辑</button>
                                                <button class="red">删除</button>
                                            </div>
                                        </div>
                                        <div class="home-blog-content">
                                            <div class="blog-msg">
                                                <ul>
                                                    <li class="nav-list home-blog-time">2019-10-1</li>
                                                    <li class="nav-list">喜欢&nbsp;16</li>
                                                    <li class="nav-list li-style">收藏&nbsp;16</li>
                                                    <li class="nav-list li-style">留言&nbsp;16</li>
                                                    <li class="nav-list li-style">浏览&nbsp;16</li>
                                                </ul>
                                                <div class="blog-content-main">
                                                    <div class="blog-text">
                                                        TCP（Transmission Control
                                                        Protocol传输控制协议）是一种面向连接的、可靠的、基于字节流的传输层通信协议，由IETF的RFC793定义。在简化的计算机网络OSI模型中，它完成第四层传输层所指定的功能，用户数据报协议（UDP）是同一层内另一个重要的传输协议。
                                                    </div>
                                                    <div class="blog-label">
                                                        <ul>
                                                            <li>编程语言</li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="home-blog-msg">
                                        <div class="home-blog-header">
                                            <div class="home-blog-title">TCP和UDP</div>
                                            <div class="home-blog-btn">
                                                <button class="gray">编辑</button>
                                                <button class="red">删除</button>
                                            </div>
                                        </div>
                                        <div class="home-blog-content">
                                            <div class="blog-msg">
                                                <ul>
                                                    <li class="nav-list home-blog-time">2019-10-1</li>
                                                    <li class="nav-list">喜欢&nbsp;16</li>
                                                    <li class="nav-list li-style">收藏&nbsp;16</li>
                                                    <li class="nav-list li-style">留言&nbsp;16</li>
                                                    <li class="nav-list li-style">浏览&nbsp;16</li>
                                                </ul>
                                                <div class="blog-content-main">
                                                    <div class="blog-text">
                                                        TCP（Transmission Control
                                                        Protocol传输控制协议）是一种面向连接的、可靠的、基于字节流的传输层通信协议，由IETF的RFC793定义。在简化的计算机网络OSI模型中，它完成第四层传输层所指定的功能，用户数据报协议（UDP）是同一层内另一个重要的传输协议。
                                                    </div>
                                                    <div class="blog-label">
                                                        <ul>
                                                            <li>编程语言</li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>

                                    </div>
                                </div>
                            </div>
                            <div class="layui-tab-item">内容2</div>
                            <div class="layui-tab-item">内容3</div>
                            <div class="layui-tab-item">内容4</div>
                            <div class="layui-tab-item">内容5</div>
                        </div>
                    </div>
                </div>
                <div class="content-right-footer">
                    <div id="test1"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    if("${msg}"!=""){
        alert("${msg}");
    }
    layui.use('element', function () {
        var $ = layui.jquery,
            element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

        //触发事件
        var active = {
            tabAdd: function () {
                //新增一个Tab项
                element.tabAdd('demo', {
                    title: '新选项' + (Math.random() * 1000 | 0) //用于演示
                    ,
                    content: '内容' + (Math.random() * 1000 | 0),
                    id: new Date().getTime() //实际使用一般是规定好的id，这里以时间戳模拟下
                })
            },
            tabDelete: function (othis) {
                //删除指定Tab项
                element.tabDelete('demo', '44'); //删除：“商品管理”


                othis.addClass('layui-btn-disabled');
            },
            tabChange: function () {
                //切换到指定Tab项
                element.tabChange('demo', '22'); //切换到：用户管理
            }
        };

        $('.site-demo-active').on('click', function () {
            var othis = $(this),
                type = othis.data('type');
            active[type] ? active[type].call(this, othis) : '';
        });

        //Hash地址的定位
        var layid = location.hash.replace(/^#test=/, '');
        element.tabChange('test', layid);

        element.on('tab(test)', function (elem) {
            location.hash = 'test=' + $(this).attr('lay-id');
        });

    });
    layui.use('laypage', function () {
        var laypage = layui.laypage;

        //执行一个laypage实例
        laypage.render({
            elem: 'test1' //注意，这里的 test1 是 ID，不用加 # 号
            ,
            count: 50 //数据总数，从服务端得到
        });
    });
</script>

</body>
</html>