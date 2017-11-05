package com.goldendays.contract.modelobjects;

public class Person {

	private Integer id;
	private String user;
	private String mail;
	private String pass;

	public Person(Integer id, String user, String mail, Integer pass) {

	}

	public Person() {

	}

	public Integer getId() {
		return id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass() {
		return pass;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person Id:- " + getId() + " First Name:- " + getUser() + " Last Name:- " + getMail() + " Age:- "
				+ getPass());
		return builder.toString();
	}

}