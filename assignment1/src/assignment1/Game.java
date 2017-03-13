package assignment1;

public class Game {
	
	private String gameID;
	private String gameType;
	private String gameOfficial;
	private String[] gameAthletes;
	private int athleteCount;
	
	private final int maxAthlete = 8;
	
	public Game(String gameID, String gameType){
		this.gameID = gameID;
		this.gameType = gameType;
	}
	
	public String getGameID(){
		return this.gameID;
	}
	
	public String getGameType(){
		return this.gameType;
	}
	
	public String getGameOfficial(){
		return this.gameOfficial;
	}
	
	public int getAthleteCount(){
		return this.athleteCount;
	}
	
	public int getMaxAthlete(){
		return this.maxAthlete;
	}
	
}
