package com.springboot.junit.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;


public class SomeBussinessImplMockTest {

	@Test
	public void calculateSumUsingDataService_basic() {
		SomeBussinessImp bussiness = new SomeBussinessImp();
		
		SomeDataService someDataService = mock(SomeDataService.class);
		when(someDataService.retrieveAllData()).thenReturn(new int[] {1, 2, 3});
		
		bussiness.setSomeDataService(someDataService);
		int actualResult = bussiness.calculateSumUsingDataService();
		int expectedResult = 6;
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void calculateSumUsingDataService_empty() {
		SomeBussinessImp bussiness = new SomeBussinessImp();
		
		SomeDataService someDataService = mock(SomeDataService.class);
		when(someDataService.retrieveAllData()).thenReturn(new int[] {});
		
		bussiness.setSomeDataService(someDataService);
	
		int actualResult = bussiness.calculateSumUsingDataService();
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void calculateSumUsingDataService_oneValue() {
		SomeBussinessImp bussiness = new SomeBussinessImp();
		
		SomeDataService someDataService = mock(SomeDataService.class);
		when(someDataService.retrieveAllData()).thenReturn(new int[] {5});
		
		bussiness.setSomeDataService(someDataService);
		
		int actualResult = bussiness.calculateSumUsingDataService();
		int expectedResult = 5;
		assertEquals(expectedResult, actualResult);
	}

}
