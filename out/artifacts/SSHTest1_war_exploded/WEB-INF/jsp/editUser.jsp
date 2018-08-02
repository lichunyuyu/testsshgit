<%--
  Created by IntelliJ IDEA.
  User: vtstar
  Date: 2017/12/13
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript" src="../js/jquery-1.10.2.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1>编辑用户</h1>
<form action="${pageContext.request.contextPath}/user/updateUser" name="userForm" method="post">
    <input type="hidden" name="id" value="${user.id }">
    姓名：<input type="text" name="userName" value="${user.userName }">
    年龄：<input type="text" name="age" value="${user.age }">
    <input type="hidden" name="recordDate" value="${user.recordDate }">
    <input type="hidden" name="password" value="${user.password }">
    <input type="submit" value="更新" >
</form>
</body>
</html>
