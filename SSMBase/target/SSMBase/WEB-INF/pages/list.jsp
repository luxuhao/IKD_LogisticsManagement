<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>查询所有的账户</h2>
<table>
<c:forEach items="${list}" var="account">
    <tr>
        <td>${account.name}</td>
        <td>${account.money}</td>

    </tr>
</c:forEach>
</table>
</body>
</html>

