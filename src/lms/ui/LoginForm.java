package lms.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginForm extends Application {

	    @Override
	    public void start(Stage stage) throws Exception {
	        Parent root = FXMLLoader.load(getClass().getResource("fxml/Login.fxml"));
	        
	        stage.setTitle("Login");
	        stage.setScene(new Scene(root, 340, 250));
	        stage.show();
	    }
}