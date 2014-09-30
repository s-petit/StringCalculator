package com.spe.jdk7.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

   private static final String CUSTOM_DELIMITER = "//(.)\n(.+)";
   private static final String DEFAULT_DELIMITER = ",|\n";

   public static int add(String numbers) {

      if ("".equals(numbers)) {
         return 0;
      }

      String[] tokens = tokenize(numbers);
      checkNegativeNumbers(tokens);

      return sumTokens(tokens);

   }

   private static int sumTokens(String[] tokens) {
      int result = 0;
      for (String token : tokens) {
         result += convertStringToInt(token);
      }

      return result;
   }
   
   private static void checkNegativeNumbers(String[] tokens) {
       List<String> negativeNumbers = new ArrayList<>();
       for (String token : tokens) {
          int number = convertStringToInt(token);
          if (number < 0) {
             negativeNumbers.add(number+"");
          }
         
       }
       if(!negativeNumbers.isEmpty()) {
           throw new NumberFormatException("negatives not allowed : " + String.join(",", negativeNumbers));
        }
    }

   private static String[] tokenize(String numbers) {

      String delimiter = DEFAULT_DELIMITER;
      String stringToSplit = numbers;

      Pattern pattern = Pattern.compile(CUSTOM_DELIMITER);
      Matcher matcher = pattern.matcher(stringToSplit);

      if (matcher.find()) {
         delimiter = matcher.group(1);
         stringToSplit = matcher.group(2);
      }

      return stringToSplit.split(delimiter);
   }

   private static int convertStringToInt(String numbers) {
      return Integer.parseInt(numbers);
   }

}