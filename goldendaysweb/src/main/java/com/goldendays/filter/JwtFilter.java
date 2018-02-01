package com.goldendays.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.GenericFilterBean;

import com.goldendays.config.constant.ConfigConstant;
import com.goldendays.config.util.JwtUtil;
import com.goldendays.contract.IUserService;

import io.jsonwebtoken.SignatureException;

@Order(value=2)
public class JwtFilter extends GenericFilterBean {
	
	@Autowired
	IUserService userservice;
	
	/**
	 * Authentication Filter
	 */
	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest request = (HttpServletRequest) req;
		
//		Arrays.asList(ConfigConstant.Directions.values());
		
		if(request.getRequestURL().toString().contains(ConfigConstant.LOGIN_SOURCE_URL_SUFFIX) 
		   || request.getRequestURL().toString().contains(ConfigConstant.LOGIN_REST_URL_SUFFIX)
		   || request.getRequestURL().toString().contains(ConfigConstant.SINGUP_SOURCE_URL_SUFFIX)
		   || request.getRequestURL().toString().contains(ConfigConstant.SINGUP_REST_URL_SUFFIX)
			 ){
			chain.doFilter(req, res);
		}else{

			String authHeaderToken = request.getParameter("token");
			final HttpServletResponse response = (HttpServletResponse) res;
//			final String authHeader = request.getHeader("authorization");
			
			//cors filter and  which type of method will be allowed
			if ("OPTIONS".equals(request.getMethod())) {
				response.setStatus(HttpServletResponse.SC_OK);
				chain.doFilter(req, res);
			} else {
				
				/*
				if (authHeader == null || !authHeader.startsWith("Bearer ")) {
					throw new ServletException("Missing or invalid Authorization header");
				}
				
				String authHeaderToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmlsdXN1bEBnbWFpbC5jb20iLCJyb2xlcyI6InVzZXIiLCJpYXQiOjE1MTEyMDI4OTh9.f6DGMKEm0XscpTm_ckdfKrOOnWquLRkhfaO4BjWRq8M";
				final String token = authHeaderToken.substring(7);
				*/
				
				try {
					JwtUtil.parseJWT(userservice, authHeaderToken);
				}catch (final SignatureException e) {
					throw new ServletException("Invalid token");
				}
				/*
				try {
					final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(authHeaderToken).getBody();
					request.setAttribute("claims", claims);
				} catch (final SignatureException e) {
					throw new ServletException("Invalid token");
				}
				*/
				chain.doFilter(req, res);
			}
		}
		
	}
}