<%--
  Created by IntelliJ IDEA.
  User: Fangxihao
  Date: 2019/10/21
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>管理员登录</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.jpg" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/initialization.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconfont.css">

</head>

<body>

<div class="container">
    <form class="content" method="post" action="${pageContext.request.contextPath}/manage/doLogin">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/img/logo.jpg">
        </div>
        <div class="heading">管理员登录</div>
        <div class="form-group">
            <input type="text" class="form-control" id="adminname" name="adminname" placeholder="请输入账号" required>
            <i class="iconfont icon-yonghu"></i>
        </div>
        <div class="form-group">
            <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码" required>
            <i class="iconfont icon-mima"></i>
        </div>
        <div class="form-group">
            <button class="btn" type="submit">登录</button>
        </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/js/login.js"></script>

</body>

</html>
