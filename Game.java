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
	private boolean hasResult = false;
	
	public Game(String gameID, String gameType, Official gameOfficial, Athlete[] gameAthletes){
		this.gameID = gameID;
		this.gameType = gameType;
		this.gameOfficial = gameOfficial;
		for(int i=0;i<gameAthletes.length;i++){
			this.gameAthletes[i] = gameAthletes[i];
		}
	}
	
	public boolean hasResult(){
		return this.hasResult;
	}
	
	public String getGameID(){
		return this.gameID;
	}
	
	public String getGameType(){
		return this.gameType;
	}
	
	public int getAthleteCount(){
		//count the non null
		int count = 0;
		for(int i=0;i<this.gameAthletes.length;i++){
			if(this.gameAthletes != null){
				count++;
			}
		}
		return count;
	}
	
	public String getGamePrediction(){
		return this.gamePrediction;
	}
	
	public void setGamePrediction(String athleteID){
		this.gamePrediction = athleteID;
	}
	
	public void getAthleteNames(Game game){
		//this returns all the athlete names
		
		for(int i=0;i<this.gameAthletes.length;i++){
			//check if there is an athlete
			if(game.gameAthletes[i] != null){
				System.out.println((i+1)+": " + game.gameAthletes[i].getName());
			}
			
		}
	}
	
	public String getGameAthleteID(int athleteNo){
		return this.gameAthletes[athleteNo].getId();
	}
	
	public int runGame(){
		boolean isValid = true;
		int prediction = 0;
		
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
		
		//after checks perform actual game
		if(isValid){
			//get times for each athlete
			for (int i=0;i<this.getAthleteCount();i++){
				gameAthletesTimes[i] = gameAthletes[i].compete();
				gameAthletes[i].setTime(gameAthletesTimes[i]);
			}
			
			//sort based on times
			this.gameAthletes = gameOfficial.sumGame(this.gameAthletes);
			
			//display the results
			displayGameResults();
			this.hasResult =  true;
		}
		
		return prediction;
	}
	
	private void displayGameResults(){
		//
	}
	
}
