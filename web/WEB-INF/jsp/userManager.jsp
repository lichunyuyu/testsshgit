<%--
  Created by IntelliJ IDEA.
  User: vtstar
  Date: 2017/12/8
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--加上，列表才会显示--导入 jstl.jar包-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="../js/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>
usermanger.jsp

<div class="content">
    <a href="${pageContext.request.contextPath}/user/logintest">退出登录</a>
</div>
<div class="content">
    <a href="${pageContext.request.contextPath}/user/toAddUser">注册(即添加新用户)</a>
</div>

<table border="1">
    <tbody>
    <tr>
        <th>姓名</th>
        <th>年龄</th>
        <th>操作</th>
    </tr>
    <c:if test="${!empty userList }">
        <c:forEach items="${userList }" var="user">
            <tr>
                <td>${user.userName }</td>
                <td>${user.age }</td>
                <td>
                        <%--不对 <a href="/SSHTest1/user/getUser?id=${user.id }">编辑</a>--%>
                    <a href="${pageContext.request.contextPath}/user/getUser?id=${user.id }">编辑</a>
                    <a href="javascript:del('${user.id }')">删除</a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>

<!--另一个数据库 testfirst 的 userone 表-->
<table border="1">
    <tbody>
    <tr>
        <th>姓名</th>
        <th>年龄</th>
        <th>操作</th>
    </tr>
    <c:if test="${!empty mapList }">
        <c:forEach items="${mapList }" var="map">
            <tr>
                <td>${map.name }</td>
                <td>${user.age }</td>
                <td>
                        <%--不对 <a href="/SSHTest1/user/getUser?id=${user.id }">编辑</a>--%>
                    <a href="${pageContext.request.contextPath}/user/getUser?id=${map.id }">编辑</a>
                    <a href="javascript:del('${map.id }')">删除</a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>

</body>
</html>
