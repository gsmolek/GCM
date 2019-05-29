package Entity;
/**
 * TO-DO:
 * 1.Create City Class
 * 2.create getVersionRequest method- what it means?
 * 3.create removeVersion method -what it means?
 * */



public class Version {
	private City _city=null;
	private double _versionNumber=0.0;
	private String _description=null;
	
	public Version(City _city,double _versionNumber,String _description)
	{
		/*constructor get City Object, double versionNumber' String description */
		this._city=_city;
		this._versionNumber=_versionNumber;
		this._description=_description;
	}
	public City getCityName()
	{
		return this._city;
	}
	
	public void setCityName(City _city)
	{
		this._city=_city;
	}
	
	public double getVersionNumber() 
	{
		return this._versionNumber;
	}
	
	public void setVersionNumber(double _versionNumber)
	{
		this._versionNumber=_versionNumber;
	}
	
	public String getDescription()
	{
		return _description;
	}
	
	public void setDescription(String _description)
	{
		this._description=_description;
	}
	
	public void removeVersion()//incomplete-gilad-sharon
	{
		
	}
	public void getVersionRequests()//incomplete-gilad-sharon
	{
		
	}
}
