<%--
  Created by IntelliJ IDEA.
  User: Fangxihao
  Date: 2019/10/21
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>栏目管理</title>
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
            <li class="nav-list" style="background-color: #fff;">
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
    <div class="main-right">
        <div class="title-bar">
            <div>首页</div>
            <i class="iconfont icon-jiantou"></i>
            <a href="${pageContext.request.contextPath}/manage/column">栏目管理</a>
        </div>
        <div class="colum-manage">
            <div class="search-box">
                <div  style="float: left">
                    <input type="text" class="search-input" placeholder="请输入栏目名称">
                    <div class="new-msg" onclick="searchSome()">搜索</div>
                    <div style="float: right;margin-right: 30px;margin-top: 7px;">
                        <a class="btn btn-primary btn-ls" onclick="addColumn()">新增栏目</a>
                    </div>
                </div>

            </div>
            <table class="table table-bordered table-striped table-primary table-hover" style="margin-top: 30px;font-size: 28px">
                <thead>
                <tr style="text-align: center; font-weight: bold">
                    <td style="width: 60px"><input type="checkbox"  id="topCheck" value=""></td>
                    <td>栏目名称</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody style="text-align: center;">



                </tbody>
            </table>

            <div class="msg-footer">
                <button class="new-msg" style="background-color: #f56c6c" onclick="deleteBatch()">批量删除</button>
                <nav aria-label="Page navigation">
                    <ul class="pagination" style="margin-bottom: 110px ">

                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/manage.js"></script>
<script>
    var myData = {};
    var myDeleteData = {};
    var changeFlag=true;
    $(function () {
      $("#topCheck").click(function(){
            var topCheckStatus=this.checked;
            $("tbody tr td input[type='checkbox']").prop("checked",topCheckStatus)
        })
        pageChange(1)
    })
    function pageChange(pageNumber) {
        myData.pageNumber=pageNumber;
        console.log(myData.pageNumber)
        // var load=layer.load(2,{offset:'auto'});
        var load=layer.msg("正在加载数据.。。",{icon:16,time:15055550})
        $.ajax({
            url:"${pageContext.request.contextPath}/manage/getColumn",
            type:"post",
            data:myData,
            success:function (page) {
                layer.close(load);
                if(page.data.length>0){
                    //内容
                    var context="";
                    //页码
                    var foot=""
                    var currentPage=page.pageNumber;
                    $.each(page.data,function(i,n){
                        context+='<tr>'
                        context+='<td><input type="checkbox"  value="'+n.id+'"></td>'
                        context+='<td>'+n.blogtypename+'</td>'
                        context+='<td><a class="btn btn-primary btn-xs" onclick="updateType('+n.id+',\''+n.blogtypename+'\')">修改</a>&nbsp;<a class="btn btn-danger btn-xs" onclick="deleteType('+n.id+',\''+n.blogtypename+'\')">删除</a></td>'
                        context+='</tr>'
                    })
                    foot='<li><a href="javascript:void(0)" onclick="pageChange(1)">首页</a></li>'
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
                    }else {
                        foot += '<li><a href="javascript:void(0)" onclick="pageChange(' + (currentPage + 1) + ')">下一页</a></li>'
                    }
                    $('tbody').html(context)
                    $(".pagination").html(foot)
                    changeFlag=true;
                }else {
                    layer.msg("不存在当前查询的信息",{icon:5,time:2100})
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
    function searchSome() {
        let searchValue = $(".search-input").val();
        myData.queryValue=searchValue;
        pageChange(1)
    }
    function deleteType(typeid,name) {
        layer.confirm('确定删除'+name+'?', {btn: ['确定','取消']}, function(){
            myDeleteData.typeid="typeid="+typeid;
            deleteAjax();
        }, function(){

        });
    }
    function deleteBatch(){
        var allCheck=$("tbody tr td input:checked");
        if(allCheck.length==0){
            layer.msg("请选择要删除的数据 ！",{icon:2,time:1500})
            return false;
        }
        var ids=""
        $.each(allCheck,function(i,n){
            if(i!=0){
                ids+="&"
            }
            ids+="typeid="+n.value;
        })
        layer.confirm('确定删除这【 '+allCheck.length+' 】条数据?', {btn: ['确定','取消']}, function(){
            myDeleteData.typeid=ids;
            deleteAjax();

        }, function(){

        });
    }
    function  deleteAjax() {
        $.ajax({
            url:"${pageContext.request.contextPath}/manage/deleteColumn",
            type:"post",
            data:myDeleteData.typeid,
            success:function(result){
                if(result>0){
                    pageChange(myData.pageNumber)
                    layer.msg("删除成功 ！",{icon:1,time:1500})
                }else{
                    layer.msg("删除失败 ！请重试",{icon:2,time:1500})
                }
            },error:function () {
                layer.msg("网络繁忙 ！请重试",{icon:2,time:1500})
            }

        })
    }
    function  addColumn() {
        layer.prompt({title:"新增栏目"},function(val, index){
            layer.close(index);
            if(val.length==0){
                layer.msg("请输入新增栏目名称！",{icon:2,time:1500})
                return;
            }
            $.ajax({
                url:"${pageContext.request.contextPath}/manage/addColumn",
                type:"post",
                data:{
                    "blogtypename":val
                },
                success:function(result){
                    if(result>0){
                        pageChange(myData.pageNumber)
                        layer.msg("新增成功 ！",{icon:1,time:1500})
                    }else{
                        layer.msg("新增失败 ！请重试",{icon:2,time:1500})
                    }
                },error:function () {
                    layer.msg("网络繁忙 ！请重试",{icon:2,time:1500})
                }

            })
        });
    }
    function  updateType(id,name) {

        layer.prompt({title:"修改名称",value:name},function(val, index){
            layer.close(index);
            if(val==name){
                layer.msg("修改的名称和原名称一致，请重新输入！",{icon:2,time:1500})
                return;
            }
            $.ajax({
                url:"${pageContext.request.contextPath}/manage/updateColumn",
                type:"post",
                data:{
                    "typeid":id,
                    "blogtypename":val
                    
                },
                success:function(result){
                    if(result>0){
                        layer.msg("修改成功 ！",{icon:1,time:1500})
                        pageChange(myData.pageNumber)
                    }else{
                        layer.msg("修改失败 ！请重试",{icon:2,time:1500})
                    }
                },error:function () {
                    layer.msg("网络繁忙 ！请重试",{icon:2,time:1500})
                }

            })
        });
      
    }

</script>
</body>

</html>
