package lms.ui;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class InterfaceController {
	
	@FXML private AnchorPane mainContent;

	
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

	protected void handleLabelClickListener(MouseEvent event) {
		Parent root;
		String clickedLabel = ((Label) event.getSource()).getId();
		
		switch (clickedLabel) {
		case "author":
			try {
				mainContent.getChildren().clear();
				root = FXMLLoader.load(getClass().getResource("fxml/Login.fxml"));
				mainContent.getChildren().add(root);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "book":
			/*try {
				mainContent.getChildren().clear();
				root = FXMLLoader.load(getClass().getResource("fxml/Book.fxml"));
				mainContent.getChildren().add(root);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			break;
			
		case "member":

			/*try {
				mainContent.getChildren().clear();
				root = FXMLLoader.load(getClass().getResource("fxml/Member.fxml"));
				mainContent.getChildren().add(root);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			break;
			
		

		default:
			break;
		}
		
		
		
//		// actiontarget.setText("Sign in button pressed");
//		System.out.println("Author clicked");
		
	}


}
