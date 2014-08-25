package com.spe;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

	private static final String CUSTOM_DELIMITER = "//(.)\n(.+)";
	private static final String DEFAULT_DELIMITER = ",|\n";

	public static int add(String text) {
		
		if("".equals(text)){
			return 0;
		}
		
		String[] tokens = tokenize(text);
		List<String> numbersToAdd = convertTokensToList(tokens);
		
		checkForNegativeNumbers(numbersToAdd);
		return convertAndSum(numbersToAdd);
	}

	private static List<String> convertTokensToList(String[] tokens) {
		return Arrays.asList(tokens);
	}

	private static String[] tokenize(String text) {
		String delimiter = DEFAULT_DELIMITER;
		String textToSum = text;
		
		Pattern pattern = Pattern.compile(CUSTOM_DELIMITER);
		Matcher matcher = pattern.matcher(textToSum);
		
		if(matcher.find()) {
			delimiter = matcher.group(1);
			textToSum = matcher.group(2);
		}

		return textToSum.split(delimiter);
	}

	private static int convertAndSum(List<String> numbersToAdd) {
		return numbersToAdd.stream().mapToInt(x -> convertStringToInt(x)).sum();
	}

	private static void checkForNegativeNumbers(List<String> numbersToAdd) {
		List<String> negativeNumbers = filterNegativeNumbers(numbersToAdd);
		
		if(!negativeNumbers.isEmpty()) {
			throw new RuntimeException("negatives not allowed : " + negativeNumbers.toString());
		}
	}

	private static List<String> filterNegativeNumbers(List<String> numbersToAdd) {
		return numbersToAdd.stream().filter(x -> convertStringToInt(x) < 0).collect(Collectors.toList());
	}

	private static int convertStringToInt(String text) {
		return Integer.parseInt(text);
	}
	
}
