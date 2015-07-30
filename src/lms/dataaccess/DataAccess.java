package lms.dataaccess;

import java.util.HashMap;

import lms.business.Book;
import lms.business.LibraryMember;
import lms.business.LibrarySystemException;





public interface DataAccess {
	//public LibraryMember searchMember(String memberId);
	public Book searchBook(String isbn);
	///////save methods
	public void saveNewMember(LibraryMember member);
	//public void updateMember(LibraryMember member);
	
	//save new book
	public void saveNewBook(Book book);
	
	//////read methods 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	//check book exists or not
	public boolean isBookExists(Book book);
	
	public boolean isMemberExist(LibraryMember member) ;
}
