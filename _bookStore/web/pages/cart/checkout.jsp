<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>订单管理界面</title>
    <!--静态包含公共引用代码-->
    <jsp:include page="/pages/common/jquery_common.jsp"></jsp:include>
    <!--外部引用css文件-->
    <link rel="stylesheet" type="text/css" href="static/css/order.css">
</head>
<body>

<img src="static/img/default.png" id="logo">
<div id="div_1">
    <h1>结算</h1>
</div>

<div id="div_2">
    <span style="font-size: 20px">欢迎
        <span
                class="um_span"
                style="font-size: 20px;
                font-family: 新宋体;
                color: red">
            ${sessionScope.user.username}
        </span>光临书城!
    </span>
    <a href="pages/order/order.jsp">我的订单</a>
    <a href="UserServlet?action=logout">注销</a>
    <a href="index.jsp">返回</a>
</div>

<div id="div_3">
     <p style="font-family: 黑体;font-size: 30px;margin-top: 150px">您的订单号为【${sessionScope.orderID}】</p>
</div>


<div id="footer">
    <!--静态包含页脚-->
    <jsp:include page="/pages/common/footer.jsp"></jsp:include>
</div>


</body>