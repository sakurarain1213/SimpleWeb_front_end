package com.wf.login.servlet;
//中文

import com.wf.login.common.utils.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URLEncoder;
import java.io.InputStream;

/**
 * Servlet implementation class GetDetails
 */
@WebServlet("/DBFileDownload")
public class DBFileDownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String id = request.getParameter("id") != null ? request.getParameter("id") : "NA";

        ServletOutputStream sos;
        Connection con = null;
        PreparedStatement pstmt = null;




        sos = response.getOutputStream();




        ResultSet rset = null;
        try {
            try {
                con = DBUtil.getConnection();
            } catch (Exception e) {
                System.out.println(e);
                System.exit(0);
            }
            pstmt = con.prepareStatement("Select file,filename from upload where id=?");
            System.out.println("Select file,filename from upload where id=" + id.trim());
            pstmt.setString(1, id.trim());
            rset = pstmt.executeQuery();
            if (rset.next()) {
                response.setContentType("APPLICATION/OCTET-STREAM");

                response.setHeader("Content-disposition", "inline; filename*=UTF-8''" + URLEncoder.encode(rset.getString("filename"), "UTF-8"));

                InputStream inputStream = rset.getBinaryStream("file");

                int i;
                while ((i = inputStream.read()) != -1) {
                    sos.write(i);
                }

                System.out.println(rset.getBytes("file"));
                System.out.println(rset.getString("filename"));
            } else
                return;

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

        }

        sos.flush();
        sos.close();

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}