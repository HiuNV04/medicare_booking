package dal;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MyDAO extends DBContext {

    protected Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;
    protected String xSql;

    public MyDAO() {
        this.con = connection; // lấy connection từ DBContext
    }

    /**
     * Đóng tài nguyên rs và ps sau mỗi truy vấn
     */
    public void closeResources() {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
