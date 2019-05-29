package Entity;

import java.util.ArrayList;

public class Map {
	
private City _city=null;
private ArrayList<PlaceOfInterestInMap> _allPlaceOfInterestInThisMap=null;
private String _description=null;

public Map(City _city,String _description)
{
	this._city=_city;
	this._description=_description;
	_allPlaceOfInterestInThisMap=new ArrayList<PlaceOfInterestInMap>();
}

public City getCity()
{
	return this._city;
}

public String getDescription()
{
	return this._description;
}

public void setCity(City city)
{
	this._city=_city;
}

public void SetDescription(String description)
{
	this._description=_description;
}

public ArrayList<PlaceOfInterestInMap> getPlaceOfInterestInThisMap()
{
	return this._allPlaceOfInterestInThisMap;
}

public void addToPlaceOfInterestInThisMap(PlaceOfInterestInMap poi)
{
	this._allPlaceOfInterestInThisMap.add(poi);
}
public void addTourToPlaceOfInterestInMap()
{
	/*add Tour to city Array of tours*/
}



}
