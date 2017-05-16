package fusheng;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import jitender.Athlete;
import jitender.Cyclist;
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
				//look for the athlete with the id
				for (Athlete athlete : driver.getAthleteList()) {
					//the athlete ID is stored in the checkbox ID
					if (athlete.getId().equals(((CheckBox) child).getId())) {
						// check athlete type
						// System.out.println(athlete.getName() + " " + driver.getGame().getGameType() + " " +!(driver.getGame().getGameType().equals("Cycling")));
						if (athlete instanceof Swimmer && !(driver.getGame().getGameType().equals("Swimming"))) {
							errorLabel.setText("Wrong athlete type has been selected");
							//System.out.println(athlete.getName() + " swim");
							isValid = false; break;
						}else if((athlete instanceof Cyclist) && !(driver.getGame().getGameType().equals("Cycling"))) {
							errorLabel.setText("Wrong athlete type has been selected");
							//System.out.println(athlete.getName() + " cyc");
							isValid = false; break;
						}else if(athlete instanceof Sprinter && !(driver.getGame().getGameType().equals("Sprinting"))) {
							errorLabel.setText("Wrong athlete type has been selected");
							//System.out.println(athlete.getName() + " spr");
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

		System.out.println(isValid);
		if(isValid){
			//move to game
			driver.getGameList().add(driver.getGame());
			displayGameScene();
		}
	}
	
	private boolean validate(int athleteCounter) throws TooFewAthleteException, GameFullException{
		boolean isValid = true;
		if(athleteCounter < 4){
			isValid = false;
			throw new TooFewAthleteException("Please select at least 4 athletes to participate");
		}else if(athleteCounter > 8){
			isValid = false;
			throw new GameFullException("Please select at least 4 athletes to participate");
		}
		return isValid;
	}

	private void displayGameScene(){
		//sort the times first
		System.out.println("Game Running");
		try {
			driver.getGame().runGame();
			GameAnimation gameAnimate=new GameAnimation(driver.getPrimaryStage(), driver.getMainMenuScene(), driver.getGame());
			gameAnimate.runAnimation();
		} catch (TooFewAthleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoRefereeException e) {
			// TODO Auto-generated catch block
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
