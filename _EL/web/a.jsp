<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/24 0024
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL表达式</title>
</head>
<body>
     <%
         request.setAttribute("key","值");
     %>

     表达式脚本输出的key的值是：<%= request.getAttribute("key")%><br>
     EL表达式输出key的值是：${key}<br>

     <%--EL表达式对域值的输出顺序--%>
     <%--
       1.application  一个工程
       2.request      一次请求
       3.session      当前会还(关闭浏览器则消失)
       4.pageContext  当前jsp页面
     --%>
    <%
        request.setAttribute("key","request");
        pageContext.setAttribute("key","pageContext");
        application.setAttribute("key","application");
        session.setAttribute("key",session);
    %>

     ${key}

</body>
</html>
