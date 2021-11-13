<%@ page import="java.util.List" %>
<%--<%@ page import="com.atChenKuan.pojo.Student" %>--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.atChenKuan.pojo.Student" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/23 0023
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>showStudent</title>
    <style>
        table{
            border: 1px blue solid;
            width: 600px;
            border-collapse: collapse;
        }
        td,th{
            border: 1px blue solid;
        }
    </style>
</head>
<body>

    <%--从searchStudentShow类里面 通过查询数据库查到数据后
        封装到request中，通过跳转请求发送到text_2.jsp中
    --%>
    <% List<Student> studentList = (List<Student>) request.getAttribute("List"); %>

    <table>
        <tr>
            <td>编号</td>
            <td>姓名</td>
            <td>年龄</td>
            <td>电话</td>
            <td>操作</td>
        </tr>
        <% for (Student student : studentList) { %>
        <tr>
            <td><%= student.getId()%></td>
            <td><%= student.getName()%></td>
            <td><%= student.getAge()%></td>
            <td><%= student.getPhone()%></td>
            <td>删除/修改</td>
        </tr>
        <% } %>
    </table>
</body>
</html>
