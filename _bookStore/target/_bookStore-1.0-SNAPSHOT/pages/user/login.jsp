<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <!--静态包含公共引用代码-->
    <jsp:include page="/pages/common/jquery_common.jsp"></jsp:include>
    <!--外部引用css文件-->
    <link rel="stylesheet" type="text/css" href="static/css/login_css.css">
    <!--外部引用JQuery代码-->
    <script type="text/javascript" src="static/JS/login.js"></script>

    <script type="text/javascript">
        //浏览器加载完毕后执行
       $(function (){
           //给验证码的图片，绑定单击点击事件
           $("#code_img").click(function (){
               //事件响应的function函数中有一个this对象。这个this对象，是当前正在响应事件的dom对象
               //src属性表示验证码img便签的 图片路径，可读可写。
               <%--alert(${basePath});--%>
                                            //如果不加时间戳，浏览器会每次从缓存中去拿验证码图片
                                            //这样会导致其他浏览器无法点击验证码图片刷新
               this.src = "${basePath}kaptcha.jpg?d=" + new Date();
           });
       });

    </script>
</head>


<body>
<div id="div_1">
                                                 <!--执行baseServlet的doPost方法-->
    <form method="post" id="login" name="login" action="UserServlet">
        <input type="hidden" name="action" value="login">
        <fieldset>
            <legend style="text-align: center">用户登录</legend>
            <label id="label_hint" name="label_hint">
<%--                <%=request.getAttribute("msg") == null? "请输入用户名或密码": request.getAttribute("msg")%>--%>
                <!--用EL表达式代替-->
                ${empty requestScope.msg ? "请输入用户名和密码" : requestScope.msg}
            </label>
            <table id="table_1">
                <!--用户登录-->
                <tr>
                    <td class="info">账号:</td>
                    <td>
                        <input type="text" placeholder="请输入账号"
                               name="username"
                               <%--
                               用EL表达式替代
                               value="<%=request.getAttribute("username") == null? "":request.getAttribute("username")%>"
                               --%>
                                <%--EL表达式为空时，直接表示为空串--%>
                               value = "${requestScope.username}"
                        >

                                      <!--登录失败保留用户名，不保留密码-->
                    </td>
                </tr>

                <!--密码-->
                <tr>
                    <td class="info">密码:</td>
                    <td>
                        <input type="password"  placeholder="请输入12位密码" name="password"></td>
                </tr>

                <!--重复密码-->
                <tr>
                    <td class="info">重复密码:</td>
                    <td>
                        <input type="password"  placeholder="请重复密码" name="repetition"></td>
                </tr>

                <!--输入验证码-->
                <tr>
                    <td class="info">验证码:</td>
                    <td>
                        <input type="text" name="code" style="width: 80px;">
                        <img src="kaptcha.jpg" style="width: 100px;height: 50px" id="code_img">
                        <label style="color: red">
                            ${empty requestScope.codetip? "请输入验证码" : requestScope.codetip}
                        </label>
                    </td>
                </tr>

                <!--提交-->
                <tr>
                    <td colspan="2" id="sub" style="text-align: center"><input type="submit"  value="登录"></td>
                </tr>
            </table>
        </fieldset>
    </form>
</div>
       <!--静态包含页脚-->
       <jsp:include page="/pages/common/footer.jsp"></jsp:include>
</body>
</html>
