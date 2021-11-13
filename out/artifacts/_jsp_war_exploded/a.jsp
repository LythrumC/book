<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.security.PrivateKey" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/22 0022
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试使用jsp脚本代码</title>
    <!--生成的编译文件路径-->
    <!--C:\Users\Administrator\AppData\Local\JetBrains\IntelliJIdea2020.3\tomcat\46a10717-03e5-4498-84c3-0d1b4d8417c1\work\Catalina\localhost\_jsp\org\apache\jsp-->
</head>
<body>
    <%--1.声明属性类型--%>
    <%!
        private Integer a;
        private String b;
        private static Map<String,Object> map;
    %>

    <%--2.声明静态代码块--%>
    <%!
       static {
           map = new HashMap<String,Object>();
           map.put("key1","value1");
           map.put("key2","value2");
       }
    %>

    <%--3.声明类方法--%>
    <%!
        public int abc(){
            return 12;
        }
    %>

    <%--4.声明内部类--%>
    <%!
        public static class A{
            private Integer id=12;
            private String abc = "abc";
        }
    %>

    <%--1.输出整型
    2.输出浮点型
    3.输出字符串型
    4.输出对象--%>
    <%=12 %><br>
    <%=12.12 %><br>
    <%="我是字符串 "%><br>
    <%=map %><br>
    <%=request.getParameter("username")%>


<%--    练习--%>
<%--    1.代码脚本  if语句--%>
    <%
        int i = 12;
        if(i == 12){
            System.out.println("i是12");
        }else {
            System.out.println("i不是12");
        }
    %>
    <br>
<%--    2.代码脚本  for循环--%>
    <%
        int j;
       for( j=0;j<10;j++){
    %>

    <%=j %><br>
    <%}%>
<%--    3.翻译后java文件中_jspService方法内的代码都可以写--%>
    <%
        String username = request.getParameter("username");
        System.out.println(username);
    %>






</body>
</html>
