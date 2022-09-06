package com.qa.main.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.qa.main.entities.Customer;

@RestController
public class CustomerController {

	// Temporary Storage, until we implement the real database
	private List<Customer> customers = new ArrayList<>();
	
	// Get Requests (READ)
	@GetMapping("/getAll")
	public List<Customer> getAll() {
		return customers;
	}
	
	// Post Requests (CREATE)
	@PostMapping("/create")
	public Customer create(@RequestBody Customer input) {
		customers.add(input);
		
		return customers.get(customers.size() - 1);
	}
	
	// Put Requests (UPDATE)
	
	
	// Delete Requests (DELETE)
	
}
