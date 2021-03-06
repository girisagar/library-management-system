package lms.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lms.business.LibraryMember;
import lms.business.LibrarySystemException;
import lms.business.SystemController;
import lms.business.rulsets.AddMemberRuleSet;
import lms.business.rulsets.RuleException;

public class AddMemberController {

	@FXML
	private TextField textId;
	
	@FXML
	private Text actiontarget;

	@FXML
	private TextField textStreet;

	@FXML
	private TextField textNumber;

	@FXML
	private TextField textFirstName;

	@FXML
	private TextField textEmail;

	@FXML
	private TextField textZip;

	@FXML
	private TextField textState;

	@FXML
	private TextField textLastName;

	@FXML
	private TextField textCity;

	@FXML
	void handleHideMemberButtonAction(MouseEvent event) {
		MemberTableController controller = new MemberTableController();
		controller.clearChildren();
	}
	
	
	@FXML
	void handleAddMemberButtonAction(MouseEvent event) {
		AddMemberRuleSet rule = new AddMemberRuleSet();
		
		try {
			rule.applyRule(textId, textStreet, textNumber, textFirstName, textEmail, textZip, textState, textCity);
			
			SystemController controller = new SystemController();
			 try {
				boolean added = controller.addNewMember(textId.getText(), textFirstName.getText(),
						 textLastName.getText(), textEmail.getText(),
						 textNumber.getText(), textStreet.getText(), textCity.getText(), textState.getText(), textZip.getText());
				if(added) {
					MemberTableController memberTable = new MemberTableController();
					memberTable.addNewMember(new LibraryMember(textId.getText(), textFirstName.getText(),
						 textLastName.getText(), textEmail.getText(),
						 textNumber.getText(), textStreet.getText(), textCity.getText(), textState.getText(), textZip.getText()));
				}
				
				
			} catch (LibrarySystemException e) {
				Alert alert = new Alert(AlertType.ERROR, e.getMessage());
				alert.show();
			}
		} catch (RuleException e1) {
			Alert alert = new Alert(AlertType.ERROR, e1.getMessage());
			alert.show();
		}
		 
		 
		 

	}

}
