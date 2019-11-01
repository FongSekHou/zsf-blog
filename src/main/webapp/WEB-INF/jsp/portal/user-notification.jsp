<%--
  Created by IntelliJ IDEA.
  User: Fangxihao
  Date: 2019/10/30
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>消息通知</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/initialization.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/personal.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconfont.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.jpg" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
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
        <li class="nav-list"><a href="${pageContext.request.contextPath}/user/index">主页</a></li>
        <li class="nav-list"><a href="${pageContext.request.contextPath}/user/notification" style="color:#00cbba"><div class="${tipstyle}"></div>消息通知</a></li>
        <li class="nav-list part"></li>
        <li class="nav-list"><a href="${pageContext.request.contextPath}/user/personal">个人中心</a>
        </li>
        <li class="nav-list writing"><a href="${pageContext.request.contextPath}/blog/edit">写博客</a></li>
        <li class="quit"><a href="${pageContext.request.contextPath}/user/logout">退出</a></li>
    </ul>
</div>
<div class="container">
    <div class="content home-content">
        <div class="home-personal">
                <div class="home-personal-msg">
                    <div class="home-personal-header" id="userheader">
                    </div>
                    <div class="home-personal-footer">
                        <div class="article-msg"
                             onclick="window.location.href='${pageContext.request.contextPath}/blog/publish'"
                             style="cursor: pointer">
                            <i class="iconfont icon-fabiao"></i>我发表的文章
                            <p id="postblognum">(0)</p>
                        </div>
                        <div class="article-msg"
                             onclick="window.location.href='${pageContext.request.contextPath}/blog/like'"
                             style="cursor: pointer">
                            <i class="iconfont icon-xin"></i>我喜欢的文章
                            <p id="likeblognum">(0)</p>
                        </div>
                        <div class="article-msg"
                             onclick="window.location.href='${pageContext.request.contextPath}/blog/collect'"
                             style="cursor: pointer">
                            <i class="iconfont icon-star"></i>我收藏的文章
                            <p id="collectblognum">(0)</p>
                        </div>
                    </div>
                </div>
        </div>
            <div class="home-content-right">
                <div class="content-right-box">
                    <div class="my-msg">我的消息</div>
                   <div class="my-msg-content" id="msgContent">
                       <!-- <div>
                           <span class="msg-user-name">点赞人的用户名</span>
                           <span>点赞了</span>
                           <span>你的文章</span>
                           <span class="msg-blog-name"><a href="">（你的文章名）</a></span>
                           <div class="msg-tip"></div>
                       </div>
                       <div>
                           <span class="msg-user-name">点赞人的用户名</span>
                           <span>点赞了</span>
                           <span>你的文章</span>
                           <span class="msg-blog-name"><a href="">（你的文章名）</a></span>
                           <div class="msg-tip"></div>
                       </div>-->
                    </div>
                    <div class="content-right-footer">
                        <div id="pagination" class="pagination"></div>
                    </div>
                </div>
            </div>


    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script>
    layui.use('layer', function () {
        var layer = layui.layer;
        var msg = getQueryVariable("msg");
        if (msg != undefined && msg != '') {
            layer.msg(decodeURIComponent(msg));
        }
    });

    function spliceNoticeContent(notices) {
        var noticescontent = "";
        $.each(notices,function (index,notice) {
            noticescontent +="<div><span class=\"msg-user-name\">用户&nbsp;";
            noticescontent += notice.username+"&nbsp;</span><span>&nbsp;";
            noticescontent += notice.operate+"&nbsp;</span><span>你的文章</span>";
            noticescontent += "<span class=\"msg-blog-name\">";
            noticescontent += "<a href=\"${pageContext.request.contextPath}/blog/showBlog?blogid="+notice.blogid+"\">《";
            noticescontent += notice.blogname;
            noticescontent += "》</a></span></div></div>";
        });
        return noticescontent;
    }

    function splicePaginationContent(page) {
        var contentBar = '';
        if (page.currentPage == 1) {
            contentBar += '<li class="disabled"><a href="#"> << </a></li>';
        } else {
            contentBar += '<li><a href="#" onclick="queryNotices(' + (page.currentPage - 1) + ')"> << </a></li>';
        }
        for (var i = 0; i < page.pageBar.length; i++) {
            contentBar += '<li';
            if (page.currentPage == page.pageBar[i]) {
                contentBar += ' class="active"';
            }
            contentBar += '><a href="#" onclick="queryNotices(' + page.pageBar[i] + ')">' + page.pageBar[i] + '</a></li>';
        }
        if (page.currentPage == page.totalPage || page.totalPage == 0) {
            contentBar += '<li class="disabled"><a href="#"> >> </a></li>';
        } else {
            contentBar += '<li><a href="#" onclick="queryNotices(' + (page.currentPage + 1) + ')"> >> </a></li>';
        }
        return contentBar;
    }

    function queryNotices(currentpage) {
        $.ajax({
            url: "${pageContext.request.contextPath}/user/queryNotices",
            type: "post",
            data: {
                "currentPage": currentpage
            },
            success(page) {
                var notices = page.entitys;
                var noticescontent = spliceNoticeContent(notices);
                $("#msgContent").html(noticescontent);

                var contentBar = splicePaginationContent(page);
                $("#pagination").html(contentBar);
            },
            error() {
                layer.msg("服务器异常，查询失败");
            }
        });
    }

    $(function () {
        $.getJSON("${pageContext.request.contextPath}/user/loadNotification", function (vo) {
            var user = vo.user;
            $("#userheader").html("<img src='${pageContext.request.contextPath}" + user.iconpath + "'/><h1>" + user.username + "</h1>");
            $("#postblognum").html("(" + vo.postblognum + ")");
            $("#likeblognum").html("(" + vo.likeblognum + ")");
            $("#collectblognum").html("(" + vo.collectblognum + ")");

            var notices = vo.pageBean.entitys;
            var noticescontent = spliceNoticeContent(notices);
            $("#msgContent").html(noticescontent);

            var page = vo.pageBean;
            var contentBar = splicePaginationContent(page);
            $("#pagination").html(contentBar);
        });
    });



    layui.use('element', function () {
        var $ = layui.jquery,
            element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
    });
</script>


</html>
