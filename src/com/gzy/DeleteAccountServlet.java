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

public class DeleteAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    // 需要知道删什么
    // 从 数据列表传过来
    //    id
    // 开始进行删除
    //    1. sql 改为 删除的
    //    2. 把参数传进去
    // 重定向到 数据列表页面
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Integer intId = Integer.valueOf(id.trim());
        response.getWriter().println(id);
        // 删除 等于参数id 的数据

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test2019";
            String user = "root";
            String pwd = "root";
            connection = DriverManager.getConnection(url,user,pwd);

            String sql = "DELETE FROM account WHERE id = ?";
            statement = connection.prepareStatement(sql);// 参数化查询
            statement.setInt(1,intId);

            int rowNumber = statement.executeUpdate();
            response.sendRedirect("account");

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
}
