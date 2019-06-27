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
	private static String portFromUser;
	private static ArrayList<String> listOfCons=new ArrayList<String>();
	private static ArrayList<String> listOfConsToGCM;
	private Thread[] threads;
	private static GUIcontroller gui;
	public static ArrayList<String> getListOfConsToGCM() {
		return listOfConsToGCM;
	}
	
	public static void setListOfConsToGCM(ArrayList<String> listOfConsToGCM) {
		EchoServer.listOfConsToGCM = listOfConsToGCM;
	}

	public static ArrayList<String> getListOfCons() {
		return listOfCons;
	}

	public static void setListOfCons(ArrayList<String> listOfCons) {
		EchoServer.listOfCons = listOfCons;
	}

	mysqlConnection MySQLConnection = new mysqlConnection();

	public mysqlConnection getMySQLConnection() {
		return MySQLConnection;
	}

	public EchoServer(int port) {
		super(port);
	}

	private void arrangeResultset(ResultSet rs) {

	}

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 */
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		ArrayList<Object> getArrayFromClient = (ArrayList<Object>) msg;
		String operation = (String) getArrayFromClient.get(0);
		int arrayLength = getArrayFromClient.size();
		InetAddress clientDataInformation= (InetAddress)getArrayFromClient.get(2);
		if(!listOfCons.contains(clientDataInformation.getHostAddress()))
		{
			listOfCons.add(client.getInetAddress().getHostAddress());
			System.out.print(LocalDate.now()+" , "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":  ");
			System.out.println("Connection created with "+client.getInetAddress().getHostName()+" , ip: "+client.getInetAddress().getHostAddress());
		}
		/**
		 * 1-
		 * 2-get info from SQL table 
		 * 3-edit table 
		 * 4-Error
		 * 5-get image as Object
		 */
		switch (operation) {
		case "1": {
			String query = (String) getArrayFromClient.get(arrayLength);
			MySQLConnection.setValueInSqlTable(query);

			break;
		}
		case "2": {
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
			System.out.print(LocalDate.now()+" , "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":  ");
			System.out.println("Image Request: ");
			try {
			String fileName=(String) getArrayFromClient.get(1);
			String filePath="../gcm/Maps/"+fileName;
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
			clientDataInformation = (InetAddress)getArrayFromClient.get(2);
			if(!listOfConsToGCM.contains(clientDataInformation.getHostAddress()))
			{
				listOfConsToGCM.add(clientDataInformation.getHostAddress());
			}
		}
	}
	}

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
			listOfConsToGCM=array.get(0);
			return array.get(0);
		} catch (SQLException e) {
		System.out.println("	Error getting information into arraylist");
		}
		return null;
	}
	public void printGcmConnectionList()
	{
		Thread[] thread=this.getClientConnections();
		for(int i=0;i<thread.length;i++)
		{
			System.out.print(((ConnectionToClient)thread[i]).getInetAddress().getHostAddress()+", ");
		}
	}
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
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
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
	 * This method is responsible for the creation of the server instance (there is
	 * no UI in this phase).
	 *
	 * @param args[0] The port number to listen on. Defaults to 5555 if no argument
	 *        is entered.
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
