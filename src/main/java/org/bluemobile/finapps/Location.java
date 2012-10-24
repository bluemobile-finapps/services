package org.bluemobile.finapps;

public class Location {
	private double latitude;
	private double longitude;
	
	public Location(double lat, double lon) {
		this.latitude = lat;
		this.longitude = lon;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
