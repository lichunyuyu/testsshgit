<%--
  Created by IntelliJ IDEA.
  User: vtstar
  Date: 2017/12/29
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Title</title>
</head>
<body>
<form method ="POST" action="${pageContext.request.contextPath}/vote/saveVoter" name="frmLogin"  >
<table background="images\bg_img1.jpg " width="933" height="412">
    <tr height="170">
        <td width="570px"> </td>
        <td> </td>
    </tr>
    <tr>
        <td> </td>
        <td><table>
            <%--<form method ="POST" action="http://localhost:8081/user/login" name="frmLogin"  >--%>

                <h1>添加投票模型</h1>
                <tr>
                    <td>投票名称：</td>
                    <td><input type="text" name="voteName"  size="20" maxlength="20" onfocus="if (this.value=='Your name')  this.value='';" /></td>
                    <td > </td>
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
                <td><input type="submit" name="login" value="提交" /></td>
            </tr>
            </tr>
        </table>
        </td>
</table>
</form>
</body>
</html>
