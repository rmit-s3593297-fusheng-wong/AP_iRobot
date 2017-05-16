package fusheng;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import jitender.Athlete;

public class ResultsController {
	private Driver driver;
	
	@FXML private TableView<Athlete> athleteTable;
	@FXML private TableColumn<Athlete, String> id;
	@FXML private TableColumn<Athlete, String> name;
	@FXML private TableColumn<Athlete, Integer> age;
	@FXML private TableColumn<Athlete, String> state;
	@FXML private TableColumn<Athlete, Integer> points;
	
	public ObservableList<Athlete> athleteList;
	
	public void setDriver(Driver driver) {
		this.driver = driver;
		athleteList = FXCollections.observableList(driver.getAthleteList());
	}
	
	public void viewAthleteResults() {
		id.setCellValueFactory(new PropertyValueFactory<Athlete, String>("id"));
		name.setCellValueFactory(new PropertyValueFactory<Athlete, String>("name"));
		age.setCellValueFactory(new PropertyValueFactory<Athlete, Integer>("age"));
		state.setCellValueFactory(new PropertyValueFactory<Athlete, String>("state"));
		points.setCellValueFactory(new PropertyValueFactory<Athlete, Integer>("points"));
		
		athleteTable.setItems(athleteList);
		
		driver.getPrimaryStage().setTitle("View Athlete Results");
		driver.getPrimaryStage().setScene(driver.getAthletesResultScene());
		driver.getPrimaryStage().show();
	}
	
	//return to the main menu
	@FXML
	public void back(){
		driver.displayMainMenu();
	}
}
