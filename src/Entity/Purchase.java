package Entity;

import java.sql.Date;

public class Purchase 
{

	public Purchase(Date dateOfPruchase, PruchaseType pruchaseType, int downloadNumbers, double period) {
		this.dateOfPruchase = dateOfPruchase;
		this.pruchaseType = pruchaseType;
		this.downloadNumbers = downloadNumbers;
		this.period = period;
	}
	public enum PruchaseType
	{
		oneTimePruchase,
		subscriptionPurchase;
	}
	
	private Date dateOfPruchase;
	private PruchaseType pruchaseType;
	private int downloadNumbers;
	private double period;
	
	
	
	
	public Date getDateOfPruchase() {
		return dateOfPruchase;
	}
	public void setDateOfPruchase(Date dateOfPruchase) {
		this.dateOfPruchase = dateOfPruchase;
	}
	public PruchaseType getPruchaseType() {
		return pruchaseType;
	}
	public void setPruchaseType(PruchaseType pruchaseType) {
		this.pruchaseType = pruchaseType;
	}
	public int getDownloadNumbers() {
		return downloadNumbers;
	}
	public void setDownloadNumbers(int downloadNumbers) {
		this.downloadNumbers = downloadNumbers;
	}
	public double getPeriod() {
		return period;
	}
	public void setPeriod(double period) {
		this.period = period;
	}
	
	
	
	
}
