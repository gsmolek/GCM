package Entity;

public class ClientCard {
	
	private int credit_card;
	private int phone_number;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + credit_card;
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
		ClientCard other = (ClientCard) obj;
		if (credit_card != other.credit_card)
			return false;
		if (phone_number != other.phone_number)
			return false;
		return true;
	}
	
	public void credit_card (int credit_card, int phone_number) {
	this.credit_card=credit_card;
	this.phone_number=phone_number;
}

public void setCreditCard(int creditCardToInsert ) {
    this.credit_card=creditCardToInsert;
}
public int getJob (){
    return this.credit_card;
}


public void setPhoneNumber(int phoneNumberToInsert ) {
    this.phone_number=phoneNumberToInsert;
}
public int getPhoneNumber (){
    return this.phone_number;
}



}
