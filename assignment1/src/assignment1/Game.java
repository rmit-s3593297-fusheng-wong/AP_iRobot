package assignment1;

/**
 * @author fushwong
 *
 */
public class Game {
	private static final int MAXATHLETE = 8;
	private String gameID;
	private String gameType;
	private Official gameOfficial;
	private String gamePrediction;
	private Athlete[] gameAthletes = new Athlete[MAXATHLETE];
	private int[] gameAthletesTimes;
	
	public Game(String gameID, String gameType, Official gameOfficial, Athlete[] gameAthletes){
		this.gameID = gameID;
		this.gameType = gameType;
		this.gameOfficial = gameOfficial;
		for(int i=0;i<gameAthletes.length;i++){
			this.gameAthletes[i] = gameAthletes[i];
		}
	}
	
	public String getGameID(){
		return this.gameID;
	}
	
	public String getGameType(){
		return this.gameType;
	}
	
	public int getAthleteCount(){
		return this.gameAthletes.length;
	}
	
	public String getGamePrediction(){
		return this.gamePrediction;
	}
	
	public String[] getAthleteNames(){
		//this returns all the athlete names
		String[] athleteNames = new String[this.gameAthletes.length];
		
		for(int i=0;i<this.gameAthletes.length;i++){
			//athleteNames[i] = game.gameAthletes[i].getName();
			athleteNames[i] = "AthleteName";
		}
		return athleteNames;
	}
	
	public void setGamePrediction(String gamePrediction){
		this.gamePrediction = gamePrediction;
	}
	
	public void runGame(){
		boolean isValid = true;
		
		//check there are enough athletes
		if(this.getAthleteCount()<4){
			System.out.println("There are insufficient athletes to run this game");
			isValid = false;
		}
		
		//check for a prediction prediction
		if(this.getAthleteCount()<4){
			System.out.println("There are insufficient athletes to run this game");
			isValid = false;
		}
		
		//get times for each athlete
		for (int i=0;i<this.getAthleteCount();i++){
			gameAthletesTimes[i] = gameAthletes[i].compete();
			//gameAthletes[i].setFinishTime(gameAthletesTimes[i]);
		}
		
		//get the winner
		//Official.sumGame();
		
		//display the results
		displayGameResults();
	}
	
	private void displayGameResults(){
		
	}
	
}
