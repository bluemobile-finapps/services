package org.bluemobile.shakeandpay;

public class ApiResult {

	boolean success;
	Object data;
	long operationId;
	
	public boolean isSuccess() {
		return success;
	}

	public Object getData() {
		return data;
	}
	
	public long getOperationId(){
		return operationId;
	}

	public ApiResult(boolean status, long opid, Object data){
		success = status;
		this.data = data;
		operationId = opid;
	}
}
