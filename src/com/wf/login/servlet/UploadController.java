package com.wf.login.servlet;

import com.wf.login.common.utils.UploadUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UploadController", value = "/upload")
@MultipartConfig(maxFileSize = 1024 * 1024 * 100, maxRequestSize = 1024 * 1024 * 200)
public class UploadController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取请求的数据
        String username = request.getParameter("username");
        Part part = request.getPart("file1");

        //获取上传文件的路径  真实路径
        String basepath = request.getServletContext().getRealPath("/WEB-INF/upload");
        File file = new File(basepath);
        if (!file.exists()) {
            file.mkdirs();
        }

        String primName = part.getSubmittedFileName();
        List<String> suffixname = new ArrayList<>();
        suffixname.add(".jpg");
        suffixname.add(".bmp");
        suffixname.add(".png");
        suffixname.add(".docx");
        suffixname.add(".pdf");
        suffixname.add(".txt");


        String extName = primName.substring(primName.lastIndexOf("."));
        if (!suffixname.contains(extName)) {
            System.out.println("文件类型错误，请重写选择文件上传！");
            return;
        }
        String newName = UploadUtils.NewFileName(primName);
        String newPath = UploadUtils.NewFilePath(basepath, primName);
        //文件上传
        part.write(newPath + "\\" + newName);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

