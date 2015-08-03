package lms.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lms.business.CheckoutRecord;
import lms.business.CheckoutRecordEntry;
import lms.business.ControllerInterface;
import lms.business.LibrarySystemException;
import lms.business.SystemController;

public class PrintCheckoutRecordController {

	@FXML
	private Button addCheckoutRecordButton;

	@FXML
	private Label labelIsbnValue;

	@FXML
	private TextField textMemberId;

	@FXML
	void handlePrintCheckoutRecordActionListener(MouseEvent event) {
		String memberId = textMemberId.getText().toString();
		ControllerInterface controller = new SystemController();
		
		try {
			controller.getCheckoutRecord(memberId);
			CheckoutRecordController ctr = new CheckoutRecordController(); 
			ctr.clearTableSpace();
			ctr.clearSubView();
			
			
		} catch (LibrarySystemException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.show();
		}
	}
	
	@FXML
	void handleHideCheckoutRecordActionListener(MouseEvent event) {
		CheckoutRecordController controller = new CheckoutRecordController();
		controller.clearSubView();
	}

}
