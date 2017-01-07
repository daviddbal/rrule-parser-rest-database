package net.balsoftware.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnection {

	public static void main(String[] args) throws SQLException {
		Connection c = ConnectionHelper.getConnection();
        System.out.println("GOT CONNECTION");
		String sql = "SELECT * FROM rrule.history";
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(2));
        }
	}

}
