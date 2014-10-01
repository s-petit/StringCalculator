package com.spe.jdk7;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    private static final String CUSTOM_DELIMITER = "//(.)\n(.+)";
    private static final String DEFAULT_DELIMITER = ",|\n";
    
    private StringCalculator() {}
    
    public static int add(String textToAdd) {

        if (textToAdd.isEmpty()) {
            return 0;
        }

        String[] numberTokens = tokenizeEachNumber(textToAdd);

        checkNegativeNumbers(numberTokens);
        return sumTokensBelow1001(numberTokens);
    }

    private static int sumTokensBelow1001(String[] numberTokens) {
        int result = 0;
        for (String token : numberTokens) {
           int number = convertStringToInt(token);
           if(number < 1001) {
               result += number;
           }        
        }

        return result;
    }

    private static void checkNegativeNumbers(String[] tokens) {
        List<Integer> negativeNumbers = new ArrayList<>();
        for (String token : tokens) {
           int number = convertStringToInt(token);
           if (number < 0) {
              negativeNumbers.add(number);
           }
          
        }
        if(!negativeNumbers.isEmpty()) {
            throw new NumberFormatException("negatives not allowed : " + negativeNumbers.toString());
         }
     }

    private static String[] tokenizeEachNumber(String numbers) {

        String delimiter = DEFAULT_DELIMITER;
        String stringToSplit = numbers;

        Pattern pattern = Pattern.compile(CUSTOM_DELIMITER);
        Matcher matcher = pattern.matcher(stringToSplit);

        if (matcher.find()) {
           delimiter = Pattern.quote(matcher.group(1));
           stringToSplit = matcher.group(2);
        }

        return stringToSplit.split(delimiter);

    }

    private static int convertStringToInt(String number) {
        return Integer.parseInt(number);
    }

}
