<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/23 0023
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>动态包含</title>
</head>
<body>
      <h1>顶部</h1>
      <h1>中间内容</h1>

      <%--
         <jsp:include page="/Include/footer.jsp"></jsp:include> 是动态包含
         动态包含实现的功能和静态包含一样，但是底层原理不一样
         1.动态包含会把jsp页面翻译成java代码，而不是和静态包含一样把footer.jsp的内容直接通过writer流写进去
         2.动态包含底层代码使用如下代码去调用被包含的jsp页面执行输出.
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/Include/footer.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("username", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("ck", request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("password", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("ck", request.getCharacterEncoding()), out, false);
         3.动态包含，还可以包含参数，把域对象传递给footer.jsp使用
      --%>
      <jsp:include page="/Include/footer.jsp">
          <jsp:param name="username" value="ck"/>
          <jsp:param name="password" value="ck"/>
      </jsp:include>

      <%--jsp可以直接使用请求转发--%>
      <jsp:forward page="/scope_2.jsp"></jsp:forward>


</body>
</html>
