package fusheng;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import jitender.Official;

public class ChooseOfficialController {
	private Driver driver;
	
	@FXML
	private FlowPane flowPane;

	public void setDriver(Driver driver) {
		this.driver = driver;
		displayList();
	}
	
	public void displayList(){
		for(int i=0;i<driver.getOfficialList().size();i++){
			Button button = new Button(driver.getOfficialList().get(i).getName());
			button.setId(driver.getOfficialList().get(i).getId());
			button.getStyleClass().add("officials");
			
			officialHandlerClass handler = new officialHandlerClass();
			button.setOnAction(handler);

			flowPane.getChildren().add(button);
		}
	}
	
	//display the athlete list after selecting official
	class officialHandlerClass implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent e){
			Button btn = (Button) e.getSource();
			for(Official official : driver.getOfficialList()) { 
			   if(official.getId().equals(btn.getId())) { 
			       //there wont be any duplicates so get out of the loop
				   driver.getGame().setGameOfficial(official);
				   break;
			   }
			}
			
			displayAthletesScene();
		}
	}
	
	private void displayAthletesScene(){
		driver.getPrimaryStage().setTitle("Select Athlete");
		driver.getPrimaryStage().setScene(driver.getChooseAthletesScene());
		driver.getPrimaryStage().show();
	}
	
	//return to the main menu
	@FXML
	public void back(){
		//maybe create a switch case if there is time
		driver.displayMainMenu();
	}
}
