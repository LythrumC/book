<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.atChenKuan.pojo.Student" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/25 0025
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>foreach遍历List集合</title>
    <style>
        table{
            border: 1px blue solid;
            width: 600px;
            border-collapse: collapse;
        }
        td,th{
            border: 1px blue solid;
            text-align: center;
        }
    </style>
</head>
<body>
  <%--  遍历List集合----list中存放Studen类，有属性:编号，用户名，密码，年龄，电话信息--%>
   <%
      List<Student> studentList = new ArrayList<>();
      for (int i=1; i<=10; i++){
          studentList.add(new Student(i,"username" + i,"password" + i,18 + i,"numbers" + i));
      }
      request.setAttribute("Student",studentList);
   %>


   <table>
       <tr>
           <th>编号</th>
           <th>用户名</th>
           <th>密码</th>
           <th>年龄</th>
           <th>电话信息</th>
           <th>删除/修改</th>
       </tr>

       <c:forEach items="${requestScope.Student}" var="stu">  <!--也可以用begin end 表示开始索引和结束索引-->
       <tr>
           <td>${stu.id}</td>
           <td>${stu.username}</td>
           <td>${stu.password}</td>
           <td>${stu.age}</td>
           <td>${stu.numbers}</td>
           <td>删除/修改</td>
       </tr>
       </c:forEach>

   </table>



</body>
</html>
