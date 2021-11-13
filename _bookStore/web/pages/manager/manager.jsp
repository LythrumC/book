<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/2 0002
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!--静态包含公共引用代码-->
    <jsp:include page="/pages/common/jquery_common.jsp"></jsp:include>
    <!--外部引用css文件-->
    <link rel="stylesheet" type="text/css" href="static/css/manager.css">
    <!--外部引用JQuery代码-->
    <script type="text/javascript" src="static/JS/login.js"></script>
</head>
<body>
<p>图书管理系统后台</p>
<div id="div_3">
    <a href="manager/bookServlet?action=list">图书管理</a>
    <a href="pages/order/order.jsp">我的订单</a>
    <a href="index.jsp">返回</a>
    <a href="UserServlet?action=logout">注销</a>
</div>
<div id="div_1">
    <div id="div_2">
        <h1>欢迎进入后台管理系统</h1>
    </div>
</div>

<!--静态包含页脚-->
<jsp:include page="/pages/common/footer.jsp"></jsp:include>

</body>
</html>
