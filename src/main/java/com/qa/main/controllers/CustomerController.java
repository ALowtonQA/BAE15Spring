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
import com.qa.main.services.CustomerService;

@RestController
public class CustomerController {
	
	private CustomerService service;
	
	public CustomerController(CustomerService service) {
		super();
		this.service = service;
	}

	// Post Requests (CREATE)
	@PostMapping("/create")
	public Customer create(@RequestBody Customer input) {
		return service.create(input);
	}
	
	// Get Requests (READ)
	@GetMapping("/getAll")
	public List<Customer> getAll() {
		return service.getAll();
	}
	
	@GetMapping("/getById/{id}")
	public Customer getById(@PathVariable long id) {
		return service.getById(id);
	}
	
	// Put Requests (WHOLE UPDATE)
	@PutMapping("/update/{id}")
	public Customer update(@PathVariable long id, @RequestBody Customer input) {
		return service.update(id, input);
	}
	
	@DeleteMapping("/delete/{id}")
	public boolean delete(@PathVariable long id) {
		return service.delete(id);
	}
}
