<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册界面</title>
    <!--静态包含公共引用代码-->
    <jsp:include page="/pages/common/jquery_common.jsp"></jsp:include>
    <!--JQuery代码-->
    <script type="text/javascript" src="static/JS/regist.js"></script>
    <!--外部引用css文件-->
    <link rel="stylesheet" type="text/css" href="static/css/regist.css">



</head>
<body >
<div id="div_1">
    <form method="post" id="register" name="register" action="UserServlet">
        <input type="hidden" name="action" value="regist">
        <fieldset>
            <legend style="text-align: center">表单注册</legend>
            <label id="label_hint" name="label_hint">
<%--                用EL表达式替代
                    <%=request.getAttribute("msg") == null? "": request.getAttribute("msg")%>
                    当值为null时，EL表达式直接输出 空串
--%>
                ${requestScope.msg}

            </label>
            <table id="table_1">
                <!--用户注册-->
                <tr>
                    <td class="info">账号:</td>
                    <td>
                        <!--验证码错误则保留原来输入的账号-->
                        <input type="text" placeholder="请输入账号"
                               name="username"
                               <%--
                               EL表达式替代
                               value="<%=request.getAttribute("username") == null? "" : request.getAttribute("username")%>">
                               --%>
                               value="${requestScope.username}">
                    </td>
                </tr>

                <!--邮箱-->
                <tr>
                    <td class="info">邮箱:</td>
                    <td>
                        <input type="text" placeholder="请输入邮箱地址"
                               name="email"
<%--                               value="<%=request.getAttribute("email") == null? "" : request.getAttribute("email")%>"--%>
                               value="${requestScope.email}">

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
                        <img src="kaptcha.jpg" style="width: 100px;height: 50px">
                        <label style="color: red">
                            ${empty requestScope.codetip? "请输入验证码" : requestScope.codetip}
                        </label>
                    </td>
                </tr>

                <!--提交-->
                <tr>
                    <td colspan="2" id="sub" style="text-align: center"><input type="submit"  value="注册"></td>
                </tr>
            </table>
        </fieldset>
    </form>
</div>

<!--静态包含页脚-->
<jsp:include page="/pages/common/footer.jsp"></jsp:include>

</body>
</html>
