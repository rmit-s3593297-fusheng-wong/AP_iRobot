/*****Author - Jitender Singh Padda***********
 *****Student Id - 3628144************************/
package jitender;

import java.util.Random;

public class Cyclist extends Athlete {
	
	private final static int MAX_TIME =800;//Constant
	private final static int MIN_TIME =500;//Constant
	
	//Constructor
	public Cyclist(String id,String name,int age,String state,int points){
		super(id,name,age,state,points);
	}
	
	//Overriding compete() method from Athlete class
	@Override
	public int compete(){
		//using Random class to generate Random integers between the MIN_TIME and MAX_TIME
		return new Random().nextInt(MAX_TIME-MIN_TIME+1)+MIN_TIME; 
	}
}
