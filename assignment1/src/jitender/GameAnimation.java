/*****Author - Jitender Singh Padda***********
 *****Student Id - 3628144************************/
package jitender;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.event.EventHandler;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameAnimation {
	private HashMap<Athlete,ProgressBar> athleteProgBarMap;
	private HashMap<Athlete,ProgressIndicator> athleteProgIndMap;
	private Timer timer;
	Stage stage;
	Scene mainMenuScene;
	Game finishedGame;

	public GameAnimation(Stage stage,Scene mainMenuScene,Game finishedGame) {
		athleteProgBarMap=new HashMap<Athlete,ProgressBar>();
		athleteProgIndMap=new HashMap<Athlete,ProgressIndicator>();
		this.stage=stage;
		this.mainMenuScene=mainMenuScene;
		this.finishedGame=finishedGame;
	}
	public void runAnimation(){
		Group root = new Group();
        Scene scene = new Scene(root, 300, 250);
        stage.setScene(scene);
        stage.setTitle("Ozlympic");
        
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
        Button back = new Button("Back to Main");
        backHandlerClass handler = new backHandlerClass();
        back.setOnAction(handler);
        
        progress(athleteProgBarMap,athleteProgIndMap);
        final VBox vb = new VBox();
        vb.setSpacing(5);
        vb.getChildren().addAll(HBoxes);
        vb.getChildren().add(back);
        scene.setRoot(vb);
        stage.setMinHeight(600);
        stage.setMinWidth(600);
        stage.show();

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
            		System.out.println(athlete.getTime());
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
                	String strOutput = "1st: " + finishedGame.getGameAthletes().get(0).getName() + "|" + finishedGame.getGameAthletes().get(0).getTime();
                	strOutput += "\n 2nd: " + finishedGame.getGameAthletes().get(1).getName() + "|" + finishedGame.getGameAthletes().get(1).getTime();
                	strOutput += "\n 3rd: " + finishedGame.getGameAthletes().get(2).getName() + "|" + finishedGame.getGameAthletes().get(2).getTime();
                	JOptionPane.showMessageDialog(null, strOutput);
                	//System.exit(0);
                	//Take Back Control from here
                	
                }
            }
        };
        timer = new Timer(1000, listener);
        timer.start();
        
    }
    
    private void goHome(){
    	stage.setTitle("Main Menu");
    	stage.setScene(mainMenuScene);
    	stage.show();
    }
    
  //display the athlete list after selecting official
  	class backHandlerClass implements EventHandler<javafx.event.ActionEvent>{

		@Override
		public void handle(javafx.event.ActionEvent event) {
			stage.setTitle("Main Menu");
  	    	stage.setScene(mainMenuScene);
  	    	stage.show();
		}
  	}

}
