<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/31 0031
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404页面</title>
	<!--静态包含公共引用代码-->
	<jsp:include page="/pages/common/jquery_common.jsp"></jsp:include>

<!--公共样式-->
<link rel="stylesheet" type="text/css" href="static/css/error404_H-ui.reset.css">

<!--主要样式-->
<link rel="stylesheet" type="text/css" href="static/css/error404_index.css">

</head>
<body>

<div class="system" style="text-align: center">
		<img src="static/img/404.jpg" >
	<div class="title">
		<h2>网站维护中...</h2>
		<h4>为了让您更好的使用系统，我们正在对服务器进行升级，升级期间暂时无法访问。</h4>
		<h4>给您带来的不便，敬请谅解！</h4>
	</div>	
</div>

</body>
</html>
