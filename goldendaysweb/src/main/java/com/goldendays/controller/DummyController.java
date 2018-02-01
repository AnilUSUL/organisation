package com.goldendays.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goldendays.contract.modelobjects.Person;
import com.goldendays.implementation.DummyService;

@Controller
public class DummyController extends BaseController{
	@Autowired
	DummyService dummyService;

	// @RequestMapping(value = "/getDatasource")
	// public ModelAndView getDatasource(String name) {
	// ModelAndView model = new ModelAndView("HelloWorldPage");
	// model.addObject("msg", dummyService.getDatasource());
	// return model;
	// }
	
	//page return
	@RequestMapping(value = "/greet")
	public ModelAndView greet(String name) {
		ModelAndView model = new ModelAndView("HelloWorldPage");
		model.addObject("msg", "hello world");
		return model;
	}

	//db_manuplation
	@RequestMapping(value = "/service")
	public ModelAndView service(String name) {
		Person person = new Person();
		
		person.setName("ASLI");
		person.setEmail("asli@gmail.com");
		person.setPassword("asdafasf");
		
		dummyService.addPerson(person);
		List<Person> listPerson = dummyService.findAll();
		
		System.out.println(listPerson.toString());
		listPerson.toString();
		ModelAndView model = new ModelAndView("HelloWorldPage");
		model.addObject("msg",listPerson.toString() );
		return model;
	}

}