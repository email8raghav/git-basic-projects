package com.springboot.junit.service;

import static org.junit.Assert.*;

import org.junit.Test;



class SomeDataServiceStub implements SomeDataService{
	@Override
	public int[] retrieveAllData() {
		return new int[] {1,2,3};
	}
}

class SomeDataServiceEmptyStub implements SomeDataService{
	@Override
	public int[] retrieveAllData() {
		return new int[] {};
	}
}

class SomeDataServiceOneElemntStub implements SomeDataService{
	@Override
	public int[] retrieveAllData() {
		return new int[] {5};
	}
}



public class SomeBussinessImplStubTest {

	@Test
	public void calculateSumUsingDataService_basic() {
		SomeBussinessImp bussiness = new SomeBussinessImp();
		bussiness.setSomeDataService(new SomeDataServiceStub());
		int actualResult = bussiness.calculateSumUsingDataService();
		int expectedResult = 6;
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void calculateSumUsingDataService_empty() {
		SomeBussinessImp bussiness = new SomeBussinessImp();
		bussiness.setSomeDataService(new SomeDataServiceEmptyStub());
		int actualResult = bussiness.calculateSumUsingDataService();
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void calculateSumUsingDataService_oneValue() {
		SomeBussinessImp bussiness = new SomeBussinessImp();
		bussiness.setSomeDataService(new SomeDataServiceOneElemntStub());
		int actualResult = bussiness.calculateSumUsingDataService();
		int expectedResult = 5;
		assertEquals(expectedResult, actualResult);
	}

}
