package com.atChenKuan.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 陈宽
 * @create 2021-04-26 21:37
 * @description
 */
public class SessionServlet extends baseServlet{
    /**
     * 立马使当前Session超时(销毁)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String id = session.getId();
               //使当前Session马上超时
        session.invalidate();
        resp.getWriter().write("使当前Session马上销毁");
    }

    /**
     *可以手动设置Session的生命周期，这个是针对当前工程而言
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void Life3(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        //手动修改Session的生命周期时间(超时时间)，针对当前工程而言
        //修改后之前新建的Session会在间隔三秒后销毁，再点击新建时会重新得到一个Session
        //注意这里是间隔3S后才会销毁！
        //正数表示设置时长，负数表示永不删除
        session.setMaxInactiveInterval(3);
        resp.getWriter().write("修改当前工程的Session超时时间为3S");

    }

    /**
     * Session的默认生命周期
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void defalutLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int maxInactiveInterval = req.getSession().getMaxInactiveInterval();
                                                           //默认最大生命时间是1800S，半小时，可以自己在web.xml文件中修改，这个是针对整个工程而言
        resp.getWriter().write("Session的默认生命周期是：" + maxInactiveInterval);
    }

    /**
     * 通过Session设置域数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void setAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("key_1","value_1");
        resp.getWriter().write("setAttribute成功");

    }

    /**
     * 通过Session得到域数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object key_1 = req.getSession().getAttribute("key_1");
        resp.getWriter().write("key_1的Session域值是：" + key_1);

    }

    protected void CreateOrGetSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Session一开始是存在的，只需要从服务器中获取就行了,这是新建，之后再点击就是引用之前创建的Session
        //Session用来存储用户登录后的信息
        //Session的数据时存在服务器上的，Cookie是存在客户端(浏览器)上的
        //1.获取Session
        HttpSession session = req.getSession();
        //2.判断Session是新创建的
        boolean isNew = session.isNew();
        //3.获取SessionID
        String id = session.getId();

        resp.getWriter().write("Session是否是新创建的:" + isNew + "<br/>");
        resp.getWriter().write("SessionID:" + id + "<br/>");



    }
}
