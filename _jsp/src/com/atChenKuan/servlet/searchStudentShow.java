package com.atChenKuan.servlet;

import com.atChenKuan.pojo.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈宽
 * @create 2021-02-23 21:10
 * @description
 */
public class searchStudentShow extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        //发sql语句查询学生的信息
        //使用for循环生成查到的数据库信息
        List<Student> studentList = new ArrayList<>();
        for(int j=0; j<10; j++){
            int t = j+1;
            studentList.add(new Student(t,"name" + t,18 + t,"phone" + t));
        }
        //保存查询的结果(学生信息)到request域中
        req.setAttribute("List",studentList);
        //请求转发跳转到test_2.jsp中
        req.getRequestDispatcher("/test/test_2.jsp").forward(req,resp);

    }
}
