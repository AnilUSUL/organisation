package com.goldendays.config;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goldendays.webservice.contract.Baeldung;

@Configuration
public class WebServiceClientConfig {

	@Bean(name = "client")
	public Object generateProxy() {
		return proxyFactoryBean().create();
	}

	@Bean
	public JaxWsProxyFactoryBean proxyFactoryBean() {
		JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
		proxyFactory.setServiceClass(Baeldung.class);
		proxyFactory.setAddress("http://localhost:8080/services/baeldung");
		return proxyFactory;
	}
}
