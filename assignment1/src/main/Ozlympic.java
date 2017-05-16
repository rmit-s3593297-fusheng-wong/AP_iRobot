/*****Author - Jitender Singh Padda***********
 *****Student Id - 3628144************************/
package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import fusheng.Driver;
import jitender.DatabaseHelper;
import jitender.Game;

public class Ozlympic {

	public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, SQLException {		
		
		Driver userInterface = new Driver();
		userInterface.begin(args);
		//userInterface.console(games,athletes);
	}
	
	public void writeGameResults(Game finishedGame) throws ClassNotFoundException, SQLException, IOException{
		/****WRITE****/
		DatabaseHelper dbHelper= new DatabaseHelper();
		dbHelper.writeGameResults(finishedGame);
	}
	
}
