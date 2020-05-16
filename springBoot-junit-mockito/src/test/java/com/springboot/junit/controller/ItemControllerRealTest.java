package com.springboot.junit.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.springboot.junit.model.Item;
import com.springboot.junit.service.ItemBusinessService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ItemController.class)
public class ItemControllerRealTest {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@MockBean
	private ItemBusinessService businessService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllItems_basicTest0() throws Exception {

		when(businessService.retrieveAllItems())
				.thenReturn(Arrays.asList(new Item(10001, "Item1", 10, 20), new Item(10002, "Item2", 5, 10)));

		RequestBuilder request = MockMvcRequestBuilders.get("/items-from-database").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andExpect(content()
				.json("[{id:10001, name:Item1, price:10, quantity:20}, {id:10002, name:Item2, price:5, quantity:10}]"))
				.andReturn();

		logger.info("The MvcResult is -> {}", result);

	}
	
	
	/**
	 * R Test fails due to space in attributes names Item 1 
	 */
	@Test
	public void getAllItems_basicTest1() throws Exception {

		when(businessService.retrieveAllItems())
				.thenReturn(Arrays.asList(new Item(10001, "Item 1", 10, 20), new Item(10002, "Item2", 5, 10)));

		RequestBuilder request = MockMvcRequestBuilders.get("/items-from-database").accept(MediaType.APPLICATION_JSON);
		
		/**
		 * Always remember no space between attribute names like Item 1
		 */
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andExpect(content()
				.json("[{id:10001, name:Item 1, price:10, quantity:20}, {id:10002, name:Item2, price:5, quantity:10}]"))
				.andReturn();

		logger.info("The MvcResult is -> {}", result);

	}

}
