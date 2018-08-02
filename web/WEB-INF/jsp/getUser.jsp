<%--
  Created by IntelliJ IDEA.
  User: vtstar
  Date: 2017/12/7
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/user/getUser">
        工号：<input type="text" name="EnID" />
        起止日期：<input type="date"name="dateafter" />
        截止日期：<input type="date"name="datebefore" />
        <input type="submit" value="查询" />
    </form>
</body>
</html>
