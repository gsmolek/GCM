/** @author DORON */

package Entity;

public class VersionOfMapCollection {
	private double _confirmedVersion;
	
	/**	Description of the constructor  
	 * Initializing	the confirmedVersion to zero 
	 */
	public VersionOfMapCollection() {
		this._confirmedVersion=0.0;
	}
	
	/**	Description of get_Version method
	 * 
	 * @return the version of the map
	 */
	public double get_Version() {
		return this._confirmedVersion;
	}
	
	/**	Description of set_versionOfMapCollection method
	 * 
	 * @param newVersion	get the new 
	 * change the version of the map
	 * 
	 */
	public void set_versionOfMapCollection(double newVersion) {
		this._confirmedVersion = newVersion;
	}
}
