/**
 * class that maintaining the Email sending for subscription renewing's  
 * 
 */


package ServerControllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
/**
 * looking in the SQL for subscriptions ending in three days
 * and send them mails about renew
 */
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
			try {
			sqlStatment="SELECT email,first_name,last_name FROM purchases p,user_card u,users r WHERE p.user_id=u.user_id AND p.user_id=r.Id AND p.date_end='"+today.now().plusDays(3)+"';";
			ResultSet resultSet =sqlCommunication.getValueInSqlTable(sqlStatment);
			ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
			ArrayList<String> temp = null;
			
			System.out.println();
			System.out.print(LocalDate.now()+" , "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":  ");			
			System.out.println("Obtaining information about subscription and sending renew notifications");
				ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();
				int column = rsmd.getColumnCount();
				while (resultSet.next()) {
					temp = new ArrayList<String>();
					for (int i = 1; i <= column; i++) {
						temp.add(resultSet.getString(i));
					}
					array.add(temp);
				}
				for(int i=0;i<array.size();i++)
					sendNotificationToSubscribersNeedToRenew(array.get(i));
				if(array.size()==0)
				{
					System.out.print(LocalDate.now()+" , "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":  ");
					System.out.println("No users with subscription running out");
				}
				System.out.print(LocalDate.now()+" , "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":  ");
				System.out.println("will check renews once again tomorrow");
				
			} catch (SQLException e) {
				System.out.print(LocalDate.now()+" , "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":  ");
				System.out.println("Error getting information into arraylist");
			} catch (NullPointerException a)
			{
				System.out.print(LocalDate.now()+" , "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":  ");
				System.out.println("Null pointer Exception in E-Mail - empty E-Mail Table");
			}
			
			try {
				TimeUnit.DAYS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
			
		}
		
		
	}
	/**
	 * gets an ArrayList of Email's and sends them a renew Email message
	 * @param array of Email's
	 */
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
		String body= "<html>"
				+ "<head></head>"
				+ "<body>"
				+ "<p style=\"font-size:24px;color:blue\"><u> hello "+array.get(2)+" "+array.get(1)+ "!</u></p>"
				+ "<p style=\"font-size:20px;color:#059124\">We discovered that your subscription is about to end in just <u style=\"color:red;font-size:20px \">three days!</u></p>"
				+ "<p style=\"font-size:20px;color:#059124\"> if you wish to continue using our application please renew your subscription</p>"
				+ "</body>"
				+ "</html>";
		Email send=new Email(toSend,header,body);
		send.sendMail();
	}
}
