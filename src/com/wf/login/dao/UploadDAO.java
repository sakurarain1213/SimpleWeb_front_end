package com.wf.login.dao;

import com.wf.login.common.utils.DBUtil;
import com.wf.login.servlet.Upload;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UploadDAO {

 /*   public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                    "root", "root");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }*/

    public static List<Upload> listAllUploads() throws SQLException {

        List<Upload> listUpload = new ArrayList<>();

        String sql = "SELECT id,filename FROM upload";

        Connection jdbcConnection = DBUtil.getConnection();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {

            String id = resultSet.getString("id");
            String filename = resultSet.getString("filename");

            Upload upload = new Upload(id, filename);
            listUpload.add(upload);
        }

        resultSet.close();
        statement.close();

        jdbcConnection.close();

        return listUpload;
    }

}