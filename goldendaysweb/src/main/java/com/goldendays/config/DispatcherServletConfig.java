package com.goldendays.config;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebMvc
@ComponentScan({ "com.goldendays.controller", "com.goldendays.restcontroller", "com.goldendays.implementation" })
public class DispatcherServletConfig extends WebMvcConfigurerAdapter {
	// Here is the equivalent DispatcherServlet registration logic,
	// WebApplicationInitializer-style:

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/assets/**").addResourceLocations("/resources/");
		// .setCachePeriod(31556926);
		registry.addResourceHandler("/resources/css/**").addResourceLocations("/resources/");
		// .setCachePeriod(31556926);
		registry.addResourceHandler("/resources/js/**").addResourceLocations("/resources/");
		// .setCachePeriod(31556926);
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof AbstractJackson2HttpMessageConverter) {
				AbstractJackson2HttpMessageConverter c = (AbstractJackson2HttpMessageConverter) converter;
				ObjectMapper objectMapper = c.getObjectMapper();
				objectMapper.setSerializationInclusion(Include.NON_NULL);
			}
		}
		super.extendMessageConverters(converters);
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/contactdb");
		dataSource.setUsername("root");
		dataSource.setPassword("P@ssw0rd");

		return dataSource;
	}

}
