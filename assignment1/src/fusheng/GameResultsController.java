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
	@FXML private TableColumn<Game, String> gameOfficial;
	@FXML private TableColumn<Game, String> gameAthlete1;
	@FXML private TableColumn<Game, String> gameAthlete2;
	@FXML private TableColumn<Game, String> gameAthlete3;
	
public ObservableList<Game> gameList;
	
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	public void viewGameResults() {
		gameID.setCellValueFactory(new PropertyValueFactory<Game, String>("gameID"));
		gameType.setCellValueFactory(new PropertyValueFactory<Game, String>("gameType"));
		gameOfficial.setCellValueFactory(new PropertyValueFactory<Game, String>(driver.getGame().getGameOfficial().getName()));
		gameOfficial.setCellValueFactory(new PropertyValueFactory<Game, String>(driver.getGame().getGameAthletes().get(0).getName()));
		gameOfficial.setCellValueFactory(new PropertyValueFactory<Game, String>(driver.getGame().getGameAthletes().get(1).getName()));
		gameOfficial.setCellValueFactory(new PropertyValueFactory<Game, String>(driver.getGame().getGameAthletes().get(2).getName()));
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
