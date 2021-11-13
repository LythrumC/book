<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/24 0024
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL表达式11个隐藏对象</title>
</head>
<body>

    <%
        request.setAttribute("key","request");
        pageContext.setAttribute("key","pageContext");
        application.setAttribute("key","application");
        session.setAttribute("key","session");
    %>
          <!--分别一一对应各个 域对象-->
    rqueest:  ${requestScope.key}<br>
    pageContext:  ${pageScope.key}<br>
    application:  ${applicationScope.key}<br>
    session:  ${sessionScope.key}<br>
</body>
</html>
