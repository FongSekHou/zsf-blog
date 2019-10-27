function manageLogout() {
    layer.confirm('退出当前登录？', {
        btn: ['确定','取消'] ,
        title:['提示'],
        icon:3//按钮
    }, function(){
        window.location.href="logout"
    }, function(){

    });

}

