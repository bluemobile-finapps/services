package org.bluemobile.finapps;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Main API for fin apps
 * @author bluemobile
 *
 */
@Controller
public class TransferHitController { 
	// milliseconds
	final long MS = 3000;
	final long INTERVAL = 100L; 
	
	// meters
	final long DISTANCE = 100;
	
	private final HitRepo repo;	
	
	private final OperationsRepo ops;
	
	@Autowired
	public TransferHitController(HitRepo repo, OperationsRepo ops) {
		this.repo = repo;
		this.ops = ops;
	}
	
    @RequestMapping(value = "/send", 
    				method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ApiResult send(	@RequestParam("lat") double lat,
    						@RequestParam("lon") double lon,
    						@RequestParam("name") String name,
    						@RequestParam("lastname") String lastName,
    						@RequestParam("accn") String accNumber,
    						@RequestParam("amount") double amount) throws InterruptedException{
    	
    	 
		long start = System.currentTimeMillis();
		TargetHit target = null;
		
		// TODO: true async model.
		
		SourceHit me = new SourceHit(name, lastName, accNumber, lat, lon, amount); 
		repo.addSource(me);		
		while((target = repo.findTarget(me, DISTANCE, MS)) == null &&
				System.currentTimeMillis() - start < MS);{
			Thread.sleep(INTERVAL);
		}
		repo.removeSource(me);
		
		if(target != null){
			Operation op = ops.getOperation(me);
			if(op == null){
				ops.addOperation(new Operation(me, target, me.amount));
			}
			TargetHit thwithoutacc = new TargetHit(target.name, target.lastName, null, 
													target.loc.getLatitude(), target.loc.getLongitude());
			return new ApiResult(true, ops.getOperation(me).number, thwithoutacc);
		}
		
		return new ApiResult(false, 0L, null);
    }
    
    @RequestMapping(value = "/receive", 
			method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ApiResult receive(@RequestParam("lat") double lat,
							@RequestParam("lon") double lon,
							@RequestParam("name") String name,
							@RequestParam("lastname") String lastName,
							@RequestParam(value="accn") String accNumber) throws InterruptedException{
		
		 
		long start = System.currentTimeMillis();
		SourceHit source = null;
		
		// TODO: true async model.
		
		TargetHit me = new TargetHit(name, lastName, accNumber, lat, lon); 
		repo.addTarget(me);		
		while((source = repo.findSource(me, DISTANCE, MS)) == null &&
				System.currentTimeMillis() - start < MS);{
			Thread.sleep(INTERVAL);
		}
		repo.removeTarget(me);
		
		if(source != null){
			Operation op = ops.getOperation(source);
			if(op == null){
				ops.addOperation(new Operation(source, me, source.amount));
			}			
			return new ApiResult(true, ops.getOperation(source).number, source);
		}
		
		return new ApiResult(false, 0L, null);

	}
    
    @RequestMapping(value = "/accept/{role}", 
			method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ApiResult accept(@PathVariable("role") String role,
							@RequestParam("idop") long idop) throws InterruptedException{ 
    	    	
    	Enumeration<Operation> operations = ops.getOperations();
    	while (operations.hasMoreElements()){
    		Operation op = operations.nextElement();
    		if(op.number == idop){
    			if("receive".equals(role)){
    				op.targetOk = true;
    				return new ApiResult(true, idop, op.source);
    			}
    			else if("send".equals(role)){
    				op.sourceOk = true;
    				// wait for target to accept
    				long start = System.currentTimeMillis();
    				while(op.targetOk == false &&
    					System.currentTimeMillis() - start < MS){
    					Thread.sleep(INTERVAL);
    				}
    				ops.removeOperation(op);
    				if (op.targetOk == true){
    					return new ApiResult(true, idop, op.target);
    				}
    			}
    		}
    		
    	}
    	return new ApiResult(false, 0L, null);
    }
    
//    @RequestMapping(method = RequestMethod.OPTIONS, value="/**")
//    public void manageOptions(HttpServletRequest req, HttpServletResponse response)
//    {
//    	String origin = req.getHeader("Origin");
//		if(origin!=null && origin.length()!=0){
//			response.setHeader("Access-Control-Allow-Origin", origin);
//		}
//		response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
//    }
  
}