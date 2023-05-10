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

import java.util.Collection;


@WebServlet(name = "MoreUpladController", value = "/moreUpload")
@MultipartConfig(maxRequestSize = 1024 * 1024 * 300, maxFileSize = 1024 * 1024 * 100)
public class MoreUpladController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String basePath = request.getServletContext().getRealPath("/WEB-INF/upload");
        File file = new File(basePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //获取表单提交的数据
        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
            String filename = part.getSubmittedFileName();
            if (filename != null) {
                String newName = UploadUtils.NewFileName(filename);
                String newPath = UploadUtils.NewFilePath(basePath, filename);
                part.write(newPath + "\\" + newName);
            } else {
                String username = request.getParameter(part.getName());
                System.out.println(username);
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
