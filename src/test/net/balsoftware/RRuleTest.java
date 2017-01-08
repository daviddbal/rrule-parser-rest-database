package net.balsoftware;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.junit.BeforeClass;
import org.junit.Test;

import net.balsoftware.bean.RRule;
import net.balsoftware.service.RRuleService;
import net.balsoftware.util.ConnectionHelper;

public class RRuleTest
{
	protected static Connection connection;
	protected static RRuleService service = new RRuleService();
	
    @BeforeClass
    public static void setupTableName() throws SQLException
    {
    	connection = ConnectionHelper.getConnection();
    }
    
    @Test
    public void canMakeConnection()
    {
    	assertTrue(connection != null);
    }
    
    @Test
    public void canAddRRule() throws SQLException
    {
    	RRule test = new RRule();
    	test.setRruleContent("RRULE:FREQ=WEEKLY");
    	test.setDtstartContent("DTSTART;VALUE=DATE:20170101");
    	test.setMaxRecurrences(10);
    	test.setCreated(LocalDateTime.now());
    	service.addRRule(test);
    }
    
    @Test
    public void canCreateUser()
    {
    	String user = "rruleuser";
    	String URL = "%";
    	String password = "rrulepassword";
    	String database = "rrule";
        final String createUser =
                "CREATE USER '" + user + "'@'" + URL + "' IDENTIFIED BY '" + password + "';";
        final String grantPrivilegesToUser =
                "GRANT INSERT, SELECT, DELETE  ON " + database + ".* TO '" + user + "'@'" + URL + "';";
        System.out.println(createUser);
        System.out.println(grantPrivilegesToUser);
    }
}
