package com.spe;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;


public class StringCalculatorTest {

	@Test
	public void should_add_zero_when_string_is_empty() {
		int result = StringCalculator.add("");
		assertThat(result).isEqualTo(0);
	}
	
	@Test
	public void should_add_with_one_number() {
		int result = StringCalculator.add("1");
		assertThat(result).isEqualTo(1);		
	}
	
	@Test
	public void should_add_with_two_numbers() {
		int result = StringCalculator.add("1,2");
		assertThat(result).isEqualTo(3);
	}
	
	@Test
	public void should_add_with_several_numbers() {
		int result = StringCalculator.add("1,2,3");
		assertThat(result).isEqualTo(6);
	}
	
	@Test
	public void should_add_with_newline_delimiter() {
		int result = StringCalculator.add("1\n2,3");
		assertThat(result).isEqualTo(6);
	}

	
	@Test
	public void should_add_with_different_delimiters() {
		
		int result = StringCalculator.add("//;\n1;2");
		assertThat(result).isEqualTo(3);
	}
	
	@Test
	public void should_throw_exception_when_add_negative_number() {
		
		try {
			StringCalculator.add("//;\n-1;2;-3");
			fail("Exception expected");
		}
		catch(RuntimeException nne) {
			assertThat(nne.getMessage()).isEqualTo("negatives not allowed : [-1, -3]");
		}
	}
}
