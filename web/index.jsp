<%--
  Created by IntelliJ IDEA.
  User: vtstar
  Date: 2017/12/7
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort()
          + path + "/";
%>
<!DOCTYPE html>
<html lang="zh">

<head>
  <base href="<%=basePath%>">

  <title>SSHtEST-首页</title>

  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/styles.css">

</head>

<body>
<div class="container">
  <div class="header"></div>

  <div class="content">
    ${pageContext.request.contextPath}
    <img  id="qrcode" style="height:80px;width:80px" src="code.jsp" />
      <%--<img alt="" src="/home/image.action"/>--%>
  </div>


  <div class="content">
    <a href="${pageContext.request.contextPath}/login">发起请求</a>，后台响应，跳转到指定页面！(未加controller的 requestMapping的/user  进不到controller 的loginfangfa )
  </div>
  <!--加上hibernate-->
  <div class="content">
    <a href="${pageContext.request.contextPath}/user/main">发起请求</a>，后台响应，跳转到指定页面（使用hibernate查询user/main）！
  </div>

  <div class="content">
    <a href="${pageContext.request.contextPath}/user/toAddUser">发起请求</a>，后台响应，跳转到指定页面（测试toAddUser）！
  </div>

  <div class="content">
    <a href="${pageContext.request.contextPath}/user/logintest">发起请求</a>，后台响应，跳转到指定页面（modelAndView测试user/logintest）！
  </div>

  <div class="content">
    <a href="${pageContext.request.contextPath}/user/addUser">发起请求</a>，后台响应，跳转到指定页面（重定向到userManager.jsp测试user/addUser）！
  </div>

  <div class="content">
    <a href="${pageContext.request.contextPath}/user/getAllUser">发起请求</a>，后台响应，跳转到指定页面（获取列表userManager.jsp测试user/getAllUser）！
  </div>


  <div class="content">
    <a href="${pageContext.request.contextPath}/user/loginframe">发起请求</a>，后台响应，跳转到指定页面（loginframe）！
  </div>

  <div class="content">
    <a href="${pageContext.request.contextPath}/user/jfreeChart">发起请求</a>，实例四：将类似实例三生成的图片嵌入到JSP页面中去。（jfreeChart）！
  </div>



  <div class="footer"></div>
</div>
</body>
</html>
