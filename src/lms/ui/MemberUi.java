package lms.ui;

import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MemberUi {
	final HBox hb = new HBox();
	/*
	 * @Override public void start(Stage primaryStage) throws Exception { try {
	 * 
	 * Parent root = FXMLLoader.load(getClass().getResource(
	 * "fxml/Memberfx.fxml"));
	 * 
	 * primaryStage.setTitle("welcome"); primaryStage.setScene(new Scene(root,
	 * 590, 400)); primaryStage.show(); } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * public static void main(String[] args) { // launch(args);
	 * Application.launch(MemberUi.class, args); }
	 */

	private TableView<Member> table = new TableView<Member>();
	private final List<Member> data = FXCollections
			.observableArrayList(new Member("Jacob", "Smith",
					"jacob.smith@example.com", "iowa", "453535", "fairfield"),
					new Member("Isabella", "Johnson",
							"isabella.johnson@example.com", "iowa", "453535",
							"fairfield"), new Member("Ethan", "Williams",
							"ethan.williams@example.com", "iowa", "453535",
							"fairfield"), new Member("Emma", "Jones",
							"emma.jones@example.com", "iowa", "453535",
							"fairfield"), new Member("Michael", "Brown",
							"michael.brown@example.com", "iowa", "453535",
							"fairfield"));

//	public static void main(String[] args) {
//		launch(args);
//	}

	public TableView<Member> getMemberTableView() {
//		Scene scene = new Scene(new Group());
//		stage.setTitle("List of members");
//		stage.setWidth(800);
//		stage.setHeight(525);

		final Label label = new Label("List of members");
		label.setFont(new Font("Arial", 20));

		table.setEditable(true);

		TableColumn firstNameCol = new TableColumn("First Name");
		firstNameCol.setMinWidth(100);
		firstNameCol
				.setCellValueFactory(new PropertyValueFactory<Member, String>(
						"firstName"));

		TableColumn lastNameCol = new TableColumn("Last Name");
		lastNameCol.setMinWidth(100);
		lastNameCol
				.setCellValueFactory(new PropertyValueFactory<Member, String>(
						"lastName"));

		TableColumn emailCol = new TableColumn("Email");
		emailCol.setMinWidth(200);
		emailCol.setCellValueFactory(new PropertyValueFactory<Member, String>(
				"email"));

		TableColumn state = new TableColumn("State");
		state.setMinWidth(100);
		state.setCellValueFactory(new PropertyValueFactory<Member, String>(
				"state"));

		TableColumn phone = new TableColumn("Phone");
		phone.setMinWidth(100);
		phone.setCellValueFactory(new PropertyValueFactory<Member, String>(
				"phone"));
		TableColumn city = new TableColumn("City");
		city.setMinWidth(100);
		city.setCellValueFactory(new PropertyValueFactory<Member, String>(
				"city"));

		table.setItems((ObservableList<MemberUi.Member>)data);
		table.getColumns().addAll(firstNameCol, lastNameCol, emailCol, state,
				city, phone);

		return table;
		
//		final TextField addFirstName = new TextField();
//		addFirstName.setPromptText("First Name");
//		addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
//		final TextField addLastName = new TextField();
//		addLastName.setMaxWidth(lastNameCol.getPrefWidth());
//		addLastName.setPromptText("Last Name");
//		final TextField addEmail = new TextField();
//		addEmail.setMaxWidth(emailCol.getPrefWidth());
//		addEmail.setPromptText("Email");
//		final TextField stateField = new TextField();
//		stateField.setMaxWidth(state.getPrefWidth());
//		stateField.setPromptText("State");
//		final TextField cityField = new TextField();
//		cityField.setMaxWidth(city.getPrefWidth());
//		cityField.setPromptText("city");
//		final TextField phoneField = new TextField();
//		phoneField.setMaxWidth(phone.getPrefWidth());
//		phoneField.setPromptText("phone");
//
//		final Button addButton = new Button("Add");
//		addButton.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent e) {
//				data.add(new Member(addFirstName.getText(), addLastName
//						.getText(), addEmail.getText(), stateField.getText(),
//						cityField.getText(), phoneField.getText()));
//				addFirstName.clear();
//				addLastName.clear();
//				addEmail.clear();
//				stateField.clear();
//				cityField.clear();
//				phoneField.clear();
//			}
//		});
//
//		hb.getChildren().addAll(addFirstName, addLastName, addEmail,
//				stateField, cityField, phoneField, addButton);
//		hb.setSpacing(3);
//		final VBox vbox = new VBox();
//        vbox.setSpacing(5);
//        vbox.setPadding(new Insets(10, 0, 0, 10));
//        vbox.getChildren().addAll(label, table, hb);

        
//		((Group) scene.getRoot()).getChildren().addAll(vbox);
//
//		stage.setScene(scene);
//		stage.show();
	}

	public static class Member {

		private final SimpleStringProperty firstName;
		private final SimpleStringProperty lastName;
		private final SimpleStringProperty state;
		private final SimpleStringProperty city;
		private final SimpleStringProperty phone;
		private final SimpleStringProperty email;

		private Member(String fName, String lName, String email, String state,
				String phone, String city) {
			this.firstName = new SimpleStringProperty(fName);
			this.lastName = new SimpleStringProperty(lName);
			this.email = new SimpleStringProperty(email);
			this.city = new SimpleStringProperty(city);
			this.phone = new SimpleStringProperty(phone);
			this.state = new SimpleStringProperty(state);
		}

		public String getState() {
			return state.get();
		}

		public String getCity() {
			return city.get();
		}

		public String getPhone() {
			return phone.get();
		}

		public String getFirstName() {
			return firstName.get();
		}

		public void setFirstName(String fName) {
			firstName.set(fName);
		}

		public String getLastName() {
			return lastName.get();
		}

		public void setLastName(String fName) {
			lastName.set(fName);
		}

		public String getEmail() {
			return email.get();
		}

		public void setEmail(String fName) {
			email.set(fName);
		}
	}

}
