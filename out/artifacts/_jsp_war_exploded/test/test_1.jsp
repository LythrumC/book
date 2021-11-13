<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/23 0023
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>九九乘法表</title>
</head>
<body>
<h1 align="center">九九乘法表</h1>
   <table align="center">
       <%for(int i=1; i<=9; i++){%>
       <tr>
         <%for(int j=1; j<=i; j++){ %>
            <td><%=j + "*" + i + "=" + (i*j)%></td>
           <%}%>
       </tr>
       <%}%>
   </table>

</body>
</html>
