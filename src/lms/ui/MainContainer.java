package lms.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainContainer extends Application{

	public Stage stage;
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		Parent root = FXMLLoader.load(getClass().getResource("fxml/MainContainer.fxml"));
        stage.setTitle("Library Management System");
        stage.setScene(new Scene(root));
        stage.show();
	}

}
