package lms.ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lms.business.LoginException;
import lms.business.SystemController;
import lms.business.rulsets.RuleSetFactory;

public class LoginController extends Application {
		private Parent root;
		private SystemController controller;

		@FXML private TextField userName;
		@FXML private PasswordField passwordField;

//	    @FXML
//	    private Button signInButton;
		
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
	    
	    @FXML
		protected void handleLoginSubmitButtonAction(MouseEvent event) {
			controller = new SystemController();
			// System.out.println(userName.getText().toString());
			LoginController loginForm = new LoginController();
			RuleSet loginRules = RuleSetFactory.getRuleSet(loginForm);

			try {
				// loginRules.applyRules(loginForm);

				try {
					controller.login(userName.getText().toString(), passwordField
							.getText().toString());
					if (controller.currentAuth != null) {
						
						Stage stage = (Stage) userName.getScene().getWindow();
						
//						//Show the MainWindow
						Parent mainParent = FXMLLoader.load(getClass().getResource("fxml/MainContainer.fxml"));
				        Stage mainStage = new Stage();
				        mainStage.setTitle("Login");
//				        mainStage.setFullScreen(true);
				        mainStage.setScene(new Scene(mainParent));
				        mainStage.show();
				        
				        stage.close();
					}
				} catch (LoginException e) {
					Alert alert = new Alert(AlertType.ERROR, e.getMessage());
					alert.showAndWait();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				Alert alert = new Alert(AlertType.WARNING, e.getMessage());
				alert.showAndWait();
			}
		}
}