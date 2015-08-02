package lms.business;

import java.io.Serializable;

public class CheckoutRecord implements Serializable  {
	private LibraryMember libraryMember;
	private CheckoutRecordEntry[] checkoutRecordEntries ;
	
	public CheckoutRecord(LibraryMember libraryMember, CheckoutRecordEntry checkoutRecordEntry) {
		super();
		this.libraryMember = libraryMember;
		checkoutRecordEntries = new CheckoutRecordEntry[]{checkoutRecordEntry};
	}
	
	public void addCheckoutRecordEntry(CheckoutRecordEntry checkoutRecordEntry){		
		CheckoutRecordEntry[] newArr = new CheckoutRecordEntry[checkoutRecordEntries.length + 1];
		System.arraycopy(checkoutRecordEntries, 0, newArr, 0, checkoutRecordEntries.length);
		newArr[checkoutRecordEntries.length] = checkoutRecordEntry;
		checkoutRecordEntries = newArr;
	}
	

	public LibraryMember getLibraryMember() {
		return libraryMember;
	}

	public CheckoutRecordEntry[] getCheckoutRecordEntries() {
		return checkoutRecordEntries;
	}
}