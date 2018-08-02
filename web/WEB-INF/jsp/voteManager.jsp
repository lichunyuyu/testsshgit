<%--
  Created by IntelliJ IDEA.
  User: vtstar
  Date: 2017/12/29
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--加上，列表才会显示--导入 jstl.jar包-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--必加， 否则只有 放入session  的user 可以得到值-->
<script type="text/javascript" src="../js/jquery-1.10.2.js"></script>
<%--<script type="text/javascript" src="../js/jquery-1.11.1.js"></script>--%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <p>欢迎${user.userName}!</p>

    <div class="content">
        <a href="${pageContext.request.contextPath}/vote/addVote">添加新投票模型</a>
    </div>

    <tbody>
    <tr>
        <th>选择</th>
        <th>投票类型</th>
        <th>得票数</th>
        <th>操作</th>
    </tr>
    <c:if test="${!empty voteList}">
        <c:forEach items="${voteList}" var="vote">
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

    <p> <a href="${pageContext.request.contextPath}/vote/doFilter">进入投票系统</a></p>


    <%--综合上述，在a中调用js函数最适当的方法推荐使用：--%>
    <%--a href="javascript:void(0);" onclick="js_method()"--%>
    <%--a href="javascript:;" onclick="js_method()"--%>
    <%--a href="#" onclick="js_method();return false;"--%>

    <p> <a href="javascript:void(0);"  onclick="dofilter()">进入投票系统</a></p>

    ${message}
</table>
</body>
</html>
<script type="text/javascript" src="../js/jquery-1.11.1.js"></script>
<%--不行--%>
<script>
    var a = "${message}";
    console.log("avotemanager="+a);
    if(a!=null || a!=""){
        alert("${message}");
    }

    <%--function dofilter() {--%>
        <%--console.log("aaaa");--%>
        <%--$.ajax({--%>
            <%--url:"${pageContext.request.contextPath}/vote/doFilter",--%>
            <%--type: "post",--%>
            <%--async:false,          //同步请求--%>
            <%--//data:{custId:custId},--%>
            <%--dataType:"json",--%>
            <%--success:function(data){--%>
                <%--console.log("zzz");--%>
                <%--var str = data;--%>
                <%--if(str=="2"){--%>
                    <%--console.log("2222");--%>
                    <%--$.ts.EasyUI.frameDialog( {--%>
                        <%--title:"跳转登录",--%>
                         <%--href : "${pageContext.request.contextPath}/user/login"--%>
                     <%--})--%>
                <%--}else if(str=="1"){--%>
                    <%--alert("对不起，您已经投过票了，明天再来吧！");--%>
                <%--}else{--%>
                    <%--console.log("00");--%>
                <%--}--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>
</script>
