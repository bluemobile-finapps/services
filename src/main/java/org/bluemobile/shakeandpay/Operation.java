package org.bluemobile.shakeandpay;


public class Operation {
	SourceHit source;
	TargetHit target;
	double amount;
	long number;
	
	boolean sourceOk = false;
	boolean targetOk = false;
	
	long ts;
	
	public Operation(SourceHit source, TargetHit target, double amount){
		this.amount = amount;
		this.source = source;
		this.target = target;
		
		this.number = source.getTs();
		this.ts = System.currentTimeMillis();
	}
}
