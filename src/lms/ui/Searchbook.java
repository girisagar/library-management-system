package lms.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Searchbook extends javafx.application.Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
	Parent	root = FXMLLoader.load(getClass().getResource("fxml/SearchBookUi.fxml"));
		primaryStage.setTitle("Search");
		primaryStage.setScene(new Scene(root,500,500));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(Searchbook.class,args);
	}

}
