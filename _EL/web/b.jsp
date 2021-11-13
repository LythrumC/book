<%@ page import="com.atChenKuan.pojo.Person" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/24 0024
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL表达式输出对象属性</title>
</head>
<body>

     <% Person person = new Person(); %>
     <% person.setName("陈宽");

        String[] phones = {"13808139004","15082232903","13890656647"};
        person.setPhones(phones);

        List<String> cities = new ArrayList<>();
        cities.add("成都");
        cities.add("北京");
        cities.add("西安");
        person.setCities(cities);

         Map<String,Object> map = new HashMap<>();
         map.put("key1","value1");
         map.put("key2","value2");
         map.put("key3","value3");
         person.setMap(map);

         request.setAttribute("person",person);
     %>
     <!--EL表达式搜索类里面的属性，主要是看该类(例Person) 里有没有该属性对应的get()方法
         例Person中没有age的get()方法，则EL表达式 无法得到person.age的值
     -->

     输出Person:${person}<br>
     输出Person的name属性值:${person.name}<br>
     输出Person的Phones数组属性值:${person.phones}<br>
     输出Person的Phones数组属性值:${person.phones[0]}<br>   <!--数组用下标表示具体值,不用下标则输出数组内存位置-->
     输出Person的cities集合中的元素值:${person.cities}<br>   <!--List集合直接输出就是全部的值-->
     输出Person的cities集合中的个别元素值:${person.cities[2]}<br> <!--List也可以用下标输出具体值-->
     输出Person的Map集合:${person.map}<br>      <!--键值对直接输出则是全部的 键值对-->
     输出Person的Map集合指定键值对: ${person.map.key2}<br>   <!--输出指定键值对只需要跟指定 键值-->

     <h1>关系运算</h1>
     ${5 == 5} 或 ${5 eq 5}<br>
     ${5 != 5} 或 ${5 ne 5}<br>
     ${5 < 5}  或 ${5 lt 5}<br>
     ${5 >5}  或  ${5 gt 5}<br>
     ${5 <= 5} 或 ${5 le 5}<br>

</body>
</html>
