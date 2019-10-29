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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconfont2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layer/theme/default/layer.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
</head>

<body>
<jsp:include page="manage-header.jsp"></jsp:include>
<div class="main clearfix">
    <div class="main-left">
        <ul class="nav">
            <li class="nav-list">
                <a href="${pageContext.request.contextPath}/manage/article" target="_parent">
                    <i class="iconfont icon-wenzhangguanli"></i>文章管理
                </a>
            </li>
            <li class="nav-list" style="background-color: #fff;">
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

        </ul>
    </div>
    <div class="main-right clearfix">
        <div class="title-bar">
            <div>首页</div>
            <i class="iconfont icon-jiantou"></i>
            <a href="${pageContext.request.contextPath}/manage/user">用户管理</a>
        </div>
        <div class="user-manager clearfix">
            <div class="user-header">
                <input type="text" class="user-search" placeholder="输入用户名查询">
                <span class="new-msg" onclick="searchSome()">搜索</span>
            </div>
            <div class="user-content">


            </div>

        </div>
        <ul class="pagination  pagination-lg" style="margin: 70px auto;margin-left: 200px">

        </ul>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
<script src="${pageContext.request.contextPath}/js/manage.js"></script>
<script type="text/javascript">

    var myData = {}
    var changeFlag=true;
    function pageChange(pageNumber){
        myData.pageNumber=pageNumber;
        var load=layer.msg("正在加载数据",{icon:16,time:15055550})
        $.ajax({
            url:"${pageContext.request.contextPath}/manage/getUser",
            type:"post",
            data:myData,
            success:function (page) {
                layer.close(load);
                if(page.data.length>0){
                    var currentPage=page.pageNumber
                    var context="";
                    $.each(page.data,function(i,n){
                        context+='  <div class="user-msg">'
                        context+=' <div class="user-msg-header">'
                        context+=' <span class="user-name">'+n.username+'</span>'
                        context+=' <span class="user-delete">'
                        context+=' <i class="iconfont icon-icon7" title="删除该用户" onclick="deleteUser('+n.id+',\''+n.username+'\')"></i>'
                        context+='  </span>'
                        context+=' </div>'
                        context+=' <div class="user-detail">'
                        context+=' <div class="user-detail-img">'
                        context+=' <img src="'+n.iconpath+'">'
                        context+=' </div>'
                        context+=' <ul class="nav-user-msg">'
                        context+=' <li class="list-user-msg">'
                        context+=' <span>用&nbsp;&nbsp;户&nbsp;&nbsp;名 :&nbsp;</span>'
                        context+=' <span>'+n.username+'</span> </li>'
                        context+=' <li class="list-user-msg">'
                        context+='     <span>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别&nbsp;:&nbsp;</span>'
                        if(n.gender){
                            context+=' <span>女</span>'
                        }else{
                            context+=' <span>男</span>'
                        }
                        context+=' </li>'
                        context+=' <li class="list-user-msg">'
                        context+='     <span>注册时间&nbsp;:&nbsp;</span>'
                        context+=' <span>'+n.regtime+'</span>'
                        context+=' </li>'
                        context+=' <li class="list-user-msg">'
                        context+='     <span>用户状态&nbsp;:&nbsp;</span>'

                        if(n.enabled){
                            context+='    <span class="user-state user-state-'+n.id+'" onclick="stateChange('+n.id+')"><span class="state-btn" ></span></span>'
                        }else{
                            context+='    <span class="user-state user-state-'+n.id+'" onclick="stateChange('+n.id+')" style="background-color: #d1d0cf"><span class="state-btn" style="margin-left: 2px"></span></span>'
                        }
                        context+='</li></ul> </div> </div>';
                    })



                    var foot='<li><a href="javascript:void(0)" onclick="pageChange(1)">首页</a></li>'
                    if(currentPage==1){
                        foot+='<li class="disabled"><a href="javascript:void(0)">上一页</a></li>'
                    }else{
                        foot+='<li><a href="javascript:void(0)" onclick="pageChange('+(currentPage-1)+')">上一页</a></li>'
                    }
                    $.each(page.pageShow,function(i,n2){
                        if(n2=='...'){
                            foot+='<li class="disabled"><a href="javascript:void(0)">...</a></li>'
                        }else if(n2==currentPage){
                            foot+='<li class="active"><a href="javascript:void(0)" >'+n2+'</a></li>'
                        }else{
                            foot+='<li><a href="javascript:void(0)" onclick="pageChange('+n2+')">'+n2+'</a></li>'
                        }

                    })
                    if(currentPage==page.totalPage){
                        foot+='<li class="disabled"><a href="javascript:void(0)">下一页</a></li>'
                    }else{
                        foot+='<li><a href="javascript:void(0)" onclick="pageChange('+(currentPage+1)+')">下一页</a></li>'
                    }

                    $('.user-content').html(context)
                    $(".pagination").html(foot)
                    changeFlag=true;
                }else{
                    layer.msg("不存在当前查询的用户",{icon:5,time:2100})
                    //清空不存在的用户值
                    myData.queryValue=""
                    if(changeFlag==true && myData.pageNumber>1){
                        pageChange(myData.pageNumber-1)
                        changeFlag=true;
                    }
                }
            },error:function () {
                layer.close(load);
                layer.msg("网络繁忙 ！请重试",{icon:2,time:1500})
            }
        })

    }
    function deleteUser(uid,username) {
        layer.confirm('确定删除用户【 '+username+' 】？', {
            btn: ['确定','取消'] ,
            title:['提示'],
            icon:3//按钮
        }, function(){
            // var load=layer.load(2,{offset:'auto'});
            var load=layer.msg("正在删除数据",{icon:16,time:15055550})
            $.ajax({
                url:"${pageContext.request.contextPath}/manage/deleteUser",
                type:"post",
                data:{
                    "uid":uid,
                    "username":username
                },
                success:function (result) {
                    layer.close(load);
                    if(result>0){
                        pageChange(myData.pageNumber)
                        layer.msg("删除用户成功！",{icon:1,time:1500})
                    }else{
                        layer.msg("网络繁忙 ！请重试",{icon:2,time:1500})
                    }

                },error:function () {
                    layer.close(load);
                    layer.msg("网络繁忙 ！请重试",{icon:2,time:1500})
                }
            })
        })

    }
    function searchSome() {
        let searchValue = $(".user-search").val();
        /*if(searchValue.length==0){
            layer.msg("请输入查询内容",{icon:0,time:1500})
            return;
        }*/
        changeFlag=false;
        myData.queryValue=searchValue;
        console.log(searchValue)
        pageChange(1)
    }

    // 用户状态按钮
    function stateChange(uid) {
        var userState = $(".user-state-"+uid);
        var stateBtn = $(".user-state-"+uid+" .state-btn");
        let left = stateBtn.css("margin-left")
        console.log(left)
        var load;
        var enabled=0;
        //2px处于禁用状态，24处于可用状态
        if (left=='2px') {
            //启用账号状态
            enabled=1
            load=layer.msg("正在修改数据",{icon:16,time:15055550})
        }else if (left='24px') {
            //禁用账号
            enabled=0
            // load=layer.load(2,{offset:'auto'})
           load=layer.msg("正在修改数据",{icon:16,time:15055550})
        }else{
            layer.msg("网络繁忙 ！请重试",{icon:2,time:1500})
        }
        $.ajax({
            url:"${pageContext.request.contextPath}/manage/updateUser",
            type:"post",
            data:{
                "uid":uid,
                "enabled":enabled
            },
            success:function (result) {
                layer.close(load);
                if(result>0){
                    if (left=='2px') {
                        stateBtn.css("margin-left","24px");
                        userState.css("background-color","#00cb7e");
                        layer.msg("修改用户状态成功！",{icon:1,time:1500})
                    }else if (left='24px') {
                        stateBtn.css("margin-left","2px");
                        userState.css("background-color","#d1d0cf");
                        layer.msg("修改用户状态成功！",{icon:1,time:1500})
                    }else {
                        layer.msg("网络繁忙 ！请重试",{icon:2,time:1500})
                    }
                }else{
                    layer.msg("网络繁忙 ！请重试",{icon:2,time:1500})
                }

            },error:function () {
                layer.close(load);
                layer.msg("网络繁忙 ！请重试",{icon:2,time:1500})
            }
        })


    }
    $(function(){

        pageChange(1);


    })
</script>

</body>

</html>
