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
	/**Description of Version constructor
	 * @param _city							City Object type
	 * @param _versionNumber				double type
	 * @param _description					String type
	 * */
	public Version(City _city,double _versionNumber,String _description)
	{
		/*constructor get City Object, double versionNumber' String description */
		this._city=_city;
		this._versionNumber=_versionNumber;
		this._description=_description;
	}
	/**Description of getCity method
	 *@return City                          the City of the version as City Object type
	 *
	 *returns the city
	 * */
	public City getCity()
	{
		return this._city;
	}
	/**Description of setCity method
	 *@param   _city                       City type
	 *
	 *set city of this version
	 * */
	public void setCity(City _city)
	{
		this._city=_city;
	}
	/**Description of getVersionNumber method
	 *@return   _versionNumber                       double type
	 * */
	public double getVersionNumber() 
	{
		return this._versionNumber;
	}
	/**Description of setVersionNumber method
	 *@param   _versionNumber                       double type
	 *
	 *set version number of this version
	 * */
	public void setVersionNumber(double _versionNumber)
	{
		this._versionNumber=_versionNumber;
	}
	/**Description of getDescription method
	 *@return   _description                       String type
	 * */
	public String getDescription()
	{
		return _description;
	}
	/**Description of setDescription method
	 *@param   _description                       String type
	 *
	 *set description of this version
	 * */
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
