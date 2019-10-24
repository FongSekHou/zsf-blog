<%--
  Created by IntelliJ IDEA.
  User: Fangxihao
  Date: 2019/10/20
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/initialization.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconfont.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.jpg" type="image/x-icon">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>

<body>

<div class="container">
    <form class="content page-login" method="post" action="${pageContext.request.contextPath}/user/doLogin">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/img/logo.jpg">
        </div>
        <div class="heading">用户登录</div>
        <div class="form-group">
            <input type="text" class="form-control" name="username" placeholder="请输入你的用户名">
            <i class="iconfont icon-yonghu"></i>
        </div>
        <div class="form-group">
            <input type="password" class="form-control" name="password" placeholder="请输入你的密码">
            <i class="iconfont icon-mima"></i>
        </div>
        <div class="form-group">
            <button class="btn" type="submit">登录</button>
        </div>
        <span class="register">没有账号？<a>点我注册</a></span>
    </form>
    <form class="content page-register layui-form layui-form-pane"
          action="${pageContext.request.contextPath}/user/doRegister" method="post">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/img/logo.jpg">
        </div>
        <div class="heading">用户注册</div>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="请输入你的用户名" name="username">
            <i class="iconfont icon-yonghu"></i>
        </div>
        <div class="form-group">
            <input type="password" class="form-control" name="password" placeholder="请输入你的密码">
            <i class="iconfont icon-mima"></i>
        </div>
        <div class="form-group">
            <input type="password" class="form-control" id="repeatPassword" placeholder="请再次输入你的密码">
            <i class="iconfont icon-mima"></i>
        </div>
        <div class="form-group">
            <div class="title">生日</div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <div class="layui-input-block">
                        <input type="text" name="birthday" id="date1" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="title">性别</div>
            <div class="layui-form-item">
                <div class="sex layui-input-block">
                    <input type="radio" name="gender" value="false" title="男" checked="">
                    <div class="layui-unselect layui-form-radio"><i class="layui-anim layui-icon"></i>
                        <div>男</div>
                    </div>
                    <input type="radio" name="gender" value="true" title="女">
                    <div class="layui-unselect layui-form-radio layui-form-radioed"><i
                            class="layui-anim layui-icon layui-anim-scaleSpring"></i>
                        <div>女</div>
                    </div>

                </div>
            </div>
        </div>
        <div class="form-group">
            <button type="submit" class="btn">注册</button>
        </div>
        <span class="return">已有账号？<a>返回登录</a></span>
    </form>
</div>
<script>
    layui.use('form', function () {
    });
    layui.use(['form', 'layedit', 'laydate'], function () {
        var laydate = layui.laydate;
        //日期
        laydate.render({
            elem: '#date'
        });
        laydate.render({
            elem: '#date1'
        });

    });
</script>
<script>
    if ("${msg}" != "") {
        alert("${msg}");
    }
    if ("${usernameError}" != "") {
        alert("${usernameError}");
    }
</script>
<script src="${pageContext.request.contextPath}/js/login.js"></script>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
</body>

</html>