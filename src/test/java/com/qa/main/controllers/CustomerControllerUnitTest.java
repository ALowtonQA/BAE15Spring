package com.qa.main.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
	
}
