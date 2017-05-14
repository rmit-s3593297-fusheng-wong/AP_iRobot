/*****Author - Jitender Singh Padda***********
 *****Student Id - 3628144************************/
package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import fusheng.Driver;
import jitender.Athlete;
import jitender.DatabaseHelper;
import jitender.Game;
import jitender.NoRefereeException;
import jitender.Official;
import jitender.TooFewAthleteException; 

public class Ozlympic {
	private ArrayList<Athlete> athleteList;
	private ArrayList<Official> officialList;
	
	public Ozlympic() throws ClassNotFoundException, FileNotFoundException, SQLException{
		athleteList=new ArrayList<Athlete>();
		officialList=new ArrayList<Official>();
		initiateParticipants();
	}

	public static void main(String[] args) {
		//Initialize Ozlympic - Reads participants
		Ozlympic oz;
		try {
			oz = new Ozlympic();
			oz.startGame();
			oz.getAllGameResults();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out.println("Database File and Text File not found");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Driver userInterface = new Driver();
		//userInterface.console(games,athletes);
	}
	public void startGame(){
		ArrayList<Athlete> selectedAthletes = new ArrayList<Athlete>();
		Official selectedOfficial=officialList.get(0);
		selectedAthletes.addAll(athleteList);
		Game finishedGame=new Game("C02", "Cycling",selectedOfficial,selectedAthletes);
		/*****RUN GAME****/
		try {
			finishedGame.runGame();
		} catch (NoRefereeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TooFewAthleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*****WRITE GAME RESULTS*****/
		try {
			writeGameResults(finishedGame);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initiateParticipants() throws ClassNotFoundException, SQLException, FileNotFoundException{
		DatabaseHelper dbHelper;
		//Add a method for reading Game Results in DatabaseHelper
		/****READ****/
		dbHelper= new DatabaseHelper();
		dbHelper.readParticipants();
		athleteList=dbHelper.getAtheletes();
		officialList=dbHelper.getOfficials();
	}
	public void writeGameResults(Game finishedGame) throws ClassNotFoundException, SQLException, IOException{
		/****WRITE****/
		DatabaseHelper dbHelper= new DatabaseHelper();
		dbHelper.writeGameResults(finishedGame);
	}
	public ArrayList<String> getAllGameResults() throws ClassNotFoundException, SQLException, FileNotFoundException{
		DatabaseHelper dbHelper=new DatabaseHelper();
		return dbHelper.getAllGameResults();
	}
	public ArrayList<Athlete> getAthleteList() {
		return athleteList;
	}
	public ArrayList<Official> getOfficialList() {
		return officialList;
	}
}
