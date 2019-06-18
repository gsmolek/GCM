package ServerControllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import GUI.GUIcontroller;
import ocsf.server.ConnectionToClient;
import serverConnection.*;

public class RenewSubscriptionController extends Thread {
	GUIcontroller a;
	mysqlConnection sqlCommunication;
	String date;
	 
	LocalDate today;
	String sqlStatment;
	
	public RenewSubscriptionController(GUIcontroller a,mysqlConnection sqlCommunication)
	{
		this.a=a;
		this.sqlCommunication=sqlCommunication;
	}

	@Override
	public void run() {
		
		while(true)
		{
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			sqlStatment="SELECT email,first_name,last_name FROM purchases p,user_card u,users r WHERE p.user_id=u.user_id AND p.user_id=r.Id AND p.date_end='"+today.now().plusDays(3)+"';";
			ResultSet resultSet =sqlCommunication.getValueInSqlTable(sqlStatment);
			ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
			ArrayList<String> temp = null;
			
			try {
				ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();
				
				int column = rsmd.getColumnCount();
				while (resultSet.next()) {
					temp = new ArrayList<String>();
					for (int i = 1; i <= column; i++) {
						temp.add(resultSet.getString(i));
					}
					array.add(temp);
				}
				
				System.out.println(array);
				for(int i=0;i<array.size();i++)
					sendNotificationToSubscribersNeedToRenew(array.get(i));
				
			} catch (SQLException e) {
				System.out.println("Error getting information into arraylist");
			} 
			
			try {
				TimeUnit.DAYS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}
		
		
	}
	public void sendNotificationToSubscribersNeedToRenew(ArrayList<String> array)
	{
		String[] toSend=new String[1];

		toSend[0]=array.get(0);
		for(int i=0;i<1;i++)
		{
			String a=toSend[i];
			System.out.println(a);;
		}
		String header="Hello "+array.get(2)+" "+array.get(1)+" i'm from GCM!";
		String body=""+array.get(2)+" "+array.get(1)+" We dicover that your subscription is about to end in just three days! "
				+ "if you wish to continue using our application please renew your subscription";
		Email send=new Email(toSend,header,body);
		send.sendMail();
	}
}
