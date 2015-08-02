package lms.dataaccess;

import java.util.HashMap;

import lms.business.Book;
import lms.business.CheckoutRecord;
import lms.business.LibraryMember;





public interface DataAccess {
	//public LibraryMember searchMember(String memberId);
	public Book searchBook(String isbn);
	///////save methods
	public void saveNewMember(LibraryMember member);
	public void saveNewCheckoutRecord(CheckoutRecord record);
	//public void updateMember(LibraryMember member);
	public CheckoutRecord searchCheckoutRecord(String memberId);
	public boolean isMemberExist(String memberId);
	
	//save new book
	public void saveNewBook(Book book);
	
	//////read methods 
	public HashMap<String, Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public HashMap<String, CheckoutRecord> readCheckoutRecordMap();
	//check book exists or not
	public boolean isBookExists(Book book);
	public boolean isBookExists(String isbn);
	
	public boolean isMemberExist(LibraryMember member) ;
}
