<%--
  User: luxh
  Date: 2020-06-08
  Time: 11:08
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String appContext= request.getContextPath();// 获得当前应用的根路径
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + appContext ;
%>

<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>管理员登陆</title>
    <link rel="stylesheet" href="<%=basePath%>/static/admin/css/login.css">
    <link rel="stylesheet" href="<%=basePath%>/static/common/layui/css/layui.css">
    <script src="<%=basePath%>/static/common/layui/layui.js"></script>
</head>
<body id="login">
<div class="login">
    <h2>我的管理后台</h2>
    <form class="layui-form" method="post"  action="<%=basePath%>/login">
        <div class="layui-form-item">
            <input type="username" name="username" placeholder="用户名" value="${cookie.username.value}" class="layui-input" lay-verify="required|password">
            <i class="layui-icon input-icon">&#xe66f;</i>
        </div>
        <div class="layui-form-item">
            <input type="password" name="password" placeholder="密码" value="" class="layui-input" lay-verify="required|password">
            <i class="layui-icon input-icon">&#xe673;</i>
        </div>
        <div class="layui-form-item">
            <input id="checks"  type="checkbox" name="box" lay-skin="primary" title="记住密码" checked=""> <a class="back" href="javascript:;"  style="margin-top: 10px">忘记密码</a>
        </div>
        <div class="layui-form-item"><span style="color: red;"></span></div>
        <div class="layui-form-item">
<%--            <input class="layui-btn layui-btn-fluid layui-btn-normal " lay-submit="/manage/user/islogin.do" type="button" value="Login" lay-filter="login-submit">--%>
            <input id="lg" type="submit" style="width: 100%" class="layui-btn" lay-submit lay-filter="login" value="立即登录"/>

        </div>
    </form>
    <script src="<%=basePath%>/static/common/jquery-3.3.1.min.js"></script>
    <script>
        layui.use('form', function () {
            var form = layui.form,
                layer = layui.layer,
                $ = layui.jquery;


        });



    </script>
</div>
</body>

</html>
