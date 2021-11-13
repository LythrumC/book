<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
    web下的index只做跳转作用
    page分页的功能你交给pages/client/idnex.jsp
    如果此处index.jsp页面做分页的话，上方地址栏会出现很多地址
--%>
<jsp:forward page="/client/clientBookServlet?action=page"></jsp:forward>
