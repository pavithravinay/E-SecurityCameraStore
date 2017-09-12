
public class RetailItemSearchCriteria {
	
	public  boolean showAccessories; 
	public  boolean showSecurityCameras; 
	public  boolean showDvrs;
	
	public RetailItemSearchCriteria(boolean showAccessories, boolean showSecurityCameras, boolean showDvrs) {
		this.showAccessories = showAccessories;
		this.showSecurityCameras = showSecurityCameras;
		this.showDvrs = showDvrs;
	}

}