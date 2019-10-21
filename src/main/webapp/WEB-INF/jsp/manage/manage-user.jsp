<%--
  Created by IntelliJ IDEA.
  User: Fangxihao
  Date: 2019/10/21
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>用户管理</title>
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
<div class="main clearfix">
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
    <div class="main-right clearfix">
        <div class="title-bar">
            <div>首页</div>
            <i class="iconfont icon-jiantou"></i>
            <span>用户管理</span>
        </div>
        <div class="user-manager clearfix">
            <form class="user-header">
                <input type="text" class="user-search" placeholder="可通过搜索名来搜索用户">
                <span class="new-msg"><i class="iconfont icon-nav"></i>搜索</span>
            </form>
            <div class="user-content">
                <div class="user-msg">
                    <div class="user-msg-header">
                        <span class="user-name">柯裕雨</span>
                        <span class="user-delete">
                                <i class="iconfont icon-icon7" title="删除该用户"></i>
                            </span>
                    </div>
                    <div class="user-detail">
                        <div class="user-detail-img">
                            <img src="../登录/p.jpg">
                        </div>
                        <ul class="nav-user-msg">
                            <li class="list-user-msg">
                                <span>用户名:&nbsp;</span>
                                <span>屌丝</span>
                            </li>
                            <li class="list-user-msg">
                                <span>电子邮箱:&nbsp;</span>
                                <span>101010@qq.com</span>
                            </li>
                            <li class="list-user-msg">
                                <span>注册时间:&nbsp;</span>
                                <span>2019-10-1 9:59</span>
                            </li>
                            <li class="list-user-msg">
                                <span>用户状态:&nbsp;</span>
                                <span class="ban">禁用</span>
                                <span class="user-state"><span class="state-btn" onclick="stateChange()"></span></span>
                                <span class="start">启用</span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="user-msg">
                    <div class="user-msg-header">
                        <span class="user-name">柯裕雨</span>
                        <span class="user-delete">
                                <i class="iconfont icon-icon7" title="删除该用户"></i>
                            </span>
                    </div>
                    <div class="user-detail">
                        <div class="user-detail-img">
                            <img src="../登录/p.jpg">
                        </div>
                        <ul class="nav-user-msg">
                            <li class="list-user-msg">
                                <span>用户名:&nbsp;</span>
                                <span>屌丝</span>
                            </li>
                            <li class="list-user-msg">
                                <span>电子邮箱:&nbsp;</span>
                                <span>101010@qq.com</span>
                            </li>
                            <li class="list-user-msg">
                                <span>注册时间:&nbsp;</span>
                                <span>2019-10-1 9:59</span>
                            </li>
                            <li class="list-user-msg">
                                <span>用户状态:&nbsp;</span>
                                <span class="ban">禁用</span>
                                <span class="user-state"><span class="state-btn"></span></span>
                                <span class="start">启用</span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="user-msg">
                    <div class="user-msg-header">
                        <span class="user-name">柯裕雨</span>
                        <span class="user-delete">
                                <i class="iconfont icon-icon7" title="删除该用户"></i>
                            </span>
                    </div>
                    <div class="user-detail">
                        <div class="user-detail-img">
                            <img src="../登录/p.jpg">
                        </div>
                        <ul class="nav-user-msg">
                            <li class="list-user-msg">
                                <span>用户名:&nbsp;</span>
                                <span>屌丝</span>
                            </li>
                            <li class="list-user-msg">
                                <span>电子邮箱:&nbsp;</span>
                                <span>101010@qq.com</span>
                            </li>
                            <li class="list-user-msg">
                                <span>注册时间:&nbsp;</span>
                                <span>2019-10-1 9:59</span>
                            </li>
                            <li class="list-user-msg">
                                <span>用户状态:&nbsp;</span>
                                <span class="ban">禁用</span>
                                <span class="user-state"><span class="state-btn"></span></span>
                                <span class="start">启用</span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="user-msg">
                    <div class="user-msg-header">
                        <span class="user-name">柯裕雨</span>
                        <span class="user-delete">
                                <i class="iconfont icon-icon7" title="删除该用户"></i>
                            </span>
                    </div>
                    <div class="user-detail">
                        <div class="user-detail-img">
                            <img src="../登录/p.jpg">
                        </div>
                        <ul class="nav-user-msg">
                            <li class="list-user-msg">
                                <span>用户名:&nbsp;</span>
                                <span>屌丝</span>
                            </li>
                            <li class="list-user-msg">
                                <span>电子邮箱:&nbsp;</span>
                                <span>101010@qq.com</span>
                            </li>
                            <li class="list-user-msg">
                                <span>注册时间:&nbsp;</span>
                                <span>2019-10-1 9:59</span>
                            </li>
                            <li class="list-user-msg">
                                <span>用户状态:&nbsp;</span>
                                <span class="ban">禁用</span>
                                <span class="user-state"><span class="state-btn"></span></span>
                                <span class="start">启用</span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="user-msg">
                    <div class="user-msg-header">
                        <span class="user-name">柯裕雨</span>
                        <span class="user-delete">
                                <i class="iconfont icon-icon7" title="删除该用户"></i>
                            </span>
                    </div>
                    <div class="user-detail">
                        <div class="user-detail-img">
                            <img src="../登录/p.jpg">
                        </div>
                        <ul class="nav-user-msg">
                            <li class="list-user-msg">
                                <span>用户名:&nbsp;</span>
                                <span>屌丝</span>
                            </li>
                            <li class="list-user-msg">
                                <span>电子邮箱:&nbsp;</span>
                                <span>101010@qq.com</span>
                            </li>
                            <li class="list-user-msg">
                                <span>注册时间:&nbsp;</span>
                                <span>2019-10-1 9:59</span>
                            </li>
                            <li class="list-user-msg">
                                <span>用户状态:&nbsp;</span>
                                <span class="ban">禁用</span>
                                <span class="user-state"><span class="state-btn"></span></span>
                                <span class="start">启用</span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="user-msg">
                    <div class="user-msg-header">
                        <span class="user-name">柯裕雨</span>
                        <span class="user-delete">
                                <i class="iconfont icon-icon7" title="删除该用户"></i>
                            </span>
                    </div>
                    <div class="user-detail">
                        <div class="user-detail-img">
                            <img src="../登录/p.jpg">
                        </div>
                        <ul class="nav-user-msg">
                            <li class="list-user-msg">
                                <span>用户名:&nbsp;</span>
                                <span>屌丝</span>
                            </li>
                            <li class="list-user-msg">
                                <span>电子邮箱:&nbsp;</span>
                                <span>101010@qq.com</span>
                            </li>
                            <li class="list-user-msg">
                                <span>注册时间:&nbsp;</span>
                                <span>2019-10-1 9:59</span>
                            </li>
                            <li class="list-user-msg">
                                <span>用户状态:&nbsp;</span>
                                <span class="ban">禁用</span>
                                <span class="user-state"><span class="state-btn"></span></span>
                                <span class="start">启用</span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<script src="${pageContext.request.contextPath}/js/manage.js"></script>

</body>

</html>