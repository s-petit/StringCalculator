package com.spe;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

	private static final String CUSTOM_DELIMITER = "//(.)\n(.+)";
	private static final String DEFAULT_DELIMITER = ",|\n";

	public static int add(String numbers) {
		
		int result = 0;
		if ("".equals(numbers)) {
			return result;
		}
		

		String delimiter = getDelimiter(numbers);
		String numbersWithoutDelimiter = getNumbersWithoutDelimiter(numbers);
		
		List<String> numbersToAdd = splitStringToList(numbersWithoutDelimiter, delimiter);		
		
		for (String numberToAdd : numbersToAdd) {
			result += Integer.parseInt(numberToAdd);
		}

		return result;
	}

	private static String getDelimiter(String numbers) {
		
		String delimiter = DEFAULT_DELIMITER;
		
		Matcher m = getMatcher(numbers);

		boolean customDelimiterFound = m.find();
		if(customDelimiterFound) {
			delimiter = m.group(1);			
		}
		return delimiter;
	}
	
	private static String getNumbersWithoutDelimiter(String numbers) {
		
		String numbersWithoutDelimiter = numbers;
		
		Matcher m = getMatcher(numbers);		

		boolean customDelimiterFound = m.find();
		if(customDelimiterFound) {
			numbersWithoutDelimiter = m.group(2);		
		}
		return numbersWithoutDelimiter;
	}

	private static Matcher getMatcher(String numbers) {
		Pattern pattern = Pattern.compile(CUSTOM_DELIMITER);
		Matcher m = pattern.matcher(numbers);
		return m;
	}

	private static List<String> splitStringToList(String numbers, String delimiter) {
		
		String[] splittedNumbers = numbers.split(delimiter);		
		List<String> numbersToAdd = Arrays.asList(splittedNumbers);
		return numbersToAdd;
	}
	
}
