package lms.ui;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lms.business.Member;
import lms.dataaccess.TestData;
import lms.dataaccess.User;

public class MemberTableController implements Initializable {
	@FXML private TableView<Member> memberListTableView;
	
    @FXML private TableColumn<Member, String> firstName;
    @FXML private TableColumn<Member, String> lastName;
    @FXML private TableColumn<Member, String> email;
    @FXML private TableColumn<Member, String> state;
    @FXML private TableColumn<Member, String> city;
    @FXML private TableColumn<Member, String> phone;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstName.setCellValueFactory(new PropertyValueFactory<Member, String>("First Name"));
        lastName.setCellValueFactory(new PropertyValueFactory<Member, String>("Last Name"));
        email.setCellValueFactory(new PropertyValueFactory<Member, String>("Email"));
        state.setCellValueFactory(new PropertyValueFactory<Member, String>("State"));
        city.setCellValueFactory(new PropertyValueFactory<Member, String>("City"));
        phone.setCellValueFactory(new PropertyValueFactory<Member, String>("Phone"));

        memberListTableView.getItems().addAll(FXCollections.observableArrayList(parseUserList()));
    }
    private List<Member> parseUserList(){
       TestData td = new TestData();
       return td.getAllMembers();       
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
}
