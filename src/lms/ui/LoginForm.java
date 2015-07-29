package lms.ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginForm extends Application {
		private Parent root;
		@FXML private TextField userName;
		@FXML private PasswordField passwordField;
		
	    @Override
	    public void start(Stage stage) throws Exception {
	        root = FXMLLoader.load(getClass().getResource("fxml/Login.fxml"));
	        
	        stage.setTitle("Login");
	        stage.setScene(new Scene(root, 340, 250));
	        stage.show();
	    }
	    
	    public String getUserName(){
	    	System.out.println( this.userName.getText().toString());
	    	return this.userName.getText().toString();
	    }
	    
	    public String getPassword(){
	    	return this.passwordField.getText().toString();
	    }
}