
/**
 * @author GILAD MOLEK
 * @author DORON TUCHMAN
 * @author MATI HALFA
 * @author MATAN ASULIN
 * @author SHARONE BURSHTIEN
 *
 *	@version 1.5
 *	@since 2019
 */
package GUI;
/**
 * Thread that counts connections and updating log file
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import Logger.LogFile;

public class GUIthread extends Thread {

	GUIcontroller a;
	private LogFile logFile;
	public GUIthread(GUIcontroller a)
	{
		this.a=a;
		this.logFile=new LogFile();
		this.start();
	}
/**
 * every x seconds check for active connections 
 * and updating the log file
 */
	@Override
	public void run() {
		
		while(true)
		{
			
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
			this.logFile.logging(this.a.getTextAreaString());
			ArrayList<String> s= this.a.server.ActiveConnection();
			if(s!=null)
				this.a.countConnections(s.size());
			
			
		}
		
		
	}

}
