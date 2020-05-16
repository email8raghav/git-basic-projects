package com.springboot.junit.assertionFramework;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.is;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Test;

public class HamcrestMatchersTest {

	@Test
	public void hamcrestMatchers() {
		List<Integer> numbers = Arrays.asList(12, 15, 45);
		assertThat(numbers, hasSize(3));
		assertThat(numbers, hasItems(12, 45));
		assertThat(numbers, everyItem(greaterThan(10)));
		assertThat(numbers, everyItem(lessThan(100)));
		
		//different things like
		assertThat("", is(""));
		assertThat("abcde", containsString("bcd"));
		assertThat("abcde", startsWith("ab"));
		assertThat("abcde", endsWith("de"));
	}
	
	/*
	 * java.lang.SecurityException: class "org.hamcrest.Matchers"'s signer 
	 * information does not match signer information of other classes in the same package
		
		The root problem was that I included the Hamcrest library twice. 
		Once using Maven pom file. And I also added the JUnit 4 library 
		(which also contains a Hamcrest library) to the project's build path. 
		I simply had to remove JUnit from the build path and everything was fine.
	 * 
	 */
}
