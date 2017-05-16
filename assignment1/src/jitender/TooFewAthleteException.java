package jitender;

@SuppressWarnings("serial")
public class TooFewAthleteException extends Exception{

	public TooFewAthleteException(String errMsg) {
		super(errMsg);
	}
}
