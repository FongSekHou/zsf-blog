<%--
  Created by IntelliJ IDEA.
  User: Fangxihao
  Date: 2019/10/21
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>文章管理</title>
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
            <li class="nav-list" style="background-color: #fff;">
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

        </ul>
    </div>
    <div class="main-right">
        <div class="title-bar">
            <div>首页</div>
            <i class="iconfont icon-jiantou"></i>
            <a href="${pageContext.request.contextPath}/manage/article">文章管理</a>
        </div>
        <div class="article-manage">
            <div class="search-box">
                <div>
                    <select id="queryType" style="    width: 126px;height: 38px;border-radius: 6px;text-align: center;padding-left: 10px;font-size: 14px;">
                        <option value="title">按文章名查找</option>
                        <option value="username">按作者名查找</option>
                    </select>
                    <input type="text" class="search-input" placeholder="点击输入需要查询的信息">
                    <div class="new-msg" onclick="searchSome()">搜索</div>
                </div>
            </div>
            <table class="table table-bordered table-striped table-primary table-hover" style="margin-top: 30px;font-size: 28px">
                <thead>
                <tr style="text-align: center; font-weight: bold">
                    <td><input type="checkbox"  id="topCheck" value=""></td>
                    <td style="width: 600px">文章标题</td>
                    <td>作者</td>
                    <td>提交时间</td>
                    <td>所属分类</td>
                    <td>收藏量</td>
                    <td>浏览量</td>
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
</div>

<script src="${pageContext.request.contextPath}/js/manage.js"></script>

<script>

    var myData = {};
    var myDeleteData={};
    var changeFlag=true;
    $(function () {
        $("#topCheck").click(function(){
// 			var topCheck=$("#topCheck")[0].checked;
            var topCheckStatus=this.checked;
            $("tbody tr td input[type='checkbox']").prop("checked",topCheckStatus)
        });
        blogChange(1)
    });
    function searchSome() {
        let searchValue = $(".search-input").val();
        let queryType=$("#queryType").val();
        /*if(searchValue.length==0){
            layer.msg("请输入查询内容",{icon:0,time:1500})
            return;
        }*/

        if(queryType=='title'){
            myData.selectType=true;
        }else if(queryType=='username') {
            myData.selectType = false;
        }
        myData.queryValue=searchValue;
        myData.queryType=queryType;
        blogChange(1)
    }
    function blogChange(pageNumber) {
        myData.pageNumber=pageNumber;
        console.log(myData.pageNumber);
        // var load=layer.load(2,{offset:'auto'});
        var load=layer.msg("正在加载数据",{icon:16,time:15055550});

        $.ajax({
            url:"${pageContext.request.contextPath}/manage/getArticle",
            type:"post",
            data:myData,
            success:function (page) {
                layer.close(load);
                if(page.data.length>0){
                    //内容
                    var context="";
                    //页码
                    var foot="";
                    var currentPage=page.pageNumber;
                    $.each(page.data,function(i,n){
                        context+='<tr>';
                        context+='<td><input type="checkbox"  value="'+n.id+'"></td>';
                        context+='<td><a class="goBlog" target="_blank" href="${pageContext.request.contextPath}/blog/showBlog?blogid='+n.id+'">'+n.title+'</td>';
                        context+='<td>'+n.username+'</td>';
                        context+='<td>'+n.completetime+'</td>';
                        context+='<td>'+n.blogtypename+'</td>';
                        context+='<td>'+n.collectnum+'</td>';
                        context+='<td>'+n.viewnum+'</td>';
                        context+='<td align="center">';
                        context+='<a class="btn btn-danger btn-xs" onclick="deleteBlog('+n.id+')">删除</a>';
                        context+='</td>';
                        context+='</tr>'
                    });
                    foot='<li><a href="javascript:void(0)" onclick="blogChange(1)">首页</a></li>';
                    if(currentPage==1){
                        foot+='<li class="disabled"><a href="javascript:void(0)">上一页</a></li>'
                    }else{
                        foot+='<li><a href="javascript:void(0)" onclick="blogChange('+(currentPage-1)+')">上一页</a></li>'
                    }
                    $.each(page.pageShow,function(i,n2){
                        if(n2=='...'){
                            foot+='<li class="disabled"><a href="javascript:void(0)">...</a></li>'
                        }else if(n2==currentPage){
                            foot+='<li class="active"><a href="javascript:void(0)" >'+n2+'</a></li>'
                        }else{
                            foot+='<li><a href="javascript:void(0)" onclick="blogChange('+n2+')">'+n2+'</a></li>'
                        }

                    });
                    if(currentPage==page.totalPage){
                        foot+='<li class="disabled"><a href="javascript:void(0)">下一页</a></li>'
                    }else {
                        foot += '<li><a href="javascript:void(0)" onclick="blogChange(' + (currentPage + 1) + ')">下一页</a></li>'
                    }
                    $('tbody').html(context);
                    $(".pagination").html(foot);
                    changeFlag=true;
                }else {
                    layer.msg("不存在当前查询的信息",{icon:5,time:2100});
                    //清空不存在的用户值
                    myData.queryValue="";
                    if(changeFlag==true && myData.pageNumber>1){
                        blogChange(myData.pageNumber-1);
                        changeFlag=true;
                    }
                }

            },error:function () {
                layer.close(load);
                layer.msg("网络繁忙 ！请重试",{icon:2,time:1500})
            }
        })

    }
    function deleteBlog(blogid) {
        layer.confirm('确定删除这条数据?', {btn: ['确定','取消']}, function(){
            myDeleteData.blogid="blogid="+blogid;
            deleteAjax();
        }, function(){

        });
    }
    function deleteBatch(){
        var allCheck=$("tbody tr td input:checked");
        if(allCheck.length==0){
            layer.msg("请选择要删除的数据 ！",{icon:2,time:1500});
            return false;
        }
        var ids="";
        $.each(allCheck,function(i,n){
            if(i!=0){
                ids+="&"
            }
            ids+="blogid="+n.value;
        });
        layer.confirm('确定删除这【 '+allCheck.length+' 】条数据?', {btn: ['确定','取消']}, function(){
            myDeleteData.blogid=ids;
            deleteAjax();

            }, function(){

        });
    }
    function  deleteAjax() {
        $.ajax({
            url:"${pageContext.request.contextPath}/manage/deleteArticle",
            type:"post",
            data:myDeleteData.blogid,
            success:function(result){
                if(result>0){
                    blogChange(myData.pageNumber);
                    layer.msg("删除成功 ！",{icon:1,time:1500})
                }else{
                    layer.msg("删除失败 ！请重试",{icon:2,time:1500})
                }
            },error:function () {
                layer.msg("网络繁忙 ！请重试",{icon:2,time:1500})
            }

        })
    }
</script>

</body>

</html>
