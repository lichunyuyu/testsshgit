<%--
  Created by IntelliJ IDEA.
  User: vtstar
  Date: 2017/12/6
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body >

<h5><a href="${pageContext.request.contextPath}/user/getAllUser">进入用户管理页</a></h5>


<fieldset>
    <table background="images\bg_img1.jpg " width="933" height="412">
        <tr height="170">
            <td width="570px"> </td>
            <td> </td>
        </tr>
        <tr>
            <td> </td>
            <td><table>
                <%--<form method ="POST" action="http://localhost:8081/user/login" name="frmLogin"  >--%>
                 <form method ="POST" action="${pageContext.request.contextPath}/user/login" name="frmLogin"  >
                    <tr>
                        <td>用户名：</td>
                        <td><input type="text" name="userName" value="${user.userName}" size="20" maxlength="20" onfocus="if (this.value=='Your name')  this.value='';" /></td>
                        <td > </td>
                        <td> </td>
                    </tr>
                    <tr>
                        <td>密  码：</td>
                        <td><input type="password" name="password" value="${user.password}" size="20" maxlength="20" onfocus="if (this.value=='Your password')  this.value='';" /></td>
                        <td> </td>
                        <td> </td>
                    </tr>
                    <tr>
                        <td>验证码:</td>
                        <td><input type="text" size="" name="inputcode" value="Your code" onfocus="if (this.value=='Your code')  this.value='';" /></td>
                        <td><img src="images\bg_img2.gif" alt="打不开网页"> </td>
                        <td><a href="#" onclick="shuaxin();">刷新</a></td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" name="zlogin" value="1">自动登录</td>
                    </tr>
            </table>
    </td>
    <tr>
        <td> </td>
        <td><table>
            <tr>
                <td><input type="submit" name="login" value="登录" onClick="return validateLogin()"/></td>
                <td><input type="reset" name="rs" value="重置"></td>
                <%--<a href="${pageContext.request.contextPath}/user/toAddUser">--%>
                <%--<td><input type="button" name="button" value="注册" onclick="location.href='http://localhost:8080/test/zc.html?login=%B5%C7%C2%BC'"></td>--%>
                <td><input type="button" name="button" value="注册" onclick="location.href='${pageContext.request.contextPath}/user/toAddUser'"></td>
            </tr>
            </tr>
        </table>
        </td>
        </table>
</fieldset>
</form>
<script language="javascript">
    var a = "${message}";
    console.log("alogin="+a);
    if(a!=null || a!=""){
        alert("${message}");
    }

    function validateLogin(){
        var sUserName = document.frmLogin.userName.value ;
        var sPassword = document.frmLogin.password.value ;
        var sinputCode =document.frmLogin.inputcode.value ;
        if ((sUserName =="") || (sUserName=="Your name")){
            alert("请输入用户名!");
            return false ;
        }

        if ((sPassword =="") || (sPassword=="Your password")){
            alert("请输入密码!");
            return false ;
        }
        if ((sinputCode =="") || (sinputCode=="Your code")){
            alert("请输入验证码!");
            return false ;
        }
    }
</script>
</body>
</html>
