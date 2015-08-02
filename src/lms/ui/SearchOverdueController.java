package lms.ui;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import lms.business.CheckoutRecordEntry;
import lms.business.ControllerInterface;
import lms.business.LibraryMember;
import lms.business.LibrarySystemException;
import lms.business.SystemController;

public class SearchOverdueController {

    @FXML
    private Button addCheckoutRecordButton;

    @FXML
    private Label labelBookTitle;

    @FXML
    private TextField textIsbn;

    @FXML
    private Label labelIsbnValue;

    @FXML
    void handleSearchOverDueClickAction(ActionEvent event) {
//    	Alert()
    	String isbn = textIsbn.getText().toString();
    	ControllerInterface controller = new SystemController();
    	try {
    		CheckoutRecordController checkoutController = new CheckoutRecordController();
    		checkoutController.setOverdueTableView(isbn);
    		
		} catch (LibrarySystemException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.show();
		}
    }
}
