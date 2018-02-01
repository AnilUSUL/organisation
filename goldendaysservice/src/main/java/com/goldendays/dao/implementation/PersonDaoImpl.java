package com.goldendays.dao.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.goldendays.contract.modelobjects.Person;
import com.goldendays.dao.contract.PersonDao;


@Component
public class PersonDaoImpl implements PersonDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	/**
	 * 
	 */
	@Override
	public Person find(BigDecimal personId) {
		Person person = (Person) jdbcTemplate.queryForObject("SELECT * FROM user_main_info where id = ? ",
				new Object[] { personId }, new BeanPropertyRowMapper(Person.class));

		return person;
	}
	
	/**
	 * 
	 */
	@Override
	public Person findByEmail(String email){
		 Person person = (Person) jdbcTemplate.queryForObject("SELECT * FROM user_main_info where id = ? ",
					new Object[] { email }, new BeanPropertyRowMapper(Person.class));

		return person;
	 }
	
	/**
	 * 
	 */
	@Override
	public ArrayList<Person> findByEmailAndPass(Person pers) {
		ArrayList<Person> lst = (ArrayList<Person>) jdbcTemplate.query("SELECT * FROM user_main_info where email = ? AND password = ? ",
				new Object[] { pers.getEmail(),pers.getPassword() }, new BeanPropertyRowMapper(Person.class)) ;
		if(lst != null){
			return lst;
		}
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public List<Person> findAll() {
		List<Person> persons = jdbcTemplate.query("SELECT * FROM user_main_info", new BeanPropertyRowMapper(Person.class));
		return persons;
	}
	
	/**
	 * 
	 */
	@Override
	public int addPerson(Person person) {
		int rowaffected = jdbcTemplate.update("INSERT INTO user_main_info (id, name, email, password) VALUES (?, ?, ?, ?)",
				person.getId(), person.getName(), person.getEmail(), person.getPassword());
		System.out.println("Person Added!!");
		return rowaffected;
	}
	@Override
	public int editPerson(Person person, BigDecimal id) {
		int rowaffected = jdbcTemplate.update("UPDATE user_main_info SET user = ? , email = ? , password = ? WHERE id = ? ",
				person.getId(), person.getName(), person.getPassword(), id);
		System.out.println("Person Updated!!");
		return rowaffected;
	}
	
	/**
	 * 
	 */
	@Override
	public int deletePerson(BigDecimal personId) {
		int rowaffected = jdbcTemplate.update("DELETE from user_main_info WHERE id = ? ", personId);
		System.out.println("Person Deleted!!");
		return rowaffected;
	}
	

}
