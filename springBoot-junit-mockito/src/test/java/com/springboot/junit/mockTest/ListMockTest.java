package com.springboot.junit.mockTest;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
//import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;


/*
 * Every Mock object returns null by default for object,
 * 0 for integer
 * false for boolean
 * you must need to override it.
 * 
 */

public class ListMockTest {

	/*
	 * mocking ArrayList class object.
	 * 
	 */
	List<String> mockList = mock(ArrayList.class);
	
	
	@Test
	public void mockListBasic() {
		
		when(mockList.size()).thenReturn(5);
		assertEquals(5, mockList.size());
	}
	
	@Test
	public void mockListReturnDifferentValues() {
		
		when(mockList.size()).thenReturn(5).thenReturn(10);
		assertEquals(5, mockList.size());
		assertEquals(10, mockList.size());
	}
	
	@Test
	public void mockListReturnDifferentParameters() {
		
		when(mockList.get(0)).thenReturn("ranga").thenReturn("raghav");
		assertEquals("ranga", mockList.get(0));
		assertEquals("raghav", mockList.get(0));
		assertEquals(null, mockList.get(1));
	}

	@Test
	public void mockListReturnParameterWithArgumentMatcher() {
		
		when(mockList.get(anyInt())).thenReturn("ranga");
		assertEquals("ranga", mockList.get(0));
		assertEquals("ranga", mockList.get(1));
	}

		
	/*
	 * Verification basics tutorials
	 * 
	 * Here we are going to verify our mock objects list
	 * like get(0) method is called or not
	 * how many times a method was called and much more!!!
	 */
	@Test
	public void verificationBasics() {
		
		//SUT -> SYSTEM UNDER TEST
		
		String value1 = mockList.get(0);
		String value2 = mockList.get(1);
		
		/*
		 * verify() method is used for verification 
		 * 
		 * available in Mockito class
		 */
		verify(mockList).get(0);
		verify(mockList).get(1);
		verify(mockList, atLeast(1)).get(anyInt());
		verify(mockList, atLeastOnce()).get(anyInt());
		verify(mockList, times(2)).get(anyInt());
		verify(mockList, atMost(2)).get(anyInt());
		//verify(mockList, atMostOnce()).get(anyInt()); because its called two times not only once
		verify(mockList, never()).get(2);
		
	}
	
	/*
	 * How to capture argument which are adding to List or
	 * 
	 * How to capture those arguments which are not returned by a function rather than it saves into db.
	 * 
	 */
	@Test
	public void argumentCapturing() {
		
		mockList.add("SomeString");
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(mockList).add(captor.capture());
		assertEquals("SomeString", captor.getValue());
		
	}
	
	@Test
	public void multipleArgumentCapturing() {
		
		mockList.add("SomeString1");
		mockList.add("SomeString2");
		
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(mockList, times(2)).add(captor.capture());
		List<String> allValues = captor.getAllValues();
		assertEquals("SomeString1", allValues.get(0));
		assertEquals("SomeString2", allValues.get(1));
		
	}
	
	/*
	 * mock() -> when we mock any Class or Interface the entire behavior of
	 * object is lost.
	 */
	@Test
	public void mocking() {
		
		ArrayList<String> arrayListMock = mock(ArrayList.class);
		
		System.out.println(arrayListMock.size());//0
		System.out.println(arrayListMock.get(0));// null
		System.out.println(arrayListMock.get(1));// null
		
		arrayListMock.add("value1");
		arrayListMock.add("value2");
		
		System.out.println(arrayListMock.get(0));//null
		System.out.println(arrayListMock.get(1));//null
		
		when(arrayListMock.get(0)).thenReturn("value1");
		System.out.println(arrayListMock.get(0));//value1
		
		System.out.println(arrayListMock.size());//0
		
		when(arrayListMock.size()).thenReturn(5);
		
		System.out.println(arrayListMock.size());//5
		
		arrayListMock.add("value3");
		
		System.out.println(arrayListMock.size());//5
	
	}
	
	
	/*
	 * 
	 * Spying ? What do you mean by spying?
	 * 
	 * spying retains the real behavior of class;
	 * 
	 * spy object behaves like a real object while we are overriding on it.
	 * when we override its default behavior its gives only our output what we want.
	 */
	@Test
	public void spying(){
		ArrayList<String> arrayListSpy = spy(ArrayList.class);
		System.out.println(arrayListSpy.size());//0
		//System.out.println(arrayListSpy.get(0));// throw IndexedOutOfBoundException
		arrayListSpy.add("value1");
		arrayListSpy.add("value2");
		
		//arrayListSpy hold the original values
		System.out.println(arrayListSpy.get(0));//value1
		
		System.out.println(arrayListSpy.size());//0
		when(arrayListSpy.size()).thenReturn(5);
		System.out.println(arrayListSpy.size());//5
		arrayListSpy.add("value3");
		System.out.println(arrayListSpy.size());//5
		
		//uses
		//verify arryListSpy object called with a specific argument
		//so we can check any object that is called by a specific args.
		verify(arrayListSpy).add("value2");
	}
	
	

}
