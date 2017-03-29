/*****Author - Jitender Singh Padda***********
 *****Student Id - 3628144************************/
package assignment1;

public class SuperAthlete extends Athlete {
	
	private String gameType;
	
	//constructor
	public SuperAthlete(String id, String name, int age, String state, int points) {
		super(id, name, age, state, points);
	}
	
	//setter for gameType
	public void setGameType(String gameType){
		this.gameType=gameType;
	}
	
	//Overriding compete() method from Athlete class
	@Override
	public int compete() {
		Athlete superAthlete = null;
		//SuperAthlete will create an instance of the type of Athlete according to gameType and call it's compete() method
		if(gameType=="Cycling"){
			superAthlete=new Cyclist(getId(),getName(),getAge(),getState(),getPoints());
		}
		else if(gameType=="Swimming"){
			superAthlete=new Swimmer(getId(),getName(),getAge(),getState(),getPoints());
		}
		else if(gameType=="Sprinting"){
			superAthlete=new Sprinter(getId(),getName(),getAge(),getState(),getPoints());
		}
		return superAthlete.compete();
	}
}
