package assignment1;

public abstract class Athlete extends Participant {
	protected int points;
	protected int speed;
	
	public Athlete(String name, int age, String state) {
		super(name, age, state);
		this.points = 0;
	}
	
	//create an athlete with points already
	public Athlete(String name, int age, String state,int points) {
		super(name, age, state);
		this.points = points;
	}

	public abstract void compete();
}
