package lms.ui;

import java.awt.Color;
import java.util.List;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import lms.business.LibraryMember;
import lms.dataaccess.TestData;

public class MemberUi {
	final HBox hb = new HBox();
	final FlowPane tablePane = new FlowPane(Orientation.HORIZONTAL);
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

	private TableView<LibraryMember> table = new TableView<LibraryMember>();
	TestData testData = new TestData();
	
	private final ObservableList<LibraryMember> data = FXCollections.observableArrayList(testData.getAllMembers());

	public TableView<LibraryMember> getMemberTableView() {

//		table.setEditable(true);

		TableColumn firstNameCol = new TableColumn("First Name");
		firstNameCol.setMinWidth(100);
		firstNameCol
				.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>(
						"firstName"));

		TableColumn lastNameCol = new TableColumn("Last Name");
		lastNameCol.setMinWidth(100);
		lastNameCol
				.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>(
						"lastName"));

		TableColumn emailCol = new TableColumn("Email");
		emailCol.setMinWidth(200);
		emailCol.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>(
				"email"));

		TableColumn state = new TableColumn("State");
		state.setMinWidth(100);
		state.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>(
				"state"));

		TableColumn phone = new TableColumn("Phone");
		phone.setMinWidth(100);
		phone.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>(
				"phone"));
		TableColumn city = new TableColumn("City");
		city.setMinWidth(100);
		city.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>(
				"city"));

		table.setItems(data);
		
		table.getColumns().addAll(firstNameCol, lastNameCol, emailCol, state,
				city, phone);

//		return table;
		
		final TextField addFirstName = new TextField();
		addFirstName.setPromptText("First Name");
		addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
		final TextField addLastName = new TextField();
		addLastName.setMaxWidth(lastNameCol.getPrefWidth());
		addLastName.setPromptText("Last Name");
		final TextField addEmail = new TextField();
		addEmail.setMaxWidth(emailCol.getPrefWidth());
		addEmail.setPromptText("Email");
		final TextField stateField = new TextField();
		stateField.setMaxWidth(state.getPrefWidth());
		stateField.setPromptText("State");
		final TextField cityField = new TextField();
		cityField.setMaxWidth(city.getPrefWidth());
		cityField.setPromptText("city");
		final TextField phoneField = new TextField();
		phoneField.setMaxWidth(phone.getPrefWidth());
		phoneField.setPromptText("phone");

//		final Button addButton = new Button("Add");
//		addButton.setOnAction(evt -> {
//				data.add(new LibraryMember(addFirstName.getText(), addLastName
//						.getText(), addEmail.getText(), stateField.getText(),
//						cityField.getText(), phoneField.getText()));
//				
//				addFirstName.clear();
//				addLastName.clear();
//				addEmail.clear();
//				stateField.clear();
//				cityField.clear();
//				phoneField.clear();
//		});
//
//		hb.getChildren().addAll(addFirstName, addLastName, addEmail,
//				stateField, cityField, phoneField, addButton);
//		hb.setSpacing(5);
		
//		hb.setBorder(BorderFactory.createLineBorder(Color.RED));
		hb.setPadding(new Insets(5,5,5,5));
		hb.setStyle("-fx-border-style: solid; "+"-fx-border-width: 1;"+"-fx-border-color:black;");
		tablePane.setVgap(10);
		tablePane.setPadding(new Insets(10));
		tablePane.getChildren().addAll(hb, table);
//		return tablePane;
		return table;
		
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



}

//public static class Member {
//
//	private final SimpleStringProperty firstName;
//	private final SimpleStringProperty lastName;
//	private final SimpleStringProperty state;
//	private final SimpleStringProperty city;
//	private final SimpleStringProperty phone;
//	private final SimpleStringProperty email;
//
//	private Member(String fName, String lName, String email, String state,
//			String phone, String city) {
//		this.firstName = new SimpleStringProperty(fName);
//		this.lastName = new SimpleStringProperty(lName);
//		this.email = new SimpleStringProperty(email);
//		this.city = new SimpleStringProperty(city);
//		this.phone = new SimpleStringProperty(phone);
//		this.state = new SimpleStringProperty(state);
//	}
//
//	public String getState() {
//		return state.get();
//	}
//
//	public String getCity() {
//		return city.get();
//	}
//
//	public String getPhone() {
//		return phone.get();
//	}
//
//	public String getFirstName() {
//		return firstName.get();
//	}
//
//	public void setFirstName(String fName) {
//		firstName.set(fName);
//	}
//
//	public String getLastName() {
//		return lastName.get();
//	}
//
//	public void setLastName(String fName) {
//		lastName.set(fName);
//	}
//
//	public String getEmail() {
//		return email.get();
//	}
//
//	public void setEmail(String fName) {
//		email.set(fName);
//	}
//}
