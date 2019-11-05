package com.gzy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateAccountServlet extends HttpServlet {

    // 如何传数据
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        Integer intId = Integer.valueOf(id.trim());
        String password = request.getParameter("password");

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test2019";
            String user = "root";
            String pwd = "root";
            connection = DriverManager.getConnection(url,user,pwd);

            String sql = "UPDATE account SET `password` = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);// 参数化查询
            statement.setString(1,password);
            statement.setInt(2,intId);

            int rowNumber = statement.executeUpdate();
            response.sendRedirect("account");

//            response.getWriter().println("" + rowNumber);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                statement = null;
            }

            // connet
            // connection.notnull
            if (connection != null) {
                // alt + enter
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                connection = null;
            }

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
