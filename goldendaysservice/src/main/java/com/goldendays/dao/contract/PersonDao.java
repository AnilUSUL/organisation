package com.goldendays.dao.contract;

import java.util.List;

import com.goldendays.contract.modelobjects.Person;
 

public interface PersonDao {
 
    public void addPerson(Person person);
 
    public void editPerson(Person person, int personId);
 
    public void deletePerson(int personId);
 
    public Person find(int personId);
 
    public List < Person > findAll();
}