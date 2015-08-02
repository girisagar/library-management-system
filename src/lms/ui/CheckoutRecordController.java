package lms.ui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

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
import lms.business.Book;
import lms.business.CheckoutRecordEntry;

public class CheckoutRecordController implements Initializable {
	private static AnchorPane addContent;
	public static TableView<CheckoutRecordEntry> entryListTable;
	
    @FXML
    private URL location;

    @FXML
    private AnchorPane addFormContent;

    @FXML
    private TableView<CheckoutRecordEntry> bookListTableView;
    

    @FXML
    private TableColumn<CheckoutRecordEntry, String> copies;

    @FXML
    private TableColumn<CheckoutRecordEntry, String> checkOutDate;

    @FXML
    private TableColumn<CheckoutRecordEntry, String> isbn;

    @FXML
    private TableColumn<CheckoutRecordEntry, String> dueDate;

    @FXML
    private TableColumn<CheckoutRecordEntry, String> bookTitle;

    @FXML
    void handleAddCheckoutClickListener(ActionEvent event) {
    	Parent root;
		try {
			addFormContent.getChildren().clear();
			root = FXMLLoader.load(getClass().getResource("fxml/AddCheckoutRecord.fxml"));
			addFormContent.setStyle("-fx-background-color: white;");
			addFormContent.getChildren().add(root);
			addContent = addFormContent;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void handlePrintCheckoutClickListener(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		entryListTable = bookListTableView;
		isbn.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, String>("bookIsbn"));
		bookTitle.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, String>("bookTitle"));
		copies.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, String>("bookCopy"));
		checkOutDate.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, String>("checkoutDate"));
		dueDate.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, String>("dueDate"));
	}
	
	public void addDataToTable(ArrayList<CheckoutRecordEntry> arrayList){
		entryListTable.getItems().clear();
		entryListTable.getItems().addAll(FXCollections.observableArrayList(arrayList));
	}
}