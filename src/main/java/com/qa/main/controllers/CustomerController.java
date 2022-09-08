package com.qa.main.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qa.main.entities.Customer;
import com.qa.main.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	private CustomerService service;
	
	public CustomerController(CustomerService service) {
		super();
		this.service = service;
	}

	// Post Requests (CREATE)
	@PostMapping("/create")
	public ResponseEntity<Customer> create(@RequestBody Customer input) {
		return new ResponseEntity<Customer>(service.create(input), HttpStatus.CREATED);
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
	
	@GetMapping("/getByFirstName/{firstName}")
	public List<Customer> getByFirstName(@PathVariable String firstName) {
		return service.getByFirstName(firstName);
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
