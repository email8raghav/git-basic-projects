package com.springboot.junit.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
public class ItemControllerTest {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@MockBean
	private ItemBusinessService businessService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getDummyItem_basic0() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/dummy-item").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().string("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}")).andReturn();

		logger.info("The MvcResult is -> {}", result.getResponse().getContentAsString());

	}
	

	@Test
	public void getDummyItem_basic1() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/dummy-item").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}")).andReturn();

		logger.info("The MvcResult is -> {}", result.getResponse().getContentAsString());

	}

	@Test
	public void getDummyItem_basic() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/dummy-item").accept(MediaType.APPLICATION_JSON);

		/**
		 * here json is converted to string called jsonString exact String must be
		 * matched even a single space is not allowed here it's also case sensitive.
		 * 
		 * exact number of variable id,name,price,quantity is also required and matched
		 */

		/**
		 * Fails due to exact String did not match with response
		 * 
		 * Normal String matching is required exact match with case sensitive and spaces
		 * too
		 * 
		 */
		MvcResult result1 = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().string("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}")).andReturn();

		/**
		 * Pass due to json string
		 * 
		 * Json String didn't care about exact matching it compares only expected json
		 * String is matches with output
		 */
		MvcResult result2 = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}")).andReturn();
		
		logger.info("The MvcResult is -> {}", result1);
		logger.info("*****************************************************************");
		logger.info("The MvcResult is -> {}", result2);

	}

	@Test
	public void getDummyItem_basic_normal_string() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/dummy-item").accept(MediaType.APPLICATION_JSON);

		/*
		 * Fails due to expected String exactly did not match with response String
		 * 
		 * we can easily use spaces between out field like Ball 2 , or Heavy Base Ball
		 */
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().string("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}")).andReturn();

		logger.info("The MvcResult is -> {}", result);

	}

	@Test
	public void getDummyItem_basic_json_string() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/dummy-item").accept(MediaType.APPLICATION_JSON);

		/*
		 * Pass because expected json String checks only the response json string
		 * contains our values or not json String didn't care about exact matching it
		 * compares only expected json String is matches with output if response json
		 * string contains our expected json string then test must be passed
		 */
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}")).andReturn();

		logger.info("The MvcResult is -> {}", result);

	}

	/*
	 * below Test fails because normal string
	 * 
	 * Normal String requires exact match 
	 */
	@Test
	public void getDummyItem_withTwoItemVariables1() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/dummy-item").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().string("{\"id\":1,\"name\":\"Ball\"}")).andReturn();// fails

		logger.info("The MvcResult is -> {}", result);

	}
	
	/*
	 * below Test pass because json string 
	 * Json String does not requires exact match
	 */
	@Test
	public void getDummyItem_withTwoItemVariables2() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/dummy-item").accept(MediaType.APPLICATION_JSON);

		/*
		 * however we can expect less item and test also succeeds
		 */

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("{\"id\":1,\"name\":\"Ball\"}")).andReturn();// fails

		logger.info("The MvcResult is -> {}", result);

	}

	@Test
	public void getDummyItem_Json() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/dummy-item").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("{\"id\": 1,\"name\":\"Ball\",\"price\":10}")).andReturn();// pass

		logger.info("The MvcResult is -> {}", result);

	}

	@Test
	public void getItemFromBusinessService_basicTest() throws Exception {

		when(businessService.retrieveHardCodedItem()).thenReturn(new Item(1, "Ball", 10, 10));

		RequestBuilder request = MockMvcRequestBuilders.get("/item-from-business-service")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("{id:1, name:Ball}")).andReturn();

		logger.info("The MvcResult is -> {}", result);

	}

	/**
	 * 
	 * @throws Exception
	 * 
	 * Amazing Scenario
	 * 
	 * Test fails due to space Ball 2 if there is a space then
	 * expected result must be contains escape character
	 */
	@Test
	public void getItemFromBusinessService_basicTest2() throws Exception {

		when(businessService.retrieveHardCodedItem()).thenReturn(new Item(1, "Ball 2", 10, 10));

		RequestBuilder request = MockMvcRequestBuilders.get("/item-from-business-service")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("{id:1, name:Ball 2}")).andReturn();

		logger.info("The MvcResult is -> {}", result);

	}

	@Test
	public void getItemFromBusinessService_dataBaseTest() throws Exception {

		List<Item> itemList = java.util.Arrays.asList(new Item(1, "Ball", 10, 10), new Item(2, "Pen", 20, 20));
		
		when(businessService.retrieveAllItems()).thenReturn(itemList);

		RequestBuilder request = MockMvcRequestBuilders.get("/items-from-database").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("[{id:1, name:Ball}, {id:2, name:Pen}]")).andReturn();

		logger.info("The MvcResult is -> {}", result);

	}

}
