function changeCaptcha() {
    $("#captcha").val("");//清空验证码输入域
    var imgSrc = $("#captchaObj");
    var src = imgSrc.attr("src");
    imgSrc.attr("src", chgUrl(src));
}
// 为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
function chgUrl(url) {
    var timestamp = (new Date()).valueOf();
    if(url.indexOf("?")!=-1) {//判断当前url是否带了时间戳，带了的话要把参数去掉重新加
        url = url.substring(0, url.indexOf("?"));
    }
    return url+"?timestamp="+timestamp;
}

function getQueryVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}