package com.spe.jdk7;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;


public class StringCalculatorTest {
        
   @Test
   public void should_return_zero_if_string_is_empty() {
       int result = StringCalculator.add("");
       
       assertThat(result).isEqualTo(0);
   }
   
   @Test
   public void should_return_same_number_if_string_contains_only_one_number() {
       int result = StringCalculator.add("1");
       
       assertThat(result).isEqualTo(1);
   }
   
   @Test
   public void should_sum_two_numbers_when_string_contains_two_numbers_delimited_by_comma() {
       int result = StringCalculator.add("1,2");
       
       assertThat(result).isEqualTo(3);
   }
   
   
   @Test
   public void should_sum_numbers_when_string_contains_several_numbers_delimited_by_comma() {
       int result = StringCalculator.add("1,2,3");
       
       assertThat(result).isEqualTo(6);
   }
   
   @Test
   public void should_sum_numbers_when_string_contains_several_numbers_delimited_by_comma_or_newline() {
       int result = StringCalculator.add("1\n2,3");
       
       assertThat(result).isEqualTo(6);
   }
   
   
   @Test
   public void should_sum_numbers_when_string_contains_several_numbers_delimited_by_custom_delimiter() {
       int result = StringCalculator.add("//;\n2;3");
       
       assertThat(result).isEqualTo(5);
   }
   
   @Test
   public void should_throw_a_NumberFormatException_if_string_contains_a_negative_number() {
       try {
           StringCalculator.add("//;\n-1;2;-3");
           fail("Exception expected");
       }
       catch(NumberFormatException nfe) {
           assertThat(nfe.getMessage()).isEqualTo("negatives not allowed : [-1, -3]");
       }
       
   }
   
   

}
