package org.bluemobile.finapps;

public interface HitRepo {
	public Object[] getSources();
	public Object[] getTargets();
	
	public void addSource(SourceHit acc);
	public void addTarget(TargetHit acc);
	
	public void removeSource(SourceHit acc);
	public void removeTarget(TargetHit acc);
	
	SourceHit findSource(TargetHit me, long d, long ms);
	TargetHit findTarget(SourceHit me, long d, long ms);

}
