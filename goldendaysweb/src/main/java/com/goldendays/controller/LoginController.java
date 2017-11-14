package com.goldendays.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goldendays.formbean.LoginBean;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login")
	public ModelAndView login() {
		LoginBean loginBean = new LoginBean();
		ModelAndView model = new ModelAndView("login","loginUrl","http://localhost:8080/goldendaysweb/loginSubmit");
		model.addObject("loginBean", loginBean);
		return model;
	}
	
	@RequestMapping(value = "/loginSubmit")
	public ModelAndView loginSubmit(LoginBean loginBean) {
		ModelAndView model ;
		//authentication
			//true
			model = new ModelAndView("login","loginUrl","http://localhost:8080/goldendaysweb/mainPage");
			//false
			model = new ModelAndView("login","loginUrl","http://localhost:8080/goldendaysweb/loginSubmit");
			
		return model;
	}

}