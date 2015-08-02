package lms.main;

import javafx.application.Application;
import lms.business.CheckoutRecord;
import lms.business.CheckoutRecordEntry;
import lms.business.SystemController;
import lms.ui.MainContainer;

public class Main {
//	private static TextField textMemberId; 

	public static void main(String[] args) {
		
//		Application.launch(LoginForm.class);
//		Application.launch(LoginForm.class);
		Application.launch(MainContainer.class);
		
//		textMemberId.setText("001");
//		handlePrintConsoleButtonAction();
		
		
	}
	
	public static void handlePrintConsoleButtonAction() {
//		String memberId = textMemberId.getText();
		SystemController controller = new SystemController();
		CheckoutRecord record = controller.getCheckoutRecord("001");
		
		String leftAlignFormat = "| %-33s | %-15s | %-10s |%n";

		System.out
				.format("+-----------------------------------+-----------------+------------+%n");
		System.out
				.printf("| Book Title                        | Checkout Date   | Due Date   |%n");
		System.out
				.format("+-----------------------------------+-----------------+------------+%n");
		for(CheckoutRecordEntry entry: record.getCheckoutRecordEntries()) {
			System.out.format(leftAlignFormat, entry.getBookCopy().getBook().getTitle(), entry.getCheckoutDate().toString(), entry.getDueDate().toString());
		}
		System.out
				.format("+-----------------------------------+-----------------+------------+%n");
		
		
		

	}
}