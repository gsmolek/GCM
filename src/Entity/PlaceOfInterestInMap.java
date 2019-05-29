package Entity;

public class PlaceOfInterestInMap extends PlaceOfInterest{

	private String _locationInMap=null;
	
	public PlaceOfInterestInMap(String _name, String _classifcation, String _shortExplanation,
			boolean _accessibleToSpecialNeeds,String _locationInMap) {
		super(_name, _classifcation, _shortExplanation, _accessibleToSpecialNeeds);
		this._locationInMap=_locationInMap;
	}

	public String getLocationInMap()
	{
		return this._locationInMap;
	}
	
	public void setLocationInMap(String _locationInMap)
	{
		this._locationInMap=_locationInMap;
	}
	
	
}
