package com.springboot.junit.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SomeBussinessImplMockFinalTest {

	@InjectMocks
	SomeBussinessImp bussiness;
	
	@Mock
	SomeDataService someDataService;
	
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
