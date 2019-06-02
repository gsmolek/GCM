package Entity;

public class PersonCard {
	
	private String person_name;
	private int phone_number;
	private String email_address;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email_address == null) ? 0 : email_address.hashCode());
		result = prime * result + ((person_name == null) ? 0 : person_name.hashCode());
		result = prime * result + phone_number;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonCard other = (PersonCard) obj;
		if (email_address == null) {
			if (other.email_address != null)
				return false;
		} else if (!email_address.equals(other.email_address))
			return false;
		if (person_name == null) {
			if (other.person_name != null)
				return false;
		} else if (!person_name.equals(other.person_name))
			return false;
		if (phone_number != other.phone_number)
			return false;
		return true;
	}
	
	public PersonCard(String person_name, int phone_number, String email_address) {
	      super( );
	      this.person_name = person_name;
	      this.phone_number = phone_number;
	      this.email_address = email_address;
	   }
	
	public void setPersoName(String personame_to_insert)
	{
		this.person_name=personame_to_insert;
	}
	
	public String getPersoName()
	{
		return this.person_name;
	}
	
	public void setPhoneNumber(int personal_number_to_insert)
	{
		this.phone_number=personal_number_to_insert;
	}
	
	public int getPhoneNumber()
	{
		return this.phone_number;
	}
	
	public void setEmailAddress(String emailaddress_to_insert)
	{
		this.email_address=emailaddress_to_insert;
	}
	
	public String getEmailAddress()
	{
		return this.email_address;
	}
	
	
	
	
	
	

}
