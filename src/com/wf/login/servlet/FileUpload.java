package com.wf.login.servlet;



import com.wf.login.common.utils.DBUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/FileUpload")
@MultipartConfig
public class FileUpload extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        final Part filePart = request.getPart("file");
        String id = request.getParameter("id");

        InputStream FileBytes = null;
        final PrintWriter writer = response.getWriter();
        Connection con = null;
        Statement stmt = null;

        try {

            //if (!filePart.getContentType().equals("image/jpeg"))
            //  {
            //             writer.println("<br/> Invalid File");
            //             return;
            //  }
            // else if (filePart.getSize()>1048576 ) { //2mb
            //     {
            //    writer.println("<br/> File size too big");
            //    return;
            //     }
            // }

            //String filename = filePart.getSubmittedFileName();

            String cd = filePart.getHeader("Content-Disposition");
              //截取不同类型的文件需要自行判断
           //String filename = cd.substring(cd.lastIndexOf("=")+2, cd.length()-1);

            String filename = cd;

            FileBytes = filePart.getInputStream(); // to get the body of the request as binary data

            try {

                con= DBUtil.getConnection();
            } catch (Exception e) {
                System.out.println(e);
                System.exit(0);
            }
            int success = 0;
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO upload VALUES(?,?,?)");
            pstmt.setString(1, id);
            pstmt.setBinaryStream(2, FileBytes); //Storing binary data in blob field.
            pstmt.setString(3, filename); //Storing binary data in blob field.
            success = pstmt.executeUpdate();
            if (success >= 1) {
                System.out.println("Data Stored");
            }
            con.close();

            writer.println("<br/> File Successfully Uploaded<br/><a href='.'>return</a>");

        } catch (FileNotFoundException fnf) {
            writer.println("You  did not specify a file to upload");
            writer.println("<br/> ERROR: " + fnf.getMessage());

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (con != null) {
                // closes the database connection
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            if (FileBytes != null) {
                FileBytes.close();
            }
            if (writer != null) {
                writer.close();
            }
        }

    }

}
