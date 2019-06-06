package GUI;
import serverConnection.*;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;



public class Main extends Application {
	
private static String[] argsSave;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			AnchorPane pane =loader.load(getClass().getResource("/GUI/guiServer.fxml"));
			Scene scene = new Scene( pane );
			scene.getStylesheets().add(getClass().getResource("/GUI/application.css").toExternalForm());
			
			GUIcontroller controller=loader.getController();
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		argsSave=args;
		launch(args);
	}



}
