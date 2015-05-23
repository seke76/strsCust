package earkiv;

import javax.annotation.ManagedBean;

@ManagedBean
public class Search {

	private String trackerId;
	private String bgcId;
	
	public Search(String trackerId, String bgcId) {
		System.out.println("Search constructur ");
		this.trackerId = trackerId;
		this.bgcId = bgcId;
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
}
