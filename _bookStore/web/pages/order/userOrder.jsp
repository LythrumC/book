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
    <link rel="stylesheet" type="text/css" href="static/css/myOrder.css">
</head>
<body>

<img src="static/img/default.png" id="logo">
<div id="div_1">
    <h1>我的订单</h1>
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
    <a href="UserServlet?action=logout">注销</a>
    <c:if test="${empty sessionScope.admin}">
        <a href="pages/cart/cart.jsp">返回</a>
    </c:if>
    <c:if test="${not empty sessionScope.admin}">
        <a href="index.jsp">返回</a>
    </c:if>

</div>

<div id="div_3">
    <table id="table_1"  cellspacing="0" cellpadding="0" >
        <tr>
            <th class="head">
                <span>订单号</span>
            </th>

            <th class="head">
                <span>日期</span>
            </th>

            <th class="head">
                <span>金额</span>
            </th>

            <th class="head">
                <span>状态</span>
            </th>

            <th class="head">
                <span>详情</span>
            </th>
        </tr>

        <!--有订单的情况下-->
        <c:if test="${ not empty sessionScope.myOrder}">
            <c:forEach items="${sessionScope.myOrder}" var="orders">
                <tr>
                    <!--订单号-->
                    <td>
                        <span>${orders.orderID}</span>
                    </td>

                    <!--订单创建时间-->
                    <td >
                        <span>${orders.createTime}</span>
                    </td>

                    <!--订单金额-->
                    <td >
                        <span>${orders.price}</span>
                    </td>

                    <!--订单状态-->
                    <td >
                        <span>${orders.status}</span>
                    </td>

                    <td >
                        <span><a href="orderServlet?action=orderDetails&orderId=${orders.orderID}">查看详情</a></span>
                    </td>
                </tr>
            </c:forEach>
        </c:if>

        <!--没有订单的情况下-->
        <c:if test="${empty sessionScope.myOrder}">
            <tr>
                <td colspan="5" align="center" style="padding-left: 230px;font-size: 30px;font-family: 新宋体">亲，当前还没有订单哦！快去逛逛吧！</td>
            </tr>
        </c:if>

    </table>
</div>

<div id="footer">
    <!--静态包含页脚-->
    <jsp:include page="/pages/common/footer.jsp"></jsp:include>
</div>


</body>