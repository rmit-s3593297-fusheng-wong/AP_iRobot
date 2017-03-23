package assignment1;

import java.util.Scanner;

/**
 * @author fushwong
 *
 */
public class Ozlympic {

	public static void main(String[] args) {
		//preload data
		Athlete[] athletes = initiateAthletes();
		Official[] officials = initiateOfficials();
		Game[] games = initiateCompetition(athletes,officials);
		
		Driver userInterface = new Driver();
		userInterface.console(games,athletes);
	}
	
	private static Athlete[] initiateAthletes(){
		Athlete athletes[] = new Athlete[9];
		athletes[0] = new Cyclist("name", 28, "state", 0);
		athletes[1] = new Cyclist("name", 28, "state", 0);
		athletes[2] = new Cyclist("name", 28, "state", 0);
		athletes[3] = new Swimmer("name", 28, "state", 0);
		athletes[4] = new Swimmer("name", 28, "state", 0);
		athletes[5] = new Swimmer("name", 28, "state", 0);
		athletes[6] = new Sprinter("name", 28, "state", 0);
		athletes[7] = new Sprinter("name", 28, "state", 0);
		athletes[8] = new Sprinter("name", 28, "state", 0);
		//athletes[9] = new SuperAthlete("name", 28, "state", 0);
		//athletes[10] = new SuperAthlete("name", 28, "state", 0);
		//athletes[11] = new SuperAthlete("name", 28, "state", 0);
		
		return athletes;
	}
	
	private static Official[] initiateOfficials(){
		Official officials[] = new Official[3];
		officials[0] = new Official("name", 28, "state");
		officials[1] = new Official("name", 28, "state");
		officials[2] = new Official("name", 28, "state");
		
		return officials;
	}
	
	private static Game[] initiateCompetition(Athlete[] athletes, Official[] officials){
		Game games[] = new Game[5];
		Athlete[] gameAthletes1 = {athletes[0],athletes[1],athletes[2],athletes[3]};
		Athlete[] gameAthletes2 = {athletes[0],athletes[0],athletes[0],athletes[0]};
		Athlete[] gameAthletes3 = {athletes[0],athletes[0],athletes[0],athletes[0]};
		Athlete[] gameAthletes4 = {athletes[0],athletes[0],athletes[0],athletes[0]};
		Athlete[] gameAthletes5 = {athletes[0],athletes[0],athletes[0]};
		
		games[0] = new Game("S01", "Cycling", officials[0], gameAthletes1);
		games[1] = new Game("S01", "Swimming", officials[0], gameAthletes2);
		games[2] = new Game("S01", "Sprinting", officials[0], gameAthletes3);
		games[3] = new Game("S01", "Cycling", officials[0], gameAthletes4);
		games[4] = new Game("S01", "Swimming", officials[0], gameAthletes5);
		
		return games;
	}

}
