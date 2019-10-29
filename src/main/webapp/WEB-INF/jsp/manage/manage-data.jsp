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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconfont2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layer/theme/default/layer.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
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
            <li class="nav-list" style="background-color: #fff;">
                <a href="${pageContext.request.contextPath}/manage/data" target="_parent">
                    <i class="iconfont icon-digital"></i>数据统计
                </a>
            </li>

        </ul>
    </div>
    <div class="main-right">
        <div class="title-bar">
            <div>首页</div>
            <i class="iconfont icon-jiantou"></i>
            <a href="${pageContext.request.contextPath}/manage/data">数据统计</a>
        </div>
        <div class="data-manager">
            <div class="data-show">

            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/manage.js"></script>
<script>
    $(function () {
        
        $.ajax({
            url:"${pageContext.request.contextPath}/manage/getData",
            type:"post",
            success:function (data) {
                var myChart = echarts.init($(".data-show")[0]);
                option = {
                    title: {
                        text: '文章类型使用情况'
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '8%',
                        containLabel: true
                    },
                    triggerEvent: true,
                    color: ['#00cbba'],
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {
                            type : 'shadow'
                        }
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : data.typeName,
                            axisTick: {
                                alignWithLabel: true
                            },axisLabel:{
                               /* formatter:function(value){
                                    return value.split("").join("\n");
                                },

                                interval:0*/
                            }
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            name:'使用次数',
                            type:'bar',
                            barWidth: '60%',
                            data:data.counts
                        }
                    ]
                };
                myChart.setOption(option);
            },error:function () {
                layer.close(load);
                layer.msg("网络繁忙 ！请重试",{icon:2,time:1500})
            }
        })



    })
</script>
</body>

</html>
