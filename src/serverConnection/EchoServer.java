package serverConnection;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import GUI.Main;
import ocsf.server.*;
import GUI.*;
/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5550;
  private static String portFromUser;
  //Constructors ****************************************************
  

/**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public EchoServer(int port) 
  {
    super(port);
  }

  
  //Instance methods ************************************************
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  mysqlConnection MySQLConnection=new mysqlConnection();
  public void handleMessageFromClient
    (Object msg, ConnectionToClient client)
  {
	  	
	  /*
	  	System.out.println(msg);
	  	
	  	ArrayList<String> m=(ArrayList<String>)msg;
	  	System.out.println(m);
	    if(m.get(0).equals("getAll"))
	    {
	    	System.out.println("getALL");
	    	MySQLConnection.UpdateCitiesName();
	    	ArrayList<String> citiesName=MySQLConnection.getArrayOfCitiesName();
	    		this.sendToAllClients(citiesName);
	    }
	    else if(m.get(0).equals("save"))
	    {
	    	System.out.println("serevr enter save");
	    	MySQLConnection.saveChanges(m);
	    	ArrayList<String> saved=new ArrayList<String>();
	    	saved.add("saved");
	    	this.sendToAllClients(saved);
	    }
	   else 
	    {
	    	System.out.println("serevr enter info");
	    	MySQLConnection.updateCityInformation(m.get(0));
	    	ArrayList<String> cityInfo=MySQLConnection.getCityInfo();
	    	this.sendToAllClients(cityInfo);
	    }

	    //System.out.println("Message received: " + msg + " from " + client);
	    //this.sendToAllClients(msg);
	     
	     */
}

    
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println
      ("Server listening for connections on port " + getPort());
    MySQLConnection.open();
    MySQLConnection.createSchema();
    MySQLConnection.createTables();
  }
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
  }
  
  //Class methods ***************************************************
  
  /**
   * This method is responsible for the creation of 
   * the server instance (there is no UI in this phase).
   *
   * @param args[0] The port number to listen on.  Defaults to 5555 
   *          if no argument is entered.
   */
 
  public static void startServer() 
  {

     int port = 0; //Port to listen on
    try
    {
      port = Integer.parseInt(portFromUser); //Get port from command line
      System.out.println("Created Port :"+port);
    }
    catch(Throwable t)
    {
      port = DEFAULT_PORT; //Set port to 5555
      System.out.println("Created Port :"+port);
    }
	
    EchoServer sv = new EchoServer(port);
    
    try 
    {
      sv.listen(); //Start listening for connections
    } 
    catch (Exception ex) 
    {
      System.out.println("ERROR - Could not listen for clients!");
    }
  }
}
//End of EchoServer class
