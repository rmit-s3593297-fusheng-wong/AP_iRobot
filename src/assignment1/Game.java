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
	private int[] gameAthletesTimes = new int[MAXATHLETE];
	private boolean hasResult = false; //this is for quick reference when displaying all games
	
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
			if(this.gameAthletes[i] != null){
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
		if(this.gamePrediction == ""){
			System.out.println("Please make a prediction before running a game");
			isValid = false;
		}
		
		//after checks perform actual game
		if(isValid){
			//get times for each athlete
			for (int i=0;i<this.getAthleteCount();i++){
				//let the superathlete know the game type
				if(gameAthletes[i] instanceof SuperAthlete){
					((SuperAthlete) gameAthletes[i]).setGameType(this.gameType);
				}
				gameAthletes[i].setTime(gameAthletes[i].compete());
			}
			
			//sort based on times
			this.gameAthletes = gameOfficial.sumGame(this.gameAthletes);
			
			for (int i=0;i<this.getAthleteCount();i++){
				//save the times to be called in the future
				gameAthletesTimes[i] = gameAthletes[i].getTime();
			}
			
			//set the winner's points
			int athletePoints=0;
			for (int i=0;i<3;i++){
				//get previous points
				athletePoints = gameAthletes[i].getPoints();
				//add new points
				gameAthletes[i].setPoints(athletePoints + (3-i));
			}
			
			//check prediction is the same as the winner
			if(this.gamePrediction == gameAthletes[0].getId()){
				prediction = 1;
			}
			
			//display the results
			displayGameResults();
			this.hasResult =  true;
		}
		
		return prediction;
	}
	
	public void displayGameResults(){
		//Display game details
		System.out.println(this.gameID + " - " + this.gameType);
		System.out.println("===================================");
		System.out.println("Official: " + this.gameOfficial.getName());
		
		//display first 3 positions
		String strOutput;
		
		for(int i = 0;i<3;i++){
			strOutput = (i+1) + ": " + this.gameAthletes[i].getName() + " - ";
			strOutput += this.gameAthletesTimes[i] + "s";
			System.out.println(strOutput);
		}
		System.out.println("");
	}
	
}
