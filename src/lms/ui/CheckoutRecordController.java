package lms.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;
import lms.business.CheckoutRecordEntry;
import lms.business.ControllerInterface;
import lms.business.LibraryMember;
import lms.business.LibrarySystemException;
import lms.business.SystemController;

public class CheckoutRecordController implements Initializable {
	private static AnchorPane addContent;
	private static AnchorPane tableContent;
	private static TextFlow textFlow;
	
	public static TableView<CheckoutRecordEntry> entryListTable;
	public static TableView<Entry<String,  HashMap<LibraryMember, CheckoutRecordEntry>>> overdueListTable;
	
    @FXML
    private URL location;
    
    
    @FXML
    private AnchorPane addTableContent;

    @FXML
    private TextFlow textPrintFlow;
    
    @FXML
    private AnchorPane addFormContent;

    @FXML
    private TableView<CheckoutRecordEntry> bookListTableView;
    
    @FXML
    private TableView<Entry<String,  HashMap<LibraryMember, CheckoutRecordEntry>>> overdueTableView;
    
    @FXML
    private TableColumn<CheckoutRecordEntry, String> copies;

    @FXML
    private TableColumn<CheckoutRecordEntry, String> checkOutDate;

    @FXML
    private TableColumn<CheckoutRecordEntry, String> isbn;

    @FXML
    private TableColumn<CheckoutRecordEntry, String> dueDate;

    @FXML
    private TableColumn<CheckoutRecordEntry, String> bookTitle;

    @FXML
    void handleAddCheckoutClickListener(MouseEvent event) {
    	Parent root;
		try {
			setChekoutVisible();
			addFormContent.getChildren().clear();
			root = FXMLLoader.load(getClass().getResource("fxml/AddCheckoutRecord.fxml"));
			addFormContent.setStyle("-fx-border-color: black;");
			addFormContent.getChildren().add(root);
			addContent = addFormContent;
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void handlePrintCheckoutClickListener(MouseEvent event) {
    	Parent root;
		try {
			setPrintCheckoutVisible();
			addFormContent.getChildren().clear();
			root = FXMLLoader.load(getClass().getResource("fxml/PrintCheckoutRecord.fxml"));
			addFormContent.setStyle("-fx-border-color: black;");
			addFormContent.getChildren().add(root);
			addContent = addFormContent;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void handleSearchOverdueClickListener(MouseEvent event) {
    	Parent root;
		try {
			setOverdueVisible();
			addFormContent.getChildren().clear();
			root = FXMLLoader.load(getClass().getResource("fxml/SearchOverdue.fxml"));
			addFormContent.setStyle("-fx-border-color: black;");
			addFormContent.getChildren().add(root);
			addContent = addFormContent;
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		entryListTable = bookListTableView;
		overdueListTable = overdueTableView;
		tableContent = addTableContent;
		textFlow = textPrintFlow;
		addContent = addFormContent;
		
		setChekoutVisible();
		isbn.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, String>("bookIsbn"));
		bookTitle.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, String>("bookTitle"));
		copies.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, String>("bookCopy"));
		checkOutDate.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, String>("checkoutDate"));
		dueDate.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, String>("dueDate"));
	}
	
	public void clearSubView() {
		addContent.setStyle("");
		addContent.getChildren().clear();
	}
	
	public void addDataToTable(ArrayList<CheckoutRecordEntry> arrayList){
		entryListTable.getItems().clear();
		entryListTable.getItems().addAll(FXCollections.observableArrayList(arrayList));
	}
	
