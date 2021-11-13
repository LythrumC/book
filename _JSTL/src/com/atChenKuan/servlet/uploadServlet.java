package com.atChenKuan.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 陈宽
 * @create 2021-02-26 14:19
 * @description
 */
public class uploadServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        System.out.println("文件已上传");
//        /*
//          用普通的得到参数方法无法得到dopost中上传的文件
//          System.out.println(req.getParameter("username"));
//          System.out.println(req.getParameter("photo"));
//        */
////        文件上传是以流的方式传输，必须使用流去得到上传的数据
//        ServletInputStream inputStream = req.getInputStream();
//        byte[] buffer = new byte[1024];  //缓冲区
//        int read = inputStream.read(buffer);
//        System.out.println(new String(buffer,0,read));
//    }


    //运用jar包进行文件上传操作
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.先判断上传的数据是不是多段数据（只有多段数据才能够上传）
        if (ServletFileUpload.isMultipartContent(req)){
            //创建FileitemFactory工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //创建用于上传数据的工具类ServletFileupload类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            //解析上传的数据，得到每一个表单项
            try {
                List<FileItem> list = servletFileUpload.parseRequest(req);
                //遍历检查表单项的类型
                for (FileItem fileItem : list) {
                    if (fileItem.isFormField()){
                        //如果是普通表单项
                        System.out.println("表单项的name属性值:" + fileItem.getFieldName() );
                                                                                      //UTF-8解决乱码问题
                        System.out.println("表单项的value属性值:" + fileItem.getString("UTF-8"));
                    }else {
                        //如果是上传文件表单项
                        System.out.println("表单项的name属性值:" + fileItem.getFieldName() );
                        System.out.println("上传的文件名字:" + fileItem.getName());
                    }

                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }



    }
}
