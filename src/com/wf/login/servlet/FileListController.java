package com.wf.login.servlet;

import com.wf.login.common.utils.FileListUtils;
import javax.servlet.ServletException;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(value="/fileList")
public class FileListController extends HttpServlet {
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

                String basePath = request.getServletContext().getRealPath("/WEB-INF/upload");
        File file = new File(basePath);
        //调用FilelistUtil的fileList方法把，当前文件file路径下全部文件的uuidname和name全部以key值和value值存入hashMap
        HashMap<String,String> fileMap = new HashMap<>();
        FileListUtils.Filelist(file,fileMap);
        request.setAttribute("fileMap",fileMap);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        doPost(request,response);
    }
}
