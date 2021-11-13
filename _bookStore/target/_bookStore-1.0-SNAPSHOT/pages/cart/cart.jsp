<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>我的购物车</title>
    <!--静态包含公共引用代码-->
    <jsp:include page="/pages/common/jquery_common.jsp"></jsp:include>
    <!--外部引用css文件-->
    <link rel="stylesheet" type="text/css" href="static/css/order.css">
    <script type="text/javascript">
        $(function (){
            $("a.deleteItem").click(function (){
            //    提示用户是否删除
                return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】吗？")
            });

            $("#clearItem").click(function (){
                //    提示用户是否清除
                return confirm("你确定要清空购物车中【" + ${sessionScope.cart.totalCount} + "】项商品吗？")
            });

            //内容发生改变时触发事件
            $(".updateCount").change(function (){
                //获取商品名字
                  var name = $(this).parent().parent().find("td:first").text();
                var id = $(this).attr("bookID");
                //获取商品数量
                var count = this.value;
                if(confirm("你确定要将商品【" + name + "】数量修改为: " + count + "吗？")){
                    //发起请求，保存修改
                    location.href = "http://localhost:8080/_bookStore/cartServlet?action=updateCount&count=" + count +"&id=" + id;
                }else {
                    //如果取消了确认框，就恢复成默认的数量
                    this.value = this.defaultValue;
                }
            });
        });
    </script>
</head>
<body>

<img src="static/img/default.png" id="logo">
<div id="div_1">
    <h1>我的购物车</h1>
</div>
<%--${sessionScope.cart.items}--%>

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
    <a href="orderServlet?action=showMyOrder">我的订单</a>
    <a href="UserServlet?action=logout">注销</a>
    <a href="index.jsp">返回</a>

</div>

<div id="div_3">
    <table id="table_1">
        <tr>
            <th class="head">
                <span>商品名称</span>
            </th>

            <th class="head">
                <span>数量</span>
            </th>

            <th class="head">
                <span>单价</span>
            </th>

            <th class="head">
                <span>金额</span>
            </th>

            <th class="head">
               <span>操作</span>
            </th>
        </tr>

        <!--当购物车Session为空时，提示用户-->
        <c:if test="${empty sessionScope.cart.items}">
            <tr>
                <td colspan="5" align="center" style="padding-left: 230px;font-size: 30px;font-family: 新宋体">亲，当前购物车为空哦！请先添加商品！</td>
            </tr>
        </c:if>

        <!--当购物车Session非空时，显示物品项-->
        <c:if test="${not empty sessionScope.cart.items}">
            <c:forEach items="${sessionScope.cart.items}" var="entry">
                <tr align="center">
                    <!--书名-->
                    <td>${entry.value.name}</td>
                    <!--数量-->
                    <td><input class="updateCount" value="${entry.value.count}" bookID="${entry.value.id}" style="width: 30px"></td>
                    <!--单价-->
                    <td>${entry.value.price}</td>
                    <!--总价-->
                    <td>${entry.value.totalPrice}</td>
                    <!--操作-->
                    <td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>

    <p style="font-size: 22px;margin-left: 300px">
        购物车中共有&nbsp;&nbsp;<span style="color: red">${sessionScope.cart.totalCount}</span>&nbsp;&nbsp;件商品
        &nbsp;&nbsp;总金额&nbsp;&nbsp;<span style="color: red">${sessionScope.cart.totalPrice}</span>&nbsp;&nbsp;元
        &nbsp;&nbsp; &nbsp;&nbsp;<a id="clearItem" href="cartServlet?action=clearItem">清空购物车</a> &nbsp;&nbsp;<a href="orderServlet?action=createOrder">去结账</a>
    </p>
</div>


<div id="footer">
    <!--静态包含页脚-->
    <jsp:include page="/pages/common/footer.jsp"></jsp:include>
</div>


</body>