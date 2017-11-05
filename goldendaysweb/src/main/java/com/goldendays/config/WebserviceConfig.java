package com.goldendays.config;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.transport.http.HttpDestinationFactory;
import org.apache.cxf.transport.servlet.ServletDestinationFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
	public boolean  m() {
		 WebServiceUtil.m(1);
		 return true;
	}

}

//@Bean
//public EndpointImpl endpoint() {
//	EndpointImpl endpoint = new EndpointImpl(cxf(), new BaeldungImpl());
//	endpoint.setAddress("/baeldung");
//	endpoint.publish();
//	return endpoint;
//}

//@Bean(name = "endpoint")
//public <E extends BaseGenericService> void endpoint(GenericService<E> clazz) {
//	System.out.println("Starting Server");
//	String address = clazz.getServiceClazz().getServiceMapping();
//	Endpoint.publish(address, clazz);
//}