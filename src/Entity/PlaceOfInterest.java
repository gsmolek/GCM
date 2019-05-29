package Entity;
/**
 * @author MOLEK 
 * @version 1            
 * @since       1        
 */
/**
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
	/**
	 * Description of PlaceOfInterest constructor
	 * @param _name String
	 * @param _classifcation String
	 * @param _shortExplanation String
	 * @param _accessibleToSpecialNeeds Boolean
	 * 
	 * Initializing all the parameters also the two arrayLists
	 * 		ArrayList<Map> _mapsArrayContainsThisPlaceOfInterest
	 * 		ArrayList<Tour> _tourArrayContainsThisPlaceOfInterest
	 * */
	public PlaceOfInterest(String _name,String _classifcation,String _shortExplanation,boolean _accessibleToSpecialNeeds)
	{
		this._mapsArrayContainsThisPlaceOfInterest=new ArrayList<Map>();
		this._tourArrayContainsThisPlaceOfInterest=new ArrayList<Tour>();
		this._name=_name;
		this._classifcation=_classifcation;
		this._accessibleToSpecialNeeds=_accessibleToSpecialNeeds;
	}
	/**
	 * Description of getName method
	 * @return _name 	type String
	 * 
	 * returns String Name Of place of interest
	 * */
	public String getName()
	{
		return this._name;
	}
	/**
	 * Description of getClassifcation method
	 * @return _classifcation 	type String
	 * 
	 * returns String Description Of place of interest
	 * */
	public String getClassifcation()
	{
		return this._classifcation;
	}
	/**
	 * Description of getShortExplanation method
	 * @return _shortExplanation 	type String
	 * 
	 * returns String short Explanation Of place of interest
	 * */
	public String getShortExplanation()
	{
		return this._shortExplanation;
	}
	/**
	 * Description of getShortExplanation method
	 * @return _accessibleToSpecialNeeds 	type Boolean
	 * 
	 * returns Boolean if place of interest is accessible To Special Needs
	 * */
	public boolean getAccessibleToSpecialNeeds()
	{
		return this._accessibleToSpecialNeeds;
	}
	/**
	 * Description of getMapsArrayContainsThisPlaceOfInterest method
	 * @return _mapsArrayContainsThisPlaceOfInterest 	type ArrayList<Map>
	 * 
	 * returns ArrayList of the maps that contains this place of interest
	 * */
	public ArrayList<Map> getMapsArrayContainsThisPlaceOfInterest()
	{
		return this._mapsArrayContainsThisPlaceOfInterest;
	}
	/**
	 * Description of getTourArrayContainsThisPlaceOfInterest method
	 * @return _tourArrayContainsThisPlaceOfInterest 	type ArrayList<Tour>
	 * 
	 * returns ArrayList of the Tours that contains this place of interest
	 * */
	public ArrayList<Tour> getTourArrayContainsThisPlaceOfInterest()
	{
		return this._tourArrayContainsThisPlaceOfInterest;
	}
	/**
	 * Description of setName method
	 * @param _name 	type String
	 * 
	 * set changes in the place of interest name
	 * */
	public void setName(String _name)
	{
		this._name=_name;
	}
	/**
	 * Description of setClassifcation method
	 * @param _classifcation 	type String
	 * 
	 * set changes in the place of interest Classification
	 * */
	public void setClassifcation(String _classifcation)
	{
		this._classifcation=_classifcation;
	}
	/**
	 * Description of setShortExplanation method
	 * @param _shortExplantion 	type String
	 * 
	 * set changes in the place of interest Short explanation
	 * */
	public void setShortExplanation(String _shortExplantion)
	{
		this._shortExplanation=_shortExplanation;
	}
	/**
	 * Description of setAccesibleToSpecialNeeds method
	 * @param _accesibleToSpecialNeeds 	type boolean
	 * 
	 * set true/false if the place of interest is accessible to special needs
	 * */
	public void setAccesibleToSpecialNeeds(boolean _accesibleToSpecialNeeds)
	{
		this._accessibleToSpecialNeeds=_accessibleToSpecialNeeds;
	}
	/**
	 * Description of addToMapsArrayContainsThisPlaceOfInterest method
	 * @param map 	type Map
	 * 
	 * adds map to the map arrayList of all maps that contains this place of interest
	 * */
	public void addToMapsArrayContainsThisPlaceOfInterest(Map map)
	{
		this._mapsArrayContainsThisPlaceOfInterest.add(map);
	}
	/**
	 * Description of addToTourArrayContainsThisPlaceOfInterest method
	 * @param tour 	type Tour
	 * 
	 * adds tour to the tour arrayList of all tours that contains this place of interest
	 * */
	public void addToTourArrayContainsThisPlaceOfInterest(Tour tour)
	{
		this._tourArrayContainsThisPlaceOfInterest.add(tour);
	}
	/**
	 * Description of getMapAtIndexInMapsArrayContainsThisPlaceOfInterest method
	 * @param index 	type int
	 * 
	 * @return Map 
	 * returns map at index in MapsArrayContainsThisPlaceOfInterest arraylist
	 * */
	public Map getMapAtIndexInMapsArrayContainsThisPlaceOfInterest(int index)
	{
		return this._mapsArrayContainsThisPlaceOfInterest.get(index);
	}
}
