<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/23 0023
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>四大域对象</title>
</head>
<body>
   <h1>scope_1页面</h1>
   <%--
     jsp注释，可以注释掉所有代码(Java,Html,Jsp)
    1.pageContext (pageContextimpl类)   当前jsp页面有效
    2.request (HttpServletRequest类)    一次请求有效
    3.session (HttpSession类)           一个会话范围有效(打开浏览器访问服务器，直到关闭服务器)
    4.application (ServletContext类)    整个web工程范围都有效(只要web工程不关闭，数据一致都存在)
   --%>

   <%
       pageContext.setAttribute("key","pageContext");   //当前jsp页面有效
       request.setAttribute("key","request");           //一次请求有效
       session.setAttribute("key","session");           //一个会话范围有效(打开浏览器访问服务器，直到关闭服务器)
       application.setAttribute("key","application");   //整个web工程范围都有效(只要web工程不关闭，数据一致都存在)
   %>

   pageContext对象:<%=pageContext.getAttribute("key")%><br>
   request对象:<%=request.getAttribute("key")%><br>
   session对象:<%=session.getAttribute("key")%><br>
   applicationt对象:<%=application.getAttribute("key")%><br>

   <%
     request.getRequestDispatcher("scope_2.jsp").forward(request,response);
   %>

</body>
</html>
