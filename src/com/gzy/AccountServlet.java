package com.gzy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

// 增
// 查
// 删
// 改
public class AccountServlet extends HttpServlet {
    // 增
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("ok");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // response.getWriter().println(username + " > " + password);
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test2019";
            String user = "root";
            String pwd = "root";
            connection = DriverManager.getConnection(url,user,pwd);

            String sql = "INSERT INTO account (`username`,`password`) VALUES(?,?)";
            statement = connection.prepareStatement(sql);// 参数化查询
            statement.setString(1,username);
            statement.setString(2,password);
            int rowNumber = statement.executeUpdate();
            response.getWriter().println("create user: " + username + " password:" + password);

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

    // 查 （多查）
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test2019";
            String user = "root";
            String pwd = "root";
            Connection connection = DriverManager.getConnection(url,user,pwd);
 
            String sql = "SELECT * FROM account;";

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                response.getWriter().println("id:" + id + " username:"+username + " password:" +password);

                response.getWriter().println("<a href='delete_account?id="+id+"'>delete</a>");
                response.getWriter().println("<a href='update_account.html?id="+id+"'>edit</a>");

                response.getWriter().println("<br>");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
