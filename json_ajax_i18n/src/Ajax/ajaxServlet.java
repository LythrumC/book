package Ajax;

import JavaBean.Person;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈宽
 * @create 2021-05-13 19:36
 * @description
 */
public class ajaxServlet extends baseServlet {


    protected void javaScriptAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("收到Ajax请求");

        //如果要向由Ajax发出的请求返回数据，要注意一下几点
        //1.返回的数据在服务端，Ajax是由客户端发送来的，两个不在同一个地址下(因为没有用session或request)
        //2.服务器和客户端数据的通信要用JSON格式
        Person person = new Person(1, "陈宽");

        Gson gson = new Gson();
        String personString = gson.toJson(person);

        //用response向客户端回传数据
        resp.getWriter().write(personString);
        System.out.println(personString);
    }

    protected void JqueryAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("  Jquery == 收到AJAX请求");
        //如果要向由Ajax发出的请求返回数据，要注意一下几点
        //1.返回的数据在服务端，Ajax是由客户端发送来的，两个不在同一个地址下(因为没有用session或request)
        //2.服务器和客户端数据的通信要用JSON格式
        Person person = new Person(1, "陈宽");
        Gson gson = new Gson();
        String personString = gson.toJson(person);
        //用response向客户端回传数据
        resp.getWriter().write(personString);
//        System.out.println(personString);
    }

    protected void getAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("  get == 收到AJAX请求");
        //如果要向由Ajax发出的请求返回数据，要注意一下几点
        //1.返回的数据在服务端，Ajax是由客户端发送来的，两个不在同一个地址下(因为没有用session或request)
        //2.服务器和客户端数据的通信要用JSON格式
        Person person = new Person(1, "陈宽");
        Gson gson = new Gson();
        String personString = gson.toJson(person);
        //用response向客户端回传数据
        resp.getWriter().write(personString);
//        System.out.println(personString);
    }

    protected void postAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("  post == 收到AJAX请求");
        //如果要向由Ajax发出的请求返回数据，要注意一下几点
        //1.返回的数据在服务端，Ajax是由客户端发送来的，两个不在同一个地址下(因为没有用session或request)
        //2.服务器和客户端数据的通信要用JSON格式
        Person person = new Person(1, "陈宽");
        Gson gson = new Gson();
        String personString = gson.toJson(person);
        //用response向客户端回传数据
        resp.getWriter().write(personString);
//        System.out.println(personString);
    }

    protected void getJSONAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("  getJSONAjax == 收到AJAX请求");
        //如果要向由Ajax发出的请求返回数据，要注意一下几点
        //1.返回的数据在服务端，Ajax是由客户端发送来的，两个不在同一个地址下(因为没有用session或request)
        //2.服务器和客户端数据的通信要用JSON格式
        Person person = new Person(1, "陈宽");
        Gson gson = new Gson();
        String personString = gson.toJson(person);
        //用response向客户端回传数据
        resp.getWriter().write(personString);
//        System.out.println(personString);
    }

    protected void Jqueryserialize(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("  Jqueryserialize == 收到AJAX请求");
        //如果要向由Ajax发出的请求返回数据，要注意一下几点
        //1.返回的数据在服务端，Ajax是由客户端发送来的，两个不在同一个地址下(因为没有用session或request)
        //2.服务器和客户端数据的通信要用JSON格式

        System.out.println("username:" +req.getParameter("username"));
        System.out.println("password:" +req.getParameter("password"));

        Person person = new Person(1, "陈宽");
        Gson gson = new Gson();
        String personString = gson.toJson(person);
        //用response向客户端回传数据
        resp.getWriter().write(personString);
//        System.out.println(personString);
    }
}


