package lms.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lms.business.Author;
import lms.business.Book;
import lms.business.LibrarySystemException;
import lms.business.SystemController;
import lms.business.rulsets.AddBookRuleSet;
import lms.business.rulsets.RuleException;
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
		comboMaxChekoutLength.getSelectionModel().selectFirst();
	}	

    @FXML
    void handleAddBookClickListener(MouseEvent event) {
    	
    	AddBookRuleSet rule = new AddBookRuleSet();
    	try {
			
			
			List<Author> selectedAuthors = listAuthors.getSelectionModel().getSelectedItems();
			rule.applyRule(textIsbn, textTitle, selectedAuthors);

	    	Book book = new Book(textIsbn.getText(),
	    							textTitle.getText(), 
	    							comboMaxChekoutLength.getSelectionModel().getSelectedItem(), 
	    							selectedAuthors);
	    	DataAccessFacade aa = new DataAccessFacade();
	    	
	    	List<Author> newAuthors = new ArrayList<Author>();
	    	for(Author a: selectedAuthors){
	    		newAuthors.add(a);
	    	}
	    	
	    	int maxLength = comboMaxChekoutLength.getSelectionModel().getSelectedItem();    	
	    	SystemController controller = new SystemController();
	    	
	    	try {
				boolean value = controller.addBook(textIsbn.getText(), 
									textTitle.getText(),
									maxLength,
									newAuthors);
				if(value){
					BookTableController bc = new BookTableController();
		    		bc.addNewBook(
		    				new Book(textIsbn.getText(), 
		    						textTitle.getText(),
		    						comboMaxChekoutLength.getSelectionModel().getSelectedItem(),
		    						newAuthors)
		    		);
				}
				
			} catch (LibrarySystemException e) {
				Alert alert = new Alert(AlertType.ERROR, e.getMessage());
				alert.show();
			}
			
			
		} catch (RuleException e1) {
			Alert alert = new Alert(AlertType.ERROR, e1.getMessage());
			alert.show();
		}
    	
    }
    
    @FXML
    void handleHideBookClickListener(MouseEvent event) {
    	BookTableController controller = new BookTableController();
    	controller.clearSubView();
    }
    
  

}
