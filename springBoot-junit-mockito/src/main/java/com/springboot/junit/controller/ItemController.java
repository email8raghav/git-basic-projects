package com.springboot.junit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.junit.model.Item;
import com.springboot.junit.service.ItemBusinessService;

@RestController
public class ItemController {
	
	@Autowired
	private ItemBusinessService businessService;
	
	@GetMapping(path = "/dummy-item")
	public Item getDummyItem() {
		return new Item(1, "Ball", 10, 100);
	}
	
	@GetMapping(path = "/item-from-business-service")
	public Item getItemFromBusinessService() {
		return businessService.retrieveHardCodedItem();
	}
	
	@GetMapping(path = "/items-from-database")
	public List<Item> getAllItems() {
		return businessService.retrieveAllItems();
	}
	
}
