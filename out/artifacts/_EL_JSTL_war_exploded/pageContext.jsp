<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/24 0024
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PageContext对象使用</title>
</head>
<body>
   <p>PageContext可以获取jsp九大内置对象</p>



   <%
//       request.getScheme();
//       request.getServerName();
//       request.getServerPort();
//       request.getContextPath();
//       request.getMethod();
//       request.getRemoteHost();
//       session.getId();
   %>

    1.协议:  ${ pageContext.request.scheme}<br>
    2.服务器IP:  ${ pageContext.request.serverName}<br>
    3.服务器端口:  ${ pageContext.request.serverPort}<br>
    4.获取工程路径:  ${ pageContext.request.contextPath}<br>
    5.获取请求方法:  ${pageContext.request.method}<br>
    6.获取客户端ip地址:  ${ pageContext.request.remoteHost}<br>
    7.获取会话ID编号:  ${ pageContext.session.id}<br>

</body>
</html>
