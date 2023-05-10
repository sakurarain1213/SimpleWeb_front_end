package com.wf.login.servlet;

import com.wf.login.common.utils.UploadUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(value="/downLoad")
public class DownLoadController extends HttpServlet{
protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        //list.jsp页面传来的是uuidname
        String filename = request.getParameter("filename");
        String basePath = request.getServletContext().getRealPath("/WEB-INF/upload");
        //以"_"分割uuid_name为数组{uuid,name}
        String realName = filename.split("_")[1];
        String newPath = UploadUtils.NewFilePath(basePath,realName);
        //设置
        response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(filename,"utf-8"));
        FileInputStream fis = new FileInputStream(newPath+"\\"+filename);
        ServletOutputStream sos = response.getOutputStream();
        byte[] buffer = new byte[1024*1024*100];
        int len = 0;
        while((len=fis.read(buffer))!=-1){
        sos.write(buffer,0,len);
        }
        fis.close();
        sos.close();
        }
protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        doPost(request,response);
        }
        }
