<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/27 0027
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
     <%
         //获取动态IP地址以及工程路径
         //不同用户访问时能够正确获取文件
        String path = request.getScheme()
         + "://"
         + request.getServerName()
         + ":"
         + request.getServerPort()
         + request.getContextPath()
         + "/";

        //用pageContext传递 工程路径path
         pageContext.setAttribute("basePath",path);
     %>


<%--     <%=path%>--%>

     <!--引用文件的公共代码-->
     <base href="<%=path%>">
     <script src="pages/user/JQueryKit/jquery-3.3.1.js"></script>
     <script src="pages/user/JQueryKit/jquery.validate.min.js"></script>
     <script src="pages/user/JQueryKit/messages_zh.min.js"></script>

</body>
</html>
