package org.bluemobile.shakeandpay;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryOperationsRepo implements OperationsRepo {

	private final ConcurrentHashMap<SourceHit, Operation> ops = new ConcurrentHashMap<SourceHit, Operation>();
	
	public Enumeration<Operation> getOperations() {
		return ops.elements();
	}

	public void addOperation(Operation op) {		
		ops.putIfAbsent(op.source, op);
	}
	
	public Operation getOperation(SourceHit sh){
		return ops.get(sh);
	}

	public void removeOperation(Operation op) {
		ops.remove(op.source);		
	}

}
