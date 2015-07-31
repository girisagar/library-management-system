package lms.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lms.business.Book;
import lms.business.LibrarySystemException;
import lms.business.SystemController;

public class AddBookCopyController implements Initializable {

	@FXML
	private TextField textIsbn;

	@FXML
	private Label labelBookTitle;

	@FXML
	private Label labelIsbnValue;
	

	@FXML
	protected void handleAddBookCopyActionListener(ActionEvent event) {
		String numberOfCopies = textIsbn.getText().toString();
		BookTableController bc = new BookTableController();
		Book book = bc.getSelectedBook();
		Book updatedBook = null;
		
		SystemController controller = new SystemController();
		for (int i = 0; i < Integer.parseInt(numberOfCopies); i++) {
			try {
				updatedBook = controller.addBookCopy(book.getIsbn());	
				
			} catch (LibrarySystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		bc.changeCellValue(updatedBook);
		
//		try {
//			Book updatedBook = controller.addBookCopy(book.getIsbn());
//			bc.changeCellValue(updatedBook);
//		} catch (LibrarySystemException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BookTableController bc = new BookTableController();
		Book book = bc.getSelectedBook();
		this.labelBookTitle.setText(book.getTitle());
		this.labelIsbnValue.setText(book.getIsbn());
		
	}

}
