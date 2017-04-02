package fusheng;

import jitender.Athlete;
import jitender.Cyclist;
import jitender.Official;
import jitender.Sprinter;
import jitender.SuperAthlete;
import jitender.Swimmer; 

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
		
		System.out.println("Ozlympic Game");
		Driver userInterface = new Driver();
		userInterface.console(games,athletes);
	}
	
	private static Athlete[] initiateAthletes(){
		Athlete athletes[] = new Athlete[12];
		athletes[0] = new Cyclist("AC01","Rohan Dennis", 26, "South Australia", 0);
		athletes[1] = new Cyclist("AC02","Simon Gerrens", 36, "Victoria", 0);
		athletes[2] = new Cyclist("AC03","Cadel Evans", 40, "Northern Territory", 0);
		athletes[3] = new Swimmer("AS01","Libby Trickett", 32, "Queensland", 0);
		athletes[4] = new Swimmer("AS02","Leisel Jones", 31, "Northern Territory", 0);
		athletes[5] = new Swimmer("AS03","Emily Seebohm", 24, "South Australia", 0);
		athletes[6] = new Sprinter("AP01","Alex Hartmann", 24, "South Australia", 0);
		athletes[7] = new Sprinter("AP02","Josh Clarke", 21, "New South Wales", 0);
		athletes[8] = new Sprinter("AP03","Tim Leathart", 27, "New South Wales", 0);
		athletes[9] = new SuperAthlete("AA01","Chuck Norris", 77, "Oklahoma", 0);
		athletes[10] = new SuperAthlete("AA02","Triple H", 47, "New Hampshire", 0);
		athletes[11] = new SuperAthlete("AA03","Mike Tyson", 50, "New York", 0);
		
		return athletes;
	}
	
	private static Official[] initiateOfficials(){
		Official officials[] = new Official[3];
		officials[0] = new Official("O01","Cycling Referee", 40, "Victoria");
		officials[1] = new Official("O02","Swimming Referee", 41, "Victoria");
		officials[2] = new Official("O03","Sprinting Referee", 42, "Victoria");
		
		return officials;
	}
	
	private static Game[] initiateCompetition(Athlete[] athletes, Official[] officials){
		Game games[] = new Game[5];
		Athlete[] gameAthletes1 = {athletes[0],athletes[1],athletes[2],athletes[9]};
		Athlete[] gameAthletes2 = {athletes[3],athletes[4],athletes[5],athletes[10]};
		Athlete[] gameAthletes3 = {athletes[6],athletes[7],athletes[8],athletes[11]};
		Athlete[] gameAthletes4 = {athletes[0],athletes[1],athletes[2],athletes[9],athletes[10],athletes[11]};
		Athlete[] gameAthletes5 = {athletes[3],athletes[4],athletes[11]};
		
		games[0] = new Game("C01", "Cycling", officials[0], gameAthletes1);
		games[1] = new Game("S01", "Swimming", officials[1], gameAthletes2);
		games[2] = new Game("P01", "Sprinting", officials[2], gameAthletes3);
		games[3] = new Game("C02", "Cycling", officials[0], gameAthletes4);
		games[4] = new Game("S02", "Swimming", officials[1], gameAthletes5);
		
		return games;
	}

}
