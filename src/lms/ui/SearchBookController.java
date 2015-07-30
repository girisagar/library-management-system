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
	private javafx.scene.control.TextField searchBookText;

	DataAccessFacade fd ;
	final ObservableList<String> listItems = FXCollections.observableArrayList();
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	protected void OnKeypressedForSearchBook() {
		fd = new DataAccessFacade();
		try {
			Book bb = fd.searchBook(searchBookText.getText().toString());
			System.out.println(bb.getIsbn());
		} catch (Exception e) {
			System.out.println("error");
		}
		
	//	listItems.add(bb);
		listItems.add("saugat");
		searchBookListView.setItems(listItems);
	}

}
