package fusheng;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import jitender.Athlete;
import jitender.Cyclist;
import jitender.Sprinter;
import jitender.Swimmer;

public class ChooseAthletesController {
	private Driver driver;
	
	@FXML private VBox vBox;
	@FXML private Label errorLabel;

	public void setDriver(Driver driver) {
		this.driver = driver;
		displayList();
	}
	
	public void displayList(){
		String gameType;
		for(int i=0;i<driver.getAthleteList().size();i++){
			if(driver.getAthleteList().get(i) instanceof Swimmer){
				gameType = "Swimmer";
			}else if(driver.getAthleteList().get(i) instanceof Cyclist){
				gameType = "Cyclist";
			}else if(driver.getAthleteList().get(i) instanceof Sprinter){
				gameType = "Sprinter";
			}else{
				gameType = "Super";
			}
			CheckBox checkbox = new CheckBox(driver.getAthleteList().get(i).getName() + "-" + gameType);
			checkbox.setId(driver.getAthleteList().get(i).getId());

			vBox.getChildren().add(checkbox);
		}
	}
	
	//assign the athletes to the current game object
	//need to check for quantity error
	@FXML
	public void setGameAthletes(){
		int athleteCounter=0;
		boolean isValid = true;
		errorLabel.setText("");
		//destroy incase there is preexisting data
		driver.getGame().getGameAthletes().clear();
		
		//loop through checkboxes
		for(Node child : vBox.getChildren()){
			if(((CheckBox) child).isSelected()){
				System.out.println("Selected");
				for (Athlete athlete : driver.getAthleteList()) {
					//the athlete ID is stored in the checkbox ID
					if (athlete.getId().equals(((CheckBox) child).getId())) {
						// check athlete type
						if (athlete instanceof Swimmer && driver.getGame().getGameType()!="Swimming") {
							//wrong type error
							errorLabel.setText("Wrong athlete type has been selected");
							isValid = false; break;
						}else if(athlete instanceof Cyclist && driver.getGame().getGameType()!="Cycling") {
							//wrong type error
							errorLabel.setText("Wrong athlete type has been selected");
							isValid = false; break;
						}else if(athlete instanceof Sprinter && driver.getGame().getGameType()!="Sprinting") {
							//wrong type error
							errorLabel.setText("Wrong athlete type has been selected");
							isValid = false; break;
						}else{
							athleteCounter++;
							driver.getGame().getGameAthletes().add(athlete);
						}
					}
				}
			}
		}

		//if < 4 or > 8, cancel display athletes again
		if(athleteCounter<4 && isValid){
			errorLabel.setText("Please select at least 4 athletes to participate");
			isValid = false;
		}else if(athleteCounter < 8 && isValid){
			errorLabel.setText("Please select at most 8 athletes to participate");
			isValid = false;
		}
		
		if(isValid){
			//move to game
			displayGameScene();
		}
	}

	private void displayGameScene(){
		//sort the times first
		System.out.println("Game Running");
		runGame();
		//depends on game type
		//switch case
		switch(driver.getGame().getGameType()){
			case "Swimming":
				driver.getPrimaryStage().setTitle("Swimming Game");
				//driver.getPrimaryStage().setScene(driver.getSwimmingGame());
				driver.getPrimaryStage().show();
				break;
			case "Cycling":
				break;
			case "Sprinting":
				break;
		}
	}
	
	//this method will run the game
	private void runGame(){
		//sort based on times
		driver.getGame().setGameAthletes(driver.getGame().getGameOfficial().sumGame(driver.getGame().getGameAthletes()));
		//add to completed games
		driver.getGameList().add(driver.getGame());
	}
		
	//return to the main menu
	@FXML
	public void back(){
		//maybe create a switch case if there is time
		driver.displayMainMenu();
	}
}
