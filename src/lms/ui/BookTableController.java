package lms.ui;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import lms.business.Book;
import lms.dataaccess.DataAccess;
import lms.dataaccess.DataAccessFacade;

public class BookTableController implements Initializable {
	private static TableView<Book> bookTable;
	private static AnchorPane addContent;
	
	@FXML
	private TableView<Book> bookListTableView;

	@FXML
	private AnchorPane addFormContent;

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
		bookTable = bookListTableView;
		
		bookTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("Title"));
		isbn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
		author.setCellValueFactory(new PropertyValueFactory<Book, String>("Authors"));
		copies.setCellValueFactory(new PropertyValueFactory<Book, String>("NumCopies"));
		maxCheckout.setCellValueFactory(new PropertyValueFactory<Book, Integer>("maxCheckoutLength"));

		bookListTableView.getItems().addAll(FXCollections.observableArrayList(parseBookList()));
	}

	private Collection<Book> parseBookList() {
		DataAccess daf = new DataAccessFacade();
		return daf.readBooksMap().values();
	}

	@FXML
	protected void handleAddBookClickListener(ActionEvent event) {
		Parent root;
		try {
			// InterfaceController controller = new InterfaceController();
			addFormContent.getChildren().clear();
			root = FXMLLoader.load(getClass().getResource("fxml/AddBook.fxml"));
			addFormContent.setStyle("-fx-background-color: white;");
			// System.out.println(super.getMainContent());
			addFormContent.getChildren().add(root);
			addContent = addFormContent;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void addNewBook(Book book){		
		this.bookTable.getItems().add(book);
		addContent.setStyle("");
		addContent.getChildren().clear();
	}
}
