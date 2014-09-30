package com.spe.jdk7.v2;


import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class StringCalculatorTest {

   @Test
   public void should_return_zero_if_empty_string() {
      int result = StringCalculator.add("");

      assertThat(result).isEqualTo(0);
   }

   @Test
   public void should_return_same_number_if_string_contains_one_number() {
      int result = StringCalculator.add("1");

      assertThat(result).isEqualTo(1);
   }

   @Test
   public void should_return_sum_if_string_contains_two_number() {
      assertThat(StringCalculator.add("1,2")).isEqualTo(3);
   }

   @Test
   public void should_return_sum_if_string_contains_more_than_one_number() {
      assertThat(StringCalculator.add("1,2,3")).isEqualTo(6);
   }

   @Test
   public void should_sum_with_newline_as_a_delimiter() {
      assertThat(StringCalculator.add("1\n2,3")).isEqualTo(6);
   }

   @Test
   public void should_sum_with_custom_delimiter() {
      assertThat(StringCalculator.add("//;\n1;2;3")).isEqualTo(6);
   }

   @Test
   public void should_throw_an_exception_if_numbers_contains_a_negative_number() {
      try {
         StringCalculator.add("//;\n-1;2;-3");
         fail("an NumberFormatException should be thrown");
      } catch (NumberFormatException nfe) {
         assertThat(nfe.getMessage()).isEqualTo("negatives not allowed : -1,-3");
      }

   }
}