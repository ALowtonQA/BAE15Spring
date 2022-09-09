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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.main.entities.Customer;

@SpringBootTest
@AutoConfigureMockMvc // Allows us to send mock requests using MockMvc
@Sql(scripts = {"classpath:testschema.sql", "classpath:testdata.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test") // switch to the test profile (H2 database)
public class CustomerControllerIntegrationTest {

	@Autowired
	private MockMvc mvc; // This is for sending mock requests
	
	@Autowired
	private ObjectMapper mapper; // Is for converting TO and FROM JSON data
	
	@Test
	public void createTest() throws Exception {
		// An object for sending in the body of the request
		Customer input = new Customer("Bob", "Lowton", 30);
		String inputAsJSON = mapper.writeValueAsString(input);
		
		// An object for checking the response
		Customer response = new Customer(2L, "Bob", "Lowton", 30);
		String responseAsJSON = mapper.writeValueAsString(response);
		
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
		
		mvc.perform(get("/customer/getAll")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json(resultAsJSON));
	}
	
	@Test
	public void getByIdTest() throws Exception{

		Customer result = new Customer(1L, "Anoush", "Lowton", 29);
		
		String resultAsJSON = mapper.writeValueAsString(result);
		
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
		
		mvc.perform(put("/customer/update/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(inputAsJSON))
			.andExpect(status().isOk())
			.andExpect(content().json(responseAsJSON));		
	}
	
	@Test
	public void deleteTest() throws Exception{
		mvc.perform(delete("/customer/delete/1")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().string("true"));
	}

}
