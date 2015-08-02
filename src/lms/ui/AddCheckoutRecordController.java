package lms.ui;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import lms.business.CheckoutRecord;
import lms.business.ControllerInterface;
import lms.business.LibrarySystemException;
import lms.business.SystemController;

public class AddCheckoutRecordController {
    @FXML
    private URL location;

    @FXML
    private TextField textIsbn;

    @FXML
    private Button addCheckoutRecordButton;

    @FXML
    private Label labelBookTitle;

    @FXML
    private Label labelIsbnValue;

    @FXML
    private TextField textMemberId;

    @FXML
    void handleAddCheckoutRecordActionListener(ActionEvent event) {
    	String isbn = textIsbn.getText().toString();
    	String memberId = textMemberId.getText().toString();
    	
    	ControllerInterface controller = new SystemController();
    	try {
			CheckoutRecord record = controller.checkoutBook(memberId, isbn);
			addEntryToTables(record);
			
		} catch (LibrarySystemException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.show();
		}
    }
    
    static void addEntryToTables(CheckoutRecord record){
    	CheckoutRecordController cController = new CheckoutRecordController();
    	cController.addDataToTable(record.getCheckoutRecordEntries());

    }
}
