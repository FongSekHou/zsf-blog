<%--
  Created by IntelliJ IDEA.
  User: Fangxihao
  Date: 2019/10/25
  Time: 1:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="multipart/form-data;charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>博客编辑</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/initialization.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/personal.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconfont.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.jpg" type="image/x-icon">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/editor.md-master/css/style.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/editor.md-master/css/editormd.css" />
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
        <li class="nav-list"><a href="${pageContext.request.contextPath}/user/notification"><div class="${tipstyle}"></div>消息通知</a></li>
        <li class="nav-list part"></li>
        <li class="nav-list"><a href="${pageContext.request.contextPath}/user/personal">个人中心</a></li>
        <li class="nav-list writing" id="submitLi">发表博客</li>
        <li class="quit"><a href="${pageContext.request.contextPath}/user/logout">退出</a></li>
    </ul>
</div>
<div class="container">
    <div class="content">
        <form class="layui-form blog-edit" action="${pageContext.request.contextPath}/blog/commitBlog" method="post" enctype="multipart/form-data" name="form" id="form">
            <input type="hidden" name="id" value="${blog.id}"/>
            <input type="hidden" name="userId" value="${user.id}"/>
            <div class="content-edit-header">
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <input type="text" name="title" required lay-verify="required" placeholder="请输入标题"
                               autocomplete="off" class="layui-input" value="${blog.title}">
                    </div>
                </div>
                <div class="blog-choose">
                    <select name="blogtypeId" lay-verify="" lay-search>
                        <option value="-1">请选择博客栏目</option>
                        <c:forEach items="${blogtypes}" var="blogtype">
                            <option value="${blogtype.id}" ${blog.blogtypeId==blogtype.id?"selected":""}>${blogtype.blogtypename}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="blog-choose">
                    <select name="status" lay-verify="">
                        <option value="-1">请选择博客状态</option>
                        <option value="1" ${blog.status==1?"selected":""}>存入草稿</option>
                        <option value="2" ${blog.status==2?"selected":""}>发表博客</option>
                    </select>
                </div>
            </div>
            <div id="editormd">
                <textarea class="editormd-markdown-textarea" name="content">${blog.content}</textarea>
                <textarea class="editormd-html-textarea"></textarea>
            </div>
        </form>

    </div>

</div>
<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
<script src="${pageContext.request.contextPath}/editor.md-master/editormd.min.js"></script>
<script>

    layui.use('form', function () {
        var form = layui.form;
    });
    layui.use('layer', function(){
        var layer = layui.layer;
    });
    var testEditor;
    $(function () {
        testEditor = editormd("editormd", {
            width: "100%",
            height: 640,
            syncScrolling: "single",
            path: "${pageContext.request.contextPath}/editor.md-master/lib/",
            saveHTMLToTextarea: true,
            imageUpload: true,
            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL: "${pageContext.request.contextPath}/blog/uploadImg?blogid=${blogid}",//将当前博客的id一同交给后台
            emoji: true,
            taskList: true,
            tocm: true,                     // Using [TOCM]
            tex: true,                      // 开启科学公式TeX语言支持，默认关闭
            flowChart: true,                // 开启流程图支持，默认关闭
            sequenceDiagram: true           // 开启时序/序列图支持，默认关闭
        });
    });

   /* window.addEventListener("beforeunload", function (e) {
        (e || window.event).returnValue = '你的操作还未保存，确定离开此页吗？';
    });*/


    //提交触发事件解绑beforeunload事件
    $("#submitLi").click(function () {
        $(window).unbind('beforeunload');
        $("#form").submit();
    });

    //绑定beforeunload事件
    $(window).bind('beforeunload',function(){
        return '你的操作还未保存，确定离开此页吗？';
    });


</script>

</body>

</html>
