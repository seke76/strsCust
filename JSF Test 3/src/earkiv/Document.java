package earkiv;

import javax.annotation.ManagedBean;

@ManagedBean
public class Document {

	private int id;
	private int inputDeliveryId;
	private String trackerId;
	private String bgcId;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInputDeliveryId() {
		return inputDeliveryId;
	}
	public void setInputDeliveryId(int inputDeliveryId) {
		this.inputDeliveryId = inputDeliveryId;
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