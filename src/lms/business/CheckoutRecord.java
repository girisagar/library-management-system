package lms.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckoutRecord implements Serializable  {
	private LibraryMember member;
	private CheckoutRecordEntry[] checkoutRecordEntries ;
	
	public CheckoutRecord(LibraryMember member, CheckoutRecordEntry checkoutRecordEntry) {
		super();
		this.member = member;
		checkoutRecordEntries = new CheckoutRecordEntry[]{checkoutRecordEntry};
	}
	
	public void addCheckoutRecordEntry(CheckoutRecordEntry checkoutRecordEntry){		
		CheckoutRecordEntry[] newArr = new CheckoutRecordEntry[checkoutRecordEntries.length + 1];
		System.arraycopy(checkoutRecordEntries, 0, newArr, 0, checkoutRecordEntries.length);
		newArr[checkoutRecordEntries.length] = checkoutRecordEntry;
		checkoutRecordEntries = newArr;
	}
	
	public LibraryMember getLibraryMember(){
		return this.member;
	}
}