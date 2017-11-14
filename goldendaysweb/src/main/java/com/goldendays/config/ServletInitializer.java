package com.goldendays.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
 * Application initilizer class
 */
@Configuration
@EnableWebMvc
public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/*
	 * org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet
	 * .ServletContext)
	 */

	public void onStartup(ServletContext servletContext) throws ServletException {
		// createRootContext(servletContext);
		// createDispatcherServletContext(servletContext);
		super.onStartup(servletContext);
		createWebServiceServletContext(servletContext);
		// addFilter(servletContext);

	}

	/*
	 * Application context private void createRootContext(ServletContext
	 * container) { // Create the 'root' Spring application context
	 * AnnotationConfigWebApplicationContext rootContext = new
	 * AnnotationConfigWebApplicationContext();
	 * rootContext.register(RootWebApplicationConfig.class); // <-- Use //
	 * RootWebApplicationConfig.java
	 * 
	 * // Manage the lifecycle of the root application context
	 * container.addListener(new ContextLoaderListener(rootContext)); }
	 */
	/*
	 * Creating adispatcher servlet, context, mapping, loadorder.
	 *
	 * private void createDispatcherServletContext(ServletContext container) {
	 * // Create the dispatcher servlet's Spring application context
	 * AnnotationConfigWebApplicationContext dispatcherContext = new
	 * AnnotationConfigWebApplicationContext();
	 * dispatcherContext.register(WebAppConfig.class); // <-- Use //
	 * DispatcherServletConfig.java
	 * 
	 * // Define mapping between <servlet> and <servlet-mapping>
	 * ServletRegistration.Dynamic dispatcher =
	 * container.addServlet("dispatcherServlet", new
	 * DispatcherServlet(dispatcherContext)); dispatcher.addMapping("/");
	 * dispatcher.setLoadOnStartup(1); }
	 */
	/*
	 * Apache - CXF WebService, CXFServlet, mapping, loadorder
	 */
	private void createWebServiceServletContext(ServletContext container) {

		String servletName = "CXFServlet";
		Assert.hasLength(servletName, "servletName must not return empty or null");
		
		ServletRegistration.Dynamic registration = container.addServlet("CXFServlet", new CXFServlet());
		Assert.notNull(registration, "Failed to register servlet with name '" + servletName + "'."
				+ "Check if there is another servlet registered under the same name.");

		registration.setLoadOnStartup(2);
		registration.addMapping("/ws/*");
		// registration.setAsyncSupported(isAsyncSupported());

		// Filter[] filters = getServletFilters();
		// if (!ObjectUtils.isEmpty(filters)) {
		// for (Filter filter : filters) {
		// registerServletFilter(servletContext, filter);
		// }
		// }

		customizeRegistration(registration);

		// Create the dispatcher servlet's Spring application context
		// ServletRegistration.Dynamic dispatcher =
		// container.addServlet("CXFServlet", new CXFServlet());
		// dispatcher.addMapping("/ws/*");
		// dispatcher.setLoadOnStartup(2);
	}

	/*
	 * filter configuration void addFilter(ServletContext container) { String
	 * filterName = "WhatEverYouWantToNameYourFilter"; String filterBeanName =
	 * "mdcInsertingServletFilter"; container.addFilter(filterName, new
	 * DelegatingFilterProxy(filterBeanName)).addMappingForUrlPatterns(null,
	 * false, "/"); }
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { WebSecurityConfig.class, RepositoryConfig.class, WebserviceConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebAppConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
