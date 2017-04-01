package assignment1;

import java.util.Scanner;

/**
 * @author fushwong
 *
 */
public class Driver {
	private static final int SELECT_GAME = 1;
	private static final int PREDICT_WINNER = 2;
	private static final int START_GAME = 3;
	private static final int DISPLAY_ALL_GAMES = 4;
	private static final int DISPLAY_ALL_ATHLETES = 5;
	private static final int EXIT_GAME = 6;
	private int currentGame = 0;
	private int predictions = 0;
	
	public void console(Game[] games, Athlete[] athletes){
		Scanner sc = new Scanner(System.in);
		displayMenu();
		if (sc.hasNextInt()) {
			switch(sc.nextInt()){
			case EXIT_GAME:
				System.out.println("Thank you for playing");
				break;
			case SELECT_GAME:
				selectGame(games, athletes);
				break;
			case PREDICT_WINNER:
				predictWinner(games, athletes);
				break;
			case START_GAME:
				runGame(games, athletes);
				break;
			case DISPLAY_ALL_GAMES:
				displayAllGameResults(games, athletes);
				break;
			case DISPLAY_ALL_ATHLETES:
				displayAthletePoints(games, athletes);
				break;
			default:
				System.out.println("Please choose a number between 1 and 6");
				console(games,athletes);
			}
		}else{
			System.out.println("Please choose a valid input");
			console(games,athletes);
		}
		sc.close();
	}
	
	private void selectGame(Game[] games, Athlete[] athletes){
		
		System.out.println("List of Games");
		System.out.println("===================================");
		String strOutput;
		for(int i=0;i<games.length;i++){
			strOutput = (i+1) + ". " + games[i].getGameID();
			strOutput += " - " + games[i].getGameType();
			System.out.println(strOutput);
		}
		System.out.println("Select a Game: ");
		
		Scanner sc1 = new Scanner(System.in);
		
		if (sc1.hasNextInt()) {
			int input = sc1.nextInt();
			if(input<=games.length){
				//this is the integer the user sees, in array it needs to be -1
				currentGame = input;
				
				//reset the games prediction
				games[currentGame-1].setGamePrediction("");
				console(games,athletes);
			}else{
				System.out.println("Please choose a number between 1 and " + games.length);
				selectGame(games, athletes);
			}
		}else{
			System.out.println("Please choose a number between 1 and " + games.length);
			selectGame(games, athletes);
		}
		sc1.close();
	}
	
	private void predictWinner(Game[] games, Athlete[] athletes){
		//check that game is chosen
		if(gameChosen()){
			//list athletes
			System.out.println("List of athletes");
			System.out.println("===================================");
			games[currentGame-1].getAthleteNames(games[currentGame-1]);
			System.out.println("Select an Athlete: ");
			
			Scanner sc = new Scanner(System.in);
			if (sc.hasNextInt()) {
				int input = sc.nextInt();
				if(input > 0 && input <= games[currentGame-1].getAthleteCount()){
					//get the athletes ID
					String athleteID = games[currentGame-1].getGameAthleteID(input-1);
					games[currentGame-1].setGamePrediction(athleteID); 
					
					console(games,athletes);
				}else{
					System.out.println("Please choose a number between 1 and " + games[currentGame-1].getAthleteCount());
					selectGame(games, athletes);
				}
				
			}else{
				System.out.println("Please choose a number between 1 and " + games[currentGame-1].getAthleteCount());
				selectGame(games, athletes);
			}
			sc.close();
			
		}else{
			System.out.println("Please choose a game first");
			console(games,athletes);
		}
		
		
	}
	
	private void runGame(Game[] games,Athlete[] athletes){
		//check that a game has been chosen
		if(gameChosen()){
			//check that a prediction has been made
			if(games[currentGame-1].getGamePrediction() != ""){
				predictions += games[currentGame-1].runGame();
				
				//reset game's prediction
				games[currentGame-1].setGamePrediction("");
				
				//reset the chosen game
				currentGame = 0;
			}else{
				System.out.println("Please predict a winner first");
			}
		}else{
			System.out.println("Please choose a game first");
		}
		console(games,athletes);
	}
	
	private void displayAllGameResults(Game[] games, Athlete[] athletes){
		for(int i=0;i<games.length;i++){
			if(games[i].hasResult()){
				games[i].displayGameResults();
			}else{
				System.out.println(games[i].getGameID() + "has not yet been run");
			}
		}
		console(games,athletes);
	}
	
	private void displayAthletePoints(Game[] games, Athlete[] athletes){
		for(int i=0;i<athletes.length;i++){
			System.out.println("Name: " + athletes[i].getName());
			System.out.println("===================================");
			System.out.println("Age: " + athletes[i].getAge());
			System.out.println("State: " + athletes[i].getState());
			System.out.println("Points: " + athletes[i].getPoints());
			System.out.println("");
		}
		console(games,athletes);
	}
	
	private void displayMenu(){
		System.out.println("Total successful predictions: " + predictions);
		System.out.println("===================================");
		System.out.println("1. Select a game to run");
		System.out.println("2. Predict the winner of the game");
		System.out.println("3. Start the game");
		System.out.println("4. Display the final results of all games");
		System.out.println("5. Display the points of all athletes");
		System.out.println("6. Exit");
		System.out.println("Enter an option: ");
		System.out.println(" ");
	}
	
	private boolean gameChosen(){
		if(currentGame > 0){
			return true;
		}else{
			return false;
		}
	}
}
