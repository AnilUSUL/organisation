package com.goldendays.contract;

import java.math.BigDecimal;
import java.util.List;

import com.goldendays.contract.modelobjects.Person;

public interface IDummyService {

	public void addPerson(Person person);

	public void editPerson(Person person, BigDecimal personId);

	public void deletePerson(BigDecimal personId);

	public Person find(BigDecimal personId);

	public List<Person> findAll();
}


