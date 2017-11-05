package com.goldendays.webservice.implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;

import com.goldendays.webservice.contract.IDummyWebService;
import com.goldendays.webservice.implementation.temporarybean.Person;

@WebService(endpointInterface = "com.goldendays.webservice.contract.IDummyWebService")
public class DummyWebService implements IDummyWebService {
	private static Map<Integer, Person> persons = new HashMap<Integer, Person>();

	public boolean addPerson(Person p) {
		if (persons.get(p.getId()) != null)
			return false;
		persons.put(p.getId(), p);
		return true;
	}

	public boolean deletePerson(int id) {
		if (persons.get(id) == null)
			return false;
		persons.remove(id);
		return true;
	}

	public Person getPerson(int id) {
		return persons.get(id);
	}

	public Person[] getAllPersons() {
		Set<Integer> ids = persons.keySet();
		Person[] p = new Person[ids.size()];
		int i = 0;
		for (Integer id : ids) {
			p[i] = persons.get(id);
			i++;
		}
		return p;
	}

}
