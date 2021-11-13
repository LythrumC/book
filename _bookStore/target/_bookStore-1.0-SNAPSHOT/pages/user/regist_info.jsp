<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <!--静态包含公共引用代码-->
    <jsp:include page="/pages/common/jquery_common.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/regist_info.css">
<%--    <script src="static/JS/login_info.js"></script>--%>
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

            //AJAX验证用户名是否可用
            $("#username").blur(function (){
                //1.获取用户名
                var username = this.value;
                $.getJSON(
                      "http://localhost:8080/_bookStore/UserServlet",
                      "action=ajaxExistsusername&username=" + username ,
                       function (data){
                          if(data.existsUsername){
                              $("#errorSpan").text("用户名可用")
                          }else{
                              $("#errorSpan").text("用户名已存在")
                          }
                      }
                );
            });
        });
    </script>
</head>
<body style="background: url(static/img/bg.jpg) no-repeat;background-size: cover">
<div class="loginmain">
    <div class="login-title">
        <span>用户注册</span>
    </div>

    <span style="margin-left: 85px; font-size: 11px;color: red;font-family: 'Gudea', sans-serif;margin-top: -10px;float:left;" id="errorSpan">
        ${requestScope.msg}
    </span>

    <div class="login-con">
                                        <!--执行baseServlet的doPost方法-->
        <form method="post" id="login" name="login" action="UserServlet">
            <!--隐藏表示，用于判断具体执行哪一个方法-->
            <input type="hidden" name="action" value="regist">

            <!--用户名-->
            <div class="login-user">
                <div class="icon">
                    <img src="static\img\user_icon_copy.png" alt="">
                </div>
                <input type="text" id="username"  name="username" placeholder="用户名" autocomplete="off" value = "${requestScope.username}">
            </div>

            <!--email-->
            <div class="login-pwd">
                <div class="icon">
                    <img src="static\img\lock_icon_copy.png" alt="">
                </div>
                <input type="text" name="email" placeholder="邮箱" autocomplete="off" value="${requestScope.email}">
            </div>

            <!--密码-->
            <div class="login-pwd">
                <div class="icon">
                    <img src="static\img\lock_icon_copy.png" alt="">
                </div>
                <input type="password" name="password" placeholder="密码" autocomplete="off" value="">
            </div>

            <!--重复密码-->
            <div class="login-pwd">
                <div class="icon">
                    <img src="static\img\lock_icon_copy.png" alt="">
                </div>
                <input type="password" name="repetition" placeholder="重复密码" autocomplete="off" value="">
            </div>

            <!--验证码-->
            <div class="login-yan">
                <div class="icon">
                    <img src="static\img\key.png" alt="">
                </div>
                <input type="text" name="code" placeholder="验证码" autocomplete="off" value="">
                <img src="kaptcha.jpg" style="margin-left: 300px;width: 130px;" id="code_img">
            </div>

           <!--提示信息-->
            <div>
                <a href="pages/user/login_info.jsp" style="margin-left: 45px;font-size: 15px;font-family:'Gudea';color: #61BFFF !important;">已有账号？立即登录</a>
            </div>

            <!--登录按钮-->
            <div class="login-btn">
                <input type="submit" value="注册">
            </div>
        </form>
    </div>
</div>

</body>
</html>