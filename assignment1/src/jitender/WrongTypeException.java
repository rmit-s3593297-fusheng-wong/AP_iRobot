package jitender;

@SuppressWarnings("serial")
public class WrongTypeException extends Exception {

	public WrongTypeException(String errMsg,String expectedType) {
		super(errMsg);
	}
}
