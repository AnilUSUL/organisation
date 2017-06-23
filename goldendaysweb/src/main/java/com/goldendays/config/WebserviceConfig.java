package com.goldendays.config;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.http.HttpDestinationFactory;
import org.apache.cxf.transport.servlet.ServletDestinationFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.goldendays.webservice.implementation.BaeldungImpl;

@Configuration
@ComponentScan("com.goldendays.webservice")
public class WebserviceConfig {

	@Bean
	public SpringBus cxf() {
		SpringBus bus = new SpringBus();
		ServletDestinationFactory destinationFactory = new ServletDestinationFactory();
		bus.setExtension(destinationFactory, HttpDestinationFactory.class);
		return bus;
	}

	@Bean
	public EndpointImpl endpoint() {
		EndpointImpl endpoint = new EndpointImpl(cxf(), new BaeldungImpl());
		endpoint.setAddress("/baeldung");
		endpoint.publish();
		return endpoint;
	}
}
