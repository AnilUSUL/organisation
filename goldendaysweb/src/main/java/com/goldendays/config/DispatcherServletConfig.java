package com.goldendays.config;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;


/*
 * This class is implemented to configure DispatcherServlet.
 * Here is the equivalent DispatcherServlet registration logic.
 */
@Configuration
@EnableWebMvc
@ComponentScan({ "com.goldendays.controller", "com.goldendays.restcontroller" ,"com.goldendays.dao.implementation", "com.goldendays.implementation"})
@PropertySource(value = { "classpath:application.properties" })
public class DispatcherServletConfig extends WebMvcConfigurerAdapter {
	
	/* 
	 * This is created to get jsp files in project directory
	 */
	@Bean 
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	/* 
	 * Handling project static files by mapped path(non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/assets/**").addResourceLocations("/resources/");
		// .setCachePeriod(31556926);
		registry.addResourceHandler("/resources/css/**").addResourceLocations("/resources/");
		// .setCachePeriod(31556926);
		registry.addResourceHandler("/resources/js/**").addResourceLocations("/resources/");
		// .setCachePeriod(31556926);
	}

	/*
	 * reason of json based rest controller can be working, this method implemented(non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#extendMessageConverters(java.util.List)
	 */
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
	
	@Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.mysql.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.mysql.url"));
        dataSource.setUsername(env.getProperty("jdbc.mysql.username"));
        dataSource.setPassword(env.getProperty("jdbc.mysql.password"));
        // dataSource.setDefaultAutoCommit(false);
        return dataSource;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }

}
