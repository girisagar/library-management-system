package lms.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lms.business.ControllerInterface;
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
    void handleSearchOverDueClickAction(MouseEvent event) {
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
    
    @FXML
    void handleHideOverDueClickAction(MouseEvent event) {
    	CheckoutRecordController controller = new CheckoutRecordController();
    	controller.clearSubView();
    }
}
