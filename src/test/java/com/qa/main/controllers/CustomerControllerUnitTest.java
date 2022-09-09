package com.qa.main.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.main.entities.Customer;
import com.qa.main.services.CustomerService;

@WebMvcTest
public class CustomerControllerUnitTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private CustomerService service;
	
	@Test
	public void createTest() throws Exception {
		// An object for sending in the body of the request
		Customer input = new Customer("Bob", "Lowton", 30);
		String inputAsJSON = mapper.writeValueAsString(input);
		
		// An object for checking the response
		Customer response = new Customer(2L, "Bob", "Lowton", 30);
		String responseAsJSON = mapper.writeValueAsString(response);
		
		Mockito.when(service.create(input)).thenReturn(response);
		
		mvc.perform(post("/customer/create")
			.contentType(MediaType.APPLICATION_JSON)
			.content(inputAsJSON))
			.andExpect(status().isCreated())
			.andExpect(content().json(responseAsJSON));
	}
	
	@Test
	public void getAllTest() throws Exception {
		// Created a List
		List<Customer> result = new ArrayList<>();
		// Added my expected Customer to the List
		result.add(new Customer(1L, "Anoush", "Lowton", 29));
		// Converted that list into JSON
		String resultAsJSON = mapper.writeValueAsString(result);
		
		Mockito.when(service.getAll()).thenReturn(result);
		
		mvc.perform(get("/customer/getAll")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json(resultAsJSON));
	}
	
	@Test
	public void getByIdTest() throws Exception{

		Customer result = new Customer(1L, "Anoush", "Lowton", 29);
		
		String resultAsJSON = mapper.writeValueAsString(result);
		
		Mockito.when(service.getById(1L)).thenReturn(result);
		
		mvc.perform(get("/customer/getById/1")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json(resultAsJSON));
	}
	
	@Test
	public void getByFirstNameTest() throws Exception{
		// Created a List
		List<Customer> result = new ArrayList<>();
		// Added my expected Customer to the List
		result.add(new Customer(1L, "Anoush", "Lowton", 29));
		// Converted that list into JSON
		String resultAsJSON = mapper.writeValueAsString(result);
		
		Mockito.when(service.getByFirstName("Anoush")).thenReturn(result);
		
		mvc.perform(get("/customer/getByFirstName/Anoush")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json(resultAsJSON));
	}
	
	@Test
	public void updateTest() throws Exception{
		// An object for sending in the body of the request
		Customer input = new Customer("Bob", "Lowton", 30);
		String inputAsJSON = mapper.writeValueAsString(input);
		
		// An object for checking the response
		Customer response = new Customer(1L, "Bob", "Lowton", 30);
		String responseAsJSON = mapper.writeValueAsString(response);
		
		Mockito.when(service.update(1L, input)).thenReturn(response);
		
		mvc.perform(put("/customer/update/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(inputAsJSON))
			.andExpect(status().isOk())
			.andExpect(content().json(responseAsJSON));		
	}
	
	@Test
	public void deleteTest() throws Exception{
		Mockito.when(service.delete(1L)).thenReturn(true);
		
		mvc.perform(delete("/customer/delete/1")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().string("true"));
	}
	
	@Test
	public void deleteFailTest() throws Exception{
		Mockito.when(service.delete(1L)).thenReturn(false);
		
		mvc.perform(delete("/customer/delete/1")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().string("false"));
	}
}
