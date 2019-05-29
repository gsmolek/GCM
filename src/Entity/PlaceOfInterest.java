package Entity;
/**
 * @author      Gilad Molek 
 * @version     1               
 * @since       1        
 */
/*
 * complete
 * */
import java.util.ArrayList;

public class PlaceOfInterest {
	private ArrayList<Map> _mapsArrayContainsThisPlaceOfInterest=null; 
	private ArrayList<Tour> _tourArrayContainsThisPlaceOfInterest=null; 
	private String _name=null;
	private String _classifcation=null;
	private String _shortExplanation=null;
	private boolean _accessibleToSpecialNeeds;
	
	public PlaceOfInterest(String _name,String _classifcation,String _shortExplanation,boolean _accessibleToSpecialNeeds)
	{
		/*creates new empty _mapsArrayContainsThisPlaceOfInterest
		 * creates new empty _tourArrayContainsThisPlaceOfInterest
		 * constructor gets string name string classification string shortExplantion,boolean accessibleToSpecialNeeds*/
		this._mapsArrayContainsThisPlaceOfInterest=new ArrayList<Map>();
		this._tourArrayContainsThisPlaceOfInterest=new ArrayList<Tour>();
		this._name=_name;
		this._classifcation=_classifcation;
		this._accessibleToSpecialNeeds=_accessibleToSpecialNeeds;
	}
	
	public String getName()
	{
		return this._name;
	}
	
	public String getClassifcation()
	{
		return this._classifcation;
	}
	
	public String getShortExplanation()
	{
		return this._shortExplanation;
	}
	
	public boolean getAccessibleToSpecialNeeds()
	{
		return this._accessibleToSpecialNeeds;
	}
	
	public ArrayList<Map> getMapsArrayContainsThisPlaceOfInterest()
	{
		return this._mapsArrayContainsThisPlaceOfInterest;
	}
	
	public ArrayList<Tour> getTourArrayContainsThisPlaceOfInterest()
	{
		return this._tourArrayContainsThisPlaceOfInterest;
	}
	
	public void setName(String _name)
	{
		this._name=_name;
	}
	
	public void setClassifcation(String _classifcation)
	{
		this._classifcation=_classifcation;
	}
	
	public void setShortExplanation(String _shortExplantion)
	{
		this._shortExplanation=_shortExplanation;
	}
	
	public void setAccesibleToSpecialNeeds(boolean _accesibleToSpecialNeeds)
	{
		this._accessibleToSpecialNeeds=_accessibleToSpecialNeeds;
	}
	
	public void addToMapsArrayContainsThisPlaceOfInterest(Map map)
	{
		this._mapsArrayContainsThisPlaceOfInterest.add(map);
	}
	
	public void addToTourArrayContainsThisPlaceOfInterest(Tour tour)
	{
		this._tourArrayContainsThisPlaceOfInterest.add(tour);
	}
	
	public Map getMapAtIndexInMapsArrayContainsThisPlaceOfInterest(int index)
	{
		return this._mapsArrayContainsThisPlaceOfInterest.get(index);
	}
}
