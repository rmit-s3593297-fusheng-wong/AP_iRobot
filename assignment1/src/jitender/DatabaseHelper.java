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
import java.util.Scanner;

import fusheng.Game;

public class DatabaseHelper {
	private Connection con;
	
	public DatabaseHelper() throws ClassNotFoundException, SQLException{
		File file = new File ("Ozlympic.db");
		if(file.exists()){
			DatabaseConnection dbCon=new DatabaseConnection();
			con=dbCon.SQLiteConnection();
		}		
	}
	public String readParticipants() throws FileNotFoundException, SQLException{
		return (con==null)? validate(readParticipantsTxt()):validate(readSQLiteDB());
	}
	public void writeGameResults() throws SQLException, IOException{
		if(con==null){
			Athlete athletes[] = new Athlete[12];
			athletes[0] = new Cyclist("AC01","Rohan Dennis", 26, "South Australia", 0);
			athletes[1] = new Cyclist("AC02","Simon Gerrens", 36, "Victoria", 0);
			athletes[2] = new Cyclist("AC03","Cadel Evans", 40, "Northern Territory", 0);
			athletes[3] = new Swimmer("AS01","Libby Trickett", 32, "Queensland", 0);
			athletes[4] = new Swimmer("AS02","Leisel Jones", 31, "Northern Territory", 0);
			athletes[5] = new Swimmer("AS03","Emily Seebohm", 24, "South Australia", 0);
			athletes[6] = new Sprinter("AP01","Alex Hartmann", 24, "South Australia", 0);
			athletes[7] = new Sprinter("AP02","Josh Clarke", 21, "New South Wales", 0);
			athletes[8] = new Sprinter("AP03","Tim Leathart", 27, "New South Wales", 0);
			athletes[9] = new SuperAthlete("AA01","Chuck Norris", 77, "Oklahoma", 0);
			athletes[10] = new SuperAthlete("AA02","Triple H", 47, "New Hampshire", 0);
			athletes[11] = new SuperAthlete("AA03","Mike Tyson", 50, "New York", 0);

			Athlete[] gameAthletes1 = {athletes[0],athletes[1],athletes[2],athletes[9]};
			writeGameResultTxt(new Game("C01", "Cycling",new Official("O01","Cycling Referee", 40, "Victoria"),gameAthletes1));
		}
		else {
			writeDatabase();
		}
	}
	public String readParticipantsTxt() throws FileNotFoundException{
		String participants="";
		Scanner	scan = new Scanner(new FileReader("participants.txt"));
		//Read file participants.txt
		while(scan.hasNextLine()){
			participants=participants+scan.nextLine()+"\n";
		}
		scan.close();
		System.out.println("participants---"+participants);
		return participants;
	}
	public void writeGameResultTxt(Game finishedGame) throws IOException{

		PrintWriter out = new PrintWriter(new FileWriter("C:/Users/TD/Desktop/gameResults.txt", true));
		String timeStamp=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(System.currentTimeMillis());
		out.println(finishedGame.getGameID()+", "+"finishedGame.getGameOfficial().Id"+", "+timeStamp.substring(0,timeStamp.length()-1));
		for(int i=0;i<finishedGame.getAthleteCount();i++){
			out.println(finishedGame.getGameAthleteID(i)+", "+"finishedGame.getResult()"+", "+"finishedGame.getAthletePoints()");
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
		System.out.println(participants);
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
					validParticipants=validParticipants+participant+"\n";
				}
			}
			System.out.println("validParticipants->"+validParticipants);
		}
		return validParticipants;
	}
	public void writeDatabase() throws SQLException{
		String updateStatement="INSERT INTO gameResults (\"Game ID\",\"Official ID\",\"Athlete ID\",Result,Score) VALUES ('G01','OFF1','AT1',10.5,2);";
		int result=con.createStatement().executeUpdate(updateStatement);
	}
}