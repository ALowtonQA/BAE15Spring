package com.qa.main.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

	@Id // This is my primary key, so it's unique and not null.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	// camelCase is used for variable names
	// String is the datatype we use for text
	// @Column(nullable = false, unique = true, length = 30) - length is used to limit the max length
	@Column(nullable = false)
	private String firstName; // This makes a column called first_name
	
	@Column(nullable = false)
	private String lastName; // This makes a column called last_name
	
	// Int is the datatype use tend to use for whole numbers
	@Column(nullable = false)
	private int age;

	// Default constructor
	public Customer() {}
	
	// Used for creating
	public Customer(String firstName, String lastName, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	// Used for reading
	public Customer(long id, String firstName, String lastName, int age) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, firstName, id, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return age == other.age && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName);
	}

}
