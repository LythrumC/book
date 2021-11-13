<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/31 0031
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>

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