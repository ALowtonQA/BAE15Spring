package com.qa.main.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.main.entities.Customer;
import com.qa.main.exceptions.CustomerNotFoundException;
import com.qa.main.repos.CustomerRepo;

@SpringBootTest
public class CustomerServiceUnitTest {
	
	@Autowired
	private CustomerService service;
	
	@MockBean
	private CustomerRepo repo;
	
	@Test
	public void testCreate() {
		// Create and object for saving
		Customer input = new Customer("Anoush", "Lowton", 29);
		
		// Create an object for the result
		Customer result = new Customer(2L, "Anoush", "Lowton", 29);
		
		Mockito.when(repo.saveAndFlush(input)).thenReturn(result);
		
		// assertEquals(expected, actual);
		assertEquals(result, service.create(input));
	}
	
	@Test
	public void testGetAll() {
		// Create and object for saving
		List<Customer> result = new ArrayList<>();
		result.add(new Customer(1L, "Anoush", "Lowton", 29));

		Mockito.when(repo.findAll()).thenReturn(result);
		
		assertEquals(result, service.getAll());
	}
	
	@Test
	public void getByIdTest() {
		Optional<Customer> OptionalOutput = Optional.of(new Customer(1L, "Anoush", "Lowton", 29));
		Customer output = new Customer(1L, "Anoush", "Lowton", 29);
		
		Mockito.when(repo.findById(1L)).thenReturn(OptionalOutput);
		
		assertEquals(output, service.getById(1L));
	}
	
	@Test
	public void getByIdFailTest() {
		Optional<Customer> OptionalOutput = Optional.empty();
		
		Mockito.when(repo.findById(1L)).thenReturn(OptionalOutput);
		
		assertThrows(CustomerNotFoundException.class, () -> service.getById(1L));
	}
	
	@Test
	public void testGetByFirstName() {
		// Create and object for saving
		List<Customer> result = new ArrayList<>();
		result.add(new Customer(1L, "Anoush", "Lowton", 29));

		Mockito.when(repo.findCustomerByFirstName("Anoush")).thenReturn(result);
		
		assertEquals(result, service.getByFirstName("Anoush"));
	}
	
	@Test
	public void updateTest() {
		Customer input = new Customer("Bob", "Lowton", 30);
		Optional<Customer> existing = Optional.of(new Customer(1L, "Anoush", "Lowton", 29));
		Customer output = new Customer(1L, "Bob", "Lowton", 30);
		
		Mockito.when(this.repo.findById(1L)).thenReturn(existing);
		Mockito.when(this.repo.saveAndFlush(output)).thenReturn(output);
		
		assertEquals(output, this.service.update(1L, input));

	}
	
	@Test
	public void deleteTrueTest() {
		Mockito.when(this.repo.existsById(1L)).thenReturn(false);
		
		assertTrue(this.service.delete(1L));
	}
	
	@Test
	public void deleteFalseTest() {
		Mockito.when(this.repo.existsById(1L)).thenReturn(true);
		
		assertFalse(this.service.delete(1L));
	}
}
