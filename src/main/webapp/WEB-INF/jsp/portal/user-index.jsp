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
        <li class="nav-list"><a href="${pageContext.request.contextPath}/user/index" style="color:#00cbba">主页</a></li>
        <li class="nav-list"><a href="${pageContext.request.contextPath}/user/notification"><div class="${tipstyle}"></div>消息通知</a></li>
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
                <div class="content-right-header">
                    <div class="layui-tab">
                        <div class="content-header-nav">
                            <ul class="layui-tab-title" id="blogtypecontent">
                                <li class="layui-this" value="-1" onclick="switchTab(-1)">全部</li>
                            </ul>
                            <div class="blog-search">
                                <form>
                                    <input type="text" class="search-input" placeholder="通过标题搜索该分类下的博客" id="condition">
                                    <i class="search-icon iconfont icon-sousuokuang"></i>
                                    <button type="button" class="layui-btn layui-btn-primary blog-btn" id="btnQuery">
                                        搜索
                                    </button>
                                </form>
                            </div>
                        </div>
                        <div class="layui-tab-content">
                            <div class="layui-tab-item layui-show">
                                <div class="home-content-blog" id="blogscontent">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="content-right-footer">
                <div id="pagination" class="pagination"></div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
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

    var blogtypeid;
    var condition;

    function spliceBlogContent(blogs) {
        var blogscontent = "";
        $.each(blogs, function (index, blog) {
            blogscontent += "<div style='cursor: pointer' onclick=\"goShowBlog('" + blog.id + "')\"><div class=\"home-blog-msg\"><div class=\"home-blog-header\"><div class=\"home-blog-title\">";
            blogscontent += blog.title;
            blogscontent += "</div></div><div class=\"home-blog-content\"><div class=\"blog-msg\"><ul><li class=\"nav-list home-blog-time\">";
            blogscontent += blog.completetime;
            blogscontent += "</li><li class=\"nav-list li-style\">浏览&nbsp;";
            blogscontent += blog.viewnum;
            blogscontent += "</li><li class=\"nav-list\">喜欢&nbsp;";
            blogscontent += blog.likenum;
            blogscontent += "</li><li class=\"nav-list li-style\">收藏&nbsp;";
            blogscontent += blog.collectnum;
            blogscontent += "</li><li class=\"nav-list li-style\">留言&nbsp;";
            blogscontent += blog.commentnum;
            blogscontent += "</li></ul><div class=\"blog-content-main\"><div class=\"blog-text\">";
            blogscontent += cutstr(blog.content, 100);
            blogscontent += "</div><div class=\"blog-label\"><ul><li>";
            blogscontent += blog.blogtypename;
            blogscontent += "</li></ul></div></div></div></div></div></div>";
        });
        return blogscontent;
    }

    function splicePaginationContent(page) {
        var contentBar = '';
        if (page.currentPage == 1) {
            contentBar += '<li class="disabled"><a href="#"> << </a></li>';
        } else {
            contentBar += '<li><a href="#" onclick="queryBlogs(' + (page.currentPage - 1) + ')"> << </a></li>';
        }
        for (var i = 0; i < page.pageBar.length; i++) {
            contentBar += '<li';
            if (page.currentPage == page.pageBar[i]) {
                contentBar += ' class="active"';
            }
            contentBar += '><a href="#" onclick="queryBlogs(' + page.pageBar[i] + ')">' + page.pageBar[i] + '</a></li>';
        }
        if (page.currentPage == page.totalPage || page.totalPage == 0) {
            contentBar += '<li class="disabled"><a href="#"> >> </a></li>';
        } else {
            contentBar += '<li><a href="#" onclick="queryBlogs(' + (page.currentPage + 1) + ')"> >> </a></li>';
        }
        return contentBar;
    }

    function queryBlogs(currentpage) {
        $.ajax({
            url: "${pageContext.request.contextPath}/blog/queryBlogs",
            type: "post",
            data: {
                "currentPage": currentpage,
                "blogtypeid": blogtypeid,
                "condition": condition
            },
            success(page) {
                var blogs = page.entitys;
                var blogscontent = spliceBlogContent(blogs);
                $("#blogscontent").html(blogscontent);

                //bootstrap分页条
                var contentBar = splicePaginationContent(page);
                $("#pagination").html(contentBar);
            },
            error() {
                layer.msg("服务器异常，查询失败");
            }
        });
    }

    $.getJSON("${pageContext.request.contextPath}/user/loadIndex", function (userIndexVO) {
        var user = userIndexVO.user;
        if (user != undefined && user != null) {
            $("#userheader").html("<img src='${pageContext.request.contextPath}" + user.iconpath + "'/><h1>" + user.username + "</h1>");
            $("#postblognum").html("(" + userIndexVO.postblognum + ")");
            $("#likeblognum").html("(" + userIndexVO.likeblognum + ")");
            $("#collectblognum").html("(" + userIndexVO.collectblognum + ")");
        } else {
            $("#userheader").html("<a href='${pageContext.request.contextPath}/user/login'><div class='please-login'>请先<span class='please-login-last'>登录</span></div></a>");
        }
        var blogtypes = userIndexVO.blogTypes;
        var blogtypecontent = "";
        $.each(blogtypes, function (index, blogtype) {
            blogtypecontent += "<li onclick=\"switchTab('" + blogtype.id + "')\">" + blogtype.blogtypename + "</li>";
        });
        $("#blogtypecontent").append(blogtypecontent);

        var blogs = userIndexVO.pageBean.entitys;
        var blogscontent = spliceBlogContent(blogs);
        $("#blogscontent").append(blogscontent);

        //bootstrap分页条
        var page = userIndexVO.pageBean;
        var contentBar = splicePaginationContent(page);
        $("#pagination").html(contentBar);
    });

    function goShowBlog(blogid) {
        window.location.href = "${pageContext.request.contextPath}/blog/showBlog?blogid=" + blogid;
    }

    function switchTab(id) {//向数据库执行查询再回显数据
        blogtypeid = id;
        condition = $("#condition").val();
        queryBlogs(1);
    }

    $("#btnQuery").click(function () {
        condition = $("#condition").val();
        queryBlogs(1);
    });


    layui.use('element', function () {
        var $ = layui.jquery,
            element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
    });
</script>

</body>
</html>