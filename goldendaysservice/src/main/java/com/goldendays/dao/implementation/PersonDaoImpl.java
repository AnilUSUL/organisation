package com.goldendays.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.goldendays.contract.modelobjects.Person;
import com.goldendays.dao.contract.PersonDao;


@Component
public class PersonDaoImpl implements PersonDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void addPerson(Person person) {
		jdbcTemplate.update("INSERT INTO DENEME_TABLE (id, user, mail, pass) VALUES (?, ?, ?, ?)",
				person.getId(), person.getUser(), person.getMail(), person.getPass());
		System.out.println("Person Added!!");
	}

	public void editPerson(Person person, int id) {
		jdbcTemplate.update("UPDATE DENEME_TABLE SET user = ? , mail = ? , pass = ? WHERE id = ? ",
				person.getId(), person.getUser(), person.getMail(), id);
		System.out.println("Person Updated!!");
	}

	public void deletePerson(int personId) {
		jdbcTemplate.update("DELETE from DENEME_TABLE WHERE id = ? ", personId);
		System.out.println("Person Deleted!!");
	}

	public Person find(int personId) {
		Person person = (Person) jdbcTemplate.queryForObject("SELECT * FROM DENEME_TABLE where id = ? ",
				new Object[] { personId }, new BeanPropertyRowMapper(Person.class));

		return person;
	}

	public List<Person> findAll() {
		List<Person> persons = jdbcTemplate.query("SELECT * FROM DENEME_TABLE", new BeanPropertyRowMapper(Person.class));
		return persons;
	}
}
