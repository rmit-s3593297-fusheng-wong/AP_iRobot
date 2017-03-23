package assignment1;

public abstract class Athlete extends Participant {
	//ASk if he needs points
	private int points;
	private int time;
	
	
	//Constructor
	public Athlete(String id,String name,int age,String state) {
		super(id,name,age,state);
		this.points = 0;
	}
	
	//create an athlete with points already
	public Athlete(String id,String name,int age,String state,int points) {
		super(id,name,age,state);
		this.points = points;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public abstract int compete();
}
