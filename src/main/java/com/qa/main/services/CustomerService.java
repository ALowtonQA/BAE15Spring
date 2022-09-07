package com.qa.main.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.qa.main.entities.Customer;

@Service
public class CustomerService {

	// Temporary Storage, until we implement the real database
	private List<Customer> customers = new ArrayList<>();
	
	public Customer create(Customer input) {
		customers.add(input);
		
		return customers.get(customers.size() - 1);
	}
	
	public List<Customer> getAll() {
		return customers;
	}
	
	public Customer getById(int id) {
		return customers.get(id);
	}
	
	public Customer update(int id, Customer input) {
		// Remove original user
		customers.remove(id);
		
		// Add the updated user in the same position
		customers.add(id, input);
		
		// Get the user by id to check it's been updated
		return customers.get(id);
		
	}
	
	public Customer delete(int id) {
		return this.customers.remove(id);
	}
}
