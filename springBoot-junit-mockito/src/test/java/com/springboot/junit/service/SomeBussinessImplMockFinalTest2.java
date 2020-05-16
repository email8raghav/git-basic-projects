package com.springboot.junit.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;


public class SomeBussinessImplMockFinalTest2 {

	SomeBussinessImp bussiness = new SomeBussinessImp();
	SomeDataService someDataService = mock(SomeDataService.class);
	
	@Before
	public void before() {
		bussiness.setSomeDataService(someDataService);
	}
	
	@Test
	public void calculateSumUsingDataService_basic() {
		
		when(someDataService.retrieveAllData()).thenReturn(new int[] {1, 2, 3});
		assertEquals(6, bussiness.calculateSumUsingDataService());
	}
	
	@Test
	public void calculateSumUsingDataService_empty() {
		when(someDataService.retrieveAllData()).thenReturn(new int[] {});
		assertEquals(0, bussiness.calculateSumUsingDataService());
		
	}
	
	@Test
	public void calculateSumUsingDataService_oneValue() {
		when(someDataService.retrieveAllData()).thenReturn(new int[] {5});
		assertEquals(5, bussiness.calculateSumUsingDataService());
	}

}
