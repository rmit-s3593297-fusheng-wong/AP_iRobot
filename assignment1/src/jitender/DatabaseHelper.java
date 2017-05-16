/*****Author - Jitender Singh Padda***********
 *****Student Id - 3628144************************/
package jitender;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DatabaseHelper {
	private Connection con;
	private ArrayList<Athlete> athleteList;
	private ArrayList<Official> officialList;
	
	public DatabaseHelper() throws ClassNotFoundException, SQLException{
		athleteList=new ArrayList<Athlete>();
		officialList=new ArrayList<Official>();
		//Check if SQLite database file exists, otherwise don't create connection
		File dbFile = new File ("Ozlympic.db");
		if(dbFile.exists()){
			DatabaseConnection dbCon=new DatabaseConnection();
			con=dbCon.SQLiteConnection();
		}		
	}
	public ArrayList<Athlete> getAtheletes(){
		return athleteList;		
	}
	public ArrayList<Official> getOfficials(){
		return officialList;	
	}
	public void readParticipants() throws FileNotFoundException, SQLException{
		//Read and Validate from Txt or SQLite
		String participants=(con==null)? validate(readParticipantsTxt()):validate(readSQLiteDB());
		//Parse data rows into Participants
		for(String participant :participants.split("\n")){
			String[] participantAttributes=participant.split(",");
			switch((participantAttributes[1]).trim()){
				case "sprinter": athleteList.add(new Sprinter(participantAttributes[0], participantAttributes[2], Integer.valueOf(participantAttributes[3]), participantAttributes[4], 0));
								 break;
				case "cyclist": athleteList.add(new Cyclist(participantAttributes[0], participantAttributes[2], Integer.valueOf(participantAttributes[3]), participantAttributes[4], 0));
				 				 break;
				case "swimmer": athleteList.add(new Swimmer(participantAttributes[0], participantAttributes[2], Integer.valueOf(participantAttributes[3]), participantAttributes[4], 0));
				 				 break;
				case "super": athleteList.add(new SuperAthlete(participantAttributes[0], participantAttributes[2], Integer.valueOf(participantAttributes[3]), participantAttributes[4], 0));
				 				 break;
				case "officer": officialList.add(new Official(participantAttributes[0], participantAttributes[2], Integer.valueOf(participantAttributes[3]), participantAttributes[4]));
 				 				 break; 				 
			}
		}
		System.out.println("athleteList---");
		for(Athlete athlete: athleteList){
			System.out.println("athlete---"+athlete.getName()+","+athlete.getId()+","+athlete.getAge()+","+athlete.getState()+","+athlete.getPoints());
		}
		System.out.println("officialList---");
		for(Official official: officialList){
			System.out.println("official---"+official.getName()+","+official.getId()+","+official.getAge()+","+official.getState());
		}
	}
	//Write game results
	public void writeGameResults(Game finishedGame) throws SQLException, IOException{
		if(con==null)
			writeGameResultTxt(finishedGame);
		else 
			writeDatabase(finishedGame);
	}
	//Read file participants.txt
	public String readParticipantsTxt() throws FileNotFoundException{
		String participants="";
		Scanner	scan = new Scanner(new FileReader("participants.txt"));
		while(scan.hasNextLine()){
			participants=participants+scan.nextLine()+"\n";
		}
		scan.close();
		System.out.println("TextFile participants");
		return participants;
	}
	//Write to gameResults.txt
	public void writeGameResultTxt(Game finishedGame) throws IOException{

		PrintWriter out = new PrintWriter(new FileWriter("gameResults.txt", true));
		String timeStamp=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(System.currentTimeMillis());
		out.println(finishedGame.getGameID()+", "+finishedGame.getGameOfficial().getId()+", "+timeStamp.substring(0,timeStamp.length()-1));
		for(Athlete athlete:finishedGame.getGameAthletes()){
			out.println(athlete.getId()+", "+athlete.getTime()+", "+athlete.getPoints());
		}
		out.println();
		out.close();
	}
	//Read file Ozlympic.db
	public String readSQLiteDB() throws SQLException{
		String participants="";
		ResultSet result=con.createStatement().executeQuery("SELECT id,type,name,age,state FROM participants");
		while(result.next()){
			participants=participants+result.getString("id")+", "+result.getString("type")+", "+result.getString("name")+", "+result.getString("age")+", "+result.getString("state")+"\n";
		}
		System.out.println("SQLite participants");
		con.close();
		return participants;		
	}
	//Validate -> Check for duplicates and null values in columns
	public String validate(String participants){
		String validParticipants="";
		for(String participant : participants.split("\n")){
			participant=participant.trim();
			String participantId=participant.split(",")[0].trim();
			int count=0;
			//Validation for all entries of the participant - should have all values present
			for(String value:participant.split(",")){
				value=value.trim();
				if(value.isEmpty()==false){
					count++;
				}
			}
			if(count==5){// all 5 entries confirmed
				if(!validParticipants.contains(participantId)){// Duplicate Check - If ID already exists then do not add
					participant=participant.replaceAll("\\s","");
					validParticipants=validParticipants+participant+"\n";
				}
			}
		}
		//System.out.println("validParticipants\n"+validParticipants);
		return validParticipants;
	}
	//Write to Ozlympic.db
	public void writeDatabase(Game finishedGame) throws SQLException{
		String insertValues="";
		for(Athlete athlete:finishedGame.getGameAthletes()){
			insertValues=insertValues+"('"+finishedGame.getGameID()+"'"+","+"'"+finishedGame.getGameOfficial().getId()+"'"+","+"'"+athlete.getId()+"'"+","+athlete.getTime()+","+athlete.getPoints()+"),";
		}
		insertValues=insertValues.substring(0,insertValues.length()-1)+";";
		String updateStatement="INSERT INTO gameResults (\"Game ID\",\"Official ID\",\"Athlete ID\",Result,Score) VALUES "+insertValues;
		con.createStatement().executeUpdate(updateStatement);
		con.close();
	}
	//Get all Game Results
	public ArrayList<String> getAllGameResults() throws FileNotFoundException, SQLException{
		String allGameResults=(con==null?getAllResultsTxt():getAllResultsSQliteDB());
		return new ArrayList<String>(Arrays.asList(allGameResults.split("\n")));
	}
	//Read results from gameResults.txt
	public String getAllResultsTxt() throws FileNotFoundException{
		Scanner	scan = new Scanner(new FileReader("gameResults.txt"));
		String gameResults="";
		String gameDetails="";
		int flag=0;
		//Read file gameResults.txt
		while(scan.hasNextLine()){
			String[] values=scan.nextLine().split(",");
			if(values.length>1){
				if(flag==0){
					gameDetails=gameDetails+values[0]+","+values[1]+", ";
					flag=1;
				}
				else gameResults=gameResults+gameDetails+values[0]+","+values[1]+","+values[2]+"\n";
			}
			else {
				gameDetails="";
				flag=0;
			}
		}
		System.out.println("gameResultsTxt===\n"+gameResults);
		scan.close();
		return gameResults;
	}
	//Read results from gameResults table in Ozlympic.db
	public String getAllResultsSQliteDB() throws SQLException{
		String gameResults="";
		ResultSet result=con.createStatement().executeQuery("SELECT \"Game ID\",\"Official ID\",\"Athlete ID\",Result,Score FROM gameResults");
		while(result.next()){
			gameResults=gameResults+result.getString("Game ID")+", "+result.getString("Official ID")+", "+result.getString("Athlete ID")+", "+result.getInt("Result")+", "+result.getInt("Score")+"\n";
		}
		System.out.println("gameResultsSQlite===\n"+gameResults);
		con.close();
		return gameResults;
	}
}