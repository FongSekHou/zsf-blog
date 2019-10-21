<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
首页
<a href="${pageContext.request.contextPath}/user/personal">个人中心</a>
<script>
    if("${msg}"!=""){
        alert("${msg}");
    }
</script>
</body>
</html>