/*****Author - Jitender Singh Padda***********
 *****Student Id - 3628144************************/
package assignment1;

public class SuperAthlete extends Athlete {
	
	private String gameType;
	
	public SuperAthlete(String id, String name, int age, String state, int points,String gameType) {
		super(id, name, age, state, points);
		this.gameType=gameType;
	}

	@Override
	public int compete() {
		Athlete superAthlete = null;
		if(gameType=="Cycling"){
			superAthlete=new Cyclist("id","name", 28, "state", 0);
		}
		else if(gameType=="Swimming"){
			superAthlete=new Cyclist("id","name", 28, "state", 0);
		}
		else if(gameType=="Sprinting"){
			superAthlete=new Cyclist("id","name", 28, "state", 0);	
		}
		return superAthlete.compete();
	}
}
