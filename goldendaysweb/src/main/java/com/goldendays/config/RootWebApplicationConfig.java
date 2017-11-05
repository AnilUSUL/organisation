package com.goldendays.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;

@Configuration
@Import({ WebserviceConfig.class})
public class RootWebApplicationConfig {
	// logback's filter for easy logging info. e.g client's IP
	@Bean
	public MDCInsertingServletFilter mdcInsertingServletFilter() {
		return new MDCInsertingServletFilter();
	}
}
