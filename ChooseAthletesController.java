package fusheng;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import jitender.Athlete;
import jitender.Cyclist;
import jitender.DatabaseHelper;
import jitender.Game;
import jitender.GameAnimation;
import jitender.GameFullException;
import jitender.NoRefereeException;
import jitender.Sprinter;
import jitender.Swimmer;
import jitender.TooFewAthleteException;

public class ChooseAthletesController {
	private Driver driver;
	
	@FXML private VBox vBox;
	@FXML private Label errorLabel;
	@FXML private ScrollPane scrollPane;

	public void setDriver(Driver driver) {
		this.driver = driver;
		displayList();
	}
	
	//list the athletes
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
				//look for the athlete with the id
				for (Athlete athlete : driver.getAthleteList()) {
					//the athlete ID is stored in the checkbox ID
					if (athlete.getId().equals(((CheckBox) child).getId())) {
						// check athlete type
						if (athlete instanceof Swimmer && !(driver.getGame().getGameType().equals("Swimming"))) {
							errorLabel.setText("Wrong athlete type has been selected");
							isValid = false; break;
						}else if((athlete instanceof Cyclist) && !(driver.getGame().getGameType().equals("Cycling"))) {
							errorLabel.setText("Wrong athlete type has been selected");
							isValid = false; break;
						}else if(athlete instanceof Sprinter && !(driver.getGame().getGameType().equals("Sprinting"))) {
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
		if(isValid){
			try{
				isValid = validate(athleteCounter);
			}catch (TooFewAthleteException e){
				isValid = false;
				e.printStackTrace();
				errorLabel.setText(e.getMessage());
			} catch (GameFullException e) {
				isValid = false;
				e.printStackTrace();
				errorLabel.setText(e.getMessage());
			}
		}

		if(isValid){
			//move to game
			try {
				displayGameScene();
			} catch (ClassNotFoundException | SQLException | IOException e) {
				errorLabel.setText(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	private boolean validate(int athleteCounter) throws TooFewAthleteException, GameFullException{
		boolean isValid = true;
		if(athleteCounter < 4){
			isValid = false;
			throw new TooFewAthleteException("Please select at least 4 athletes to participate");
		}else if(athleteCounter > 8){
			isValid = false;
			throw new GameFullException("Please select at most 8 athletes to participate");
		}
		return isValid;
	}

	private void displayGameScene() throws ClassNotFoundException, SQLException, IOException{
		//sort the times first
		
		try {
			String newID="";
			String padding="";
			driver.increaseGameCounter();
			if(driver.getGameCounter()<10){
				padding += "0" + driver.getGameCounter();
			}else{
				padding += driver.getGameCounter();
			}
			
			switch(driver.getGame().getGameType()){
			case "Swimming":
				newID = "S" + padding;
				break;
			case "Sprinting":
				newID = "R" + padding;
				break;
			case "Cycling":
				newID = "C" + padding;
				break;
			}
			driver.getGame().runGame();
			
			String OfficialName = driver.getGame().getGameOfficial().getName();
			String AthleteName1 = driver.getGame().getGameAthletes().get(0).getName();
			String AthleteName2 = driver.getGame().getGameAthletes().get(1).getName();
			String AthleteName3 = driver.getGame().getGameAthletes().get(2).getName();
			
			driver.getGame().setGameID(newID);
			driver.getGame().setOfficialName(OfficialName);
			driver.getGame().setAthleteName1(AthleteName1);
			driver.getGame().setAthleteName2(AthleteName2);
			driver.getGame().setAthleteName3(AthleteName3);
			
			Game toSave = new Game();
			toSave.setGameID(driver.getGame().getGameID());
			toSave.setGameType(driver.getGame().getGameType());
			toSave.setGameOfficial(driver.getGame().getGameOfficial());
			toSave.setGameAthletes(driver.getGame().getGameAthletes());
			toSave.setOfficialName(driver.getGame().getOfficialName());
			toSave.setAthleteName1(driver.getGame().getAthleteName1());
			toSave.setAthleteName2(driver.getGame().getAthleteName2());
			toSave.setAthleteName3(driver.getGame().getAthleteName3());
			
			driver.getGameList().add(toSave);
			
			DatabaseHelper dbHelper= new DatabaseHelper();
			dbHelper.writeGameResults(toSave);
			GameAnimation gameAnimate=new GameAnimation(driver.getPrimaryStage(), driver.getMainMenuScene(), driver.getGame());
			gameAnimate.runAnimation();
			
		} catch (TooFewAthleteException e) {
			errorLabel.setText(e.getMessage());
			e.printStackTrace();
		} catch (NoRefereeException e) {
			errorLabel.setText(e.getMessage());
			e.printStackTrace();
		}
		
	}
		
	//return to the main menu
	@FXML
	public void back(){
		//maybe create a switch case if there is time
		driver.displayMainMenu();
	}
}
