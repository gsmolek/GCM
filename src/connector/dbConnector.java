package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class dbConnector {
	public static Connection con;
	public static void connect() {
		try 
		{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {/* handle the error*/}
        
        try 
        {
        	String dbName = "gsm";
        	String userName = "root";
        	String password = "";
        	con = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName+"?serverTimezone=IST",userName,password);
            System.out.println("SQL connection succeed");
     	} catch (SQLException ex) 
     	    {/* handle any errors*/
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            }
	}
}
