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
			this.a.countConnections();
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}
		
		
	}
}