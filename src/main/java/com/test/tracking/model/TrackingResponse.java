package com.test.tracking.model;

public class TrackingResponse {
	private String trackingNumber;
	private String createdAt;

	// Constructors
	public TrackingResponse() {
	}

	public TrackingResponse(String trackingNumber, String createdAt) {
		this.trackingNumber = trackingNumber;
		this.createdAt = createdAt;
	}

	// Getters and Setters
	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
