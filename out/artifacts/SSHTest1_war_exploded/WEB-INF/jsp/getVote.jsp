<%--
  Created by IntelliJ IDEA.
  User: vtstar
  Date: 2017/12/29
  Time: 11:59
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

<h6><a href="${pageContext.request.contextPath}/vote/getAllVote">返回投票首页</a></h6>

<form method ="POST" action="${pageContext.request.contextPath}/vote/updateVoteCount" name="frmLogin"  >
<table border="1">
    <tbody>
    <tr>
        <th>选择</th>
        <th>投票类型</th>
        <th>得票数</th>
        <th>操作</th>
    </tr>
    <c:if test="${!empty voteList }">
        <c:forEach items="${voteList }" var="vote">
            <tr>
                <td><input type="radio" name="id"
                            value="${vote.id }"></td>
                <td>${vote.voteName }</td>
                <td aria-readonly="true">${vote.voteCount }</td>
                <td>
                        <%--不对 <a href="/SSHTest1/user/getUser?id=${user.id }">编辑</a>--%>
                    <a href="${pageContext.request.contextPath}/vote/editVote?id=${vote.id }">编辑</a>
                    <a href="javascript:del('${vote.id }')">删除</a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>

<p>
    <input type="submit" value="投ta一票" />
</p>
</form>
</body>
</html>
