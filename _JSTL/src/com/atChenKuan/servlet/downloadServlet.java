package com.atChenKuan.servlet;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @author 陈宽
 * @create 2021-02-26 16:07
 * @description
 */
public class downloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取要下载文件的名字
        String file = "图片.png";
        //2.读取下载文件的内容   (通过ServletContext对象可以领取)
        ServletContext servletContext = getServletContext();
        //获取要下载的文件类型
        String mimeType = servletContext.getMimeType("/file/" + file);
        System.out.println("下载文件的类型:" + mimeType);
        //3.在回传前，通过响应头告诉客户端返回的数据类型
        resp.setContentType(mimeType);
        //4.还要告诉客户端收到的文件是用于下载的(还是使用响应头)
        //Content-Disposition响应头，表示收到的数据怎么处理
        //attachment表示附件，表示下载使用
        //filename= 表示指定下载的文件名字
                                                               //filename=2.png  可以自定义下载的文件
//        resp.setHeader("Content-Disposition","attachment; filename=" + file);
                                                                             //谷歌和IE要进行UTF-8编码
        resp.setHeader("Content-Disposition","attachment; filename=" + URLEncoder.encode(file,"UTF-8"));

        /*
          / 斜杠被服务器解析地址为 http://localhost:8080/ip:port/工程名/    映射到代码就是  web目录
        */
        InputStream resourceAsStream = servletContext.getResourceAsStream("/file/" + file);
        //获取响应输出流
        OutputStream outputStream = resp.getOutputStream();
        //5.把下载的文件回传给客户端
        //读取输入流中所有数据，复制给输出流，输出客户端
        IOUtils.copy(resourceAsStream,outputStream);
    }
}
