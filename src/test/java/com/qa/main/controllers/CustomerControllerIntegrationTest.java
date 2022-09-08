package com.qa.main.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.fasterxml.jackson.core.JsonProcessingException;
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
	public void createTest() {
		
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
		
	}
	
	@Test
	public void getByFirstNameTest() throws Exception{
		
	}
	
	@Test
	public void updateTest() throws Exception{
		
	}
	
	@Test
	public void deleteTest() throws Exception{
		
	}

}
