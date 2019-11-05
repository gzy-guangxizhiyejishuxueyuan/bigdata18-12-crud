import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class JDBCServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }


        String url = "jdbc:mysql://localhost:3306/test2019";
        String user = "root";
        String dbpassword = "root";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, dbpassword);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        String sql = "SELECT * FROM account;";
        Statement statement;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                response.getWriter().println("id:" + id + " username:"+username + " password:" +password);
                response.getWriter().println("<br>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        response.getWriter().println("JDBC ok");
    }
}
