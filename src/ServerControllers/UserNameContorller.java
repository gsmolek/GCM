package ServerControllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserNameContorller {
	private ArrayList<String> checkUserName=null;
	private ArrayList<String> checkPassword=null;
	
	public UserNameContorller(ResultSet setOfUserName) throws SQLException
	{
		this.checkUserName=(ArrayList<String>) setOfUserName.getArray(1);
		this.checkPassword=(ArrayList<String>) setOfUserName.getArray(2);
	}
	public boolean checkUsername(String enteredUsername)
	{
		String realUsername=checkUserName.get(0);
		if(realUsername.equals(enteredUsername))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean checkPassword(String enteredPassword)
	{
		String realPassword=checkPassword.get(0);
		if(realPassword.equals(enteredPassword))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
