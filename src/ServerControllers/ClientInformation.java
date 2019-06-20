package ServerControllers;

import java.net.InetAddress;

public class ClientInformation {
	private String username;
	private String computerName;
	private String ip;
	private InetAddress inetAddress;
	
	public ClientInformation(String username,InetAddress inetAddress) 
	{
		this.username=username;
		this.computerName=inetAddress.getHostName();
		this.ip=inetAddress.getHostAddress();
		this.inetAddress=inetAddress;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getComputerName() {
		return computerName;
	}
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public InetAddress getInetAddress() {
		return inetAddress;
	}
	public void setInetAddress(InetAddress inetAddress) {
		this.inetAddress = inetAddress;
	}
}
