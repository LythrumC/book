<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>图书管理系统</title>
    <!--静态包含公共引用代码-->
    <jsp:include page="/pages/common/jquery_common.jsp"></jsp:include>
    <!--外部引用css文件-->
    <link rel="stylesheet" type="text/css" href="static/css/book_manager.css">
    <!--外部引用JQuery代码-->
    <script type="text/javascript" src="static/JS/login.js"></script>

    <script type="text/javascript">
        //在事件的函数中，this代表当前对象，是当前正在响应的dom对象
        $(function () {
            $("a.deleteclass").click(function () {
                //给删除的a标签绑定单机事件，同来确认是否删除
                //confirm是确认提示框
                //点击确定返回True，点击取消返回false
                return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】?");
            });
        });
    </script>
</head>
<body>
<p>图书管理系统后台</p>
<div id="div_3">
    <!--只有管理员才能看见并进入全部订单管理-->
    <c:if test="${not empty sessionScope.admin}">
        <a href="pages/manager/order_manager.jsp">订单管理</a>
    </c:if>
    <a href="#">返回商城</a>
    <a href="UserServlet?action=logout">注销</a>
</div>
<div id="div_1">
    <div id="div_2">
        <table cellpadding="20px" cellspacing="0px">
            <tr>
                <td class="td_com">名称</td>
                <td class="td_com">价格</td>
                <td class="td_com">作者</td>
                <td class="td_com">销量</td>
                <td class="td_com">库存</td>
                <td id="td_action">操作</td>
            </tr>

            <%--               <tr>--%>
            <%--                   <td class="td_com">时间简史</td>--%>
            <%--                   <td class="td_com">435</td>--%>
            <%--                   <td class="td_com">热个人</td>--%>
            <%--                   <td class="td_com">552581</td>--%>
            <%--                   <td class="td_com">55185</td>--%>
            <%--                   <td id="td_action">--%>
            <%--                       <a href="#">修改</a>--%>
            <%--                   </td>--%>
            <%--                   <td>--%>
            <%--                       <a href="#">删除</a>--%>
            <%--                   </td>--%>
            <%--               </tr>--%>


            <c:forEach items="${requestScope.page.items}" var="book">
                <tr>
                    <td class="td_com">${book.name}</td>
                    <td class="td_com">${book.price}</td>
                    <td class="td_com">${book.author}</td>
                    <td class="td_com">${book.sales}</td>
                    <td class="td_com">${book.stock}</td>
                    <td>
                        <a href="manager/bookServlet?action=getbook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a>
                    </td>
                    <td>
                        <a class="deleteclass"
                           href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a>
                    </td>
                </tr>

            </c:forEach>
        </table>
        <!--
            更新，此处传送页码总和。 实现page分页后，添加图书自动跳转到自动一页
            并自动刷新list
        -->
        <a id="add_book" href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a>

        <!--分页开始-->
        <div id="page_nav">
            <c:if test="${requestScope.page.pageNo > 1}">
                <!--当前页码大于1时，才显示首页和上一页-->
                <a href="${requestScope.page.url}&pageNo=1">首页</a>
                <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
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
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
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
                                    <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
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
                                    <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
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
                                    <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                                </c:if>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </c:when>
            </c:choose>

            <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
                <!--当前页码小于最大页码数时，才显示末页和下一页-->
                <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
                <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
            </c:if>
                                                                                                         <!--param.pageNo得到浏览器地址上面的参数pageNo-->
            共${ requestScope.page.pageTotal }页，${ requestScope.page.pageTotalCount }条记录，跳到第<input value="${param.pageNo}"
                                                                                                name="pn"
                                                                                                id="pn_input"/>页
            <input type="button" value="跳转" id="pageNoBtn">
            <!--为跳转按钮绑定单机事件，触发页码跳转。-->
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
                        location.href = "${pageScope.bathPage}${requestScope.page.url}&pageNo=" + pageNo;
                        // alert("点击了")
                    });
                });
            </script>
        </div>
        <!--分页结束-->
    </div>


</div>

<!--静态包含页脚-->
<jsp:include page="/pages/common/footer.jsp"></jsp:include>

</body>
</html>