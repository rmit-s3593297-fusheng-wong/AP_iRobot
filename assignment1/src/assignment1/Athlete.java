/*****Author - Jitender Singh Padda***********
 *****Student Id - 3628144************************/
package assignment1;

public abstract class Athlete extends Participant {
	
	private int points;
	private int time;
	
	//Constructor
	public Athlete(String id,String name,int age,String state,int points) {
		super(id,name,age,state);
		this.points = points;
	}
	
	//Constructor to initialize with 0 points
	public Athlete(String id,String name,int age,String state) {
		this(id,name,age,state,0);
	}
	
	//getter for Points
	public int getPoints() {
		return points;
	}
	//setter for Points
	public void setPoints(int points) {
		this.points = points;
	}
	//getter for Time
	public int getTime() {
		return time;
	}
	//setter for Time
	public void setTime(int time) {
		this.time = time;
	}
	
	//Abstract method which will be implemented by all sub classes
	public abstract int compete();
}
