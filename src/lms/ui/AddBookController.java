package lms.ui;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import lms.business.Author;
import lms.business.Book;
import lms.dataaccess.DataAccessFacade;
import lms.dataaccess.TestData;

public class AddBookController implements Initializable{


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
    	Book book = new Book(textIsbn.getText(),
    							textTitle.getText(), 
    							comboMaxChekoutLength.getSelectionModel().getSelectedItem(), 
    							selectedAuthors);
    	DataAccessFacade aa = new DataAccessFacade();
    	aa.saveNewBook(book);
    }

}
