package fusheng;

import java.util.List;

import javafx.fxml.FXML;
import jitender.Athlete;
import jitender.Game;

public class ResultsController {
	private Driver driver;
	private List<Game> gameList;
	private List<Athlete> athleteList;
	
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	public void setGameList(List<Game> gameList) {
		this.gameList = gameList;
	}
	
	public void setAthleteList(List<Athlete> athleteList) {
		this.athleteList = athleteList;
	}
	
	public List<Game> getGameList() {
		return gameList;
	}
	
	public List<Athlete> getAthleteList() {
		return athleteList;
	}
	
	//return to the main menu
	@FXML
	public void back(){
		driver.displayMainMenu();
	}
}
