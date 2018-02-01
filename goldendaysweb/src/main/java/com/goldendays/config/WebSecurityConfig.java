package com.goldendays.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.filter.GenericFilterBean;

import com.goldendays.filter.JwtFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	@Bean
//    public JwtFilter jwtFilter() {
//        return new JwtFilter();
//    }
//	
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.antMatcher("/web/**").addFilter(jwtFilter(),BasicAuthenticationFilter.class)
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
	
	
	/*@Autowired
	DataSource dataSource;
	@Autowired
	 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		  auth
		   .jdbcAuthentication()
		   .dataSource(dataSource).getUserDetailsService();
		auth
	   .jdbcAuthentication()
	   .dataSource(dataSource)
	   .usersByUsernameQuery("SELECT USER_NAME,EMAIL,PASSWORD  FROM  username"
	   		+ "select username,password,enabled from users where username=?")
	   .authoritiesByUsernameQuery("select username, role from user_roles where username=?");
	 } 
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	   http.authorizeRequests()
	  .antMatchers("/hello")
	  .access("hasRole('ROLE_ADMIN')")  
	  .anyRequest().permitAll()
	  .and()
	  .formLogin().loginPage("/login")
	  .usernameParameter("username")
	  .passwordParameter("password")
	  .and()
	  .logout()
	  .logoutSuccessUrl("/login?logout") 
	  .and()
	  .exceptionHandling()
	  .accessDeniedPage("/403")
	  .and()
	  .csrf();
	 }
	*/
	/*
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("root123").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN", "DBA");
	}

	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll();
		// http.authorizeRequests().antMatchers("/resources/**", "/signup",
		// "/about").permitAll().antMatchers("/admin/**")
		// .hasRole("ADMIN").antMatchers("/db/**").access("hasRole('ADMIN') and
		// hasRole('DBA')").anyRequest()
		// .authenticated().and().formLogin();
	}
	*/
}
