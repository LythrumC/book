<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/25 0025
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>使用JSTL标签</title>
</head>
<body>
        <%--
             <c:set></c:set>
            作用:标签可以往指定域中保存数据
            域对象:setAttribute(key,value);
            scope：指定哪个域    var:键名字    value:值
        --%>
     保存之前:${requestScope.abc}<br>
     <c:set scope="request" var="abc" value="request"></c:set><br>
     保存之后:${requestScope.abc}<br>


      <%--
         <c:if test="${Expression}}"></c:if>   标签用来做if判断，没有if-else的写法,多路判断只能写多个c:if
         test用来写表达式，表达式成立则执行
      --%>

      <c:if test= "${12 == 12}">
          <h1>12==12</h1>
      </c:if>
      <c:if test="${12 != 12}">
          <h1>12 ！= 12</h1>
      </c:if>

      <% request.setAttribute("abc",148);%>
        <%--
           <c:choose></c:choose>  标签提供了多路判断，功能实现类似 switch...case...default
           只要值符合其中一个表达式，就会只执行该表达，无需手动break
           1.标签里面不能使用html标签注释，要是用jsp注释
           2.when标签的父标签一定要是choose标签
           3.<c: when>必须被  <c:choose> 包括
        --%>
      <c:choose>

          <c:when test="${requestScope.abc > 190}">
              <h2>数值大于190</h2>
          </c:when>

          <c:when test="${requestScope.abc > 180}">
              <h2>数值大于180</h2>
          </c:when>

          <c:when test="${requestScope.abc > 170}">
              <h2>数值大于170</h2>
          </c:when>

          <c:otherwise>
              <c:choose>
                  <c:when test="${requestScope.abc > 160}">
                      <h2>数值大于160</h2>
                  </c:when>

                  <c:when test="${requestScope.abc > 150}">
                      <h2>数值大于150</h2>
                  </c:when>

                  <c:when test="${requestScope.abc > 140}">
                      <h2>数值大于140</h2>
                  </c:when>
              </c:choose>
          </c:otherwise>
      </c:choose>


      <%--
         <c:forEach begin="" end="" var=""></c:forEach> 标签实现遍历输出，可以遍历表达式或者html标签
         begin  属性表示设置开始的索引
         end    属性表示设置结束的索引
         var    属性表示循环的变量(也是当前正在遍历的数据)
        for(int i=0; i<10; i++)
      --%>

        <table border="1px solid black" cellspacing="0">
            <c:forEach begin="1" end="10" var="i">
                <tr>
                    <td>第${i}行</td>
                </tr>
            </c:forEach>
        </table><br>

        <%--
         <c:forEach item"" var=""></c:forEach> foreach遍历对象类型的数组
         item属性 表示设置数据源
         var    属性表示循环的变量(也是当前正在遍历的数据)
      --%>
        <% request.setAttribute("numbers",new String[] {"13808139004","15082232903","13890656647"});%>
        <c:forEach items="${ requestScope.numbers }" var="item">
            ${ item }<br>
        </c:forEach>

       <%
           Map<String,Object> map = new HashMap<>();
           map.put("key1","value1");
           map.put("key2","value2");
           map.put("key3","value3");
           request.setAttribute("map",map);
       %>

        <%--c:foreach 遍历map合集--%>
       <c:forEach items="${requestScope.map}" var="entry">
           <h1>${entry.key} = ${entry.value}</h1>
       </c:forEach>



</body>
</html>
