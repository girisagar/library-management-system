package lms.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import lms.business.Author;
import lms.dataaccess.TestData;

public class AddBookController implements Initializable {

    @FXML
    private TextField textMaxCheckoutLength;

    @FXML
    private TextField textTitle;

    @FXML
    private Label labelMaxCheckoutLength;

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

		TestData td = new TestData();
//		System.out.println(td.allAuthors);
		listAuthors.setItems(FXCollections.observableArrayList(td.allAuthors));
		listAuthors.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
	}

}
