package com.goldendays.webservice.implementation;

import javax.jws.WebService;

import com.goldendays.webservice.contract.Baeldung;
import com.goldendays.webservice.implementation.temporarybean.Student;

@WebService(endpointInterface = "com.goldendays.webservice.contract.Baeldung")
public class BaeldungImpl implements Baeldung {
	private int counter;

	public String hello(String name) {
		return "Hello " + name + "!";
	}

	public String register(Student student) {
		counter++;
		return student.getName() + " is registered student number " + counter;
	}
}
