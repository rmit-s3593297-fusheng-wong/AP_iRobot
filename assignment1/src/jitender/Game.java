/*****Author - Jitender Singh Padda***********
 *****Student Id - 3628144************************/
package jitender;

import java.util.ArrayList;

public class Game {
	private String gameID;
	private String gameType;
	private Official gameOfficial;
	private ArrayList<Athlete> gameAthletes;
	private String officialName;
	private String athleteName1;
	private String athleteName2;
	private String athleteName3;

	public String getOfficialName() {
		return officialName;
	}

	public void setOfficialName(String officialName) {
		this.officialName = officialName;
	}

	public String getAthleteName1() {
		return athleteName1;
	}

	public void setAthleteName1(String athleteName1) {
		this.athleteName1 = athleteName1;
	}

	public String getAthleteName2() {
		return athleteName2;
	}

	public void setAthleteName2(String athleteName2) {
		this.athleteName2 = athleteName2;
	}

	public String getAthleteName3() {
		return athleteName3;
	}

	public void setAthleteName3(String athleteName3) {
		this.athleteName3 = athleteName3;
	}

	public Game() {
		gameAthletes = new ArrayList<Athlete>();
	}

	public Game(String gameID, String gameType, Official gameOfficial, ArrayList<Athlete> gameAthletes) {
		this.gameID = gameID;
		this.gameType = gameType;
		this.gameOfficial = gameOfficial;
		this.gameAthletes = gameAthletes;
	}

	public String getGameID() {
		return this.gameID;
	}

	public void setGameID(String gameID) {
		this.gameID = gameID;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public void setGameOfficial(Official gameOfficial) {
		this.gameOfficial = gameOfficial;
	}

	public void setGameAthletes(ArrayList<Athlete> gameAthletes) {
		this.gameAthletes = gameAthletes;
	}

	public String getGameType() {
		return this.gameType;
	}

	public void runGame() throws TooFewAthleteException, NoRefereeException {
		// Run Game
		for (Athlete gameAthlete : gameAthletes) {
			// Let Super Athlete know gameType
			if (gameAthlete instanceof SuperAthlete) {
				((SuperAthlete) gameAthlete).setGameType(this.gameType);
			}
			gameAthlete.setTime(gameAthlete.compete());
		}

		// Pass Athletes to Official to summarize game and return sorted Athletes based on their time
		this.gameAthletes = gameOfficial.sumGame(this.gameAthletes);

		// set the winner's points
		int athletePoints = 0;
		// get previous points
		athletePoints = gameAthletes.get(0).getPoints();
		// add new points
		gameAthletes.get(0).setPoints(athletePoints + 5); // winner gets 5
		athletePoints = gameAthletes.get(1).getPoints();
		gameAthletes.get(1).setPoints(athletePoints + 2); // 2nd gets 2
		athletePoints = gameAthletes.get(2).getPoints();
		gameAthletes.get(2).setPoints(athletePoints + 1); // 3rd gets 1

		// display the results
		displayGameResults();
	}

	public void displayGameResults() {
		// Display game details
		System.out.println(this.gameID + " - " + this.gameType);
		System.out.println("===================================");
		System.out.println("Official: " + this.gameOfficial.getName());

		// display first 3 positions
		String strOutput;

		for (int i = 0; i < 3; i++) {
			strOutput = (i + 1) + ": " + gameAthletes.get(i).getName() + " - ";
			strOutput += this.gameAthletes.get(i).getTime() + "s";
			System.out.println(strOutput);
		}
		System.out.println("");
	}

	public ArrayList<Athlete> getGameAthletes() {
		return gameAthletes;
	}

	public Official getGameOfficial() {
		return gameOfficial;
	}
}