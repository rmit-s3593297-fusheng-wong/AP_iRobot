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
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jitender.Athlete;
import jitender.DatabaseHelper;
import jitender.Game;
import jitender.NoRefereeException;
import jitender.Official;
import jitender.TooFewAthleteException; 

public class Ozlympic extends Application {
	private ArrayList<Athlete> athleteList;
	private ArrayList<Official> officialList;
	private HashMap<Athlete,ProgressBar> athleteProgBarMap;
	private HashMap<Athlete,ProgressIndicator> athleteProgIndMap;
	private Timer timer;
	
	public Ozlympic() throws ClassNotFoundException, FileNotFoundException, SQLException{
		athleteList=new ArrayList<Athlete>();
		officialList=new ArrayList<Official>();
		athleteProgBarMap=new HashMap<Athlete,ProgressBar>();
		athleteProgIndMap=new HashMap<Athlete,ProgressIndicator>();
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
		
		Group root = new Group();
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ozlympic");
        
        ArrayList<HBox> HBoxes= new ArrayList<HBox>();
        HBox gameHeader=new HBox();
        gameHeader.getChildren().add(new Label(finishedGame.getGameType()));
        gameHeader.setAlignment(Pos.CENTER);
        HBoxes.add(gameHeader);
        for (Athlete athlete : finishedGame.getGameAthletes()) {
            Label label =new Label();
            label.setText(athlete.getName()+":");
 
            ProgressBar pBar =new ProgressBar();
            pBar.setProgress(0f);
 
            ProgressIndicator pInd=new ProgressIndicator();
            pInd.setProgress(0f);
            HBox hBox=new HBox();
            hBox.setSpacing(5);
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().addAll(label,pBar,pInd);
            HBoxes.add(hBox);
            
            athleteProgBarMap.put(athlete,pBar);
            athleteProgIndMap.put(athlete,pInd);
            
        }
        progress(athleteProgBarMap,athleteProgIndMap);
        final VBox vb = new VBox();
        vb.setSpacing(5);
        vb.getChildren().addAll(HBoxes);
        scene.setRoot(vb);
        primaryStage.setMinHeight(450);
        primaryStage.setMinWidth(300);
        primaryStage.show();
		
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
