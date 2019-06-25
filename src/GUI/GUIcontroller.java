package GUI;

import java.io.IOException;
import ServerControllers.*;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import com.sun.accessibility.internal.resources.accessibility;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import serverConnection.EchoServer;

public class GUIcontroller implements Initializable{
	@FXML
	private TextArea TextA1;
	@FXML
	private TextField PortA1;
	@FXML
	private TextField serverIP;
	@FXML
	private TextField ServerName;
	@FXML
	private TextField ServerCon;
	@FXML
	private TextField GCMCon;
	@FXML
	private TextField UsingPort;
	@FXML
	private Label ActiveLabel;
	@FXML
	private Label InActiveLabel;
	@FXML
	private ImageView image;
	@FXML
	private Button StartBtn;
	@FXML
	private Button StopBtn;
	
    private GUIthread guiThread;
    RenewSubscriptionController mailThread;
	
	private InetAddress inetAddress;
	protected EchoServer server;
	
	public EchoServer getServer() {
		return server;
	}

	boolean started=false;
	final public static int DEFAULT_PORT = 5550;
	private ArrayList<ClientInformation> messageList;
	
	public ArrayList<ClientInformation> getMessageList() {
		return messageList;
	}
	public void setMessageList(ArrayList<ClientInformation> messageList) {
		this.messageList = messageList;
	}
	public void addToMessageList(ClientInformation clientInformation)
	{
		this.messageList.add(clientInformation);
	}
	public TextArea getTextA1() {
		return TextA1;
	}
	public void addTextA1(String text) {
		TextA1.insertText(TextA1.getLength(), text);
	}
	public void createTextField()
	{
		TextA1.setEditable(false);
		TextA1.getStyleClass().add("my-text-area");
		PortA1.setEditable(true);
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                appendText(String.valueOf((char)b));
            }
        };
        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
        
	}
	public void CreateServer()
	{
		if(PortA1.getText().isEmpty())
			server=new EchoServer(DEFAULT_PORT);
		else
		server=new EchoServer(Integer.valueOf(PortA1.getText()));
	    try 
	    {
	      server.listen(); //Start listening for connections
	      UsingPort.setText(String.valueOf(server.getPort()));
	      UsingPort.setEditable(false);
	      started=true;
	      InActiveLabel.setVisible(false);
	      ActiveLabel.setVisible(true);
	      this.guiThread =new GUIthread(this);
	      this.mailThread=new RenewSubscriptionController(this,server.getMySQLConnection());
	      this.mailThread.start();
	      

	    } 
	    catch (Exception ex) 
	    {
	      System.out.println("ERROR - Could not listen for clients!");
	    }
	}
	public void countConnections()
	{
		ArrayList<String> a=server.getListOfCons();
		ServerCon.setText(String.valueOf(a.size()));
		GCMCon.setText(String.valueOf(server.getListOfConsToGCM().size()));
	}
	public void startButtonAction(ActionEvent event)
	{
		this.StopBtn.setVisible(true);
		this.StartBtn.setVisible(false);
		CreateServer();
	}
	public void stopButtonAction(ActionEvent event)
	{
		if(started)	
		{
			int port=server.getPort();
			try {
				server.stopListening();
				server.close();
				UsingPort.clear();
			      InActiveLabel.setVisible(true);
			      ActiveLabel.setVisible(false);
			  	this.StopBtn.setVisible(false);
				this.StartBtn.setVisible(true);
				this.guiThread.stop();
				this.mailThread.stop();
		} catch (IOException e) {
				System.out.println("Can not close Port "+port);
			}
		}
		else
		System.out.println("There is no Server Connection!");
	}
	public void ClearBtn(ActionEvent event)
	{
		TextA1.clear();
	}
    public void appendText(String valueOf) {
        Platform.runLater(() -> TextA1.appendText(valueOf));
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			inetAddress = InetAddress.getLocalHost();
			serverIP.setText(inetAddress.getHostAddress());
			ServerName.setText(inetAddress.getHostName());
			ServerName.setEditable(false);
			serverIP.setEditable(false);
			ServerCon.setEditable(false);
			Image vs=new Image("/GUI/logoSecond.gif");
			image.setImage(vs);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println("Can't access to IP Address");
		}
		
		createTextField();
		
	}
	public boolean checkIfMeassageListContain(String usernameTocheck)
	{
		for(int i=0;i<this.messageList.size();i++)
		{
			if(messageList.get(i).getUsername().equals(usernameTocheck))
			{
				return true;
			}
		}
		return false;
	}

}
