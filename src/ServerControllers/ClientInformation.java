package ServerControllers;
/**
 *class that can hold client information 
 */
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
	/**
	 * 
	 * @return username String
	 */
	public String getUsername() {
		return username;
	}
/**
 * set user name
 * @param username
 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * get host name
	 * @return hostname string
	 */
	public String getComputerName() {
		return computerName;
	}
	/**
	 * set host name
	 * @param computerName String
	 */
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	/**
	 * get user ip
	 * @return ip String
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * set ip
	 * @param ip String
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * return the user network information
	 * @return InetAddress
	 */
	public InetAddress getInetAddress() {
		return inetAddress;
	}
	/**
	 * setter for user network information
	 * @param inetAddress InetAddress
	 */
	public void setInetAddress(InetAddress inetAddress) {
		this.inetAddress = inetAddress;
	}
}
