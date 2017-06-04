package com.goldendays.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class ServletDispatcherInitilizer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {
		useRootContext(container);
		useDispatcherContext(container);
		addFilter(container);
	}

	private void useRootContext(ServletContext container) {
		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(RootConfig.class); // <-- Use RootConfig.java

		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(rootContext));
	}

	private void useDispatcherContext(ServletContext container) {
		// Create the dispatcher servlet's Spring application context
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(DispatcherConfig.class); // <-- Use
															// DispatcherConfig.java

		// Define mapping between <servlet> and <servlet-mapping>
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher",
				new DispatcherServlet(dispatcherContext));
		dispatcher.addMapping("/");
		dispatcher.setLoadOnStartup(1);
	}

	private void addFilter(ServletContext container) {
		String filterName = "WhatEverYouWantToNameYourFilter";
		String filterBeanName = "mdcInsertingServletFilter";
		container.addFilter(filterName, new DelegatingFilterProxy(filterBeanName)).addMappingForUrlPatterns(null, false,
				"/");
	}
}
