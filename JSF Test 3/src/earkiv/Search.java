package earkiv;

import java.util.Date;

import javax.annotation.ManagedBean;

@ManagedBean
public class Search {

	private String trackerId; 
	private String bgcId;
	private Date scanDate;
	
	public Search(String trackerId, String bgcId, Date scanDate) {
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
	public Date getScanDate() {
		return scanDate;
	}
	public void setScanDate(Date scanDate) {
		//this.scanDate = Date.valueOf(scanDate);
		this.scanDate = scanDate;
	}
}
