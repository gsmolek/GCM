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
		InetAddress clientDataInformation;
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
	}
		/*
		 * System.out.println(msg);
		 * 
		 * ArrayList<String> m=(ArrayList<String>)msg; System.out.println(m);
		 * if(m.get(0).equals("getAll")) { System.out.println("getALL");
		 * MySQLConnection.UpdateCitiesName(); ArrayList<String>
		 * citiesName=MySQLConnection.getArrayOfCitiesName();
		 * this.sendToAllClients(citiesName); } else if(m.get(0).equals("save")) {
		 * System.out.println("serevr enter save"); MySQLConnection.saveChanges(m);
		 * ArrayList<String> saved=new ArrayList<String>(); saved.add("saved");
		 * this.sendToAllClients(saved); } else {
		 * System.out.println("serevr enter info");
		 * MySQLConnection.updateCityInformation(m.get(0)); ArrayList<String>
		 * cityInfo=MySQLConnection.getCityInfo(); this.sendToAllClients(cityInfo); }
		 * 
		 * //System.out.println("Message received: " + msg + " from " + client);
		 * //this.sendToAllClients(msg);
		 * 
		 */
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
			System.out.println("ERROR - Could not listen for clients!");
		}
	}
}
//End of EchoServer class
