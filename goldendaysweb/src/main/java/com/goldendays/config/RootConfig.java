package com.goldendays.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;

@Configuration
@EnableWebMvc
public class RootConfig {
	// logback's filter for easy logging info. e.g client's IP
	@Bean
	public MDCInsertingServletFilter mdcInsertingServletFilter() {
		return new MDCInsertingServletFilter();
	}
}
