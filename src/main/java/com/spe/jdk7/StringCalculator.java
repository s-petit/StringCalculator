package com.spe.jdk7;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

    private StringCalculator() {}
    
    public static int add(String numbers) {

        if (numbers.isEmpty()) {
            return 0;
        }

        String[] tokens = tokenize(numbers);

        checkForNegativeNumbers(tokens);
        return sumTokens(tokens);
    }

    private static int sumTokens(String[] tokens) {
        List<String> numbers = Arrays.asList(tokens);
        
        
        
        int result = 0;
        
        result = numbers.stream().mapToInt(x -> convertStringToNumber(x)).sum();
        
//        for (String token : tokens) {
//            result += convertStringToNumber(token);
//        }

        return result;
    }

    private static void checkForNegativeNumbers(String[] tokens) {
        
        List<String> negativeNumbers = Arrays.asList(tokens);
        
        negativeNumbers = negativeNumbers.stream().filter(x -> convertStringToNumber(x) < 0).collect(Collectors.toList());

        if (!negativeNumbers.isEmpty()) {
            throw new NumberFormatException("negatives not allowed : "
                    + negativeNumbers.toString());
        }
    }

    private static String[] tokenize(String numbers) {

        Pattern pattern = Pattern.compile("//(.)\n(.+)");
        Matcher matcher = pattern.matcher(numbers);

        String delimiter = ",|\n";
        String textToSum = numbers;
        if (matcher.find()) {
            delimiter = matcher.group(1);
            textToSum = matcher.group(2);
        }

        return textToSum.split(delimiter);

    }

    private static int convertStringToNumber(String number) {
        return Integer.parseInt(number);
    }

}
