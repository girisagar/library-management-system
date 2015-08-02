package lms.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lms.dataaccess.DataAccess;
import lms.dataaccess.DataAccessFacade;

public interface ControllerInterface {

	public void login(String id, String password) throws LoginException;
//	public void addNewMember(String memberId, String firstName, String lastName,
//			String telNumber, Address addr) throws LibrarySystemException;
//	public LibraryMember search(String memberId);
//	public void updateMemberInfo(String memberId, String firstName, String lastName,
//			String telNumber, Address addr) throws LibrarySystemException;
	public CheckoutRecord checkoutBook(String memberId, String isbn) throws LibrarySystemException;
	//public boolean addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors)
	//	throws LibrarySystemException;
	public Book addBookCopy(String isbn) throws LibrarySystemException;
//	public void printCheckoutRecord(String memberId) throws LibrarySystemException;
//	public CopyStatus computeStatus(BookCopy copy);
	public Book searchBook(String isbn) throws LibrarySystemException;
	public LibraryMember searchMember(String memberId) throws LibrarySystemException;
	public BookCopy searchAvailablBookCopy(String isbn) throws LibrarySystemException;
	public CheckoutRecord getCheckoutRecord(String memberId) throws LibrarySystemException;
	public HashMap<String, HashMap<LibraryMember, CheckoutRecordEntry>> searchOverdue(String isbn) throws LibrarySystemException;
}
