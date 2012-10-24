package org.bluemobile.finapps;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryHitRepo implements HitRepo {
	
	private final ConcurrentLinkedQueue<SourceHit> sources = new ConcurrentLinkedQueue<SourceHit>();
	private final ConcurrentLinkedQueue<TargetHit> targets = new ConcurrentLinkedQueue<TargetHit>();

	
	public Object[] getSources() {
		return sources.toArray();
	}
	
	public Object[] getTargets() {
		return targets.toArray();
	}

	public void addSource(SourceHit acc) {
		sources.add(acc);
	}
	
	public void addTarget(TargetHit acc) {
		targets.add(acc);
	}

	public SourceHit findSource(TargetHit me, long distance, long milliseconds) {
		// first, find by time
		for(SourceHit acc: sources){
			if (Math.abs(me.ts - acc.ts) < milliseconds &&
					distFrom(me.loc.getLatitude(), me.loc.getLongitude(),
							 acc.loc.getLatitude(), acc.loc.getLongitude()) < distance){
				return acc;
			}		
		}
		return null;
	}

	public TargetHit findTarget(SourceHit me, long distance, long milliseconds) {
		// first, find by time
		for(TargetHit acc: targets){			
			if (Math.abs(me.ts - acc.ts) < milliseconds&&
					distFrom(me.loc.getLatitude(), me.loc.getLongitude(),
							 acc.loc.getLatitude(), acc.loc.getLongitude()) < distance){
				return acc;
			}
		}
		return null;
	}

	public void removeSource(SourceHit acc) {
		sources.remove(acc);
	}

	public void removeTarget(TargetHit acc) {
		targets.remove(acc);		
	}
	
	
	public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
	    // double earthRadius = 3958.75; // miles
		double earthRadius = 3958.75 * 1609; // meters
	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lng2-lng1);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double dist = earthRadius * c;

	    return dist;
	}
}
