package com.goldendays.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goldendays.formbean.UserBean;

@Controller
public class LoginController extends BaseController{
	
	@RequestMapping(value = "/login")
	public ModelAndView login() {
		ModelAndView model = new ModelAndView("login","loginUrl","http://localhost:8080/goldendaysweb/web/loginSubmit");
		return model;
	}
	
	@RequestMapping(value = "/signup")
	public ModelAndView signup() {
		ModelAndView model = new ModelAndView("signup","signup","http://localhost:8080/goldendaysweb/web/signup");
		return model;
	}
	
	@RequestMapping(value = "/loginSubmit")
	public ModelAndView loginSubmit(UserBean userBean) {
		ModelAndView model ;
		//authentication
			//true
			model = new ModelAndView("login","loginUrl","http://localhost:8080/goldendaysweb/mainPage");
			//false
			model = new ModelAndView("login","loginUrl","redirect://localhost:8080/goldendaysweb/web/loginSubmit");
			
		return model;
	}
	
	@RequestMapping(value = "/signupSubmit")
	public ModelAndView signupSubmit(UserBean userBean) {
		ModelAndView model ;
		//authentication
			//true
			model = new ModelAndView("signup","signupUrl","http://localhost:8080/goldendaysweb/mainPage");
			//false
			model = new ModelAndView("signup","signupUrl","redirect://localhost:8080/goldendaysweb/web/loginSubmit");
			
		return model;
	}
	

}