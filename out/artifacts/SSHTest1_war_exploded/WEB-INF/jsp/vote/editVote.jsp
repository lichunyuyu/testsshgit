<%--
  Created by IntelliJ IDEA.
  User: vtstar
  Date: 2017/12/29
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--加上，列表才会显示--导入 jstl.jar包   否则只有放进session的user 才有值-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--必加， 否则只有 放入session  的user 可以得到值-->
<script type="text/javascript" src="../js/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/vote/updateVote" name="voteForm" method="post">
    <input type="hidden" name="id" value="${vote.id }">
    模型：<input type="text" name="voteName" value="${vote.voteName }">
    票数：<input type="text" name="voteCount" readonly value="${vote.voteCount}">
    <input type="submit" value="更新" >
</form>
</body>
</html>
