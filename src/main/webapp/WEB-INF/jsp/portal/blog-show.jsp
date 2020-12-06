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
    <style type="text/css">
        /* 评论部分 */
        .comment-box{
            display: flex;
            justify-content: center;
            width: 100%;
            background-color: #f3f1f1;
            overflow: hidden;
        }
        .comment {
            display: flex;
            flex-direction: column;
            float: left;
            width: 75%;
            padding-top: 20px;
            margin-bottom: 30px;
            background-color: #fff;
            text-align-last: left;
        }

        .comment-item {
            display: inline-block;
            width: 600px;
            margin-left: 200px;
        }

        .user-face {
            position: relative;
            float: left;
            margin: 24px 0 0 5px;
        }

        .user-face>img {
            width: 48px;
            height: 48px;
            border-radius: 50%;
        }

        .con {
            position: relative;
            margin-left: 85px;
            padding: 22px 0 14px;
            border-top: 1px solid #e5e9ef;
        }

        .user {
            padding-bottom: 4px;
            text-align: left;
            line-height: 18px;
            font-size: 12px;
            font-weight: 700;
            color: #00a1d6;
            word-wrap: break-word;
        }

        .text {
            padding: 2px 0;
            line-height: 20px;
            font-size: 14px;
            text-shadow: none;
            overflow: hidden;
            word-wrap: break-word;
            word-break: break-word;
        }

        .info {
            line-height: 26px;
            font-size: 12px;
            color: #99a2aa;
        }

        .time {
            padding: 0 !important;
        }

        .info>span {
            display: inline-block;
            padding: 0 5px;
            margin-right: 20px;
            border-radius: 4px;
            cursor: pointer;
        }

        .like>i {
            font-size: 12px;
            margin-right: 5px;
        }

        .like:hover,
        .reply:hover {
            color: #00a1d6;
            background: #e5e9ef;
        }

        .reply-item {
            padding: 10px 0;
        }

        .reply-face {
            float: left;
            position: relative;
            vertical-align: top;
            margin-right: 10px;
        }

        .reply-face>img {
            width: 24px;
            height: 24px;
            border-radius: 50%;
        }

        .reply-con {
            float: left;
            width: calc(100% - 34px);
        }

        .reply-user {
            position: relative;
            display: inline-block;
            color: #00a1d6;
        }

        .reply-text {
            display: inline-block;
            font-weight: 400;
            font-size: 14px;
            line-height: 20px;
            word-break: break-all;
        }

        .view-more {
            font-size: 12px;
            color: #6d757a;
        }

        .view-more a {
            padding: 2px 3px;
            color: #00a1d6;
            border-radius: 4px;
        }

        .view-more a:hover {
            color: #00a1d6;
            background: #e5e9ef;
        }
        .take-back{
            width: 54px;
            font-size: 12px;
            padding: 2px 3px;
            color: #00a1d6;
            border-radius: 4px;
            cursor: pointer;
        }
        .take-back:hover{
            color: #00a1d6;
            background: #e5e9ef;
        }
        /* 回复输入框 */
        .comment-send {
            margin: 10px 0;
        }

        .comment-send>.user-face {
            margin: 7px 0 0 5px;
        }

        .textarea-container {
            position: relative;
            margin-left: 85px;
            margin-right: 80px;
        }

        .ipt-txt {
            display: inline-block;
            width: 100% !important;
            height: 65px;
            font-size: 12px;
            box-sizing: border-box;
            padding: 5px 10px;
            line-height: normal;
            color: #555;
            background-color: #e5e9ef;
            border: 1px solid #e5e9ef;
            border-radius: 4px;
            outline: none;
            overflow: auto;
            resize: none;
            transition: .3s;
        }

        .comment-submit {
            position: absolute;
            vertical-align: top;
            top: 0;
            right: -80px;
            width: 70px;
            height: 64px;
            min-width: 60px;
            padding: 4px 15px;
            font-size: 14px;
            text-align: center;
            color: #b8c0cc;
            background-color: #e5e9ef;
            border: 1px solid #e5e9ef;
            border-radius: 4px;
            outline: none;
            user-select: none;
            transition: .3s;
            cursor: pointer;
        }
    </style>

</head>

