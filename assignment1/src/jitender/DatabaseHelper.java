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
		String participants=(con==null)? validate(readParticipantsTxt()):validate(readSQLiteDB());
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
	public void writeGameResults(Game finishedGame) throws SQLException, IOException{
		if(con==null)
			writeGameResultTxt(finishedGame);
		else 
			writeDatabase(finishedGame);
	}
	public String readParticipantsTxt() throws FileNotFoundException{
		String participants="";
		Scanner	scan = new Scanner(new FileReader("participants.txt"));
		//Read file participants.txt
		while(scan.hasNextLine()){
			participants=participants+scan.nextLine()+"\n";
		}
		scan.close();
		System.out.println("TextFile participants");
		return participants;
	}
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
	public ArrayList<String> getAllGameResults() throws FileNotFoundException, SQLException{
		String allGameResults=(con==null?getAllResultsTxt():getAllResultsSQliteDB());
		//Creating Map of gameId and Participants - Assuming all athletes to be Sprinters
		/*HashMap<String,ArrayList<Participant>> gameId_ParticipantList_Map=new HashMap<String,ArrayList<Participant>>();
		for(String athleteRecord: allGameResults.split("\n")){
			String gameId=athleteRecord.split(",")[0].trim();
			if(gameId_ParticipantList_Map.containsKey(gameId)){
				gameId_ParticipantList_Map.get(gameId).add(new Sprinter(athleteRecord.split(",")[2].trim(), "Irrelevant", 0, "Irrelevant", 0));
			}
			else {
				ArrayList<Participant> participantList=new ArrayList<Participant>();
				participantList.add(new Official(athleteRecord.split(",")[1], "Irrelevant", 0, "Irrelevant"));
				Sprinter sprint=new Sprinter(athleteRecord.split(",")[2].trim(), "Irrelevant", 0, "Irrelevant", 0);
				sprint.setTime(Integer.valueOf(athleteRecord.split(",")[3].trim()));
				participantList.add(sprint);
				gameId_ParticipantList_Map.put(gameId,participantList);
			}
		}
		//Creating Game Objects from gameId_ParticipantList_Map
		ArrayList<Game> gameList=new ArrayList<Game>();
		ArrayList<Athlete> athList=new ArrayList<Athlete>();
		for(String gameId:gameId_ParticipantList_Map.keySet()){
			Official gameOfficial = null;
			ArrayList<Participant> participantList=gameId_ParticipantList_Map.get(gameId);
			for(Participant participant: participantList){
				if(participant instanceof Official){
					gameOfficial=(Official) participant;
				}
				else athList.add((Athlete) participant);
			}
			participantList.remove(gameOfficial);
			gameList.add(new Game(gameId, "Sprinting", gameOfficial, athList));
		}
		for(Game game:gameList){
			System.out.println("gameId----"+game.getGameID());
		}*/
		return new ArrayList<String>(Arrays.asList(allGameResults.split("\n")));
	}
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
