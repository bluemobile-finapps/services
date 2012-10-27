package org.bluemobile.shakeandpay;

import java.util.Enumeration;

public interface OperationsRepo {
	public Enumeration<Operation> getOperations();
	
	/**
	 * only adds once for each hit
	 * @param op
	 */
	public void addOperation(Operation op);
	
	public Operation getOperation(SourceHit sh);
	
	public void removeOperation(Operation op);
}
