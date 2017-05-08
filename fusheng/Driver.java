package fusheng;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Ozlympic;

//this class will handle the views
public class Driver extends Application {
	private Stage primaryStage;
	private MainMenuController mainMenuController;
	private NewGameController newGameController;
	private ResultsController gameResultsController;
	private ResultsController athleteResultsController;
	private Scene mainMenuScene;
	private Scene chooseGameScene;
	private Scene chooseOfficialScene;
	private Scene chooseAthletesScene;
	private Scene runSprintScene;
	private Scene runSwimmingScene;
	private Scene runCyclingScene;
	private Scene gameResultsScene;
	private Scene athleteResultsScene;

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public MainMenuController getMainMenuController() {
		return mainMenuController;
	}

	public NewGameController getNewGameController() {
		return newGameController;
	}

	public ResultsController getGameResultsController() {
		return gameResultsController;
	}
	
	public ResultsController getAthleteResultsController() {
		return athleteResultsController;
	}

	public Scene getMainMenuScene() {
		return mainMenuScene;
	}

	public Scene getChooseGameScene() {
		return chooseGameScene;
	}

	public Scene getChooseOfficialScene() {
		return chooseOfficialScene;
	}

	public Scene getChooseAthletesScene() {
		return chooseAthletesScene;
	}

	public Scene getRunSprintScene() {
		return runSprintScene;
	}

	public Scene getRunSwimmingScene() {
		return runSwimmingScene;
	}

	public Scene getRunCyclingScene() {
		return runCyclingScene;
	}
	
	public Scene getGameResultsScene() {
		return gameResultsScene;
	}
	
	public Scene getAthletesResultScene() {
		return athleteResultsScene;
	}

	//real program starts here
	public void begin(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setResizable(false);

		initiateScenes();
		displayMainMenu();
		//load db
	}
	
	//this method initiates the scenes
	private void initiateScenes(){
		try {
			//main menu
			FXMLLoader loader = new FXMLLoader(Ozlympic.class.getResource("/fusheng/MainMenu.fxml"));
			Parent root = (Parent) loader.load();
			mainMenuController = loader.getController();
			mainMenuScene = new Scene(root, 600, 600);
			mainMenuController.setDriver(this);
			
			//choose game
			FXMLLoader loader2 = new FXMLLoader(Ozlympic.class.getResource("/fusheng/ChooseGame.fxml"));
			Parent root2 = (Parent) loader2.load();
			chooseGameScene = new Scene(root2, 600, 600);
			newGameController = loader2.getController();
			newGameController.setDriver(this);
			
			//choose official
			/*FXMLLoader loader3 = new FXMLLoader(Ozlympic.class.getResource("/fusheng/ChooseOfficial.fxml"));
			Parent root3 = (Parent) loader3.load();
			chooseGameScene = new Scene(root3, 600, 600);
			newGameController = loader3.getController();
			newGameController.setDriver(this);
			
			//choose athletes
			FXMLLoader loader4 = new FXMLLoader(Ozlympic.class.getResource("/fusheng/ChooseAthletes.fxml"));
			Parent root4 = (Parent) loader4.load();
			chooseGameScene = new Scene(root4, 600, 600);
			newGameController = loader4.getController();
			newGameController.setDriver(this);			*/
			
			//game results
			FXMLLoader loader5 = new FXMLLoader(Ozlympic.class.getResource("/fusheng/GameResults.fxml"));
			Parent root5 = (Parent) loader5.load();
			gameResultsScene = new Scene(root5, 600, 600);
			gameResultsController = loader5.getController();
			gameResultsController.setDriver(this); 
			
			//athlete results
			FXMLLoader loader6 = new FXMLLoader(Ozlympic.class.getResource("/fusheng/AthletePoints.fxml"));
			Parent root6 = (Parent) loader6.load();
			athleteResultsScene = new Scene(root6, 600, 600);
			gameResultsController = loader6.getController();
			gameResultsController.setDriver(this); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println(e.getMessage());
		}
	}
	
	//this is the start of the user interface
	public void displayMainMenu(){
		primaryStage.setTitle("Main Menu");
		primaryStage.setScene(mainMenuScene);
		primaryStage.show();
	}

}
