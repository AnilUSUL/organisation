package com.goldendays.config.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.xml.bind.DatatypeConverter;

import com.goldendays.contract.IUserService;
import com.goldendays.contract.modelobjects.JwtToken;
import com.goldendays.dto.JwtTokenClaimDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JwtUtil {
	
	private static String secretKey="secretkey";
	
	
	//Sample method to construct a JWT
	public static String createJWT(IUserService userService,JwtTokenClaimDto tokenClaimDto) {
	 
		String token;
		Date createDate = Calendar.getInstance(TimeZone.getTimeZone("UTC+03:00")).getTime();
		Date invalidateDate = new Date(Long.sum(Calendar.getInstance(TimeZone.getTimeZone("UTC+03:00")).getTime().getTime(),tokenClaimDto.getTtlMillis()));
		
		Map<String, Object> headerMap = new HashMap<String, Object>();
		Claims claims  = Jwts.claims();
		
		//HEADER
		headerMap.put("typ","JWT");
		headerMap.put("alg","HS256");
		
		//BODY
		claims.setId(tokenClaimDto.getId());//userid
		claims.setIssuer(tokenClaimDto.getIssuer());//"dirtywall.com"
		claims.setSubject(tokenClaimDto.getEmail());
		claims.setExpiration(invalidateDate);
		claims.setIssuedAt(createDate);
		
		//SIGNATURE
		JwtBuilder jwtBuilder =Jwts.builder();
		jwtBuilder.setHeader(headerMap);
		jwtBuilder.setClaims(claims);
		jwtBuilder.signWith(SignatureAlgorithm.HS256,"secretkey");
		
		
		token = jwtBuilder.compact();
		//save jwt infos to db
		JwtToken jwtToken = new JwtToken();
		jwtToken.setId(tokenClaimDto.getId());//userid
		jwtToken.setIssuer(tokenClaimDto.getIssuer());//"dirtywall.com"
		jwtToken.setSubject(tokenClaimDto.getEmail());
		jwtToken.setExpiration(invalidateDate);
		jwtToken.setIssuedAt(createDate);
		jwtToken.setJwtToken(jwtBuilder.compact());
		userService.addToken(jwtToken);
		
		return token;
	}
	
	//Sample method to validate and read the JWT
	public static JwtToken parseJWT(IUserService userService, String jwt) throws ServletException {
	 
	    //This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser()         
	       .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
	       .parseClaimsJws(jwt).getBody();
	    //Verify at DB record
	    JwtToken jwtToken = new JwtToken();
		jwtToken.setIssuer(claims.getIssuer());//"dirtywall.com"
		jwtToken.setSubject(claims.getSubject());
		jwtToken.setExpiration(claims.getExpiration());
		jwtToken.setIssuedAt(claims.getIssuedAt());
		jwtToken.setJwtToken(jwt);
	    
		int result = userService.checkToken(jwtToken);
	    
		if(result < 1){
			throw new ServletException("Token already invalidated");
		}
		
		return jwtToken;
	}
	
	public static void deleteToken(IUserService userService,JwtToken jwt) throws ServletException {
		
		userService.deleteToken(jwt);
		
	}
	public static void invalidToken(IUserService userService, String jwt, String subject) throws ServletException {
		JwtToken jwtToken ;
		try {
			//token invalid exception throws if the ttl time expired
			//Or login called after 
			jwtToken = JwtUtil.parseJWT(userService, jwt);
			deleteToken(userService, jwtToken);
			System.out.println("Token invalidated");
		}catch(ExpiredJwtException JwtException ){
			jwtToken = new JwtToken();
			jwtToken.setSubject(subject);
			deleteToken(userService, jwtToken);
			System.out.println("Token expired ");
		}catch (ServletException e) {
			jwtToken = new JwtToken();
			jwtToken.setSubject(subject);
			deleteToken(userService, jwtToken);
			System.out.println("Token already invalidated");
		}
		
	}
	
}
