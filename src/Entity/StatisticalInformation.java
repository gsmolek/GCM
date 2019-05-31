package Entity;

import java.io.File;
import java.sql.Date;

public class StatisticalInformation
{
	private Date _dateStatisticalInformation;
	private int _subscriptionRenewal;
	private File _staticInfo;
	
	public StatisticalInformation(Date dateStatisticalInformation) {
		this._dateStatisticalInformation = dateStatisticalInformation;
	}

	/**
	 * @return the _dateStatisticalInformation
	 */
	public Date get_dateStatisticalInformation() {
		return _dateStatisticalInformation;
	}

	/**
	 * @return the _subscriptionRenewal
	 */
	public int get_subscriptionRenewal() {
		return _subscriptionRenewal;
	}

	/**
	 * @return the _staticInfo
	 */
	public File get_staticInfo() {
		return _staticInfo;
	}

	/**
	 * @param dateStatisticalInformation the _dateStatisticalInformation to set
	 */
	public void set_dateStatisticalInformation(Date _dateStatisticalInformation) {
		this._dateStatisticalInformation = _dateStatisticalInformation;
	}

	/**
	 * @param subscriptionRenewal the _subscriptionRenewal to set
	 */
	public void setSubscriptionRenewal(int subscriptionRenewal) {
		this._subscriptionRenewal = subscriptionRenewal;
	}

	/**
	 * @param staticInfo the _staticInfo to set
	 */
	public void set_staticInfo(File staticInfo) {
		this._staticInfo = staticInfo;
	}
	
	
	
	
	
	
}
