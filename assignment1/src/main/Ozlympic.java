package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import javafx.application.Application;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import jitender.Athlete;
import jitender.DatabaseHelper;
import jitender.Game;
import jitender.GameAnimation;
import jitender.NoRefereeException;
import jitender.Official;
import jitender.TooFewAthleteException; 

public class Ozlympic extends Application {
	private ArrayList<Athlete> athleteList;
	private ArrayList<Official> officialList;
	private Timer timer;
	
	public Ozlympic() throws ClassNotFoundException, FileNotFoundException, SQLException{
		athleteList=new ArrayList<Athlete>();
		officialList=new ArrayList<Official>();
		new HashMap<Athlete,ProgressBar>();
		new HashMap<Athlete,ProgressIndicator>();
		initiateParticipants();
		
	}
	
	public Game startGame(){
		ArrayList<Athlete> selectedAthletes = new ArrayList<Athlete>();
		Official selectedOfficial=officialList.get(0);
		selectedAthletes.addAll(athleteList);
		Game finishedGame=new Game("C02", "Sprinting",selectedOfficial,selectedAthletes);
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
		return finishedGame;
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

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		Ozlympic oz;
		Game finishedGame = null;
		try {
			oz = new Ozlympic();
			finishedGame=oz.startGame();
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
		GameAnimation gameAnimate=new GameAnimation(primaryStage, finishedGame);
		gameAnimate.runAnimation();
		
	}
	//Listener
    public void progress(HashMap<Athlete,ProgressBar> athleteProgBarMap,HashMap<Athlete,ProgressIndicator> athleteProgIndMap){
    	
    	ActionListener listener = new ActionListener() {
            int counter = 1;
            double progress;
            public void actionPerformed(ActionEvent ae) {
                int countCompleted=0;
            	for(Athlete athlete:athleteProgBarMap.keySet() ){
            		progress=Double.valueOf(counter)/athlete.getTime();
                    DecimalFormat df = new DecimalFormat("#.##");
                    df.setRoundingMode(RoundingMode.CEILING);
                    progress=Double.valueOf(df.format(progress));
                    athleteProgBarMap.get(athlete).setProgress(progress);
                    if (progress>=1) {
                    	countCompleted++;
                    }
            	}
            	for(Athlete athlete:athleteProgIndMap.keySet() ){
            		progress=Double.valueOf(counter)/athlete.getTime();
                    DecimalFormat df = new DecimalFormat("#.##");
                    df.setRoundingMode(RoundingMode.CEILING);
                    progress=Double.valueOf(df.format(progress));
                    athleteProgIndMap.get(athlete).setProgress(progress);
            	}
            	counter++;
            	if (countCompleted==athleteProgBarMap.size()) {
                	timer.stop();
                	JOptionPane.showMessageDialog(null, "Game Complete !");
                	System.exit(0);
                	//Take Back Control from here
                }
            }
        };
        timer = new Timer(1000, listener);
        timer.start();
    }
}