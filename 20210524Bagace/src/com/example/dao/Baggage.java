package com.example.dao;

public class Baggage {
	private int baggageid;
	private String trackingNumber;
	private int baggageStatusId;
	private String status;

	public Baggage(int baggageid, String trackingNumber, int baggageStatusId, String status) {
		this.baggageid = baggageid;
		this.trackingNumber = trackingNumber;
		this.baggageStatusId = baggageStatusId;
		this.status = status;
	}

	public Baggage(String trackingNumber, int baggageStatusId) {
		this(0, trackingNumber, baggageStatusId, null);
	}

	public int getBaggageid() {
		return baggageid;
	}

	public void setBaggageid(int baggageid) {
		this.baggageid = baggageid;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public int getBaggageStatusId() {
		return baggageStatusId;
	}

	public void setBaggageStatusId(int baggageStatusId) {
		this.baggageStatusId = baggageStatusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}