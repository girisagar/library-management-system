package lms.ui;

import java.awt.TextField;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InterfaceController {
	
	
	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
		// actiontarget.setText("Sign in button pressed");
		System.out.println("Submit button pressed");
	}

	@FXML
	protected void handleAddMemberButtonAction(ActionEvent event) {
		// actiontarget.setText("Sign in button pressed");
		 
		
		
		
		System.out.println("Member Submit button pressed");
	}
	@FXML
	protected void handleAddMemberSubmitButtonAction(ActionEvent event) {
		// actiontarget.setText("Sign in button pressed");
		
		 
	//	 System.out.println(addmember_fname.getText().toString());
		
		
		
		//System.out.println("Member Submit button pressed");
	}
}
