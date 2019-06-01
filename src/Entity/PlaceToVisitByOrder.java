/** @author DORON */
package Entity;

public class PlaceToVisitByOrder {
	private double _visitingTime;
	
	/**	Description of the constructor  
	 * @param visitingTime		the time that's takes to visit the place
	 * 
	 * Initializing	the visiting time for a place  
	 */
	public PlaceToVisitByOrder(double visitingTime) {
		this._visitingTime = visitingTime;
	}
	
	
	/**	Description of the get_VisitingTime method
	 * 
	 * @return	the time that needed to visit the place
	 */
	public double get_VisitingTime() {
		return this._visitingTime;
	}
	
	
	/**	Description of the set_VisitingTime method
	 * 
	 * the method set a new time that required for visiting the place
	 * 
	 * @param newVisitingTime		gets the new time that required to visit the place
	 */
	public void set_VisitingTime(double newVisitingTime) {
		this._visitingTime = newVisitingTime;
	}

}
