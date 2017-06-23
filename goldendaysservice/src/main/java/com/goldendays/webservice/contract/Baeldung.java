package com.goldendays.webservice.contract;

import javax.jws.WebService;

import com.goldendays.webservice.implementation.temporarybean.Student;

@WebService
public interface Baeldung {
	String hello(String name);
	String register(Student student);
}