package serverConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class mysqlConnection {
	
	Connection conn;
	ArrayList<String> returnedCitiesName=null;
	ArrayList<String> returnedCityInfo=null;
	public void open()
	{
		try 
		{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {/* handle the error*/}
        
        try 
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/?serverTimezone=IST","root","Aa123456");//add password
            //Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.3.68/test","root","Root");
            
            System.out.println("SQL connection succeed");
     	} catch (SQLException ex) 
     	    {/* handle any errors*/
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            }
   	}
	/**Description of createSchema method
	 * 
	 * create a new schema in mySQL
	 *
	 * */
	public void createSchema()
	{
	    Statement SQLstatment;
		try {
			SQLstatment = conn.createStatement();
			SQLstatment.executeUpdate("CREATE DATABASE gcm;");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/gcm?serverTimezone=IST","root","Aa123456");
			
				System.out.println("gcm Schema created");

		} catch (SQLException e) {
			System.out.println("Schema already exists");
		}	
	}
	public void setValueInSqlTable(String query)
	{
		Statement sqlStatment;
		try {
			sqlStatment = conn.createStatement();
			sqlStatment.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("ERROR-SQL STATMENT:"+query+" "
					+ "leaded to an error in setValueInSqlTable method");
		}
		
	}
	public ResultSet getValueInSqlTable(String query)
	{
		
		try {
			Statement sqlStatment = conn.createStatement();
			ResultSet resultSet = sqlStatment.executeQuery(query);
			return resultSet;
		} catch (SQLException e) {
			System.out.println("ERROR-SQL STATMENT:"+query+" "
					+ "leaded to an error in getValueInSqlTable method");
			return null;
		}
		
		
	}
	
	public void createTables()
	{
		/**
		 * add new tables 
		 *@author matan
		 *@param query_to_add_new_table - string to add tables using query
		 *
		 */
		String query_to_add_new_table = "CREATE TABLE IF NOT EXISTS gcm.Tours (Tour_number INTEGER not null, General_description VARCHAR(255), PRIMARY KEY(Tour_number))";
		try {
			Statement SQLstatment;
			SQLstatment = conn.createStatement();
			SQLstatment.executeUpdate(query_to_add_new_table);
	     // is name the key in this table ask!! //
	     query_to_add_new_table = "CREATE TABLE IF NOT EXISTS `gcm`.`placeofintrest` (`name` VARCHAR(255) not null, `Classification` VARCHAR(255),`shortExplanation` VARCHAR(255) ,`accessibleToSpecialNeeds` VARCHAR(255),`Tour_number` INTEGER , PRIMARY KEY(name,Tour_number),FOREIGN KEY (`Tour_number`)  REFERENCES `gcm`.`tours` (`Tour_number`))";
		SQLstatment.executeUpdate(query_to_add_new_table);
		query_to_add_new_table = "CREATE TABLE IF NOT EXISTS `gcm`.`version` (`version_number` INTEGER not null, `description` VARCHAR(255),PRIMARY KEY(version_number))";
		SQLstatment.executeUpdate(query_to_add_new_table);
		query_to_add_new_table = "CREATE TABLE IF NOT EXISTS `gcm`.`city` (`name` VARCHAR(255) not null, `numberOfViews` VARCHAR(255),PRIMARY KEY(name))";
		SQLstatment.executeUpdate(query_to_add_new_table);
		query_to_add_new_table = "CREATE TABLE IF NOT EXISTS `gcm`.`versionOfMapCollection` (`confirmedVersion` VARCHAR(255),`version_number` INTEGER, FOREIGN KEY (`version_number`)  REFERENCES `gcm`.`version` (`version_number`))";
		SQLstatment.executeUpdate(query_to_add_new_table);
		query_to_add_new_table = "CREATE TABLE IF NOT EXISTS `gcm`.`placeToVisitByOrder` (`visitingTime` VARCHAR(255),`Tour_number` INTEGER,`name` VARCHAR(255), FOREIGN KEY (`Tour_number`)  REFERENCES `gcm`.`Tours` (`Tour_number`),FOREIGN KEY (`name`)  REFERENCES `gcm`.`placeofintrest` (`name`),PRIMARY KEY(name,Tour_number))";
		SQLstatment.executeUpdate(query_to_add_new_table);
		// ask team if there is other fields needed //
		query_to_add_new_table = "CREATE TABLE IF NOT EXISTS `gcm`.`PlaceOfInterestInMap` (`locationInMap` VARCHAR(255),PRIMARY KEY(locationInMap))";
		SQLstatment.executeUpdate(query_to_add_new_table);
		System.out.println("new PlaceOfInterestInMap table added");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}
	
	/*
	public void AlterTable(Object msg){
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int length = ((ArrayList<String>) msg).size();
			System.out.println(length);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO city (CityName,NumOfMAp,NumOfPoi,NumOfPTours,VersionNum)  VALUES(?,?,?,?,?);");
			
			ps.setString(1,((ArrayList<String>)msg).get(0));
			ps.setInt(2,((ArrayList<Integer>)msg).get(1));
			ps.setInt(3,((ArrayList<Integer>)msg).get(2));
			ps.setInt(4,((ArrayList<Integer>)msg).get(3));
			ps.setInt(5,((ArrayList<Integer>)msg).get(4));
			
			ps.executeUpdate();
		} catch (SQLException e) {	e.printStackTrace();}
		 		
	}
	public void UpdateCitiesName()
	{
		returnedCitiesName=new ArrayList<String>();
	try {	
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT CityName FROM city");
		while(rs.next())
		{
			returnedCitiesName.add(rs.getString("CityName"));
		}
	}catch (SQLException e) {	e.printStackTrace();}
	}
	
	public ArrayList<String> getArrayOfCitiesName()
	{
		return this.returnedCitiesName;
	}
	public void updateCityInformation(String val)
	{
		try {
		returnedCityInfo=new ArrayList<String>();
		PreparedStatement ps = conn.prepareStatement("SELECT NumOfMap FROM city WHERE CityName=?;");
		ps.setString(1,val);
		ResultSet rs=ps.executeQuery();
		rs.next();
		returnedCityInfo.add(rs.getString("NumOfMap"));
		
		 ps = conn.prepareStatement("SELECT NumOfPoi FROM city WHERE CityName=?;");
		ps.setString(1,val);
		 rs=ps.executeQuery();
		rs.next();
		returnedCityInfo.add(rs.getString("NumOfPoi"));
		
		 ps = conn.prepareStatement("SELECT NumOfTours FROM city WHERE CityName=?;");
		ps.setString(1,val);
		 rs=ps.executeQuery();
		rs.next();
		returnedCityInfo.add(rs.getString("NumOfTours"));
		
		 ps = conn.prepareStatement("SELECT VersionNum FROM city WHERE CityName=?;");
		ps.setString(1,val);
		 rs=ps.executeQuery();
		rs.next();
		returnedCityInfo.add(rs.getString("VersionNum"));
		}catch (SQLException e) {	e.printStackTrace();}
		
	}
	public void saveChanges(Object msg)
	{
		try {
			System.out.println("SQL enter save");
			System.out.println(msg);
			String version=(((ArrayList<String>)msg).get(1));
			String city=((ArrayList<String>)msg).get(2);
			PreparedStatement ps = conn.prepareStatement("UPDATE city SET VersionNum=? WHERE CityName=?;");
			ps.setString(1,version);
			ps.setString(2,city);
			ps.executeUpdate();
		}catch (SQLException e) {	e.printStackTrace();}
		
	}
	public ArrayList<String> getCityInfo()
	{
		return returnedCityInfo;
	}
	*/
}



