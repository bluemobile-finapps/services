package org.bluemobile.shakeandpay;

public class SourceHit extends Hit {
	double amount;
	
	public SourceHit(String name, String lastName, String acc, double lat, double lon, double amount){
		super(name, lastName, acc, lat, lon);
		this.amount = amount;
	}
	
	public double getAmount(){
		return this.amount;
	}
}
