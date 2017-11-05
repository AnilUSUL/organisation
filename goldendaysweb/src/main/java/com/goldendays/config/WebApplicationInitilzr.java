package com.goldendays.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/*
 * Application initilizer class
 */
@Configuration
@EnableWebMvc
public class WebApplicationInitilzr implements WebApplicationInitializer {

	/*
	 * Application starts from here(non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet
	 * .ServletContext)
	 */
	public void onStartup(ServletContext servletContext) throws ServletException {
		createRootContext(servletContext);
		createDispatcherServletContext(servletContext);
		createWebServiceServletContext(servletContext);
		addFilter(servletContext);

	}

	/*
	 * Application context
	 */
	private void createRootContext(ServletContext container) {
		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(RootWebApplicationConfig.class); // <-- Use
																// RootWebApplicationConfig.java

		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(rootContext));
	}

	/*
	 * Creating adispatcher servlet, context, mapping, loadorder.
	 */
	private void createDispatcherServletContext(ServletContext container) {
		// Create the dispatcher servlet's Spring application context
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(DispatcherServletConfig.class); // <-- Use
																	// DispatcherServletConfig.java

		// Define mapping between <servlet> and <servlet-mapping>
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcherServlet",
				new DispatcherServlet(dispatcherContext));
		dispatcher.addMapping("/");
		dispatcher.setLoadOnStartup(1);
	}

	/*
	 * Apache - CXF WebService, CXFServlet, mapping, loadorder
	 */
	private void createWebServiceServletContext(ServletContext container) {
		// Create the dispatcher servlet's Spring application context
		ServletRegistration.Dynamic dispatcher = container.addServlet("CXFServlet", new CXFServlet());
		dispatcher.addMapping("/ws/*");
		dispatcher.setLoadOnStartup(2);
	}

	/*
	 * filter configuration
	 */
	void addFilter(ServletContext container) {
		String filterName = "WhatEverYouWantToNameYourFilter";
		String filterBeanName = "mdcInsertingServletFilter";
		container.addFilter(filterName, new DelegatingFilterProxy(filterBeanName)).addMappingForUrlPatterns(null, false,
				"/");
	}

}
