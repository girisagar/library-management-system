package lms.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lms.business.Author;
import lms.business.Book;
import lms.business.SystemController;
import lms.dataaccess.TestData;

public class AddBookController implements Initializable{

	@FXML
	private TableView<Book> bookListTableView;
	
    @FXML
    private TextField textTitle;

    @FXML
    private ComboBox<Integer>comboMaxChekoutLength;

    @FXML
    private Label lableTitle;

    @FXML
    private Label labelIsbn;

    @FXML
    private TextField textIsbn;

    @FXML
    private Label labelAuthors;    
    
    @FXML
    private ListView<Author> listAuthors;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//TODO import data from facade
		TestData td = new TestData();
		listAuthors.setItems(FXCollections.observableArrayList(td.allAuthors));
		
		listAuthors.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		Integer[] maxLengths = {new Integer(7), new Integer(21)};
		comboMaxChekoutLength.setItems(FXCollections.observableArrayList(Arrays.asList(maxLengths)));
	}	

    @FXML
    void handleAddBookClickListener(ActionEvent event) {
    	List<Author> selectedAuthors = listAuthors.getSelectionModel().getSelectedItems();
    	List<Author> newAuthors = new ArrayList<Author>();
    	for(Author a: selectedAuthors){
    		newAuthors.add(a);
    	}
    	
    	SystemController controller = new SystemController();
    	boolean value = controller.addBook(textIsbn.getText(), 
    						textTitle.getText(),
    						comboMaxChekoutLength.getSelectionModel().getSelectedItem(),
    						newAuthors);
//    	da.saveNewBook(book);
    	try {
			Parent root = FXMLLoader.load(getClass()
					.getResource("fxml/BookList.fxml"));
			System.out.println(bookListTableView.getItems());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }

}
