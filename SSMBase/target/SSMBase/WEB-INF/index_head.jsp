<%@ page import="com.ikdzz.domain.shiro.User" %><%--
  Created by IntelliJ IDEA.
  User: lua
  Date: 2020-06-11
  Time: 11:08
  共用的导航栏
--%>
<%--加上防止 组装的时候乱码--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String username = (String) request.getSession().getAttribute("username");

%>
<div class="layui-logo"> <h1 v-text="webname"></h1></div>
<ul class="layui-nav layui-layout-left">
    <li class="layui-nav-item"><a href="">主页</a></li>
    <li class="layui-nav-item"><a href="">公司门户</a></li>
    <li class="layui-nav-item"><a href="">加入我们</a></li>
    <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
            <dd><a href="">邮件管理</a></dd>
            <dd><a href="">消息管理</a></dd>
            <dd><a href="">授权管理</a></dd>
        </dl>
    </li>
</ul>
<ul class="layui-nav layui-layout-right">
    <li class="layui-nav-item">
        <a href="javascript:;">
            <img src="<%=basePath%>/static/admin/img/人员管理.png" class="layui-nav-img">
            <%=username%>
        </a>
        <dl class="layui-nav-child">
            <dd><a href="">基本资料</a></dd>
            <dd><a href="">安全设置</a></dd>
        </dl>
    </li>
    <li class="layui-nav-item"><a href="">退了</a></li>
</ul>


