package com.goldendays.webservice.contract;

import javax.jws.WebService;

import com.goldendays.webservice.implementation.temporarybean.Person;
@WebService
public interface IDummyWebService {

	public boolean addPerson(Person p);

	public boolean deletePerson(int id);

	public Person getPerson(int id);

	public Person[] getAllPersons();

}
