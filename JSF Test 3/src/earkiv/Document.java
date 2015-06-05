package earkiv;

import java.sql.Date;

import javax.annotation.ManagedBean;

@ManagedBean
public class Document {

	private int id;
	private int inputDeliveryId;
	private String trackerId;
	private String bgcId;
	private String invoiceNumber;
	private String OCR;
	private String totalAmount;
	private Date scanDate;

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
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getOCR() {
		return OCR;
	}
	public void setOCR(String OCR) {
		this.OCR = OCR;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Date getScanDate() {
		return scanDate;
	}
	public void setScanDate(Date scanDate) {
		this.scanDate = scanDate;
	}
}