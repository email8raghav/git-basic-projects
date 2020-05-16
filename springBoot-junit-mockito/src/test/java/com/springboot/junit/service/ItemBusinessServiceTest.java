package com.springboot.junit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.springboot.junit.model.Item;
import com.springboot.junit.repository.ItemRepository;

@RunWith(MockitoJUnitRunner.class)
public class ItemBusinessServiceTest {

	@InjectMocks
	private ItemBusinessService businessService;
	
	@Mock
	private ItemRepository repository;
	
	@Test
	public void retrieveAllItems_basic() {
		when(repository.findAll()).thenReturn(Arrays.asList(
				new Item(10001, "Item1", 10, 20),
				new Item(10002, "Item2", 5, 10)));
		List<Item> items = businessService.retrieveAllItems();
		assertEquals(200, items.get(0).getValue());
		assertEquals(50, items.get(1).getValue());
		
	}
	
}
