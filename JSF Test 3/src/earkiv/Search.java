package earkiv;



import java.sql.Date;

import javax.annotation.ManagedBean;

@ManagedBean
public class Search {

	private String trackerId; 
	private String bgcId;
	private String scanDate;
	
	public Search(String trackerId, String bgcId, String scanDate) {
		System.out.println("Search constructur??");
		this.trackerId = trackerId;
		this.bgcId = bgcId;
		//this.scanDate = Date.valueOf(scanDate);
		this.scanDate = scanDate;
	}
	
	public String getTrackerId() {
		return trackerId;
	}
	public void setTrackerId(String trackerId) {
		this.trackerId = trackerId;
	}
	public String getBgcId() {
		return bgcId;
	}
	public void setBgcId(String bgcId) {
		this.bgcId = bgcId;
	}
	public String getScanDate() {
		return scanDate;
	}
	public void setScanDate(String scanDate) {
		//this.scanDate = Date.valueOf(scanDate);
		this.scanDate = scanDate;
	}
}
