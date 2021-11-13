package Ajax;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author 陈宽
 * @create 2021-02-28 16:10
 * @description  BaseServlet,整合了 UserServlet以及未来其他Servlet的共同方法，简化代码复用，以后其他Srevlet只需要继承此类就可以
 */
public class baseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //通过反射类获取方法，优化代码
        String action = req.getParameter("action");
        //解决请求响应乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        try {
            Method declaredMethod = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            //invoke(req,resp)执行该方法，里面的参数要跟得到的方法里面的参数一样
//            System.out.println(declaredMethod);
            declaredMethod.invoke(this,req,resp);
        }
        catch (Exception e) {
            e.printStackTrace();
            //向上抛出异常
            throw new RuntimeException(e);
        }
    }
}
