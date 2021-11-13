<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/2 0002
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!--静态包含公共引用代码-->
    <jsp:include page="/pages/common/jquery_common.jsp"></jsp:include>
    <!--外部引用css文件-->
    <link rel="stylesheet" type="text/css" href="static/css/book_edit.css">
    <!--外部引用JQuery代码-->
    <script type="text/javascript" src="static/JS/login.js"></script>
</head>
<body>
<p>图书管理后台_编辑图书</p>
  <div id="div_3">
      <a href="manager/bookServlet?action=list">图书管理</a>
      <a href="#">订单管理</a>
      <a href="#">返回商城</a>
  </div>

<div id="div_1">
    <div id="div_2">
        <form method="get" action="manager/bookServlet">
            <input type="hidden" name="pageNo" value="${param.pageNo}">
                                                    <%--
                                                       book_edit.jsp页面既要执行add操作，又要执行update操作
                                                       为了分清执行的操作，需要进行判断来执行相应操作
                                                       通过判断传进来的参数是否含 id属性 来区分操作
                                                    --%>
            <input value="${empty param.id ? "add" : "update"}" type="hidden" name="action">
                   <%--  update 操作需要得到图书的 id  --%>
            <input value="${requestScope.book.id}" type="hidden" name="id">
            <table cellpadding="20px" cellspacing="0px">
                <tr>
                    <td class="td_com">名称</td>
                    <td class="td_com">价格</td>
                    <td class="td_com">作者</td>
                    <td class="td_com">销量</td>
                    <td class="td_com">库存</td>
                    <td id="td_action">操作</td>
                </tr>

                <tr>
                    <td>
                        <input type="text" name="name" placeholder="名称" value="${requestScope.book.name}">
                    </td>

                    <td>
                        <input type="text" name="price" placeholder="价格" value="${requestScope.book.price}">
                    </td>

                    <td>
                        <input type="text" name="author" placeholder="作者" value="${requestScope.book.author}">
                    </td>

                    <td>
                        <input type="text" name="sales" placeholder="销量" value="${requestScope.book.sales}">
                    </td>

                    <td>
                        <input type="text" name="stock" placeholder="库存" value="${requestScope.book.stock}">
                    </td>

                    <td>
                        <input type="submit" value="提交">
                    </td>
                </tr>



            </table>

        </form>



    </div>


</div>

<!--静态包含页脚-->
<jsp:include page="/pages/common/footer.jsp"></jsp:include>

</body>
</html>
