<%--
  Created by IntelliJ IDEA.
  User: Fangxihao
  Date: 2019/10/21
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>管理员管理</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.jpg" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/initialization.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconfont.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconfont2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>

<body>
<jsp:include page="manage-header.jsp"></jsp:include>
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
            <a href="${pageContext.request.contextPath}/manage/administrator">管理员管理</a>
        </div>
        <div class="colum-manage">
            <div class="search-box">
                <form>
                    <input type="text" class="search-input" placeholder="请输入栏目名称">
                    <div class="new-msg">新增栏目</div>
                </form>
            </div>
            <div class="msg-content">
                <ul class="nav-msg-list">
                    <li class="msg-list msg-title">
                            <span class="square-box">
                                <input type="checkbox" id="choose-box">
                                <label for="choose-box"></label>
                            </span>
                        <span class="msg-number">编号</span>
                        <span class="msg-name">账号</span>
                        <span class="msg-time">密码</span>
                        <span class="msg-do">操作</span>
                    </li>
                    <li class="msg-list">
                            <span class="square-box">
                                <input type="checkbox">
                                <label for="choose-box"></label>
                            </span>
                        <span class="msg-number">001</span>
                        <span class="msg-name">ZSFBLOG</span>
                        <span class="msg-time">2019-10-1</span>
                        <span class="msg-control">
                                <button class="new-msg msg-edit">编辑</button>
                                <button class="new-msg msg-delete">删除</button>
                                <button class="new-msg msg-finish" style="display: none;">完成</button>
                            </span>
                    </li>

                </ul>
                <div class="msg-footer">
                    <button class="new-msg" style="background-color: #f56c6c">批量删除</button>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="${pageContext.request.contextPath}/js/manage.js"></script>

</body>

</html>
