<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
  <title>商城首页</title>
    <!--静态包含公共引用代码-->
    <jsp:include page="/pages/common/jquery_common.jsp"></jsp:include>
    <!--外部引用css文件-->
    <link rel="stylesheet" type="text/css" href="static/css/index_css.css">
    <script type="text/javascript">
        $(function (){
            //给加入购物车按钮绑定单击事件
            $("button.addItem").click(function (){
                /**
                 * this对象指的是当前dom对象，attr()方法获取对应属性值
                 * @type {*|jQuery}
                 */
                var bookID = $(this).attr("bookID");
                // location.href = "http://localhost:8080/_bookStore/cartServlet?action=addItem&id=" + bookID;
                // location.href = "http://www.baidu.com"
                // alert(bookID)

                //2021年5月14日16:40:06  更新功能，使用AJAX实现此功能
                $.getJSON("http://localhost:8080/_bookStore/cartServlet",
                           "action=addItembyAjax&id=" + bookID,
                           function (data){
                    // alert(data.totalCount);
                    // console.log(data.totalCount);
                    // console.log(data.lastCartItem);
                               $("#totalCount").text("您的购物车有" + data.totalCount + "件商品");
                               $("#lastCartItem").text("您刚刚将《" + data.lastCartItem + "》加入到购物车");
                           });
            });
        });
    </script>
