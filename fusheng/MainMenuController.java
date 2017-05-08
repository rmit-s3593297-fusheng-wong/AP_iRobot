package fusheng;

import javafx.fxml.FXML;

public class MainMenuController {

	private Driver driver;

	public void setDriver(Driver driver){
		this.driver = driver;
	}	

	// begins creates a new game
	@FXML
	public void createGame() {
		driver.getPrimaryStage().setTitle("Create New Game");
		driver.getPrimaryStage().setScene(driver.getChooseGameScene());
		driver.getPrimaryStage().show();
	}

	// displays all game results
	@FXML
	public void viewGameResults() {
		driver.getPrimaryStage().setTitle("View Game Results");
		driver.getPrimaryStage().setScene(driver.getGameResultsScene());
		driver.getPrimaryStage().show();
	}

	// displays all athletes results
	@FXML
	public void viewAthleteResults() {
		driver.getPrimaryStage().setTitle("View Athlete Results");
		driver.getPrimaryStage().setScene(driver.getAthletesResultScene());
		driver.getPrimaryStage().show();
	}

	// closes the application
	@FXML
	public void closeApplication() {
		System.exit(0);
	}

}
