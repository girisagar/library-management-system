package lms.ui;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import lms.business.Book;
import lms.business.LibrarySystemException;
import lms.business.SystemController;
import lms.dataaccess.DataAccess;
import lms.dataaccess.DataAccessFacade;

import com.sun.prism.impl.Disposer.Record;

public class BookTableController implements Initializable {
	private static TableView<Book> bookTable;
	private static AnchorPane addContent;
	private static Book selectedBook;
	private static int selectedIndex;

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

	@FXML
	private TextField textSearch;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bookTable = bookListTableView;

		bookTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("Title"));
		isbn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
		author.setCellValueFactory(new PropertyValueFactory<Book, String>("Authors"));
		copies.setCellValueFactory(new PropertyValueFactory<Book, String>("NumCopies"));
		maxCheckout.setCellValueFactory(new PropertyValueFactory<Book, Integer>("maxCheckoutLength"));
		addContent = addFormContent;
		
		Callback<TableColumn, TableCell> cellFactory = p -> new EditingCell();
		// Insert Button
		TableColumn col_action = new TableColumn<>("Action");
		col_action.setSortable(false);

		col_action
				.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Record, Boolean>, ObservableValue<Boolean>>() {

					@Override
					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});

		col_action.setCellFactory(new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

			@Override
			public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
				return new ButtonCell();
			}

		});
		bookListTableView.getColumns().add(col_action);

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

	public void addNewBook(Book book) {
		this.bookTable.getItems().add(book);
		clearSubView();
	}
	
	public void clearSubView() {
		addContent.setStyle("");
		addContent.getChildren().clear();
	}

	@FXML
	void handleBookSearchAction(ActionEvent event) {
		String isbn = textSearch.getText().toString();
		// DataAccess da = new DataAccessFacade();
		SystemController controller = new SystemController();
		try {
			Book book = controller.searchBook(isbn);
			this.bookTable.getItems().clear();
			this.bookTable.getItems().add(book);
		} catch (LibrarySystemException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.show();
		}
	}

	// Define the button cell
	private class ButtonCell extends TableCell<Record, Boolean> {
		final Button cellButton = new Button("Add Book Copy");

		ButtonCell() {

			cellButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					selectedIndex = getTableRow().getIndex();
					
					//get the Book from the selected index
					selectedBook = (Book) bookListTableView.getItems().get(selectedIndex);
					
					Parent root;
					try {
						// InterfaceController controller = new InterfaceController();
						addFormContent.getChildren().clear();
						root = FXMLLoader.load(getClass().getResource("fxml/AddBookCopy.fxml"));
						addFormContent.setStyle("-fx-background-color: white;");
						// System.out.println(super.getMainContent());
						addFormContent.getChildren().add(root);
//						addContent = addFormContent;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println(selectedBook);
					
				}
			});
		}

		// Display button if the row is not empty
		@Override
		protected void updateItem(Boolean t, boolean empty) {
			super.updateItem(t, empty);
			if (!empty) {
				setGraphic(cellButton);
			} else {
				setGraphic(null);
			}
		}
	}

	

	class EditingCell extends TableCell<XYChart.Data, Number> {

		private TextField textField;

		public EditingCell() {
		}

		@Override
		public void startEdit() {

			super.startEdit();

			if (textField == null) {
				createTextField();
			}

			setGraphic(textField);
			setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			textField.selectAll();
		}

		@Override
		public void cancelEdit() {
			super.cancelEdit();

			setText(String.valueOf(getItem()));
			setContentDisplay(ContentDisplay.TEXT_ONLY);
		}

		@Override
		public void updateItem(Number item, boolean empty) {
			super.updateItem(item, empty);

			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				if (isEditing()) {
					if (textField != null) {
						textField.setText(getString());
					}
					setGraphic(textField);
					setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				} else {
					setText(getString());
					setContentDisplay(ContentDisplay.TEXT_ONLY);
				}
			}
		}

		private void createTextField() {
			textField = new TextField(getString());
			textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
			textField.setOnKeyPressed(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent t) {
					if (t.getCode() == KeyCode.ENTER) {
						commitEdit(Integer.parseInt(textField.getText()));
					} else if (t.getCode() == KeyCode.ESCAPE) {
						cancelEdit();
					}
				}
			});
		}

		private String getString() {
			return getItem() == null ? "" : getItem().toString();
		}
	}
	
	public Book getSelectedBook() {
		return selectedBook;
	}

	public static int getSelectedIndex() {
		return selectedIndex;
	}
	
	public void changeCellValue(Book book) {
		bookTable.getItems().clear();
		bookTable.getItems().addAll(FXCollections.observableArrayList(parseBookList()));
	}
}
