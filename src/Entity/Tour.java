package Entity;

import java.util.ArrayList;

public class Tour {
private City _city=null;
private ArrayList<PlaceOfInterest> _placeOfInterestsThatContainsThisTour=null;
private String _generalDescription=null;

public Tour(City _city,String _generalDescription)
{
	this._city=_city;
	this._generalDescription=_generalDescription;
	_placeOfInterestsThatContainsThisTour=new ArrayList<PlaceOfInterest>();
}

public City get_city() {
	return _city;
}

public void set_city(City _city) {
	this._city = _city;
}

public ArrayList<PlaceOfInterest> get_placeOfInterestsThatContainsThisTour() {
	return _placeOfInterestsThatContainsThisTour;
}

public void set_placeOfInterestsThatContainsThisTour(ArrayList<PlaceOfInterest> _placeOfInterestsThatContainsThisTour) {
	this._placeOfInterestsThatContainsThisTour = _placeOfInterestsThatContainsThisTour;
}

public String get_generalDescription() {
	return _generalDescription;
}

public void set_generalDescription(String _generalDescription) {
	this._generalDescription = _generalDescription;
}
public void addToplaceOfInterestsThatContainsThisTour(PlaceOfInterest poi)
{
	this._placeOfInterestsThatContainsThisTour.add(poi);
}




}
