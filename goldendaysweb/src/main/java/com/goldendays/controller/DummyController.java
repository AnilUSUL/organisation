package com.goldendays.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goldendays.implementation.DummyService;

@Controller
public class DummyController {
	@Autowired
	DummyService dummyService;

	// @RequestMapping(value = "/getDatasource")
	// public ModelAndView getDatasource(String name) {
	// ModelAndView model = new ModelAndView("HelloWorldPage");
	// model.addObject("msg", dummyService.getDatasource());
	// return model;
	// }

	@RequestMapping(value = "/greet")
	public ModelAndView greet(String name) {
		ModelAndView model = new ModelAndView("HelloWorldPage");
		model.addObject("msg", "hello world");
		return model;
	}

	@RequestMapping(value = "/service")
	public ModelAndView service(String name) {
		ModelAndView model = new ModelAndView("HelloWorldPage");
		model.addObject("msg", dummyService.getString());
		return model;
	}

}