package lms.ui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lms.business.LibraryMember;
import lms.dataaccess.TestData;

public class MemberTableController implements Initializable {
	@FXML
	private TableView<LibraryMember> memberListTableView;
	@FXML
	private TableColumn<LibraryMember, String> memberId;
	@FXML
	private TableColumn<LibraryMember, String> firstName;
	@FXML
	private TableColumn<LibraryMember, String> lastName;
	@FXML
	private TableColumn<LibraryMember, String> email;
	@FXML
	private TableColumn<LibraryMember, String> phone;
	@FXML
	private TableColumn<LibraryMember, String> street;
	@FXML
	private TableColumn<LibraryMember, String> city;
	@FXML
	private TableColumn<LibraryMember, String> state;
	@FXML
	private TableColumn<LibraryMember, String> zip;

	@FXML
	private AnchorPane addFormContent;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		memberId.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("memberId"));
		firstName.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("firstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("lastName"));
		email.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("Email"));
		phone.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("Phone"));
		street.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("Street"));
		city.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("City"));
		state.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("State"));
		zip.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("Zip"));

		memberListTableView.setItems((FXCollections.observableArrayList(parseMemberList())));
	}

	private List<LibraryMember> parseMemberList() {
		TestData td = new TestData();
		return td.getAllMembers();
	}

	@FXML
	protected void handleAddMemberClickListener(ActionEvent event) {
		Parent member;
		try {
			// InterfaceController controller = new InterfaceController();
			addFormContent.getChildren().clear();
			member = FXMLLoader.load(getClass().getResource("fxml/AddMember.fxml"));
			addFormContent.setStyle("-fx-background-color: white;");
			// System.out.println(super.getMainContent());
			addFormContent.getChildren().add(member);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// @FXML
	// protected void handleAddMemberButtonAction(ActionEvent event) {
	// // actiontarget.setText("Sign in button pressed");
	//
	// System.out.println("Member Submit button pressed");
	// }
	//
	// @FXML
	// protected void handleAddMemberSubmitButtonAction(ActionEvent event) {
	// // actiontarget.setText("Sign in button pressed");
	//
	// // System.out.println(addmember_fname.getText().toString());
	//
	// // System.out.println("Member Submit button pressed");
	// }
}
