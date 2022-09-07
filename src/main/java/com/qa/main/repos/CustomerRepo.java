package com.qa.main.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.qa.main.entities.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

	// Find by firstName
	// This will return multiple records, so we need a List<Customer>
	// I need a first name to search
	
	// SELECT * FROM customers WHERE first_name = ?1
	List<Customer> findCustomerByFirstName(String firstName);
	
}
