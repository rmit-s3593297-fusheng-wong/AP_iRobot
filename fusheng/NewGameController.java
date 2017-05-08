package fusheng;

import javafx.fxml.FXML;
import jitender.Game;

public class NewGameController {
	private Driver driver;
	private Game game;

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	//
	private void displayOfficials(){
		driver.getPrimaryStage().setTitle("Select Official");
		driver.getPrimaryStage().setScene(driver.getChooseOfficialScene());
		driver.getPrimaryStage().show();
	}
	
	//
	private void displayAthletes(){
		driver.getPrimaryStage().setTitle("Select Official");
		driver.getPrimaryStage().setScene(driver.getChooseAthletesScene());
		driver.getPrimaryStage().show();
	}
	
	//assign the game type to the current game object
	@FXML
	public void setGameType(){
		
	}
	
	
	
	//assign the official to the current game object
	@FXML
	public void setGameOfficial(){
		
	}
	
	//assign the athletes to the current game object
	//need to check for quantity error
	@FXML
	public void setGameAthletes(){
		
	}
	
	//return to the main menu
	@FXML
	public void back(){
		driver.displayMainMenu();
	}
	
	//this method will run the game
	public void runGame(){
		
	}
}
