package lms.ui;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lms.business.Book;
import lms.dataaccess.DataAccessFacade;

public class BookTableController implements Initializable{
	@FXML
	private TableView<Book> bookListTableView;

	@FXML
	private TableColumn<Book, String> bookTitle;
	@FXML
	private TableColumn<Book, String> isbn;
	@FXML
	private TableColumn<Book, String> author;
	@FXML
	private TableColumn<Book, String> copies;
	@FXML
	private TableColumn<Book, Integer> maxCheckout;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bookTitle.setCellValueFactory(new PropertyValueFactory<Book, String>(
				"Title"));
		isbn.setCellValueFactory(new PropertyValueFactory<Book, String>(
				"isbn"));
		author.setCellValueFactory(new PropertyValueFactory<Book, String>(
				"Authors"));
		copies.setCellValueFactory(new PropertyValueFactory<Book, String>(
				"NumCopies"));
		maxCheckout.setCellValueFactory(new PropertyValueFactory<Book, Integer>(
				"maxCheckoutLength"));
		
		bookListTableView.getItems().addAll(
				FXCollections.observableArrayList(parseBookList()));
	}

	private Collection<Book> parseBookList() {
//		TestData td = new TestData();
		DataAccessFacade daf = new DataAccessFacade();	

		System.out.println(daf.readBooksMap().values());
		return daf.readBooksMap().values();
	}

	@FXML
	protected void handleAddBookClickListener(ActionEvent event) {
		
	}
}
