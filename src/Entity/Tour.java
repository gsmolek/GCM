/**
 * @author MOLEK
 * @version 1
 * */
package Entity;

import java.util.ArrayList;

public class Tour {
	private City _city=null;
	private ArrayList<PlaceOfInterest> _placeOfInterestsThatContainsThisTour=null;
	private String _generalDescription=null;
	
	/**Description of Tour constructor
	 * @param _city							City Object type
	 *  @param _generalDescription			String Type get general description about the tour
	 * */
	public Tour(City _city,String _generalDescription)
	{
		this._city=_city;
		this._generalDescription=_generalDescription;
		_placeOfInterestsThatContainsThisTour=new ArrayList<PlaceOfInterest>();
	}
	
	/**Description of get_city method
	 *@return City                          the City of the Tour as City Object type
	 *
	 *returns the city
	 * */
	public City get_city() {
		return _city;
	}
	
	/**Description of set_city
	 * @param _city							City Object type
	 * 
	 * sets the City
	 * */
	public void set_city(City _city) {
		this._city = _city;
	}
	/**Description of get_placeOfInterestsThatContainsThisTour method
	 *@return ArrayList<PlaceOfInterest>  _placeOfInterestsThatContainsThisTour                 ArrayList of all places of interest connected to tour
	 *
	 *returns the city
	 * */
	public ArrayList<PlaceOfInterest> get_placeOfInterestsThatContainsThisTour() {
		return _placeOfInterestsThatContainsThisTour;
	}
	/**Description of set_placeOfInterestsThatContainsThisTour
	 * @param _placeOfInterestsThatContainsThisTour						ArrayList<PlaceOfInterest> type
	 * 
	 * sets the places of interest array that connected with this tour
	 * */
	public void set_placeOfInterestsThatContainsThisTour(ArrayList<PlaceOfInterest> _placeOfInterestsThatContainsThisTour) {
		this._placeOfInterestsThatContainsThisTour = _placeOfInterestsThatContainsThisTour;
	}
	
	/**Description of get_generalDescription method
	 *@return _generalDescription                 String of the description of this tour
	 *
	 *returns description of this tour
	 * */
	public String get_generalDescription() {
		return _generalDescription;
	}
	/**Description of set_generalDescription method
	 * @param _generalDescription				String type
	 * 
	 * sets the Description of tour
	 * */
	public void set_generalDescription(String _generalDescription) {
		this._generalDescription = _generalDescription;
	}
	/**Description of addToplaceOfInterestsThatContainsThisTour method
	 * @param poi				PlaceOfInterest type
	 * 
	 * adds poi to the place of interest ArrayList that connected to this tour
	 * */
	public void addToplaceOfInterestsThatContainsThisTour(PlaceOfInterest poi)
	{
		this._placeOfInterestsThatContainsThisTour.add(poi);
	}
	
	


}
