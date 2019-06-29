package serverConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
			System.out.print(LocalDate.now()+" , "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":  ");
            System.out.println("SQL connection succeed");
     	} catch (SQLException ex) 
     	    {/* handle any errors*/
			System.out.println("***"+LocalDate.now()+" , "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+"***");
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
			System.out.print(LocalDate.now()+" , "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":  ");
			SQLstatment.executeUpdate("CREATE DATABASE gcm;");
			//conn = DriverManager.getConnection("jdbc:mysql://localhost/gcm?serverTimezone=IST","root","Aa123456");
			connectToSQL();
			System.out.print(LocalDate.now()+" , "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":  ");
			System.out.println("gcm Schema Created");

		} catch (SQLException e) {			
			System.out.print(LocalDate.now()+" , "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":  ");
			System.out.println("Schema already exists");
			connectToSQL();
			
		}	
	}
	public void connectToSQL()
	{
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/gcm?serverTimezone=IST","root","Aa123456");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setValueInSqlTable(String query)
	{
		Statement sqlStatment;
		try {
			sqlStatment = conn.createStatement();
			sqlStatment.executeQuery(query);
		} catch (SQLException e) {
			System.out.println();
			System.out.println("***"+LocalDate.now()+" , "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+"***");
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
			System.out.println();
			System.out.println("***"+LocalDate.now()+" , "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+"***");
			System.out.println("ERROR-SQL STATMENT:"+query+" "
					+ "leaded to an error in getValueInSqlTable method");
			return null;
		}
		
		
	}
	public void  insertIntoSql(String query) {
		PreparedStatement sqlStatment;
		try {
			sqlStatment = conn.prepareStatement(query);
			sqlStatment.executeUpdate();
		} catch (SQLException e) {
			System.out.println("***"+LocalDate.now()+" , "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+"***");
			System.out.println("\n"+ e + "\n");
			System.out.println("ERROR-SQL STATMENT:"+query+" "
					+ "leaded to an error in insertIntoSql method");
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
		
		try {
			String query_to_add_new_table;
			Statement SQLstatment;
			SQLstatment = conn.createStatement();
			
			query_to_add_new_table = 
					" CREATE TABLE IF NOT EXISTS `city` (`Id` int(11) NOT NULL AUTO_INCREMENT,`name` varchar(255) DEFAULT NULL, PRIMARY KEY (`Id`)) ;"; 
					
			SQLstatment.executeUpdate(query_to_add_new_table);
			query_to_add_new_table = 
					"CREATE TABLE IF NOT EXISTS`download` (\r\n" + 
					"  `date` date NOT NULL DEFAULT '0000-00-00',\r\n" + 
					"  `user_id` int(11) unsigned NOT NULL DEFAULT '0',\r\n" + 
					"  `collaction_id` int(11) unsigned NOT NULL DEFAULT '0',\r\n" + 
					"  `time` time NOT NULL DEFAULT '00:00:00',\r\n" + 
					"  `id` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
					"  PRIMARY KEY (`id`)\r\n" + 
					") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;" + 
					"";
			SQLstatment.executeUpdate(query_to_add_new_table);
			
			query_to_add_new_table = 
					"CREATE TABLE IF NOT EXISTS `employee` (\r\n" + 
					"  `employee_no` int(11) unsigned NOT NULL DEFAULT '0',\r\n" + 
					"  `first_name` varchar(255) NOT NULL DEFAULT '',\r\n" + 
					"  `last_name` varchar(255) NOT NULL DEFAULT '',\r\n" + 
					"  `job_title` varchar(255) NOT NULL DEFAULT '',\r\n" + 
					"  `password` varchar(255) NOT NULL DEFAULT '',\r\n" + 
					"  `user_name` varchar(255) NOT NULL DEFAULT '',\r\n" + 
					"  PRIMARY KEY (`employee_no`),\r\n" + 
					"  UNIQUE KEY `user_name` (`employee_no`)\r\n" + 
					") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;" + 
					"";
			SQLstatment.executeUpdate(query_to_add_new_table);
			
			query_to_add_new_table = 
					"CREATE TABLE IF NOT EXISTS `map_mapcollection` (\r\n" + 
					"  `Id` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
					"  `map_id` int(11) unsigned DEFAULT NULL,\r\n" + 
					"  `collection_id` int(11) unsigned DEFAULT NULL,\r\n" + 
					"  PRIMARY KEY (`Id`)\r\n" + 
					") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;" + 
					"";
			SQLstatment.executeUpdate(query_to_add_new_table);
			
			
			query_to_add_new_table =  
					"CREATE TABLE IF NOT EXISTS`map` (\r\n" + 
					"  `Id` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
					"  `city_id` int(11) unsigned NOT NULL DEFAULT '0',\r\n" + 
					"  `description` varchar(255) DEFAULT NULL,\r\n" + 
					"  `id_collaction` varchar(255) DEFAULT NULL,\r\n" + 
					"  `path` varchar(255) NOT NULL DEFAULT '',\r\n" + 
					"  PRIMARY KEY (`Id`)\r\n" + 
					") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;" + 
					"";
			SQLstatment.executeUpdate(query_to_add_new_table);
			
			query_to_add_new_table =
					"CREATE TABLE IF NOT EXISTS `map_collection` (\r\n" + 
					"  `Id` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
					"  `vertion` double(4,1) unsigned NOT NULL DEFAULT '0.0',\r\n" + 
					"  `oneTimePrice` int(11) unsigned NOT NULL DEFAULT '0',\r\n" + 
					"  `approved` tinyint(1) unsigned NOT NULL DEFAULT '0',\r\n" + 
					"  `subscriptionPrice` int(11) unsigned DEFAULT NULL,\r\n" + 
					"  `userName` varchar(255) DEFAULT NULL,\r\n" + 
					"  `old_collection` int(11) unsigned DEFAULT NULL,\r\n" + 
					"  PRIMARY KEY (`Id`)\r\n" + 
					") ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;" + 
					"";
			SQLstatment.executeUpdate(query_to_add_new_table);
			
			query_to_add_new_table =
					"CREATE TABLE IF NOT EXISTS `map_mapcollection` (\r\n" + 
					"  `Id` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
					"  `map_id` int(11) unsigned DEFAULT NULL,\r\n" + 
					"  `collection_id` int(11) unsigned DEFAULT NULL,\r\n" + 
					"  PRIMARY KEY (`Id`)\r\n" + 
					") ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;" + 
					"";
			SQLstatment.executeUpdate(query_to_add_new_table);
			
			query_to_add_new_table =  
					"CREATE TABLE IF NOT EXISTS `map_site` (\r\n" + 
					"  `map_id` int(11) unsigned NOT NULL DEFAULT '0',\r\n" + 
					"  `site_id` int(11) unsigned NOT NULL DEFAULT '0'\r\n" + 
					") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;" + 
					"";
			SQLstatment.executeUpdate(query_to_add_new_table);
			
			query_to_add_new_table =  
					"CREATE TABLE IF NOT EXISTS `purchases` (\r\n" + 
					"  `user_id` int(11) unsigned NOT NULL DEFAULT '0',\r\n" + 
					"  `collaction_id` int(11) unsigned NOT NULL DEFAULT '0',\r\n" + 
					"  `date_buy` date NOT NULL DEFAULT '0000-00-00',\r\n" + 
					"  `date_end` date NOT NULL DEFAULT '0000-00-00',\r\n" + 
					"  `type_of_purchases` tinyint(1) unsigned NOT NULL DEFAULT '0',\r\n" + 
					"  `renew` tinyint(1) unsigned NOT NULL DEFAULT '0',\r\n" + 
					"  `id` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
					"  PRIMARY KEY (`id`)\r\n" + 
					") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;" + 
					"";
			SQLstatment.executeUpdate(query_to_add_new_table);
			
			query_to_add_new_table =  
					"CREATE TABLE IF NOT EXISTS `site` (\r\n" + 
					"  `Id` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
					"  `type` varchar(255) NOT NULL DEFAULT '',\r\n" + 
					"  `description` varchar(255) NOT NULL DEFAULT '',\r\n" + 
					"  `accessible` tinyint(1) unsigned NOT NULL DEFAULT '0',\r\n" + 
					"  `time` double(4,2) unsigned NOT NULL DEFAULT '0.00',\r\n" + 
					"  `location_x` double unsigned NOT NULL DEFAULT '0',\r\n" + 
					"  `location_y` double unsigned NOT NULL DEFAULT '0',\r\n" + 
					"  PRIMARY KEY (`Id`)\r\n" + 
					") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;" + 
					"";
			SQLstatment.executeUpdate(query_to_add_new_table);
			
			query_to_add_new_table = 
					"CREATE TABLE IF NOT EXISTS `site_tour` (\r\n" + 
					"  `site_id` int(11) unsigned NOT NULL DEFAULT '0',\r\n" + 
					"  `tour_id` int(11) unsigned NOT NULL DEFAULT '0'\r\n" + 
					") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;" + 
					"";
			SQLstatment.executeUpdate(query_to_add_new_table);
			
			query_to_add_new_table =  
					"CREATE TABLE IF NOT EXISTS `tours` (\r\n" + 
					"  `Id` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
					"  `description` varchar(255) DEFAULT NULL,\r\n" + 
					"  PRIMARY KEY (`Id`)\r\n" + 
					") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;" + 
					"";
			SQLstatment.executeUpdate(query_to_add_new_table);
			
			
			query_to_add_new_table =  
					"CREATE TABLE IF NOT EXISTS`users` (\r\n" + 
					"  `Id` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
					"  `password` varchar(255) NOT NULL DEFAULT '',\r\n" + 
					"  `first_name` varchar(255) NOT NULL DEFAULT '',\r\n" + 
					"  `last_name` varchar(255) NOT NULL DEFAULT '',\r\n" + 
					"  `login` varchar(255) NOT NULL DEFAULT '',\r\n" + 
					"  `user_name` varchar(255) NOT NULL DEFAULT '',\r\n" + 
					"  PRIMARY KEY (`Id`),\r\n" + 
					"  UNIQUE KEY `user_name` (`Id`),\r\n" + 
					"  UNIQUE KEY `email` (`Id`)\r\n" + 
					") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;" + 
					"";
			SQLstatment.executeUpdate(query_to_add_new_table);
			
			
			query_to_add_new_table =  
					"CREATE TABLE IF NOT EXISTS `views` (\r\n" + 
					"  `user_id` int(11) unsigned NOT NULL DEFAULT '0',\r\n" + 
					"  `collaction_id` int(11) unsigned NOT NULL DEFAULT '0',\r\n" + 
					"  `date` date NOT NULL DEFAULT '0000-00-00',\r\n" + 
					"  `time` time NOT NULL DEFAULT '00:00:00',\r\n" + 
					"  `map_id` int(11) unsigned NOT NULL DEFAULT '0'\r\n" + 
					") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;" + 
					"";
			SQLstatment.executeUpdate(query_to_add_new_table);
			
			
			query_to_add_new_table =  
					"CREATE TABLE IF NOT EXISTS `employee_card` (\r\n" + 
					"  `user_id` int(11) unsigned NOT NULL DEFAULT '0',\r\n" + 
					"  `employee_no` int(11) unsigned NOT NULL DEFAULT '0',\r\n" + 
					"  `job_title` varchar(255) NOT NULL DEFAULT '',\r\n" + 
					"  `permission` tinyint(1) NOT NULL DEFAULT '0'\r\n" + 
					") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;" + 
					"";
			SQLstatment.executeUpdate(query_to_add_new_table);
			
			
			query_to_add_new_table =  
					"CREATE TABLE IF NOT EXISTS `user_card` (\r\n" + 
					"  `email` varchar(255) NOT NULL DEFAULT '',\r\n" + 
					"  `user_id` int(11) NOT NULL DEFAULT '0',\r\n" + 
					"  `phone` varchar(255) NOT NULL DEFAULT '',\r\n" + 
					"  `creditcard` varchar(255) NOT NULL DEFAULT '',\r\n" + 
					"  PRIMARY KEY (`user_id`)\r\n" + 
					") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;" + 
					"";
			SQLstatment.executeUpdate(query_to_add_new_table);
			
		
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



