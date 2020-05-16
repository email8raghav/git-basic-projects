package com.springboot.junit.assertionFramework;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;


import org.junit.Test;

public class AssertJTest {

	@Test
	public void basic_Test() {
		List<Integer> numbers = Arrays.asList(12, 15, 45);
		assertThat(numbers)
		.hasSize(3)
		.contains(12, 45)
		.allMatch(x-> x>10)
		.allMatch(x-> x<100)
		.noneMatch(x -> x<0);
		
		//play with strings
		assertThat("").isEmpty();
		
		assertThat("abcd")
		.contains("bcd")
		.startsWith("ab")
		.endsWith("cd");
		
	}
	

}
