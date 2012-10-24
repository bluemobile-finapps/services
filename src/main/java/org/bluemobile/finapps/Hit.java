package org.bluemobile.finapps;

/**
 * Class representing an account
 * @author bluemobile
 *
 */
public class Hit {
	String account;
	String name, lastName;
	Location loc;
	long ts;
	
	public Hit(String name, String lastName, String acc, double lat, double lon) {
		this.name = name;
		this.lastName = lastName;
		this.account = acc;
		this.loc = new Location(lat, lon);
		this.ts = System.currentTimeMillis();
	}

	public String getAcccount() {
		return account;
	}

	public String getName() {
		return name;
	}
	
	public String getLastName() {
		return lastName;
	}

	public Location getLoc() {
		return loc;
	}

	public long getTs() {
		return ts;
	}
	
}
