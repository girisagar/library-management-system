package lms.ui;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import lms.business.LoginException;
import lms.business.SystemController;
import lms.business.rulsets.*;

public class InterfaceController {
	private SystemController controller;
	@FXML private TextField userName;
	@FXML private PasswordField passwordField;
	@FXML private GridPane loginPane;
	
	@FXML protected void handleLoginSubmitButtonAction(ActionEvent event){
		controller = new SystemController();
//		System.out.println(userName.getText().toString());
		LoginForm loginForm = new LoginForm();
		RuleSet loginRules = RuleSetFactory.getRuleSet(loginForm);
		
		try {
//			loginRules.applyRules(loginForm); 
			
			try {
				controller.login(userName.getText().toString(), passwordField.getText().toString());
				if(controller.currentAuth !=null){
					System.out.println(controller.currentAuth);
				}
			} catch (LoginException e) {
				Alert alert = new Alert(AlertType.ERROR, e.getMessage() );
				alert.showAndWait();
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			Alert alert = new Alert(AlertType.WARNING, e.getMessage() );
			alert.showAndWait();
		}
    }
	
	public String getUserName(){
		return this.userName.getText().toString();
	}
	
	public String getPassword(){
		return this.passwordField.getText().toString();
	}
	
	
}