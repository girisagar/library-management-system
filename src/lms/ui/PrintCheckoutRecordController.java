package lms.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	void handlePrintCheckoutRecordActionListener(ActionEvent event) {
		String memberId = textMemberId.getText().toString();
		ControllerInterface controller = new SystemController();
		
		try {
			CheckoutRecord record = controller.getCheckoutRecord(memberId);
			String leftAlignFormat = "| %-33s | %-15s | %-10s |%n";

			System.out.format("+-----------------------------------+-----------------+------------+%n");
			System.out.printf("| Book Title                        | Checkout Date   | Due Date   |%n");
			System.out.format("+-----------------------------------+-----------------+------------+%n");
			for (CheckoutRecordEntry entry : record.getCheckoutRecordEntries()) {
				System.out.format(leftAlignFormat, entry.getBookCopy().getBook().getTitle(), entry.getCheckoutDate()
						.toString(), entry.getDueDate().toString());
			}
			System.out.format("+-----------------------------------+-----------------+------------+%n");
			CheckoutRecordController ctr = new CheckoutRecordController(); 
			ctr.clearTableSpace();
			
		} catch (LibrarySystemException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.show();
		}
	}

}
