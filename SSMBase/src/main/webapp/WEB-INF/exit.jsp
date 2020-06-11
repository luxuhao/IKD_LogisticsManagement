<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String appContext= request.getContextPath();// 获得当前应用的根路径
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + appContext ;
%>
<html>
<head>
    <title>退出成功</title>
</head>
<body>
<span style="font-size:18px;">3秒后跳回登录页面...</span><span style="font-size:24px;"><meta http-equiv="refresh" content="3;URL=islogin"> </span>
</body>
</html>
