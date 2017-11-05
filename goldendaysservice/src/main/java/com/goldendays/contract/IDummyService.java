package com.goldendays.contract;

import java.util.List;

import com.goldendays.contract.modelobjects.Person;

public interface IDummyService {

	public void addPerson(Person person);

	public void editPerson(Person person, int personId);

	public void deletePerson(int personId);

	public Person find(int personId);

	public List<Person> findAll();
}


