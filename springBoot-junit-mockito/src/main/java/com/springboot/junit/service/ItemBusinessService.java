package com.springboot.junit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.junit.model.Item;
import com.springboot.junit.repository.ItemRepository;

@Service
public class ItemBusinessService {

	@Autowired
	private ItemRepository itemRepository;

	
	public Item retrieveHardCodedItem() {
		return new Item(1, "Ball", 10, 100);
	}
	
	public List<Item> retrieveAllItems(){
		List<Item> items = itemRepository.findAll();
		for(Item item:items) {
			item.setValue(item.getPrice()*item.getQuantity());
		}
		return items;
	}
}
