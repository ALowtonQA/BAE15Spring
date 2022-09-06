package com.qa.main.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.qa.main.entities.Customer;

@RestController
public class CustomerController {

	// Temporary Storage, until we implement the real database
	private List<Customer> customers = new ArrayList<>();
	
	// Post Requests (CREATE)
	@PostMapping("/create")
	public Customer create(@RequestBody Customer input) {
		customers.add(input);
		
		return customers.get(customers.size() - 1);
	}
	
	// Get Requests (READ)
	@GetMapping("/getAll")
	public List<Customer> getAll() {
		return customers;
	}
	
	@GetMapping("/getById/{id}")
	public Customer getById(@PathVariable int id) {
		return customers.get(id);
	}
	
	// Put Requests (UPDATE)
	@PutMapping("/update/{id}")
	public Customer update(@PathVariable int id, @RequestBody Customer input) {
		// Remove original user
		customers.remove(id);
		
		// Add the updated user in the same position
		customers.add(id, input);
		
		return customers.get(id);
		
	}
	
//	// Delete Requests (DELETE)
//	@DeleteMapping("/delete")
//	public Customer delete() {
//		
//	}
}
