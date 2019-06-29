/**
 * @author GILAD MOLEK
 * @author DORON TUCHMAN
 * @author MATI HALFA
 * @author MATAN ASULIN
 * @author SHARONE BURSHTIEN
 *
 *	@version 1.40
 *	@since 2019
 */
package serverConnection;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.sun.accessibility.internal.resources.accessibility;

import java.io.*;

import ocsf.server.*;
import GUI.*;
import ServerControllers.ImageStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EchoServer extends AbstractServer {

	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5550;
	/**
	 * The port the user selected
	 */
	private static String portFromUser;
	private static ArrayList<String> listOfCons=new ArrayList<String>();
	private static ArrayList<String> listOfConsToGCM;
	private Thread[] threads;
	private static GUIcontroller gui;
	
	/**
	 * 
	 * @return ArrayList of the connected clients
	 */
	public static ArrayList<String> getListOfConsToGCM() {
		return listOfConsToGCM;
	}
	
	/**
	 * 
	 * @return list of clients that have been in contact with the server (asking for information or updating information)
	 */
	public static ArrayList<String> getListOfCons() {
		return listOfCons;
	}
	/**
	 * set the connection to server ArrayList
	 * @param listOfCons array of Strings
	 */
	public static void setListOfCons(ArrayList<String> listOfCons) {
		EchoServer.listOfCons = listOfCons;
	}

	mysqlConnection MySQLConnection = new mysqlConnection();
	/**
	 * getter of the mySQLConnection
	 * @return MySQLConnection the connection with the SQL database
	 */
	public mysqlConnection getMySQLConnection() {
		return MySQLConnection;
	}
/**
 * constructor of the EchoServer
 * @param port get the integer port
 */
	public EchoServer(int port) {
		super(port);
	}


	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg   gets an ArrayList Of Objects, the first Object is a String of the switch case number
	 * @param client The connection from which the message originated.
	 */
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		ArrayList<Object> getArrayFromClient = (ArrayList<Object>) msg;
		String operation = (String) getArrayFromClient.get(0);
		int arrayLength = getArrayFromClient.size();
		InetAddress clientDataInformation= (InetAddress)getArrayFromClient.get(getArrayFromClient.size()-1);
		if(!listOfCons.contains(clientDataInformation.getHostAddress()))
		{
			listOfCons.add(client.getInetAddress().getHostAddress());
			System.out.print(LocalDate.now()+" , "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":  ");
			System.out.println("Connection created with "+client.getInetAddress().getHostName()+" , ip: "+client.getInetAddress().getHostAddress());
		}
		/**
		 * switch index:
		 * 1-
		 * 2-get info from SQL table 
		 * 3-edit table 
		 * 4-Error
		 * 5-get image as Object
		 * 6-password recovery to mail
		 */
		switch (operation) {
		/**
		 * case "1":
		 * set a value into the database table
		 * the ArrayList Second Object String SQL query
		 * the ArrayList Third Object is from InetAddress type
		 */
		case "1": {
			String query = (String) getArrayFromClient.get(arrayLength);
			MySQLConnection.setValueInSqlTable(query);

			break;
		}
		case "2": {
			/**
			 * case "2":
			 * get a value from the database table the return value is ArrayList<ArrayList<String>>
			 * ArrayList Second Object String SQL query
			 * the ArrayList Third Object is from InetAddress type
			 * 
			 * 	================================
			 * |      ***GET INDEX***			|
			 * |0-"2"							|
			 * |1-String SQL Query				|
			 * |2-InetAddress					|
			 *  ================================
			 * 
			 * creates a new Array to be send to client ArrayList<Object> type
			 * the first object in the return ArrayList is "2"
			 * the second object in the return ArrayList is ArrayList<ArrayList<String>> type
			 * 
			 * 	================================
			 * |      ***RETURN INDEX***		|
			 * |0-"2"							|
			 * |1-ArrayList<ArrayList<String>>	|
			 *  ================================
			 */
			String query = (String) getArrayFromClient.get(1);
			ResultSet resultSet = MySQLConnection.getValueInSqlTable(query);
			ArrayList<Object> toClient = new ArrayList<Object>();
			
			clientDataInformation = (InetAddress)getArrayFromClient.get(2);
			
			System.out.print(LocalDate.now()+" , "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":  ");
			System.out.println("Information Request: ");
			System.out.println("	User Name :"+clientDataInformation.getHostName()+
					" User IP: "+ clientDataInformation.getHostAddress()+" Trying to get information from DataBase");
			toClient.add("2");
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
				toClient.add(array);

				Thread thread = Thread.currentThread();
				((ConnectionToClient)thread).sendToClient(toClient);
				
			} catch (SQLException e) {
				System.out.println("	Error getting information into arraylist");
			} catch (IOException e) {
				System.out.println("	No Such DataBase");
			}
			break;
		}
		case "3":
			/**
			 * case "3":
			 * Table Alteration :
			 * set a value into the database table
			 * ArrayList Second Object String SQL query
			 * the ArrayList Third Object is from InetAddress type
			 * 	================================
			 * |      ***GET INDEX***			|
			 * |0-"3"							|
			 * |1-String SQL Query				|
			 * |2-InetAddress					|
			 *  ================================
			 * 
			 */
			System.out.print(LocalDate.now()+" , "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":  ");
			System.out.println("Modification Request:");
			String query = (String) getArrayFromClient.get(1);
			clientDataInformation = (InetAddress)getArrayFromClient.get(2);
			System.out.println("	User Name :"+clientDataInformation.getHostName()+
					" User IP: "+ clientDataInformation.getHostAddress()+" Trying to modify DataBase");
			MySQLConnection.insertIntoSql(query);

			break;
		case "5":
		{
			/**
			 * case "3":
			 * Image Transfer :
			 * get a name of an image, finds the image from database and send it back
			 * ArrayList Second Object String of the image name including its type
			 * the ArrayList Third Object is from InetAddress type
			 * 	================================
			 * |      ***GET INDEX***			|
			 * |0-"7"							|
			 * |1-String image name.file		|
			 * |2-InetAddress					|
			 *  ================================
			 * 
			 * creates a new Array to be send to client ArrayList<Object> type
			 * the first object in the return ArrayList is "5"
			 * the second object in the return ArrayList is the image as byte[] type
			 * 
			 * 	================================
			 * |      ***RETURN INDEX***		|
			 * |0-"5"							|
			 * |1-byte[]						|
			 *  ================================
			 * 
			 */
			System.out.print(LocalDate.now()+" , "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":  ");
			System.out.println("Image Request: ");
			try {
			String fileName=(String) getArrayFromClient.get(1);
			String filePath="C:\\maps\\"+fileName;
			clientDataInformation = (InetAddress)getArrayFromClient.get(2);
			
			File newFile = new File(filePath.trim());
			byte[] imageByte= FileUtils.readFileToByteArray(newFile);
			System.out.println("	server sends file: "+fileName+" , size: "+imageByte.length+" bytes");
			System.out.println("	File Path: "+filePath);
			System.out.println("	Sending to Client Name : "+clientDataInformation.getHostName()+", Client IP: "+clientDataInformation.getHostAddress());
			
			ArrayList<Object> toClient = new ArrayList<Object>();
			toClient.add("5");
			//toClient.add(imgToSend);
			toClient.add(imageByte);

			Thread thread = Thread.currentThread();
			((ConnectionToClient)thread).sendToClient(toClient);
			} catch (IOException e) {
				System.out.println("	No Such file");
			}
			
			
			break;
		}
		case "6":
		{
			/**
			 * case "3":
			 * Image Transfer :
			 * get an email as string check if the email exists if so, end recovered password to the email
			 * 	================================
			 * |      ***GET INDEX***			|
			 * |0-"6"							|
			 * |1-String Email					|
			 * |2-InetAddress					|
			 *  ================================
			 * 
			 * creates a new Array to be send to client ArrayList<Object> type
			 * the first object in the return ArrayList is "5"
			 * the second object in the return ArrayList is the image as byte[] type
			 * 
			 * if the email was found: creates a new ArrayList to send to the client
			 * 	the first object is "2"
			 * 	the second object is ArrayList<ArrayList<String>> type inside it the email wiil be
			 * else, send null
			 * 
			 * 	================================
			 * |      ***RETURN INDEX***		|
			 * |0-"2"							|
			 * |1-ArrayList<ArrayList<String>>	|
			 *  ================================
			 */
			System.out.println("Enter 6");
			String email = (String) getArrayFromClient.get(1);
			System.out.println(email);
			String sql="SELECT email,first_name,last_name,user_name,password FROM purchases p,user_card u,users r WHERE p.user_id=u.user_id AND p.user_id=r.Id AND u.email='"+email+"';";
			ArrayList<Object> toClient = new ArrayList<Object>();
			ResultSet resultSet = MySQLConnection.getValueInSqlTable(sql);
			
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
				System.out.println(array.get(0));
				toClient.add("2");
				toClient.add(array);
				Thread thread = Thread.currentThread();
				((ConnectionToClient)thread).sendToClient(toClient);

				if(!array.isEmpty() && array!=null)
				{
					String[] a=new String[1];
					a[0]=email;
					ArrayList<String> toSend=array.get(0);
					String header=toSend.get(1)+" "+toSend.get(2)+" "+"Password Recovery Request";
					String body= "<html>"
							+ "<head></head>"
							+ "<body>"
							+ "<p style=\"font-size:24px;color:blue\"><u> hello "+toSend.get(2)+" "+toSend.get(1)+ "!</u></p>"
							+ "<p style=\"font-size:20px;color:#059124\">Your Username : <u style=\"color:red;font-size:20px \">"+toSend.get(3)+"</u></p>"
							+ "<p style=\"font-size:20px;color:#059124\">Your Password : <u style=\"color:red;font-size:20px \">"+toSend.get(4)+"</u></p>"
							+ "</body>"
							+ "</html>";
					Email newmail=new Email(a,header,body);
					newmail.sendMail();
					System.out.println(a[0]);

				}

			} catch (SQLException e) {
				System.out.println("	Error getting information into arraylist");
			}
			catch(IOException e)
			{
				System.out.println("	Can't send to Client");
			}
			
			break;
		}
		case "7":
		{
			/**
			 * case "3":
			 * Email sender :
			 * 
			 * gets at second object ArrayList<String> of emails
			 * at third object header
			 * at the fourth object body
			 * at fifth  object  InetAddress
			 * 	================================
			 * |      ***GET INDEX***			|
			 * |0-"7"							|
			 * |1-ArrayList<String> Emails		|
			 * |2-String header					|
			 * |3-String body					|
			 * |4-InetAddress					|
			 *  ================================
			 * then sent to all emails the header and body
			 * 

			 */
			System.out.println("Enter case 7");
			ArrayList<String> email =  (ArrayList<String>) getArrayFromClient.get(1);
			String[] a=new String[email.size()];
			for(int i=0;i<a.length;i++)
			{
				a[i]=email.get(i);
			}
			String header=(String) getArrayFromClient.get(2);
			String body= (String) getArrayFromClient.get(3);
					Email newmail=new Email(a,header,body);
					newmail.sendMail();
					System.out.println("Sent emails to:");
					for(int i=0;i<a.length;i++)
					{
						System.out.print(a[i]+" , ");
					}
				}
			break;
		}
	}
	
