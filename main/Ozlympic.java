package main;


import java.io.IOException;

import fusheng.Driver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;

public class Ozlympic /*extends Application*/ {
	/*private Stage primaryStage;
	private AnchorPane anchorPane;
	MenuBar menuBar = new MenuBar();
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setResizable(false);
		
		mainView();
	}*/
	
	public static void main(String[] args) {
		//launch(args);
		Driver console = new Driver();
		console.begin(args);
	}
	
	/*public void mainView() {
		try {
			FXMLLoader loader = new FXMLLoader(Ozlympic.class.getResource("/fusheng/MainMenu.fxml"));
			anchorPane = (AnchorPane) loader.load();
			
			MainMenuController controller = loader.getController();
			//controller.setMain(this);
			
			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}	
	}*/
}
