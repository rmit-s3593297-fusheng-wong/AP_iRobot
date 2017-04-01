/*****Author - Jitender Singh Padda***********
 *****Student Id - 3628144************************/
package jitender;

import java.util.Random;

public class Sprinter extends Athlete {
	
	private final static int MAX_TIME =20;//Constants
	private final static int MIN_TIME =10;//Constants
	
	//Constructor
	public Sprinter(String id,String name,int age,String state,int points){
		super(id,name,age,state,points);
	}
	
	//Overriding compete() method from Athlete class
	@Override
	public int compete(){
		//using Random class to generate Random integers between the MIN_TIME and MAX_TIMEs
		return new Random().nextInt(MAX_TIME-MIN_TIME+1)+MIN_TIME; 
	}
}