<body>
<div class="header">
    <div class="logo">
        <img src="${pageContext.request.contextPath}/img/logo.jpg">
    </div>
    <ul class="header-nav">
        <li class="nav-list"><a href="${pageContext.request.contextPath}/user/index">主页</a></li>
        <li class="nav-list"><a href="${pageContext.request.contextPath}/user/notification">
            <div class="${tipstyle}"></div>
            消息通知</a></li>
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

<div class="comment-box">
    <div class="comment">
        <div class="comment-item">
            <div class="user-face">
                <img src="${pageContext.request.contextPath}/images/icon/default1.jpg">
            </div>
            <div class="con">
                <div class="user">Steven</div>
                <div class="text">点赞收藏过3万继续更此系列，至于下个月更不更就看你们三连多不多啦</div>
                <div class="info">
                    <span class="time">2019-10-25&nbsp;12:03</span>
                    <span class="like">
                            <i class="iconfont icon-xin"></i><span>0</span>
                        </span>
                    <span class="reply">回复</span>
                </div>
                <div class="reply-box">
                    <div class="reply-item">
                        <div class="reply-face">
                            <img src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
                        </div>
                        <div class="reply-con">
                            <div class="reply-user">fang</div>
                            <div class="reply-text">我代表大家再强调一下，完了，播放要求是不可能的</div>
                        </div>
                        <div class="info">
                            <span class="time">2019-10-25 12:25</span>
                            <span class="like ">
                                    <i class="iconfont icon-xin"></i><span>304</span>
                                </span>
                            <span class="reply">回复</span>
                        </div>
                    </div>
                    <div class="reply-item">
                        <div class="reply-face">
                            <img src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
                        </div>
                        <div class="reply-con">
                            <div class="reply-user">fang</div>
                            <div class="reply-text">我代表大家再强调一下，完了，播放要求是不可能的</div>
                        </div>
                        <div class="info">
                            <span class="time">2019-10-25 12:25</span>
                            <span class="like ">
                                    <i class="iconfont icon-xin"></i><span>304</span>
                                </span>
                            <span class="reply">回复</span>
                        </div>
                    </div>
                    <div class="reply-item" style="display: none;">
                        <div class="reply-face">
                            <img src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
                        </div>
                        <div class="reply-con">
                            <div class="reply-user">fang</div>
                            <div class="reply-text">我代表大家再强调一下，完了，播放要求是不可能的</div>
                        </div>
                        <div class="info">
                            <span class="time">2019-10-25 12:25</span>
                            <span class="like ">
                                    <i class="iconfont icon-xin"></i><span>304</span>
                                </span>
                            <span class="reply">回复</span>
                        </div>
                    </div>
                    <div class="reply-item" style="display: none;">
                        <div class="reply-face">
                            <img src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
                        </div>
                        <div class="reply-con">
                            <div class="reply-user">fang</div>
                            <div class="reply-text">我代表大家再强调一下，完了，播放要求是不可能的</div>
                        </div>
                        <div class="info">
                            <span class="time">2019-10-25 12:25</span>
                            <span class="like ">
                                    <i class="iconfont icon-xin"></i><span>304</span>
                                </span>
                            <span class="reply">回复</span>
                        </div>
                    </div>
                    <div class="view-more">共<b>81</b>条回复, <a href="javascrit:void(0)">点击查看</a></div>
                    <div class="reply-paging" style="display: none;">
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
                    <div class="take-back" style="display: none;">收回评论</div>
                    <div class="comment-send" style="display: none;">
                        <div class="user-face">
                            <img class="user-head" src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
                        </div>
                        <div class="textarea-container">
                            <textarea cols="10" rows="5" placeholder="回复fang" class="ipt-txt"></textarea>
                            <button class="comment-submit">发表评论</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="comment-item">
            <div class="user-face">
                <img src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
            </div>
            <div class="con">
                <div class="user">Steven</div>
                <div class="text">点赞收藏过3万继续更此系列，至于下个月更不更就看你们三连多不多啦</div>
                <div class="info">
                    <span class="time">2019-10-25&nbsp;12:03</span>
                    <span class="like">
                            <i class="iconfont icon-xin"></i><span>0</span>
                        </span>
                    <span class="reply">回复</span>
                </div>
                <div class="reply-box">
                    <div class="reply-item">
                        <div class="reply-face">
                            <img src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
                        </div>
                        <div class="reply-con">
                            <div class="reply-user">fang</div>
                            <div class="reply-text">我代表大家再强调一下，完了，播放要求是不可能的</div>
                        </div>
                        <div class="info">
                            <span class="time">2019-10-25 12:25</span>
                            <span class="like ">
                                    <i class="iconfont icon-xin"></i><span>304</span>
                                </span>
                            <span class="reply">回复</span>
                        </div>
                    </div>
                    <div class="reply-item">
                        <div class="reply-face">
                            <img src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
                        </div>
                        <div class="reply-con">
                            <div class="reply-user">fang</div>
                            <div class="reply-text">我代表大家再强调一下，完了，播放要求是不可能的</div>
                        </div>
                        <div class="info">
                            <span class="time">2019-10-25 12:25</span>
                            <span class="like ">
                                    <i class="iconfont icon-xin"></i><span>304</span>
                                </span>
                            <span class="reply">回复</span>
                        </div>
                    </div>
                    <div class="reply-item" style="display: none;">
                        <div class="reply-face">
                            <img src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
                        </div>
                        <div class="reply-con">
                            <div class="reply-user">fang</div>
                            <div class="reply-text">我代表大家再强调一下，完了，播放要求是不可能的</div>
                        </div>
                        <div class="info">
                            <span class="time">2019-10-25 12:25</span>
                            <span class="like ">
                                    <i class="iconfont icon-xin"></i><span>304</span>
                                </span>
                            <span class="reply">回复</span>
                        </div>
                    </div>
                    <div class="reply-item" style="display: none;">
                        <div class="reply-face">
                            <img src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
                        </div>
                        <div class="reply-con">
                            <div class="reply-user">fang</div>
                            <div class="reply-text">我代表大家再强调一下，完了，播放要求是不可能的</div>
                        </div>
                        <div class="info">
                            <span class="time">2019-10-25 12:25</span>
                            <span class="like ">
                                    <i class="iconfont icon-xin"></i><span>304</span>
                                </span>
                            <span class="reply">回复</span>
                        </div>
                    </div>
                    <div class="view-more">共<b>81</b>条回复, <a href="javascrit:void(0)">点击查看</a></div>
                    <div class="reply-paging" style="display: none;">
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
                    <div class="take-back" style="display: none;">收回评论</div>
                    <div class="comment-send" style="display: none;">
                        <div class="user-face">
                            <img class="user-head" src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
                        </div>
                        <div class="textarea-container">
                            <textarea cols="10" rows="5" placeholder="回复fang" class="ipt-txt"></textarea>
                            <button class="comment-submit">发表评论</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="comment-item">
            <div class="user-face">
                <img src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
            </div>
            <div class="con">
                <div class="user">Steven</div>
                <div class="text">点赞收藏过3万继续更此系列，至于下个月更不更就看你们三连多不多啦</div>
                <div class="info">
                    <span class="time">2019-10-25&nbsp;12:03</span>
                    <span class="like">
                            <i class="iconfont icon-xin"></i><span>0</span>
                        </span>
                    <span class="reply">回复</span>
                </div>
                <div class="reply-box">
                    <div class="reply-item">
                        <div class="reply-face">
                            <img src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
                        </div>
                        <div class="reply-con">
                            <div class="reply-user">fang</div>
                            <div class="reply-text">我代表大家再强调一下，完了，播放要求是不可能的</div>
                        </div>
                        <div class="info">
                            <span class="time">2019-10-25 12:25</span>
                            <span class="like ">
                                    <i class="iconfont icon-xin"></i><span>304</span>
                                </span>
                            <span class="reply">回复</span>
                        </div>
                    </div>
                    <div class="reply-item">
                        <div class="reply-face">
                            <img src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
                        </div>
                        <div class="reply-con">
                            <div class="reply-user">fang</div>
                            <div class="reply-text">我代表大家再强调一下，完了，播放要求是不可能的</div>
                        </div>
                        <div class="info">
                            <span class="time">2019-10-25 12:25</span>
                            <span class="like ">
                                    <i class="iconfont icon-xin"></i><span>304</span>
                                </span>
                            <span class="reply">回复</span>
                        </div>
                    </div>
                    <div class="reply-item" style="display: none;">
                        <div class="reply-face">
                            <img src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
                        </div>
                        <div class="reply-con">
                            <div class="reply-user">fang</div>
                            <div class="reply-text">我代表大家再强调一下，完了，播放要求是不可能的</div>
                        </div>
                        <div class="info">
                            <span class="time">2019-10-25 12:25</span>
                            <span class="like ">
                                    <i class="iconfont icon-xin"></i><span>304</span>
                                </span>
                            <span class="reply">回复</span>
                        </div>
                    </div>
                    <div class="reply-item" style="display: none;">
                        <div class="reply-face">
                            <img src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
                        </div>
                        <div class="reply-con">
                            <div class="reply-user">fang</div>
                            <div class="reply-text">我代表大家再强调一下，完了，播放要求是不可能的</div>
                        </div>
                        <div class="info">
                            <span class="time">2019-10-25 12:25</span>
                            <span class="like ">
                                    <i class="iconfont icon-xin"></i><span>304</span>
                                </span>
                            <span class="reply">回复</span>
                        </div>
                    </div>
                    <div class="view-more">共<b>81</b>条回复, <a href="javascrit:void(0)">点击查看</a></div>
                    <div class="reply-paging" style="display: none;">
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
                    <div class="take-back" style="display: none;">收回评论</div>
                    <div class="comment-send" style="display: none;">
                        <div class="user-face">
                            <img class="user-head" src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
                        </div>
                        <div class="textarea-container">
                            <textarea cols="10" rows="5" placeholder="回复fang" class="ipt-txt"></textarea>
                            <button class="comment-submit">发表评论</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="comment-item">
            <div class="user-face">
                <img src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
            </div>
            <div class="con">
                <div class="user">Steven</div>
                <div class="text">点赞收藏过3万继续更此系列，至于下个月更不更就看你们三连多不多啦</div>
                <div class="info">
                    <span class="time">2019-10-25&nbsp;12:03</span>
                    <span class="like">
                            <i class="iconfont icon-xin"></i><span>0</span>
                        </span>
                    <span class="reply">回复</span>
                </div>
                <div class="reply-box">
                    <div class="reply-item">
                        <div class="reply-face">
                            <img src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
                        </div>
                        <div class="reply-con">
                            <div class="reply-user">fang</div>
                            <div class="reply-text">我代表大家再强调一下，完了，播放要求是不可能的</div>
                        </div>
                        <div class="info">
                            <span class="time">2019-10-25 12:25</span>
                            <span class="like ">
                                    <i class="iconfont icon-xin"></i><span>304</span>
                                </span>
                            <span class="reply">回复</span>
                        </div>
                    </div>
                    <div class="reply-item">
                        <div class="reply-face">
                            <img src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
                        </div>
                        <div class="reply-con">
                            <div class="reply-user">fang</div>
                            <div class="reply-text">我代表大家再强调一下，完了，播放要求是不可能的</div>
                        </div>
                        <div class="info">
                            <span class="time">2019-10-25 12:25</span>
                            <span class="like ">
                                    <i class="iconfont icon-xin"></i><span>304</span>
                                </span>
                            <span class="reply">回复</span>
                        </div>
                    </div>
                    <div class="reply-item" style="display: none;">
                        <div class="reply-face">
                            <img src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
                        </div>
                        <div class="reply-con">
                            <div class="reply-user">fang</div>
                            <div class="reply-text">我代表大家再强调一下，完了，播放要求是不可能的</div>
                        </div>
                        <div class="info">
                            <span class="time">2019-10-25 12:25</span>
                            <span class="like ">
                                    <i class="iconfont icon-xin"></i><span>304</span>
                                </span>
                            <span class="reply">回复</span>
                        </div>
                    </div>
                    <div class="reply-item" style="display: none;">
                        <div class="reply-face">
                            <img src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
                        </div>
                        <div class="reply-con">
                            <div class="reply-user">fang</div>
                            <div class="reply-text">我代表大家再强调一下，完了，播放要求是不可能的</div>
                        </div>
                        <div class="info">
                            <span class="time">2019-10-25 12:25</span>
                            <span class="like ">
                                    <i class="iconfont icon-xin"></i><span>304</span>
                                </span>
                            <span class="reply">回复</span>
                        </div>
                    </div>
                    <div class="view-more">共<b>81</b>条回复, <a href="javascrit:void(0)">点击查看</a></div>
                    <div class="reply-paging" style="display: none;">
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
                    <div class="take-back" style="display: none;">收回评论</div>
                    <div class="comment-send" style="display: none;">
                        <div class="user-face">
                            <img class="user-head" src=" ${pageContext.request.contextPath}/images/icon/default1.jpg">
                        </div>
                        <div class="textarea-container">
                            <textarea cols="10" rows="5" placeholder="回复fang" class="ipt-txt"></textarea>
                            <button class="comment-submit">发表评论</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
   

                           


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
    $(document).ready(function () {
        // 查看更多评论事件
        $(".view-more>a").click(function () {
            $(this).parent().css({ display: "none" });
            $(this).parent().siblings(".take-back").css({ display: "block" });
            $(this).parent().siblings(".reply-item").css({ display: "block" });
            $(this).parent().siblings(".reply-paging").css({ display: "block" });
        });
        // 收回查看评论
        $(".take-back").click(function () {
            $(this).css({ display: "none" });
            $(this).siblings(".view-more").css({ display: "block" });
            $(this).siblings(".reply-item").css({ display: "none" });
            $(this).siblings(".reply-paging").css({ display: "none" });
            $(this).siblings(".reply-item").eq(0).css({ display: "block" });
            $(this).siblings(".reply-item").eq(1).css({ display: "block" });
        });

        // 回复点击显示输入框事件
        $(".reply").click(function () {
            $(".comment-send").css({ display: "none" });
            $(this).parent().parent().siblings(".comment-send").css({ display: "block" });
            $(this).parent().siblings(".reply-box").children(".comment-send").css({ display: "block" });
        })
    });

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
            if (blogshowVO.likeRelation) {
                $("#likeDiv").css("color", "rgb(0, 161, 214)");
            }
            if (blogshowVO.collectRelation) {
                $("#collectDiv").css("color", "rgb(0, 161, 214)");
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
        if ($("#likeDiv").css("color") == "rgb(0, 161, 214)") {//已点赞
            layer.confirm("确定取消点赞吗？", {icon: 3, title: '提示'}, function (index) {
                $.get("${pageContext.request.contextPath}/user/cancelLike?blogid=" + blogid, function (result) {
                    if (isNaN(result)) {
                        window.location.href = "${pageContext.request.contextPath}/user/login?flag=true";
                    } else {
                        $("#likenum").html("喜欢&nbsp;" + result);
                        $("#likeDiv").css("color", "rgb(102, 102, 102)");
                        layer.msg("取消点赞成功");
                    }
                });
                layer.close(index);
            });
        } else {
            $.get("${pageContext.request.contextPath}/user/doLike?blogid=" + blogid, function (result) {
                if (isNaN(result)) {
                    window.location.href = "${pageContext.request.contextPath}/user/login?flag=true";
                } else {
                    $("#likenum").html("喜欢&nbsp;" + result);
                    $("#likeDiv").css("color", "rgb(0, 161, 214)");
                    layer.msg("点赞成功");
                }
            });
        }
    });
    $("#collectDiv").click(function () {
        var blogid = getQueryVariable("blogid");
        if ($("#collectDiv").css("color") == "rgb(0, 161, 214)") {
            layer.confirm("确定取消收藏吗？", {icon: 3, title: '提示'}, function (index) {
                $.get("${pageContext.request.contextPath}/user/cancelCollect?blogid=" + blogid, function (result) {
                    if (isNaN(result)) {//返回的不是数字那么则是拦截器返回的页面，用json方式无法显示页面，所以发重定向请求
                        window.location.href = "${pageContext.request.contextPath}/user/login?flag=true";
                    } else {
                        $("#collectnum").html("收藏&nbsp;" + result);
                        $("#collectDiv").css("color", "rgb(102, 102, 102)");
                        layer.msg("取消收藏成功");
                    }
                });
                layer.close(index);
            });
        } else {
            $.get("${pageContext.request.contextPath}/user/doCollect?blogid=" + blogid, function (result) {
                if (isNaN(result)) {
                    window.location.href = "${pageContext.request.contextPath}/user/login?flag=true";
                } else {
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
