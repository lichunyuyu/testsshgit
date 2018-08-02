<%--
  Created by IntelliJ IDEA.
  User: vtstar
  Date: 2017/12/7
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>查询列表</title>>
</head>
<body>
<table border="1" style="width: 50%">
    <tr>
        <th>ID</th>
        <th>工号</th>
        <th>名字</th>
        <th>签到时间</th>
        <th>操作</th>
    </tr>
    <c:forEach var="item" items="${lists}">
        <tr>
            <td>${item.id}</td>
            <td>${item.enID}</td>
            <td>${item.name}</td>
            <td>${item.datetime}</td>
            <td><a href="">编辑</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
