/** @author DORON */

package Entity;

import java.util.ArrayList;

public class City {
	private String _nameOfCity=null;
	//private int _numberOfViews;
	private ArrayList<Tour> _touresInCity=null;
	private ArrayList<Map> _mapsInCity=null;

	/**  Description of City constructor
	 * 	@param _name			Name of the city
	 *  @param _touresInCity	ArrayList that contains the tours in the city
	 *  @param _mapsInCity		ArrayList that contains the maps in the city
	 *
	 */
	
	public City(String name) {
		this._nameOfCity = name;
		this._touresInCity = new ArrayList<Tour>();
		this._mapsInCity = new ArrayList<Map>();
	}
	
	
	/**	Description of set_Name method
	 * 
	 * @param name		String of the name of the new city
	 * 
	 * set the city name
	 */
	public void set_Name(String name) {
		this._nameOfCity = name;
	}
	
	/**	Description of get_Name method
	 * 
	 * @return name		the name of the city
	 */
	public String get_CityName() {
		return this._nameOfCity;
	}
	
	/**	Description of addTourToCity method
	 * 
	 * @param newTour		Object of new tour
	 * 
	 * 	add the new tour into the ArrayList toursInCity
	 */
	public void addTourToCity(Tour newTour) {
		this._touresInCity.add(newTour);
	}
	
	/**	Description of getToursFromCity method
	 * 
	 * @return the ArrayList that contains the tours in the city
	 */
	public ArrayList<Tour> getToursFromCity() {
		return _touresInCity;
	}
	
	/**	Description of addMapToCity method
	 * 
	 * @param newMap		Object of new map
	 * 
	 * add the new map into the ArrayList _mapsInCity
	 */
	public void addMapToCity(Map newMap) {
		this._mapsInCity.add(newMap);
	}
}

