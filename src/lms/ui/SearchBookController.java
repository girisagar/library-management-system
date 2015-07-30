package lms.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import lms.business.Book;
import lms.dataaccess.DataAccessFacade;

public class SearchBookController implements Initializable {

	@FXML
	private ListView<String> searchBookListView;
	@FXML
	private javafx.scene.control.TextField searchBookTextField;

	DataAccessFacade fd ;
	final ObservableList<String> listItems = FXCollections.observableArrayList();
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	@FXML
	protected void textChangeListener() {
		
		System.out.println(searchBookTextField.getText().toUpperCase());
		fd = new DataAccessFacade();
		
		boolean bb = fd.isBookExists(searchBookTextField.getText().toString());
		
		try {
			
	//		System.out.println(bb);
		} catch (Exception e) {
			System.out.println("error");
		}
		
	
		/*listItems.add(bb.getIsbn());
		searchBookListView.setItems(listItems);*/
	}

}
