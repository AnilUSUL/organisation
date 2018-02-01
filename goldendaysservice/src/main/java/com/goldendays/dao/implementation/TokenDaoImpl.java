package com.goldendays.dao.implementation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.goldendays.contract.modelobjects.JwtToken;
import com.goldendays.contract.modelobjects.Person;
import com.goldendays.dao.contract.TokenDao;

@Component
public class TokenDaoImpl implements TokenDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int checkToken(JwtToken jwtToken){
		ArrayList<String> strToken =  (ArrayList<String>) jdbcTemplate.query("SELECT * FROM user_token WHERE email=? AND token=?",
				new Object[] { jwtToken.getSubject(),jwtToken.getJwtToken()},new BeanPropertyRowMapper(String.class));
		if(strToken == null || strToken.size()<1){
			return 0;
		}else{
			return strToken.size();			
		}
	}

	public int addToken(JwtToken jwtToken){
		int rowaffected = jdbcTemplate.update("INSERT INTO user_token (id, email, token, time, expiration_time) VALUES (?, ?, ?, ?,?)",
				jwtToken.getId()==null?0:jwtToken.getId(), jwtToken.getSubject(), jwtToken.getJwtToken(),jwtToken.getIssuedAt(),jwtToken.getExpiration());
		System.out.println("Token Added!!");
		return rowaffected;
	}
	
	public int deleteToken(JwtToken jwtToken){
		int rowaffected = jdbcTemplate.update("DELETE FROM user_token  WHERE token = ? OR email = ?",
				jwtToken.getJwtToken(),jwtToken.getSubject());
		System.out.println("Token deleted!!");
		return rowaffected;
	}
	
	
}
