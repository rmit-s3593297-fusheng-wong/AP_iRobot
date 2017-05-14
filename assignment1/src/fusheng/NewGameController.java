package fusheng;

import java.util.ArrayList;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import jitender.Game;
import jitender.Official;
import jitender.Participant;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class NewGameController{
	private Driver driver;
	@FXML private Button Cycling, Swimming, Sprinting;
	
	@FXML
	private FlowPane pane;

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	//display the offical list
	@FXML
	private void displayOfficials(Event event){
		//set the gametype
		Button btn = (Button) event.getSource();
		driver.getGame().setGameType(btn.getId());
		
		displayOfficialsScene();
	}
	
	@FXML
	private void displayAthletes(Event event){
		//set the official
		Button btn = (Button) event.getSource();
		//get the driver object
		for(Official official : driver.getOfficialList()) { 
		   if(official.getId().equals(btn.getId())) { 
		       //there wont be any duplicates so get out of the loop
			   driver.getGame().setGameOfficial(official);
			   break;
		   }
		}
		
		displayAthletesScene();
	}
	
	//assign the athletes to the current game object
	//need to check for quantity error
	@FXML
	public void setGameAthletes(){
		//destroy incase there is preexisting data
		
		//loop through checkboxes
		/*for(){
			
		}*/
		
		//if < 4 or > 8, cancel display athletes again
		
		//else insert the array
	}
	
	private void displayOfficialsScene(){
		driver.getPrimaryStage().setTitle("Select Official");
		driver.getPrimaryStage().setScene(driver.getChooseOfficialScene());
		driver.getPrimaryStage().show();
	}
	
	private void displayAthletesScene(){
		driver.getPrimaryStage().setTitle("Select Official");
		driver.getPrimaryStage().setScene(driver.getChooseAthletesScene());
		driver.getPrimaryStage().show();
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
