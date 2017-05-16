package fusheng;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import jitender.Game;

public class GameResultsController {
	private Driver driver;
	@FXML private TableView<Game> gameTable;
	@FXML private TableColumn<Game, String> gameID;
	@FXML private TableColumn<Game, String> gameType;
	@FXML private TableColumn<Game, String> officialName;
	@FXML private TableColumn<Game, String> athleteName1;
	@FXML private TableColumn<Game, String> athleteName2;
	@FXML private TableColumn<Game, String> athleteName3;
	
public ObservableList<Game> gameList;
	
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	public void viewGameResults() {
		gameID.setCellValueFactory(new PropertyValueFactory<Game, String>("gameID"));
		gameType.setCellValueFactory(new PropertyValueFactory<Game, String>("gameType"));
		officialName.setCellValueFactory(new PropertyValueFactory<Game, String>("officialName"));
		athleteName1.setCellValueFactory(new PropertyValueFactory<Game, String>("athleteName1"));
		athleteName2.setCellValueFactory(new PropertyValueFactory<Game, String>("athleteName2"));
		athleteName3.setCellValueFactory(new PropertyValueFactory<Game, String>("athleteName3"));
		gameList = FXCollections.observableList(driver.getGameList());
		gameTable.setItems(gameList);
		driver.getPrimaryStage().setTitle("View Game Results");
		driver.getPrimaryStage().setScene(driver.getGameResultsScene());
		driver.getPrimaryStage().show();
	}
	
	//return to the main menu
	@FXML
	public void back(){
		driver.displayMainMenu();
	}
	
}
