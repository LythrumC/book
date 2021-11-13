<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/28 0028
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>注册</title>
  </head>
  <body>
     <form  action="http://localhost:8080/Vertification/tempServlet" method="get">
       账号:<input type="text" name="account" id="account"><br>
       密码:<input type="password" name="password" id="password"><br>
       验证码:<input type="text" name="code" id="code">
       <img src="http://localhost:8080/Vertification/kaptcha.jpg">
       <input type="submit" value="提交">
     </form>
  </body>
</html>
