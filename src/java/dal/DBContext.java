package dal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {
      protected Connection connection;
    public DBContext()
    {
        try {
            String user = "sa"; //sửa theo cấu hình cảu mình
            String pass = "123"; // sửa theo cấu hình cảu mình
            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=medicare_booking_final"; //đổi tên DATABASE
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}
