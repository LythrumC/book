<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/23 0023
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>四大域对象</title>
</head>
<body>
    <h1>scope_2页面</h1>

    pageContext对象:<%=pageContext.getAttribute("key")%><br>  <!--跳转到其他页面时为空-->
    request对象:<%=request.getAttribute("key")%><br>          <!--直接访问scope_2时为空-->
    session对象:<%=session.getAttribute("key")%><br>          <!--关闭浏览器再打开时为空-->
    applicationt对象:<%=application.getAttribute("key")%><br> <!--重新关闭再开启，或reploy时为空-->

</body>
</html>
