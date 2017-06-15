package com.goldendays.implementation;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldendays.contract.IDummyService;

@Service
public class DummyService implements IDummyService{

	@Autowired
	DataSource dataSource;
	
	public String getDatasource (){
		return dataSource.toString();
	}
	public String getString (){
		return "Service";
	}
}
