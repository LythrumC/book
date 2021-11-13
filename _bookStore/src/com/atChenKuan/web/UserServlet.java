package com.atChenKuan.web;

import com.atChenKuan.pojo.User;
import com.atChenKuan.service.UserService;
import com.atChenKuan.service.impl.UserServiceImpl;
import com.atChenKuan.utils.webUtils;
import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author 陈宽
 * @create 2021-02-27 21:46
 * @description  只需要写实现的方法，方法的执行doPost()由父类BaseServlet实现
 */
public class UserServlet extends baseServlet {
    private UserService userService = new UserServiceImpl();
    /**
     * 处理登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获得参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //用户提交的验证码
        String code = req.getParameter("code");

        //获得谷歌提供的图片验证码
        String google_code = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);

        //2.进行用户名和密码的验证(调用XXXXServlet.xxx()处理业务）
        //userService.login()登录
        User loginUser = userService.login(new User(username, password, null));

        //验证码验证
        if(google_code != null && google_code.equalsIgnoreCase(code)){
            //如果等于null,则登录失败
            if(loginUser == null){
                req.setAttribute("msg","用户名或密码错误");
                req.setAttribute("username",username);
                req.getRequestDispatcher("/pages/user/login_info.jsp").forward(req,resp);
            }else{
                //判断该用户是否是系统管理员admin
                if (userService.existsUsernameVip(username)){
                    req.getSession().setAttribute("admin","admin");  //给一个管理员的标识
                    req.getSession().setAttribute("user",loginUser);    //帮用户信息存进Session
                    req.setAttribute("username",username);
                    req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
                }else{
                    req.setAttribute("username",username);
                    //用Session保存用户登录后的信息
                    //因为request只能满足一个页面的数据传输，如果涉及到多个页面的数据就是用Session，他的数据域广
                    //除此之外，放在Session里面还要供Filter检查用户是否登录，登录了才能进后台
                    req.getSession().setAttribute("user",loginUser);
                    //登录成功，跳转到login_success
                    req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
                }

            }
        }else{
            req.setAttribute("username",username);
            req.setAttribute("msg","验证码错误");
            req.getRequestDispatcher("/pages/user/login_info.jsp").forward(req,resp);
        }

    }

    /**
     * 利用销毁Session的方法来退出登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //得到当前有用户信息的Session
        //然后直接销毁
        req.getSession().invalidate();
        //最后重定向到首页index（默认就是index.jsp,所以不用在后面加 index.jsp）
        resp.sendRedirect(req.getContextPath());
    }




    /**
     * 处理注册
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repetition = req.getParameter("repetition");
        //用户提交的验证码
        String code = req.getParameter("code");

        //获得谷歌提供的图片验证码
        String google_code = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);

        //通过BeanUtils注入参数
        /*
           BeanUtils.populate(bean,value);     这个方法会搜索请求的jsp页面所有的带有name属性的标签，并获取该标签的value值
           然后通过赋给某个javaBean对象，搜索这个对象里跟name一样的属性，如果有，就调用该javabean里面的set()方法进行赋值
        * */
        User user = webUtils.copyParamToBean(req.getParameterMap(), new User());
//        System.out.println(user.toString());
//        Map<String,String[]> map = req.getParameterMap();
//        for(Map.Entry<String,String[]> entry : map.entrySet()){
//            System.out.println(entry.getKey() + "=" + Arrays.asList(entry.getValue()));
//        }

        //2.检查验证码是否正确 (谷歌提供的验证码 和 用户输入的验证码 比对)
        if (google_code != null && google_code.equalsIgnoreCase(code)){
            // 3.检查用户名是否可用,可用就检查两次密码是否正确
            if (userService.existsUsername(username)){
                   //检查两次密码是否一致，一致就保存到数据库
                if(password.equals(repetition)){
                     //4.调用Servlet保存到数据库
                    userService.registUser(new User(username,password,email));
                    //跳转到注册成功页面  regist_success.jsp
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
                }else{
                    //两次密码不一致，返回到注册页面重新注册
                    req.setAttribute("msg","两次密码不一致");
                    req.setAttribute("username",username);
                    req.setAttribute("email",email);
                    req.getRequestDispatcher("/pages/user/regist_info.jsp").forward(req,resp);
                }

            }else {
                //用户名已经存在,把回显信息保存到request域中
                req.setAttribute("msg","用户名已经存在");
//                System.out.println("用户名[ " + username + " ]不可用");
                req.getRequestDispatcher("/pages/user/regist_info.jsp").forward(req,resp);
            }
        } else {
            //验证码错误，跳转回当前注册页面
            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
//            System.out.println("验证码[ " + verification + " ]错误");
            req.getRequestDispatcher("/pages/user/regist_info.jsp").forward(req,resp);
        }
    }

    /**
     * 用AJAX验证用户名是否可用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsusername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //得到AJAX传过来的用户名
        String username = req.getParameter("username");
        System.out.println(username);
        boolean existsUsername = userService.existsUsername(username);
        //把返回的结果封装成map对象,并转换成JSON数据
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);
        Gson gson = new Gson();
        String jsonObj = gson.toJson(resultMap);
        //响应给客户端
        resp.getWriter().write(jsonObj);
    }

}
