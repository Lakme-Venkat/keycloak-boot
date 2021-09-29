package com.peoplesprocessing.hotfoot.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class KeycloakServiceInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String origin = response.getHeader("Access-Control-Allow-Origin");
		if(origin == null) {
			response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

			// without this header jquery.ajax calls returns 401 even after successful login
			// and SSESSIONID being succesfully stored.
			response.setHeader("Access-Control-Allow-Credentials", "true");

			response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Headers",
					"X-Requested-With, Authorization, Origin, Content-Type");
			response.setHeader("Access-Control-Expose-Headers", "X-Requested-With, Authorization, Origin, Content-Type");
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		String origin = response.getHeader("Access-Control-Allow-Origin");
//		if(origin == null) {
//			response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//
//			// without this header jquery.ajax calls returns 401 even after successful login
//			// and SSESSIONID being succesfully stored.
//			response.setHeader("Access-Control-Allow-Credentials", "true");
//
//			response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
//			response.setHeader("Access-Control-Max-Age", "3600");
//			response.setHeader("Access-Control-Allow-Headers",
//					"X-Requested-With, Authorization, Origin, Content-Type");
//			response.setHeader("Access-Control-Expose-Headers", "X-Requested-With, Authorization, Origin, Content-Type");
//		}
//		System.out.println("need to hadnle response here");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {
	}
}
