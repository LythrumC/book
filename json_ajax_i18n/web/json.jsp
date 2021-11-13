<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/5/12 0012
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Json</title>
    <script type="text/javascript">
        //1.json的定义
        var jsonObj = {
            "key1":12,
            "key2":"abc",
            "key3":true,
            "key4":[11,"arr",false],
            //json也可以嵌套 json
            "key5":{
                "key5_1":551,
                "key5_2":"key5_2_value"
            },
            "key6":[{
                "key_6_1_1":6611,
                "key6_1_2":"key6_1_2_value"
            },{
                "key_6_2_1":6611,
                "key_6_2_2":"key6_2_2_value"
            }]
        };

        //2.打印json的类型(Object)
        //alert(typeof (jsonObj));

        //3.json的访问
        // alert(jsonObj.key1); //访问key1
        // alert(jsonObj.key4[0]);  //json中有数组，可以访问下标
        // for (var i = 0; i < jsonObj.key4.length; i++) {
        //     alert(jsonObj.key4[i])
        // }
        // alert(jsonObj.key5.key5_1);  //json中还有json，就继续访问json的方式进行访问

        //4.json常用的两种转换方法
        var jsonString = JSON.stringify(jsonObj);  //把json对象转换成字符串
        var jsonObj2 = JSON.parse(jsonString);     //把json字符串转换成json对象
        alert(jsonObj2.key1); //12
        alert(jsonObj2.key2); //abc


    </script>
</head>
<body>

</body>
</html>
