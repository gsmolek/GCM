package serverConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import ocsf.server.*;
import GUI.*;

public class EchoServer extends AbstractServer {

	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5550;
	private static String portFromUser;
	mysqlConnection MySQLConnection = new mysqlConnection();

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
		/**
		 * 1-edit a SQL table 2-get info from SQL table 3-request of a file 4-resultset
		 */
		switch (operation) {
		case "1": {
			String query = (String) getArrayFromClient.get(arrayLength);
			MySQLConnection.setValueInSqlTable(query);
		}
		case "2": {
			String query = (String) getArrayFromClient.get(arrayLength - 1);
			ResultSet resultSet = MySQLConnection.getValueInSqlTable(query);
			ArrayList<Object> toClient = new ArrayList<Object>();
			System.out.println(query);
			toClient.add("2");
			try {
				if (resultSet.next()) {
					byte[][] raw = new byte[3][];
					raw[0] = resultSet.getBytes(1);
					raw[1] = resultSet.getBytes(2);
					toClient.add(raw);
					//System.out.println("raw: "+toClient.get(2));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.sendToAllClients(toClient);
		}
		case "3": {

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
