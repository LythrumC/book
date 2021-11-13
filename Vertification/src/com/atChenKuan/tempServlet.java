package com.atChenKuan;

import com.google.code.kaptcha.servlet.KaptchaServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author 陈宽
 * @create 2021-04-28 10:34
 * @description
 */
public class tempServlet extends HttpServlet {

    /**
     * 利用谷歌图片验证实现登录
     * 1.导入Kaptcha.jar包
     * 2.在web.xml文件中去配置用于生成验证码的Servlet程序
     * 3.在表单中使用img标签去显示验证码图片(src属性中的值填在xml文件中配置的url地址)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //得到前端传过来的属性
        String code = req.getParameter("code");
        String account = req.getParameter("account");
        String password = req.getParameter("password");

        //利用验证码实现登录的作用是为了避免表单重复提交
        //在使用这个jar之前一定要去web.xml中配置！！！ KaptchaServlet!
        //谷歌的验证码是直接保存在Session中的，所以要用Session去得到这个属性
        String vertification = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //得到这个验证码后要立马删除，不允许服务器缓存，避免二次使用
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        if(vertification != null && vertification.equalsIgnoreCase(code)){
            System.out.println("保存到数据库:" + account);
            resp.sendRedirect(req.getContextPath() + "/ok.jsp");
        }else{
            //登录成功后，如果回退到上一级目录，那么利用Kaptcha生成的验证码在Session中是null值
            System.out.println("请不要重复提交");
        }

    }



}
