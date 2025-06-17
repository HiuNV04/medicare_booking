package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    protected Connection connection;

    public DBContext() {
        try {
            String user = "sa"; 
            String pass = "123456789"; 
            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=medicare_booking"; 
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // HÀM THÊM MỚI → Mở connection mới
    public Connection getConnection() throws SQLException {
        String user = "sa"; 
        String pass = "123456789"; 
        String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=medicare_booking"; 
        return DriverManager.getConnection(url, user, pass);
    }

    // Test kết nối
    public static void main(String[] args) {
        DBContext db = new DBContext();
        if (db.connection != null) {
            System.out.println("Kết nối thành công đến cơ sở dữ liệu!");
        } else {
            System.out.println("Kết nối thất bại.");
        }
    }
}
