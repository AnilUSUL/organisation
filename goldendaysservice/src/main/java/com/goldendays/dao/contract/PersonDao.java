package com.goldendays.dao.contract;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.goldendays.contract.modelobjects.Person;
 

public interface PersonDao {
 
    public int addPerson(Person person);
    
    public Person findByEmail(String email);
 
    public int editPerson(Person person, BigDecimal personId);
 
    public int deletePerson(BigDecimal personId);
 
    public Person find(BigDecimal personId);
 
    public List < Person > findAll();

	public ArrayList<Person> findByEmailAndPass(Person person);

}