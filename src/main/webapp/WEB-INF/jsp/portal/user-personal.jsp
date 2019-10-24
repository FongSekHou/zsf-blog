<%--
  Created by IntelliJ IDEA.
  User: Fangxihao
  Date: 2019/10/21
  Time: 2:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>个人中心</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/initialization.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/personal.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconfont.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.jpg" type="image/x-icon">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>

<body>

<div class="header">
    <div class="logo">
        <img src="${pageContext.request.contextPath}/img/logo.jpg">
    </div>
    <ul class="header-nav">
        <li class="nav-list">主页</li>
        <li class="nav-list">收藏</li>
        <li class="nav-list part"></li>
        <li class="nav-list"><a href="${pageContext.request.contextPath}/user/personal" style="color:#00cbba">个人中心</a></li>
        <li class="nav-list writing"><a href="${pageContext.request.contextPath}/blog/edit">写博文</a></li>
        <li class="quit"><a href="${pageContext.request.contextPath}/user/logout">退出</a></li>
    </ul>
</div>
<div class="container">
    <div class="content">
        <form class="layui-form layui-form-pane" action="${pageContext.request.contextPath}/user/updateInfo" method="post"
              enctype="multipart/form-data">
            <div class="photo">
                <input type="hidden" name="id" value="${user.id}">
                <input type="hidden" name="username" value="${user.username}">
                <img src="${pageContext.request.contextPath}${user.iconpath}">
                <input type="file" title="点击修改头像" id="filechange" name="icon">
                <label for="filechange">更改头像</label>
            </div>
            <div class="msg-list">
                <div class="title">原密码</div>
                <div class="layui-form-item">
                    <div class="layui-input-inline">
                        <input type="password" name="password" placeholder="请输入原密码"
                               autocomplete="off" class="new-password information layui-input">
                    </div>
                </div>
            </div>
            <div class="msg-list">
                <div class="title">新密码</div>
                <div class="layui-form-item">
                    <div class="layui-input-inline">
                        <input type="password" name="newPassword" placeholder="请输入新密码"
                               autocomplete="off" class="new-password information layui-input">
                    </div>
                </div>
            </div>
            <div class="msg-list">
                <div class="title">生日</div>
                <div class="layui-form layui-form-pane">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <div class="layui-input-block">
                                <input type="text" name="birthday" id="date1" autocomplete="off"
                                       class="data information layui-input" value="${user.birthday}">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="msg-list">
                <div class="title">性别</div>
                <div class="layui-form">
                    <div class="layui-form-item">
                        <div class="sex layui-input-block">
                            <input type="radio" name="gender" value="false"
                                   title="男" ${user.gender == false ? "checked" : ""}>
                            <div class="layui-unselect layui-form-radio"><i class="layui-anim layui-icon"></i>
                                <div>男</div>
                            </div>
                            <input type="radio" name="gender" value="true"
                                   title="女" ${user.gender == true ? "checked" : ""}>
                            <div class="layui-unselect layui-form-radio layui-form-radioed"><i
                                    class="layui-anim layui-icon"></i>
                                <div>女</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-form">
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                    </div>
                </div>
            </div>
        </form>

    </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
<script src="${pageContext.request.contextPath}/js/personal.js"></script>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script>
    if("${msg}"!=""){
        alert("${msg}");
    }
    var msg = getQueryVariable("msg");
    if(msg!=undefined&&msg!=""){
        if(msg=="modifysuccess"){
            alert("修改成功");
        }else if(msg=="passworderror"){
            alert("原密码输入错误");
        }
    }
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
    //图片预览的实现
    $(":file").change(function (event) {
        var files = event.target.files;
        var file;
        if (files && files.length > 0) {
            file = files[0];
            var URL = window.URL || window.webkitURL;
            // 本地图片路径
            var imgURL = URL.createObjectURL(file);
            var imgObj = $(this).prev(); //获取用于显示图片的img标签对象
            //console.log(imgObj);
            imgObj.attr("src", imgURL);
            imgObj.show();
        }
    });
</script>
</body>

</html>