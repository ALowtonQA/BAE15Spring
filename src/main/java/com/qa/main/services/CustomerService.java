package com.qa.main.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.qa.main.entities.Customer;
import com.qa.main.repos.CustomerRepo;

@Service
public class CustomerService {

	private CustomerRepo repo;
		
	public CustomerService(CustomerRepo repo) {
		super();
		this.repo = repo;
	}

	public Customer create(Customer input) {
		return repo.saveAndFlush(input);
	}
	
	public List<Customer> getAll() {
		return repo.findAll();
	}
	
	public Customer getById(long id) {
		return repo.findById(id).get();
	}
	
	public List<Customer> getByFirstName(String firstName) {
		return repo.findCustomerByFirstName(firstName);
	}
	
	public Customer update(long id, Customer input) {
		Customer existing = repo.findById(id).get();
		
		existing.setFirstName(input.getFirstName());
		existing.setLastName(input.getLastName());
		existing.setAge(input.getAge());
		
		return repo.saveAndFlush(existing);
	}
	
	public boolean delete(long id) {
		// Deletes the entry by ID
		repo.deleteById(id);
		
		// Checks if the entry exists by ID
		return !repo.existsById(id);
	}
}
