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
	Scanner sc = new Scanner(System.in);
	
	public void console(Game[] games, Athlete[] athletes){
		boolean cont = true;
		
		do{
			cont = mainMenu(games,athletes);
		}while(cont);
	}
	
	public int selectGame(Game[] games){
		Scanner sc = new Scanner(System.in);
		int selectedGame = 0;
		
		System.out.println("List of Games");
		System.out.println("===================================");
		for(int i=0;i<games.length;i++){
			System.out.println(i + ". " + games[i].getGameID());
		}
		System.out.println("Select a Game: ");
		
		boolean isValid=false;
		do{
			if (sc.hasNextInt()) {
				if(sc.nextInt()<games.length){
					selectedGame = sc.nextInt();
					isValid = true;
				}
			}
		}while(!isValid);
		
		sc.close();
		return selectedGame;
	}
	
	public void predictWinner(Game game){
		
		System.out.println("List of athletes");
		System.out.println("===================================");
		game.getAthleteNames();
		System.out.println("Select an Athlete: ");
		boolean isValid=false;
		do{
			if (sc.hasNextInt()) {
				if(sc.nextInt()<game.length){
					game.setGamePrediction(sc.nextInt());
					isValid = true;
				}
			}
		}while(!isValid);
		
	}
	
	public void runGame(Game game){
		predictions += game.runGame();
	}
	
	public void displayAllGameResults(Game[] games){
		
	}
	
	public void displayAthletePoints(Athlete[] athletes){
		
		for(int i=0;i<athletes.length;i++){
			System.out.print(i+1);
			//System.out.print(": " + athletes[i].getName());
		}
	}
	
	private boolean mainMenu(Game[] games, Athlete[] athletes){
		boolean cont = true;
		displayMenu();
		if (sc.hasNextInt()) {
			int action = sc.nextInt();
			switch(action){
			case SELECT_GAME:
				currentGame = selectGame(games);
				mainMenu(games, athletes);
				break;
			case PREDICT_WINNER:
				predictWinner(games[currentGame]);
				mainMenu(games, athletes);
				break;
			case START_GAME:
				if(currentGame == 0){
					System.out.println("Please choose a game first");
				}else{
					runGame(games[currentGame-1]);
				}
				mainMenu(games, athletes);
				break;
			case DISPLAY_ALL_GAMES:
				displayAllGameResults(games);
				mainMenu(games, athletes);
				break;
			case DISPLAY_ALL_ATHLETES:
				displayAthletePoints(athletes);
				mainMenu(games, athletes);
				break;
			case EXIT_GAME:
				cont = false;
				break;
			default:
				System.out.println("Please choose a number between 1 and 6");
				mainMenu(games,athletes);
			}
		}else{
			System.out.println("Please choose a valid input");
		}
		return cont;
	}
	
	private void displayMenu(){
		System.out.println("Ozlympic Game");
		System.out.println("===================================");
		System.out.println("1. Select a game to run");
		System.out.println("2. Predict the winner of the game");
		System.out.println("3. Start the game");
		System.out.println("4. Display the final results of all games");
		System.out.println("5. Display the points of all athletes");
		System.out.println("6. Exit");
		System.out.println("Enter an option: ");
	}
}
