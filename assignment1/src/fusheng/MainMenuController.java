package fusheng;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainMenuController {

	private Driver driver;
	
	@FXML Label errorLabel;

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
		System.out.println(driver.getGameList().size());
		if(driver.getGameList().size()>0){
			errorLabel.setText("");
			driver.getGameResultsController().viewGameResults();
			driver.getPrimaryStage().setTitle("View Game Results");
			driver.getPrimaryStage().setScene(driver.getGameResultsScene());
			driver.getPrimaryStage().show();
		}else{
			//throw error
			errorLabel.setText("No Games Have been run yet");
		}
	}

	// displays all athletes results
	@FXML
	public void viewAthleteResults() {
		driver.getAthleteResultsController().viewAthleteResults();
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
