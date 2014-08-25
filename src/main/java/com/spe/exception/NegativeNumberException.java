package com.spe.exception;

public class NegativeNumberException extends RuntimeException {

	private static final long serialVersionUID = 5695504441148861449L;
	
	public NegativeNumberException(int negativeNumber) {
		super("negatives not allowed : "+ negativeNumber);
	}

}
