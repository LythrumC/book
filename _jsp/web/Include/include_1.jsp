<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/23 0023
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>静态包含</title>
</head>
<body>
   <h1>顶部</h1>
   <h1>中间内容</h1>
   <!--页脚内容采用jsp静态包含形式-->
   <%--
      <%@include file="/Include/footer.jsp"%> 就是静态包含
         / 代表http://localhost:8080/工程名/  映射到工程中就是 web目录

   --%>
   <%@include file="/Include/footer.jsp"%>  <!--翻译到jeanServlet就是以 writer输出流输出静态包含页面的内容-->

</body>
</html>
