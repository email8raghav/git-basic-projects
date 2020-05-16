package com.springboot.junit.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class SomeBussinessImplTest {

	@Test
	public void calculateSum_basic() {
		SomeBussinessImp bussiness = new SomeBussinessImp();
		int actualResult = bussiness.calculateSum(new int[] {1, 2, 3});
		int expectedResult = 6;
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void calculateSum_empty() {
		SomeBussinessImp bussiness = new SomeBussinessImp();
		int actualResult = bussiness.calculateSum(new int[] {});
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void calculateSum_oneValue() {
		SomeBussinessImp bussiness = new SomeBussinessImp();
		int actualResult = bussiness.calculateSum(new int[] {5});
		int expectedResult = 5;
		assertEquals(expectedResult, actualResult);
	}

}
