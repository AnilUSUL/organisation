package com.goldendays.implementation;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldendays.contract.IDummyService;
import com.goldendays.contract.modelobjects.Person;
import com.goldendays.dao.contract.PersonDao;

@Service
public class DummyService implements IDummyService {

    @Autowired
    PersonDao personDao;
 
    public void addPerson(Person person) {
        personDao.addPerson(person);
 
    }
 
    public void editPerson(Person person, BigDecimal personId) {
        personDao.editPerson(person, personId);
    }
 
    public void deletePerson(BigDecimal personId) {
        personDao.deletePerson(personId);
    }
 
    public Person find(BigDecimal personId) {
        return personDao.find(personId);
    }
 
    public List < Person > findAll() {
        return personDao.findAll();
    }

}
