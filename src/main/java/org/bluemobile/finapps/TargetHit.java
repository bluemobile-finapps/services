package org.bluemobile.finapps;

public class TargetHit extends Hit {
	public TargetHit(String name, String lastName, String acc, double lat, double lon){
		super(name, lastName, acc, lat, lon);		
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	
}
