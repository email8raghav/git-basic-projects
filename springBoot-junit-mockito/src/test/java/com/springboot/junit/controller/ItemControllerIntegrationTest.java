package com.springboot.junit.controller;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.junit.repository.ItemRepository;

@RunWith(SpringRunner.class)

/*
 * @SpringBoot annotation launching up the whole entire application
 * it will search main class of SpringBootApplication in parent packages.
 * it searches @SpringBootApplication Annotation to find main class.
 */

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ItemControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	/*
	 * the dependent classes which we really don't need 
	 * so we mock them out.
	 */
	@MockBean
	private ItemRepository itemRepository;
	
	@Test
	public void contextLoads() throws JSONException {
		/*
		 * when(itemRepository.retrieveAllItems()).thenReturn(Arrays.asList(obj1, obj2));
		 * 
		 */
		String response = this.restTemplate.getForObject("/items-from-database", String.class);
		
		JSONAssert.assertEquals("[{id:10001}, {id:10002}, {id:10003}]", response, false);
	}
	
}