/**
 * 
 * @return ArrayList<String> of user_name of all connected people in case of error returns null
 */
	public ArrayList<String> ActiveConnection()
	{
		String sql="SELECT user_name fROM users u WHERE u.login='1';";
		ResultSet resultSet=this.MySQLConnection.getValueInSqlTable(sql);
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
			if(array!=null && !array.isEmpty())
			{
				listOfConsToGCM=array.get(0);
				return array.get(0);
			}
		} catch (SQLException e) {
		System.out.println("	Error getting information into arraylist");
		}
		return null;
	}
	/**
	 * prints all the IP addresses of the connected clients
	 */
	public void printGcmConnectionList()
	{
		Thread[] thread=this.getClientConnections();
		for(int i=0;i<thread.length;i++)
		{
			System.out.print(((ConnectionToClient)thread[i]).getInetAddress().getHostAddress()+", ");
		}
	}
	/**
	 * check if a client is connected
	 * @param ip as String
	 * @return true if connected
	 */
	public boolean checkIfConnected(String ip)
	{
		Thread[] thread=this.getClientConnections();
		for(int i=0;i<thread.length;i++)
		{
			if(ip.equals(((ConnectionToClient)thread[i]).getInetAddress().getHostAddress()))
			{
				return true;
			}
			
		}
		return false;
	}
/**
 * method creates a connection with the SQL database
 * if not exists create a schema 'gcm' and creates all tables
 */
	protected void serverStarted() {
		System.out.print(LocalDate.now()+" , "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":  ");
		System.out.println("Server listening for connections on port " + getPort());
		MySQLConnection.open();
		MySQLConnection.createSchema();
		MySQLConnection.createTables();
	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		System.out.print(LocalDate.now()+" , "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":  ");
		System.out.println("Server has stopped listening for connections.");
	}

	// Class methods ***************************************************

	/**
	 * This method is responsible for the creation of the server instance 
	 */

	public static void startServer() {

		int port = 0; // Port to listen on
		try {
			port = Integer.parseInt(portFromUser); // Get port from command line
			System.out.println("Created Port :" + port);
		} catch (Throwable t) {
			port = DEFAULT_PORT; // Set port to 5555
			System.out.println("Created Port :" + port);
		}

		EchoServer sv = new EchoServer(port);

		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - 111Could not listen for clients!");
		}
	}
}
//End of EchoServer class
