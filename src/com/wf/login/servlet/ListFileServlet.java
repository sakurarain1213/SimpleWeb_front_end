package com.wf.login.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName:ListFileServlet
 * @Author lihuawei
 * @Date 2022-04-14 v1.0
 * listfile()方法，listfile()方法是用来列出目录下的所有文件的，listfile()方法内部采用了递归，罗列出下载目录下的所有文件，包括子目录下的文件。
 *
 * 在实际开发当中，我们还可能会在数据库创建一张表，里面会存储要下载的文件名以及文件的具体存放目录，我们通过查询表就可以知道文件的具体存放目录，这样是不需要用到递归操作的，我们就可以修改listfile()方法即可。
 *
 * 这个例子是因为没有使用数据库存储要下载的文件名和文件的具体存放位置，而指定一个服务器上存放下载文件的位置，所以需要用到递归。在递归时，将获取到的文件名存放到从外面传递到listfile方法里面的Map集合当中，这样就可以保证所有的文件都存放在同一个Map集合当中。
 */
@WebServlet("/ListFileServlet")
public class ListFileServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 获取下载文件的目录
        String downloadPath = "D:\\downloads";

        // 存储要下载的文件名，LinkedHashMap是有序的（插入的顺序）
        Map<String, String> fileNameMap = new LinkedHashMap<String, String>();

        // 递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中
        listfile(new File(downloadPath), fileNameMap);// File既可以代表一个文件也可以代表一个目录

        // 将Map集合发送到listfile.jsp页面进行显示
        request.setAttribute("fileNameMap", fileNameMap);
        request.getRequestDispatcher("/fileuploaddownload.jsp").forward(request, response);
    }

    /**
     * @Method: listfile
     * @Description: 递归遍历指定目录下的所有文件
     * @Anthor:lihuawei
     * @Date 2022-04-14 v1.0
     * @param file 即代表一个文件，也代表一个文件目录
     * @param map  存储文件名的Map集合
     */
    public void listfile(File file, Map<String, String> map) {
        // 如果file代表的不是一个文件，而是一个目录
        if (!file.isFile()) {
            // 列出该目录下的所有文件和目录
            File files[] = file.listFiles();
            // 遍历files[]数组
            for (File f : files) {
                // 递归
                listfile(f, map);
            }
        } else {
            String absoluteName = file.getAbsolutePath();
            String fileName = file.getName();
            System.out.println("ListFileServlet.listfile()...fileName = " + fileName + ";absoluteName = " + absoluteName);
            // absoluteName是文件的绝对路径的文件名称，这个名称是唯一的，因此可以作为key；fileName是文件名，在不同的路径下有可能会重复
            map.put(absoluteName, fileName);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}