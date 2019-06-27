package GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class GUIthread extends Thread {

	GUIcontroller a;
	public GUIthread(GUIcontroller a)
	{
		this.a=a;
		this.start();
	}

	@Override
	public void run() {
		
		while(true)
		{
			
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
			
			ArrayList<String> s= this.a.server.ActiveConnection();
			this.a.countConnections(s.size());
		}
		
		
	}

}
