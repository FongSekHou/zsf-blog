<%--
  Created by IntelliJ IDEA.
  User: Fangxihao
  Date: 2019/10/21
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>数据管理</title>
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
            <span>数据管理</span>
        </div>
        <div class="data-manager">
            <div class="data-show">
                <img src="../登录/p.jpg">
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/manage.js"></script>

</body>

</html>
