package com.goldendays.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldendays.contract.IUserService;
import com.goldendays.contract.modelobjects.JwtToken;
import com.goldendays.contract.modelobjects.Person;
import com.goldendays.dao.contract.PersonDao;
import com.goldendays.dao.contract.TokenDao;

@Service
public class UserService implements IUserService{

	@Autowired
    PersonDao personDao;
	@Autowired
    TokenDao tokenDao;
 
	@Override
    public Person find(BigDecimal personId) {
    	return personDao.find(personId);
    }
    @Override
    public Person findByEmail(String email) {
    	return personDao.findByEmail(email);
    }    
    @Override
    public List < Person > findAll() {
    	return personDao.findAll();
    }
    @Override
	public ArrayList<Person> findByEmailAndPass(Person person) {
    	return personDao.findByEmailAndPass(person);
	}
    
    @Override
    public int addPerson(Person person) {
    	return personDao.addPerson(person);
    }
    @Override
    public int editPerson(Person person, BigDecimal personId) {
       return personDao.editPerson(person, personId);
    } 
    @Override
    public int deletePerson(BigDecimal personId) {
    	return personDao.deletePerson(personId);
    }
    
    
	@Override
	public int checkToken(JwtToken jwtToken){
		return tokenDao.checkToken(jwtToken);
	}
	@Override
	public int addToken(JwtToken jwtToken) {
		return tokenDao.addToken(jwtToken);
	}
	@Override
	public int deleteToken(JwtToken jwtToken) {
		return tokenDao.deleteToken(jwtToken);
	}
	

}
