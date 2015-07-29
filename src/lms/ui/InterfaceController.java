package lms.ui;

import java.io.IOException;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import lms.business.LoginException;
import lms.business.Member;
import lms.business.SystemController;
import lms.business.rulsets.RuleSetFactory;
import lms.dataaccess.TestData;

public class InterfaceController {

	@FXML
	private AnchorPane mainContent;

	private SystemController controller;
	@FXML
	private TextField userName;
	@FXML
	private PasswordField passwordField;
	@FXML
	private GridPane loginPane;
//	@FXML private memberListTableView;
	@FXML
	protected void handleLoginSubmitButtonAction(ActionEvent event) {
		controller = new SystemController();
		// System.out.println(userName.getText().toString());
		LoginForm loginForm = new LoginForm();
		RuleSet loginRules = RuleSetFactory.getRuleSet(loginForm);

		try {
			// loginRules.applyRules(loginForm);

			try {
				controller.login(userName.getText().toString(), passwordField.getText().toString());
				if (controller.currentAuth != null) {
					System.out.println(controller.currentAuth);
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

	public String getUserName() {
		return this.userName.getText().toString();
	}

	public String getPassword() {
		return this.passwordField.getText().toString();
	}

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

		// System.out.println(addmember_fname.getText().toString());

		// System.out.println("Member Submit button pressed");
	}

	@FXML
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
			/*
			 * try { mainContent.getChildren().clear(); root =
			 * FXMLLoader.load(getClass().getResource("fxml/Book.fxml"));
			 * mainContent.getChildren().add(root);
			 * 
			 * } catch (IOException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */
			try {
				mainContent.getChildren().clear();
				BookUi memberUi = new BookUi();
				 root = FXMLLoader.load(getClass().getResource("fxml/BookList.fxml"));
				mainContent.getChildren().add(root);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;

		case "member":

			/*
			 * try { mainContent.getChildren().clear(); root =
			 * FXMLLoader.load(getClass().getResource("fxml/Member.fxml"));
			 * mainContent.getChildren().add(root);
			 * 
			 * } catch (IOException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */
			try {
				mainContent.getChildren().clear();
				MemberUi memberUi = new MemberUi();
				Parent root2 = FXMLLoader.load(getClass().getResource("fxml/MemberList.fxml"));
				mainContent.getChildren().add(root2);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			break;
		}

		// // actiontarget.setText("Sign in button pressed");
		// System.out.println("Author clicked");

	}

}
