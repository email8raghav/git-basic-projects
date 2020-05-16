package com.springboot.junit.controller;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JsonAssertTest {


	//JSONAssert.assertEquals(expected, actual, strict);
	//JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);//by default false

	String actual = "{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}";
	
	String specialEditionItem = "{\"id\":1,\"name\":\"Iphone 12 Pro Max\",\"price\":100000,\"quantity\":3}";
	
	@Test
	public void jsonAssert_StrictTrue_ExactMatchExceptForSpaces() throws JSONException {
		String expected="{\"id\": 1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}";
		JSONAssert.assertEquals(expected, actual, true);
	}
	
	@Test
	public void jsonAssert_StrictFalse_ExceptForSpaces() throws JSONException {
		String expected="{\"id\": 1,\"name\":\"Ball\",\"price\":10}";
		JSONAssert.assertEquals(expected, actual, false);
	}
	
	/*
	 * But jsonAssert works very well without escapeCharecters()
	 * while there is no spaces inside String eg:
	 * it's not working with item name like "Ball 2"
	 * 
	 */
	
	@Test
	public void jsonAssert_WithoutEscapeCharecters() throws JSONException {
		String expected="{id:1, name:Ball, price:10}";
		JSONAssert.assertEquals(expected, actual, false);
	}
	
	
	@Test
	public void jsonAssert_WithoutEscapeCharectersWithSpcesInsideVariable() throws JSONException {
		String expected="{id:1, name:\"Iphone 12 Pro Max\", price:100000}";
		JSONAssert.assertEquals(expected, specialEditionItem, false);
	}
}
