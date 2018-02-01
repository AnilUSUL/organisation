package com.goldendays.dto;

public class JwtTokenClaimDto {

	private String id;
	private String issuer;
	private String email;
	private long ttlMillis;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getTtlMillis() {
		return ttlMillis;
	}
	public void setTtlMillis(long ttlMillis) {
		this.ttlMillis = ttlMillis;
	}
	
	
	
}
