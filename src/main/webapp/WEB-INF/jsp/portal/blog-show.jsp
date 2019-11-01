<%--
  Created by IntelliJ IDEA.
  User: Fangxihao
  Date: 2019/10/25
  Time: 2:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8"/>
    <title>博客展示</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/initialization.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/personal.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconfont.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.jpg" type="image/x-icon">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/editor.md-master/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/editor.md-master/css/editormd.preview.css"/>

</head>

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
        <li class="nav-list writing"><a href="${pageContext.request.contextPath}/blog/edit">写博客</a></li>
        <li class="quit"><a href="${pageContext.request.contextPath}/user/logout">退出</a></li>
    </ul>

</div>
<div class="container">
    <div class="blog-nav">
    </div>
    <div class="content blog-box clearfix">
        <div class="blog-title" id="title"></div>
        <div class="blog-msg">
            <ul>
                <li class="nav-list li-rose" id="authorname"></li>
                <li class="nav-list" id="completetime">发表于&nbsp;</li>
                <li class="nav-list li-style" id="viewnum">浏览&nbsp;</li>
                <li class="nav-list li-style" id="likenum">喜欢&nbsp;</li>
                <li class="nav-list li-style" id="collectnum">收藏&nbsp;</li>
                <li class="nav-list li-style" id="commentnum">留言&nbsp;</li>
            </ul>
        </div>
        <div class="blog-content clearfix">
            <div id="layout">
                <!-- 展示的 -->
                <div id="editormd-view">
                    <textarea style="display:none;" name="test-editormd-markdown-doc"></textarea>
                </div>
                <div id="editormd-view2">
                    <textarea id="append-test" style="display:none;"></textarea>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="right-nav">
    <div id="sidebar">
        <h1>标题导航窗格</h1>
        <div class="markdown-body editormd-preview-container" id="custom-toc-container">
        </div>
    </div>
</div>
<div class="left">
    <div id="likeDiv"><i class="iconfont icon-ai-like"></i>点赞</div>
    <div id="collectDiv"><i class="iconfont icon-xin"></i>收藏</div>
    <div><i class="iconfont icon-pinglun1"></i>评论</div>
</div>
<!--<div class="left">        
    <div onclick="doLike()"><i class="iconfont icon-ai-like"></i>点赞</div>        
    <div onclick="doCollect()"><i class="iconfont icon-xin"></i>收藏</div>        
    <div onclick=""><i class="iconfont icon-pinglun1"></i>评论</div>   
</div>-->


<!-- <script src="js/zepto.min.js"></script>
    <script>		
        var jQuery = Zepto;  // 为了避免修改flowChart.js和sequence-diagram.js的源码，所以使用Zepto.js时想支持flowChart/sequenceDiagram就得加上这一句
    </script> -->
