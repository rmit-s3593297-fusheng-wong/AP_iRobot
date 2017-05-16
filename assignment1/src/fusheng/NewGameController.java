package fusheng;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class NewGameController{
	private Driver driver;
	@FXML private Button Cycling, Swimming, Sprinting;

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	//display the official list after selecting game
	@FXML
	private void displayOfficials(Event event){
		//set the gametype
		Button btn = (Button) event.getSource();
		driver.getGame().setGameType(btn.getId());
		
		displayOfficialsScene();
	}	
	
	private void displayOfficialsScene(){
		driver.getPrimaryStage().setTitle("Select Official");
		driver.getPrimaryStage().setScene(driver.getChooseOfficialScene());
		driver.getPrimaryStage().show();
	}
	
	//return to the main menu
	@FXML
	public void back(){
		//maybe create a switch case if there is time
		driver.displayMainMenu();
	}
}
