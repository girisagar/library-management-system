package lms.dataaccess;

import java.util.HashMap;

import lms.business.Book;
import lms.business.BookCopy;
import lms.business.CheckoutRecord;
import lms.business.LibraryMember;
import lms.business.LibrarySystemException;





public interface DataAccess {
	public LibraryMember searchMember(String memberId);
	public Book searchBook(String isbn);
	///////save methods
	public void saveNewMember(LibraryMember member);
	//public void updateMember(LibraryMember member);
	
	//save new book
	public void saveNewBook(Book book);
	
	//save checkout record
	public void saveCheckoutRecord(CheckoutRecord record);
	
	//////read methods 
	public HashMap<String, Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public HashMap<String, CheckoutRecord> readCheckoutRecordMap();
	//check book exists or not
	public boolean isBookExists(Book book);
	public boolean isBookExists(String isbn);
	public boolean isCheckoutRecordExists(String memberId) ;

	public boolean isMemberExist(LibraryMember member) ;
	public boolean isMemberExist(String memberId) ;
	//
//	public BookCopy searchAvailablBookCopy(String bookId);
	public CheckoutRecord searchCheckoutRecord(String memberId);

}
