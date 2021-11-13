<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录成功</title>
    <!--静态包含公共引用代码-->
    <jsp:include page="/pages/common/jquery_common.jsp"></jsp:include>
    <!--外部引用css文件-->
    <link rel="stylesheet" type="text/css" href="static/css/index_css.css">
</head>
<body>

<img src="static/img/default.png" id="logo">
<div id="div_1">
    <h1 id="logo_text_1">网上书城</h1>
    <h1 id="logo_text_2">BookStore of Online</h1>
</div>
-

<div id="div_2">
    <span style="font-size: 20px">欢迎
        <span class="um_span" style="font-size: 20px;
                font-family: 新宋体;
                color: red">
            ${sessionScope.user.username}
        </span>光临书城!
    </span>
    <a href="UserServlet?action=logout">注销</a>
    <a href="index.jsp">返回</a>
</div>

<div id="div_3" style="text-align: center;margin-top: 10px">
    <span style="font-size: 30px">欢迎回来!</span>
    <a href="index.jsp" style="color: red; font-size: 30px">转到主页</a>
</div>


<div id="footer" style="margin-top: -100px">
    <!--静态包含页脚-->
    <jsp:include page="/pages/common/footer.jsp" ></jsp:include>
</div>


</body>