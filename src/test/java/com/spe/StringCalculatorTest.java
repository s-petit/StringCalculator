package com.spe;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;


public class StringCalculatorTest {

	@Test
	public void should_return_zero_when_string_is_empty() {
		int result = StringCalculator.add("");
		assertThat(result).isEqualTo(0);
	}
	
	@Test
	public void should_return_same_number_when_only_one_number() {
		int result = StringCalculator.add("1");
		assertThat(result).isEqualTo(1);		
	}
	
	@Test
	public void should_return_sum_on_two_numbers_when_two_numbers_delimited_by_comma() {
		int result = StringCalculator.add("1,2");
		assertThat(result).isEqualTo(3);
	}
	
	@Test
	public void should_return_sum_on_multiple_numbers_when_multiple_numbers_delimited_by_comma() {
		int result = StringCalculator.add("1,2,3");
		assertThat(result).isEqualTo(6);
	}
	
	@Test
	public void should_sum_numbers_with_newline_as_a_delimiter() {
		int result = StringCalculator.add("1\n2,3");
		assertThat(result).isEqualTo(6);
	}

	
	@Test
	public void should_sum_numbers_with_a_custom_delimiter() {
		
		int result = StringCalculator.add("//;\n1;2");
		assertThat(result).isEqualTo(3);
	}
	
	@Test
	public void should_throw_exception_with_negative_numbers() {
		
		try {
			StringCalculator.add("//;\n-1;2;-3");
			fail("Exception expected");
		}
		catch(NumberFormatException nfe) {
			assertThat(nfe.getMessage()).isEqualTo("negatives not allowed : [-1, -3]");
		}
	}
	
	@Test
	public void should_ignore_numbers_over_one_thousand() {
		int result = StringCalculator.add("1,1001,2");
		assertThat(result).isEqualTo(3);
	}
}
