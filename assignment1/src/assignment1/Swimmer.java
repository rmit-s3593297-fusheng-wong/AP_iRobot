package assignment1;

import java.util.Random;

public class Swimmer extends Athlete {

	private final static int MAX_TIME =200;
	private final static int MIN_TIME =100;
	
	public Swimmer(String id,String name,int age,String state,int points){
		super(id,name,age,state,points);
	}

	@Override
	public int compete(){
		// Looked up from - http://stackoverflow.com/questions/20389890/generating-a-random-number-between-1-and-10-java
		return new Random().nextInt(MAX_TIME-MIN_TIME+1)+MIN_TIME;
	}
}
