<%--
  Created by IntelliJ IDEA.
  User: Fangxihao
  Date: 2019/10/21
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>文章管理</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.jpg" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/initialization.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconfont.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>

<body>
<div class="header">
    <%@include file="manage-header.jsp" %>
</div>
<div class="main">
    <div class="main-left">
        <ul class="nav">
            <li class="nav-list">
                <a href="${pageContext.request.contextPath}/manage/article" target="_parent">
                    <i class="iconfont icon-wenzhangguanli"></i>文章管理
                </a>
            </li>
            <li class="nav-list">
                <a href="${pageContext.request.contextPath}/manage/user" target="_parent">
                    <i class="iconfont icon-yonghu"></i>用户管理
                </a>
            </li>
            <li class="nav-list">
                <a href="${pageContext.request.contextPath}/manage/column" target="_parent">
                    <i class="iconfont icon-nav"></i>栏目管理
                </a>
            </li>
            <li class="nav-list">
                <a href="${pageContext.request.contextPath}/manage/data" target="_parent">
                    <i class="iconfont icon-digital"></i>数据统计
                </a>
            </li>
            <li class="nav-list" style="background-color: #fff;">
                <a href="${pageContext.request.contextPath}/manage/administrator" target="_parent">
                    <i class="iconfont icon-guanliyuan"></i>管理员管理
                </a>
            </li>
        </ul>
    </div>
    <div class="main-right">
        <div class="title-bar">
            <div>首页</div>
            <i class="iconfont icon-jiantou"></i>
            <span>文章管理</span>
        </div>
        <div class="article-manage">
            <div class="search-box">
                <form>
                    <i class="search-icon iconfont icon-sousuokuang"></i>
                    <input type="text" class="search-input" placeholder="通过标题搜索改分类下的博客">
                    <div class="new-msg">搜索</div>
                </form>
            </div>
            <div class="msg-content">
                <ul class="nav-msg-list">
                    <li class="msg-list msg-title">
                            <span class="square-box">
                                <input type="checkbox" id="choose-box">
                                <label for="choose-box"></label>
                            </span>
                        <span class="article-number">标题</span>
                        <span class="article-time">最近编辑时间</span>
                        <span class="article-author">作者</span>
                        <span class="article-classify">所属分类</span>
                        <span class="article-do">操作</span>
                    </li>
                    <li class="msg-list">
                            <span class="square-box">
                                <input type="checkbox">
                                <label for="choose-box"></label>
                            </span>
                        <span class="article-number">标题</span>
                        <span class="article-time">2019-10-1</span>
                        <span class="article-author">作者</span>
                        <span class="article-classify">所属分类</span>
                        <span class="article-control">
                                <button class="new-msg article-edit">编辑</button>
                                <button class="new-msg article-delete">删除</button>
                                <button class="new-msg msg-finish" style="display: none;">完成</button>
                            </span>
                    </li>

                </ul>
                <div class="msg-footer">
                    <button class="new-msg" style="background-color: #f56c6c">批量删除</button>
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li>
                                <a href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li>
                                <a href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/manage.js"></script>
<script>
    const navList = document.querySelectorAll(".nav-list");

    for (var i = 0; i <= navList.length; i++) {
        (function (n) {
            navList[n].onclick = function () {
                for (var j = 0; j <= navList.length; j++) {
                    navList[j].style.backGround = "#ececec";
                }
                navList[n].style.backGround = "#ececec";
            }

        }(i))
    }
</script>

</body>

</html>