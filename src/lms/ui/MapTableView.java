package lms.ui;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import lms.business.CheckoutRecordEntry;
import lms.business.ControllerInterface;
import lms.business.LibraryMember;
import lms.business.LibrarySystemException;
import lms.business.SystemController;

public class MapTableView {

   public TableView<Entry<String,  HashMap<LibraryMember, CheckoutRecordEntry>>> getTableView(String isbn) throws LibrarySystemException{
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
	        
	        TableColumn<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String> columnCheckout = new TableColumn<>("Member Name");
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
	        
	        TableColumn<Map.Entry<String, HashMap<LibraryMember, CheckoutRecordEntry>>, String> columnDueDate = new TableColumn<>("Member Name");
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
	        final TableView<Entry<String,  HashMap<LibraryMember, CheckoutRecordEntry>>> table = new TableView<>(items);
	
	        table.getColumns().setAll(columnIsbn, columnBookTitle, columnCopyNumber, columnMember, columnCheckout, columnDueDate);
	        return table;
    }
}