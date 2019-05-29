package Entity;
/*need to create PersonCard Class*/
public class User {
private String _userName=null;
private String _password=null;
private PersonCard _personCard=null;

public User(String _userName,String _password,PersonCard _personCard)
{
	this._userName=_userName;
	this._password=_password;
	this._personCard=_personCard;
}

public String get_userName() {
	return _userName;
}

public void set_userName(String _userName) {
	this._userName = _userName;
}

public String get_password() {
	return _password;
}

public void set_password(String _password) {
	this._password = _password;
}

public PersonCard get_personCard() {
	return _personCard;
}

public void set_personCard(PersonCard _personCard) {
	this._personCard = _personCard;
}








}
