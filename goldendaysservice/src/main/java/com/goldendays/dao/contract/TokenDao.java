package com.goldendays.dao.contract;

import com.goldendays.contract.modelobjects.JwtToken;
import com.goldendays.contract.modelobjects.Person;

public interface TokenDao {

	public int checkToken(JwtToken jwtToken);

	public int addToken(JwtToken jwtToken);
	
	public int deleteToken(JwtToken jwtToken);
	
}