<script src="${pageContext.request.contextPath}/js/personal.js"></script>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
<script src="${pageContext.request.contextPath}/editor.md-master/lib/marked.min.js"></script>
<script src="${pageContext.request.contextPath}/editor.md-master/lib/prettify.min.js"></script>
<script src="${pageContext.request.contextPath}/editor.md-master/lib/raphael.min.js"></script>
<script src="${pageContext.request.contextPath}/editor.md-master/lib/underscore.min.js"></script>
<script src="${pageContext.request.contextPath}/editor.md-master/lib/sequence-diagram.min.js"></script>
<script src="${pageContext.request.contextPath}/editor.md-master/lib/flowchart.min.js"></script>
<script src="${pageContext.request.contextPath}/editor.md-master/lib/jquery.flowchart.min.js"></script>
<script src="${pageContext.request.contextPath}/editor.md-master/editormd.min.js"></script>
<script type="text/javascript">
    layui.use('layer', function () {
        var layer = layui.layer;
    });
    $(function () {
        var blogid = getQueryVariable("blogid");

        var editormdView, editormdView2;
        $.get("${pageContext.request.contextPath}/blog/getBlog?blogid=" + blogid, function (blogshowVO) {
            $("#title").html(blogshowVO.title);
            $("#authorname").html(blogshowVO.authorname);
            $("#completetime").append(blogshowVO.completetime);
            $("#viewnum").append(blogshowVO.viewnum);
            $("#likenum").append(blogshowVO.likenum);
            $("#commentnum").append(blogshowVO.commentnum);
            $("#collectnum").append(blogshowVO.collectnum);

            /*获取blogshowVO的关系值，根据值对点赞的样式修改*/
            if(blogshowVO.likeRelation){
                $("#likeDiv").css("color","rgb(0, 161, 214)");
            }
            if(blogshowVO.collectRelation){
                $("#collectDiv").css("color","rgb(0, 161, 214)");
            }


            var markdown = blogshowVO.content;
            editormdView = editormd.markdownToHTML("editormd-view", {
                markdown: markdown,//+ "\r\n" + $("#append-test").text(),
                //htmlDecode      : true,       // 开启 HTML 标签解析，为了安全性，默认不开启
                htmlDecode: "style,script,iframe",  // you can filter tags decode
                //toc             : false,
                tocm: true,    // Using [TOCM]
                tocContainer: "#custom-toc-container", // 自定义 ToC 容器层
                //gfm             : false,
                //tocDropdown     : true,
                // markdownSourceCode : true, // 是否保留 Markdown 源码，即是否删除保存源码的 Textarea 标签
                emoji: true,
                taskList: true,
                tex: true,  // 默认不解析
                flowChart: true,  // 默认不解析
                sequenceDiagram: true,  // 默认不解析
            });
        });

        editormdView2 = editormd.markdownToHTML("editormd-view2", {
            htmlDecode: "style,script,iframe",  // you can filter tags decode
            emoji: true,
            taskList: true,
            tex: true,  // 默认不解析
            flowChart: true,  // 默认不解析
            sequenceDiagram: true,  // 默认不解析
        });
    });

    $("#likeDiv").click(function () {
        var blogid = getQueryVariable("blogid");
       if($("#likeDiv").css("color")=="rgb(0, 161, 214)"){//已点赞
           layer.confirm("确定取消点赞吗？", {icon: 3, title:'提示'}, function(index){
               $.get("${pageContext.request.contextPath}/user/cancelLike?blogid=" + blogid, function (result) {
                   if(isNaN(result)){
                       window.location.href = "${pageContext.request.contextPath}/user/login?flag=true";
                   }else{
                       $("#likenum").html("喜欢&nbsp;" + result);
                       $("#likeDiv").css("color","rgb(102, 102, 102)");
                       layer.msg("取消点赞成功");
                   }
               });
               layer.close(index);
           });
       }else{
           $.get("${pageContext.request.contextPath}/user/doLike?blogid=" + blogid, function (result) {
               if(isNaN(result)){
                   window.location.href = "${pageContext.request.contextPath}/user/login?flag=true";
               }else {
                   $("#likenum").html("喜欢&nbsp;" + result);
                   $("#likeDiv").css("color", "rgb(0, 161, 214)");
                   layer.msg("点赞成功");
               }
           });
       }
    });
    $("#collectDiv").click(function () {
        var blogid = getQueryVariable("blogid");
       if($("#collectDiv").css("color")=="rgb(0, 161, 214)"){
           layer.confirm("确定取消收藏吗？", {icon: 3, title:'提示'}, function(index){
               $.get("${pageContext.request.contextPath}/user/cancelCollect?blogid=" + blogid, function (result) {
                   if(isNaN(result)){//返回的不是数字那么则是拦截器返回的页面，用json方式无法显示页面，所以发重定向请求
                       window.location.href = "${pageContext.request.contextPath}/user/login?flag=true";
                   }else {
                       $("#collectnum").html("收藏&nbsp;" + result);
                       $("#collectDiv").css("color", "rgb(102, 102, 102)");
                       layer.msg("取消收藏成功");
                   }
               });
               layer.close(index);
           });
       }else{
           $.get("${pageContext.request.contextPath}/user/doCollect?blogid=" + blogid, function (result) {
               if(isNaN(result)){
                   window.location.href = "${pageContext.request.contextPath}/user/login?flag=true";
               }else {
                   $("#collectnum").html("收藏&nbsp;" + result);
                   $("#collectDiv").css("color", "rgb(0, 161, 214)");
                   layer.msg("收藏成功");
               }
           });
       }
    });

</script>
</body>

</html>