	public void setOverdueTableView(String isbn) throws LibrarySystemException{
		setOverdueVisible();
		
    	ControllerInterface controller = new SystemController();
    	
			Map<String, HashMap<LibraryMember, CheckoutRecordEntry>> map =
					controller.searchOverdue(isbn);	
//	        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");


	        // use fully detailed type for Map.Entry<String, String> 
	        TableColumn<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String> columnIsbn = new TableColumn<>("ISBN");
	        
	        columnIsbn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String>, ObservableValue<String>>() {
	
	            @Override
	            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String> p) {
	                // this callback returns property for just one cell, you can't use a loop here
	                // for first column we use key
	            	for(Map.Entry<LibraryMember, CheckoutRecordEntry> record: p.getValue().getValue().entrySet()){
	            		return new SimpleStringProperty(record.getValue().getBookIsbn());
	            	}
	            	return new SimpleStringProperty("None");
//	                return new SimpleStringProperty(p.getValue().getKey());
	            }
	        });
	
	        TableColumn<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String> columnBookTitle = new TableColumn<>("Title");
	        columnBookTitle.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String>, ObservableValue<String>>() {
	
	            @Override
	            public SimpleStringProperty call(TableColumn.CellDataFeatures<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String> p) {
	                // for second column we use value
	            	for(Map.Entry<LibraryMember, CheckoutRecordEntry> record: p.getValue().getValue().entrySet()){
	            		return new SimpleStringProperty(record.getValue().getBookTitle());
	            	}
	            	return new SimpleStringProperty("None");
	            }
	        });
	        

	    	
	        TableColumn<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String> columnCopyNumber = new TableColumn<>("Copy Number");
	        columnCopyNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String>, ObservableValue<String>>() {
	
	            @Override
	            public SimpleStringProperty call(TableColumn.CellDataFeatures<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String> p) {
	                // for second column we use value
	            	for(Map.Entry<LibraryMember, CheckoutRecordEntry> record: p.getValue().getValue().entrySet()){
	            		return new SimpleStringProperty(record.getValue().getBookCopy().getCopyNum()+"");
	            	}
	            	return new SimpleStringProperty("None");
	            }
	        });
	        
	        TableColumn<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String> columnMember = new TableColumn<>("Member Name");
	        columnMember.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String>, ObservableValue<String>>() {
	
	            @Override
	            public SimpleStringProperty call(TableColumn.CellDataFeatures<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String> p) {
	                // for second column we use value
	            	for(Map.Entry<LibraryMember, CheckoutRecordEntry> record: p.getValue().getValue().entrySet()){
	            		return new SimpleStringProperty(record.getKey().getFirstName()+" "+ record.getKey().getLastName());
	            	}
	            	return new SimpleStringProperty("None");
	            }
	        });
	        
	        TableColumn<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String> isAvailable = new TableColumn<>("Member Name");
	        isAvailable.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String>, ObservableValue<String>>() {
	
	            @Override
	            public SimpleStringProperty call(TableColumn.CellDataFeatures<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String> p) {
	                // for second column we use value
	            	for(Map.Entry<LibraryMember, CheckoutRecordEntry> record: p.getValue().getValue().entrySet()){
	            		return new SimpleStringProperty(record.getValue().getBookCopy().isAvailable()+"");
	            	}
	            	return new SimpleStringProperty("None");
	            }
	        });
	        
	        TableColumn<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String> columnCheckout = new TableColumn<>("Chekout Date");
	        columnCheckout.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String>, ObservableValue<String>>() {
	
	            @Override
	            public SimpleStringProperty call(TableColumn.CellDataFeatures<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String> p) {
	                // for second column we use value
	            	for(Map.Entry<LibraryMember, CheckoutRecordEntry> record: p.getValue().getValue().entrySet()){
	            		return new SimpleStringProperty(record.getValue().getCheckoutDate().toString());
	            	}
	            	return new SimpleStringProperty("None");
	            }
	        });
	        
	        TableColumn<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String> columnDueDate = new TableColumn<>("Due Date");
	        columnDueDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String>, ObservableValue<String>>() {
	
	            @Override
	            public SimpleStringProperty call(TableColumn.CellDataFeatures<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String> p) {
	                // for second column we use value
	            	for(Map.Entry<LibraryMember, CheckoutRecordEntry> record: p.getValue().getValue().entrySet()){
	            		return new SimpleStringProperty(record.getValue().getDueDate().toString());
	            	}
	            	return new SimpleStringProperty("None");
	            }
	        });
	        
	        ObservableList<Entry<String,  HashMap<LibraryMember, CheckoutRecordEntry>>> items = FXCollections.observableArrayList(map.entrySet());
//	        final TableView<Entry<String,  HashMap<LibraryMember, CheckoutRecordEntry>>> table = new TableView<>(items);
	        overdueListTable.setItems(items);
	        overdueListTable.getColumns().setAll(columnIsbn, columnBookTitle, columnCopyNumber, columnMember, columnCheckout, columnDueDate);
//	        return table;
	}
	public void setOverdueVisible(){
		tableContent.getChildren().clear();
		overdueListTable.setMaxHeight(450);
		tableContent.getChildren().add(overdueListTable);
	}
	
	public void setChekoutVisible(){
		tableContent.getChildren().clear();
		entryListTable.setMaxHeight(450);
		tableContent.getChildren().add(entryListTable);
	}
	
	
	public void setPrintCheckoutVisible(){
		tableContent.getChildren().clear();
	}
}