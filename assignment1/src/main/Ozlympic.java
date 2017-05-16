/*****Author - Jitender Singh Padda***********
 *****Student Id - 3628144************************/
package main;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import fusheng.Driver;

public class Ozlympic {

	public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, SQLException {		
		
		Driver userInterface = new Driver();
		userInterface.begin(args);
		//userInterface.console(games,athletes);
	}
	
}
