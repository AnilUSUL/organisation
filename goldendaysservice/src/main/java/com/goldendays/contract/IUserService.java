package com.goldendays.contract;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.goldendays.contract.modelobjects.JwtToken;
import com.goldendays.contract.modelobjects.Person;

public interface IUserService {

	//Find User Functions
	public Person find(BigDecimal personId);
	public Person findByEmail(String email);
	public ArrayList<Person> findByEmailAndPass(Person person);
	public List<Person> findAll();
	
	//Manipulate User Data
	public int addPerson(Person person);
	public int editPerson(Person person, BigDecimal personId);
	public int deletePerson(BigDecimal personId);

	//SecurityToken
	public int checkToken(JwtToken jwtToken);
	public int addToken(JwtToken jwtToken);
	public int deleteToken(JwtToken jwtToken);
}
