package org.bluemobile.shakeandpay;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.mvc.WebContentInterceptor;

public class MyInterceptor extends WebContentInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    			throws ServletException {
		String origin = request.getHeader("Origin");
		if(origin!=null && origin.length()!=0)
			response.setHeader("Access-Control-Allow-Origin", origin);
		
		response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
		
		return super.preHandle(request, response, handler);
	}

}
