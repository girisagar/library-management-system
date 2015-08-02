package lms.business;

import java.io.Serializable;
import java.util.ArrayList;

public class CheckoutRecord implements Serializable  {
	private LibraryMember libraryMember;

	private ArrayList<CheckoutRecordEntry> checkoutRecordEntries;
	
	public CheckoutRecord(LibraryMember libraryMember, CheckoutRecordEntry checkoutRecordEntry) {
		super();
		checkoutRecordEntries = new ArrayList<CheckoutRecordEntry>();
		this.libraryMember = libraryMember;
		checkoutRecordEntries.add(checkoutRecordEntry);
	}
	
	public void addCheckoutRecordEntry(CheckoutRecordEntry checkoutRecordEntry){		
		checkoutRecordEntries.add(checkoutRecordEntry);
	}
	
	public LibraryMember getLibraryMember(){
		return this.libraryMember;
	}

	public ArrayList<CheckoutRecordEntry> getCheckoutRecordEntries() {
		return checkoutRecordEntries;
	}
	
}