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
    <a href="orderServlet?action=showMyOrder">返回</a>
</div>

<div id="div_3">
    <table id="table_1"  cellspacing="0" cellpadding="0" >
        <tr>
            <th class="head">
                <span>订单项名字</span>
            </th>

            <th class="head">
                <span>订单项数量</span>
            </th>

            <th class="head">
                <span>订单项单价</span>
            </th>

            <th class="head">
                <span>订单项总价</span>
            </th>

            <th class="head">
                <span>订单号</span>
            </th>
        </tr>

            <c:forEach items="${sessionScope.orderDetails}" var="orderItems">
                <tr>
                    <!--订单项名字-->
                    <td>
                        <span>${orderItems.name}</span>
                    </td>

                    <!--订单项数量-->
                    <td >
                        <span>${orderItems.count}</span>
                    </td>

                    <!--订单项金额-->
                    <td >
                        <span>${orderItems.price}</span>
                    </td>

                    <!--订单项总价-->
                    <td >
                        <span>${orderItems.totalPrice}</span>
                    </td>

                    <!--所属订单号-->
                    <td >
                        <span>${orderItems.orderID}</span>
                    </td>
                </tr>
            </c:forEach>


    </table>
</div>

<div id="footer">
    <!--静态包含页脚-->
    <jsp:include page="/pages/common/footer.jsp"></jsp:include>
</div>


</body>