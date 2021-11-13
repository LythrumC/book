<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/26 0026
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
    <form method="post" action="http://localhost:8080/_JSTL/uploadServlet" enctype="multipart/form-data">
        用户名:<input type="text" name="username"><br>
        头像:<input type="file" name="photo"><br>
        <input type="submit" value="上传">
    </form>
</body>
</html>
