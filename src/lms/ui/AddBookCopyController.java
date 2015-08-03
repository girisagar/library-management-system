package lms.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lms.business.Book;
import lms.business.LibrarySystemException;
import lms.business.SystemController;
import lms.business.rulsets.AddBookCopyRuleSet;
import lms.business.rulsets.RuleException;

public class AddBookCopyController implements Initializable {

	@FXML
	private TextField textNumberOfCopy;

	@FXML
	private Label labelBookTitle;

	@FXML
	private Label labelIsbnValue;
	

	@FXML
	protected void handleAddBookCopyActionListener(MouseEvent event) {
		
		AddBookCopyRuleSet rule = new AddBookCopyRuleSet();
		
		try {
			rule.applyRule(textNumberOfCopy);
			
			String numberOfCopies = textNumberOfCopy.getText().toString();
			BookTableController bc = new BookTableController();
			Book book = bc.getSelectedBook();
			Book updatedBook = null;
			
			SystemController controller = new SystemController();
			for (int i = 0; i < Integer.parseInt(numberOfCopies); i++) {
				try {
					updatedBook = controller.addBookCopy(book.getIsbn());	
					BookTableController bookTableController = new BookTableController();
					bookTableController.clearSubView();
					
				} catch (LibrarySystemException e) {
					e.printStackTrace();
				}
			}
			bc.changeCellValue(updatedBook);
			
		} catch (RuleException e1) {
			Alert alert = new Alert(AlertType.ERROR, e1.getMessage());
			alert.show();
		}
				
	}
	
	@FXML
	protected void handleHideBookCopyActionListener(MouseEvent event) {
		BookTableController controller = new BookTableController();
		controller.clearSubView();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BookTableController bc = new BookTableController();
		Book book = bc.getSelectedBook();
		this.labelBookTitle.setText(book.getTitle());
		this.labelIsbnValue.setText(book.getIsbn());
		
	}

}
