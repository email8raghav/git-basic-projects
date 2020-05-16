package com.springboot.junit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.junit.model.Item;


public interface ItemRepository extends JpaRepository<Item, Integer> {

}