</head>
<body>
        <div><a style="color: red; float: right;font-family: 华文宋体;font-size: 15px" >成为VIP</a></div>
        <img src="static/img/default.png" id="logo">
        <div id="div_1">
            <h1 id="logo_text_1">网上书城</h1>
            <h1 id="logo_text_2">BookStore of Online</h1>
        </div>-

        <div id="div_2">
            <%--如果没有登录--%>
            <c:if test="${empty sessionScope.user}">
                <a href="pages/user/login_info.jsp">登录</a>
                <span>丨</span>
                <a href="pages/user/regist_info.jsp">注册</a>
            </c:if>

            <%--如果已经登录--%>
            <c:if test="${not empty sessionScope.user}">
                <span
                         style="font-size: 20px;
                         font-family: 新宋体;
                         color: blue">
                    欢迎用户:  ${sessionScope.user.username}光临！
                </span>
                <a href="UserServlet?action=logout">注销</a>
            </c:if>

            <!--只有普通用户才能进入购物车，管理员不行-->
            <c:if test="${empty sessionScope.admin}">
                <a href="pages/cart/cart.jsp">购物车</a>
            </c:if>

            <!--只有管理员才能看见并进入后台-->
            <c:if test="${not empty sessionScope.admin}">
                <a href="manager/bookServlet?action=page">后台管理</a>
            </c:if>

            <!--只有管理员才能看见并进入全部订单管理-->
            <c:if test="${not empty sessionScope.admin}">
                    <a href="orderServlet?action=showAllOrder">订单管理</a>
            </c:if>
        </div>

        <div id="div_3" >
            <div id="div_3_1" >
                <!--根据关键字分页查询书籍-->
                <form action="client/clientBookServlet" method="get" >
                    <input type="hidden" name="action" value="PageByKeyName">
                    <span>关键字搜索：</span>
                    <input type="text" style="width: 100px" name="keyName" id="keyName" value="${param.KeyName}">
                    <input type="submit" value="查找" style="margin-right: 50px">
                </form>

                <!--
                  2021年4月23日21:57:14  这里把action地址写错了，导致了404
                  bookServlet通过反射去寻找哪个类有这个方法，再去执行
                -->
               <form action="client/clientBookServlet" method="get">
                   <input type="hidden" name="action" value="PageByPrice">
                   <span>价格区间:</span>
                                                        <!--
                                                          这里回显查询的价格区间，param参数就是最上方地址栏里的参数 min=200&max=600
                                                        -->
                   <input type="text" name="min" id="min" value="${param.min}">元----<input type="text" name="max" id="max" value="${param.max}">元
                   <input type="submit" value="查询">
               </form>

                <!--如果当前购物为空-->
                <c:if test="${empty sessionScope.cart.items}">
                    <p style="color: red" id="totalCount">当前购物车为空</p>
                    <p style="color: red" id="lastCartItem"></p>
                </c:if>

                <!--如果当前购物车不为空-->
                <c:if test="${not empty sessionScope.cart.items}">
                    <p><span style="color: red" id="totalCount"></span></p>
                    <p><span style="color:red;" id="lastCartItem"></span></p>
                </c:if>
            </div>

            <!--START-->
            <c:forEach items="${requestScope.page.items}" var="book">
               <div id="div_3_2">
                <div class="div_book">
                    <div class="book_image">
                        <img src="static/img/default1.png" class="img">
                    </div>
                    <div class="book_name">
                        <span>书名:${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span>作者:${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span>价格:${book.price}</span>
                    </div>
                    <div class="book_sale">
                        <span>销量:${book.sales}</span>
                    </div>
                    <div class="book_store">
                        <span>库存:${book.stock}</span>
                    </div>
                    <div class="book_add">
                                <!--通过JS获取bookID，判断当前点击的是哪本书-->
                        <button bookID="${book.id}" class="addItem">加入购物车</button>
                    </div>
                </div>
                <!--END-->
                   </c:forEach>
            </div>

            <!--分页开始-->
            <div id="page_nav">
                <c:if test="${requestScope.page.pageNo > 1}">
                    <!--当前页码大于1时，才显示首页和上一页-->
                    <a href="${ requestScope.page.url }&pageNo=1">首页</a>
                    <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo-1}">上一页</a>
                </c:if>
                <!-- page分页的具体实现-->
                <!--页码输出的开始-->
                <c:choose>
                    <%--               情况一： 当pagetotal小于5时，就遍历输出这5页--%>
                    <c:when test="${requestScope.page.pageTotal <= 5}">
                        <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                            <%--                       如果页码是当前页，则不可点击--%>
                            <c:if test="${i == requestScope.page.pageNo}">
                                【${i}】
                            </c:if>
                            <%--如果页码不是当前页，则可以点击(加了a标签)--%>
                            <c:if test="${i != requestScope.page.pageNo}">
                                <a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
                            </c:if>
                        </c:forEach>
                    </c:when>

                    <%--情况二： 当pagetotal大于5时  （假设一共10页） 此时一共有三种小情况  --%>
                    <c:when test="${requestScope.page.pageTotal > 5}">
                        <c:choose>
                            <%--小情况1 ：当前页码为前面三个 1，2，3的情况。 此时的页码为 1，2，3，4，5--%><%-- 这种情况跟页码只有5页一样 --%>
                            <c:when test="${requestScope.page.pageNo <= 3}">
                                <c:forEach begin="1" end="5" var="i">
                                    <%--如果页码是当前页，则不可点击--%>
                                    <c:if test="${i == requestScope.page.pageNo}">
                                        【${i}】
                                    </c:if>
                                    <%--如果页码不是当前页，则可以点击(加了a标签)--%>
                                    <c:if test="${i != requestScope.page.pageNo}">
                                        <a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
                                    </c:if>
                                </c:forEach>
                            </c:when>

                            <%-- 小情况二：当前页码为最后3个，8,9,10。 页码范围是：总页码-4 —— 总页码 --%>
                            <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal - 3 }">
                                <c:forEach begin="${requestScope.page.pageTotal - 4}" end="${requestScope.page.pageTotal}"
                                           var="i">
                                    <%--如果页码是当前页，则不可点击--%>
                                    <c:if test="${i == requestScope.page.pageNo}">
                                        【${i}】
                                    </c:if>
                                    <%--如果页码不是当前页，则可以点击(加了a标签)--%>
                                    <c:if test="${i != requestScope.page.pageNo}">
                                        <a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
                                    </c:if>
                                </c:forEach>
                            </c:when>

                            <%-- 小情况三： 当前页码为中间的  4,5,6,7。 页码范围是： 当前页码-2 —— 当前页码+2  即当前页码在5个数的中间--%>
                            <c:otherwise>
                                <c:forEach begin="${requestScope.page.pageNo - 2}" end="${requestScope.page.pageNo + 2}"
                                           var="i">
                                    <%--如果页码是当前页，则不可点击--%>
                                    <c:if test="${i == requestScope.page.pageNo}">
                                        【${i}】
                                    </c:if>
                                    <%--如果页码不是当前页，则可以点击(加了a标签)--%>
                                    <c:if test="${i != requestScope.page.pageNo}">
                                        <a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
                                    </c:if>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                </c:choose>

                <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
                    <!--当前页码小于最大页码数时，才显示末页和下一页-->
                    <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo+1}">下一页</a>
                    <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageTotal}">末页</a>
                </c:if>
                共${ requestScope.page.pageTotal }页，${ requestScope.page.pageTotalCount }条记录到第<input value="${param.pageNo}"
                                                                                                    name="pn"
                                                                                                    id="pn_input"/>页
                <input type="button" value="跳转" id="pageNoBtn">
                <!--未跳转按钮绑定单机事件，触发页码跳转。-->
                <script type="text/javascript">
                    $(function () {
                        //跳转到指定页码
                        $("#pageNoBtn").click(function () {
                            var pageNo = $("#pn_input").val();

                            /*符合页码边界值时，点击跳转按钮才有效*/
                            if (pageNo < 1) {
                                return;
                            } else if (pageNo > ${requestScope.page.pageTotal}) {
                                return;
                            }


                            //JavaScript提供了location地址栏对象
                            //它的属性href可以改变，获得地址栏的地址
                            //href地址可读可写
                            //这里要用动态地址，因为别人访问时，IP地址跟自己在Tomcat测试时的地址不一样
                            location.href = "${pageScope.bathPage}${ requestScope.page.url }&pageNo=" + pageNo;
                            // alert("点击了")
                        });
                    });
                </script>
            </div>
            <!--分页结束-->


            <div id="footer">
                <!--静态包含页脚-->
                <jsp:include page="/pages/common/footer.jsp"></jsp:include>
            </div>



</body>