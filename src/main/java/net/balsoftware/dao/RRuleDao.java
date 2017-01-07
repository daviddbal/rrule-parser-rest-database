package net.balsoftware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.balsoftware.bean.RRule;
import net.balsoftware.util.ConnectionHelper;

//  add/update/delete/select Entity objects into/from database and that's all.
public class RRuleDao {

    public List<RRule> getAllRRules() {
        List<RRule> list = new ArrayList<RRule>();
        Connection c = null;
    	String sql = "SELECT * FROM rrule.history ORDER BY created";
        try {
            c = ConnectionHelper.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return list;
    }
    
	public RRule getRRule(int uid)
	{
        Connection c = null;
        RRule rrule = null;
    	String sql = "SELECT * FROM rrule.history where uid = ?";
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                rrule = processRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return rrule;
	}
	
	public RRule addRRule(RRule rrule)
	{
        String sql = "INSERT INTO rrule.history (rruleContent, dtstartContent, maxRecurrences, created) VALUES (?, ?, ?, ?)";
        Connection c = null;
        System.out.println("GET CONNECTION1");
        try {
            c = ConnectionHelper.getConnection();
            System.out.println("GET CONNECTION2");
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, rrule.getRruleContent());
            ps.setString(2, rrule.getDtstartContent());
            ps.setInt(3, rrule.getMaxRecurrences());
            Timestamp timestamp = Timestamp.valueOf(rrule.getCreated());
            ps.setTimestamp(4, timestamp);
            
            ps.executeUpdate();
//            ResultSet rs = ps.getGeneratedKeys();
//            rs.next();
            // Update the id in the returned object. This is important as this value must be returned to the client.
//            int id = rs.getInt(1);
//            wine.setId(id);
            
//            System.out.println("ps:"+ps);
//            ResultSet rs = ps.executeQuery(sql);
//            while (rs.next()) {
//                rrule = processRow(rs);
//            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return rrule;		
	}
	
	public RRule updateRRule(RRule rrule)
	{
        String sql = "UPDATE rrule.history SET rruleContent=?, dtstartContent=?, maxRecurrences=?, created=? WHERE uid=?";
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, rrule.getRruleContent());
            ps.setString(2, rrule.getDtstartContent());
            ps.setInt(3, rrule.getMaxRecurrences());
            Timestamp timestamp = Timestamp.valueOf(rrule.getCreated());
            ps.setTimestamp(4, timestamp);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return rrule;		
	}
	
	public boolean deleteRRule(int uid)
	{
        String sql = "DELETE FROM rrule.history WHERE uid=?";
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, uid);
            int count = ps.executeUpdate();
            return count == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}		
	}
	
    private RRule processRow(ResultSet rs) throws SQLException {
    	RRule rrule = new RRule();
    	rrule.setRruleContent(rs.getString("rruleContent"));
    	rrule.setDtstartContent(rs.getString("dtstartContent"));
    	rrule.setMaxRecurrences(rs.getInt("maxRecurrences"));
    	Date date = rs.getDate("created");
    	rrule.setCreated(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
        return rrule;
    }
}
