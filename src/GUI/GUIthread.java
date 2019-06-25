package GUI;

import java.util.concurrent.TimeUnit;

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
			this.a.server.checkConnectionsToGcm();
			this.a.countConnections();
			this.a.server.printGcmConnectionList();
		}
		
		
	}
}
