package com.springboot.junit.assertionFramework;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class JsonPathTest {

	@Test
	public void test_basic(){
		String responseFromService = "[" + 
				 
				"{\"id\":10001, \"name\":\"Pencil\", \"quantity\":5}," + 
				"{\"id\":10002, \"name\":\"Pen\", \"quantity\":15}," + 
				"{\"id\":10003, \"name\":\"Eraser\", \"quantity\":10}" + 
				
				"]" ;
		
		DocumentContext context = JsonPath.parse(responseFromService);
		int length = context.read("$.length()");
		assertThat(length).isEqualTo(3);
		
		System.out.println(context.read("$..id").toString());
		
		List<Integer> ids = context.read("$..id");
		assertThat(ids).containsExactly(10001, 10002, 10003);
		
		//Play with JsonPath
		System.out.println(context.read("$.[1]").toString());
		System.out.println(context.read("$.[0:2]").toString());
		System.out.println(context.read("$.[?(@.name=='Eraser')]").toString());
		System.out.println(context.read("$.[?(@.quantity==5)]").toString());
		
		
	}
}
