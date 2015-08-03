package lms.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lms.dataaccess.Auth;
import lms.dataaccess.DataAccess;
import lms.dataaccess.DataAccessFacade;
import lms.dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;

	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if (!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if (!passwordFound.equals(password)) {
			throw new LoginException("Passord does not match password on record");
		}
		currentAuth = map.get(id).getAuthorization();
	}

	/**
	 * This method checks if memberId already exists -- if so, it cannot be
	 * added as a new member, and an exception is thrown. If new, creates a new
	 * LibraryMember based on input data and uses DataAccess to store it.
	 * 
	 */

	public boolean addNewMember(String memberId, String firstName, String lastName, String email, String phone, 
			String street, String city, String state, String zip) throws LibrarySystemException {
		
		LibraryMember member = new LibraryMember(memberId, firstName, lastName, email, phone, street, city, state, zip);
		
		DataAccess da = new DataAccessFacade();
		
		if(!da.isMemberExist(member)) {
			da.saveNewMember(member);
			return true;
		}
		throw new LibrarySystemException("Member already existed");
	}
		
	/**
	 * Reads data store for a library member with specified id. Ids begin at
	 * 1001... Returns a LibraryMember if found, null otherwise
	 * 
	 */
	// public LibraryMember search(String memberId) {

	/**
	 * Same as creating a new member (because of how data is stored)
	 */
	// public void updateMemberInfo(String memberId, String firstName, String
	// lastName,

	/**
	 * Looks up Book by isbn from data store. If not found, an exception is
	 * thrown. If no copies are available for checkout, an exception is thrown.
	 * If found and a copy is available, member's checkout record is updated and
	 * copy of this publication is set to "not available"
	 * @throws LibrarySystemException 
	 */
	// public void checkoutBook(String memberId, String isbn) throws
	// LibrarySystemException {

	@Override
	public Book searchBook(String isbn) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		if(!da.isBookExists(isbn)) {
			throw new LibrarySystemException("Book for this Isbn not available");
		}
		return da.searchBook(isbn);
	}

	@Override
	public LibraryMember searchMember(String memberId) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		if(!da.isMemberExist(memberId)) {
			throw new LibrarySystemException("Book for this Isbn not available");
		}
		return da.searchMember(memberId);
	}
	
	@Override
	public BookCopy searchAvailablBookCopy(String isbn) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> booksMap =  da.readBooksMap();
		
		if(booksMap.containsKey(isbn)){
			Book book = booksMap.get(isbn);
			
			for(BookCopy copy: book.getCopies()){
				if(copy.isAvailable()==true){
					copy.changeAvailability();
					book.updateCopies(copy);
					da.saveNewBook(book);
					System.out.println(copy.isAvailable());
					return copy;
				}
			}
			throw new LibrarySystemException("No Copy for this Book is available right now");
		}else{
			throw new LibrarySystemException("Book for this Isbn not available");
		}
	}
	
	public CheckoutRecord addCheckoutRecordEntry(LibraryMember member, CheckoutRecordEntry entry){
		DataAccess da = new DataAccessFacade();
		String memberId = member.getMemberId().toString();
		CheckoutRecord record = null;
		if(da.isCheckoutRecordExists(memberId)){
			System.out.println("yes");
			record = da.searchCheckoutRecord(memberId);
			record.addCheckoutRecordEntry(entry);
		}else{
			record = new CheckoutRecord(member, entry);
		}
		da.saveCheckoutRecord(record);
		return record;
	}
	
	
	/**
<<<<<<< HEAD
	 * Looks up book by isbn to see if it exists, throw exceptioni. Else add the
	 * book to storage
	 * 
	 * @throws LibrarySystemException
=======
	 * Looks up book by isbn to see if it exists, throw exceptionion.
	 * Else add the book to storage
>>>>>>> 4346d7982acb02f992d32fd58358b4a635f28bde
	 */

	public boolean addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors)
			throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		Book book = new Book(isbn, title, maxCheckoutLength, authors);
		if (da.isBookExists(book)) {
			throw new LibrarySystemException("Book already exists !");
		}
		da.saveNewBook(book);
		return true;
	}

	public Book addBookCopy(String isbn) throws LibrarySystemException {
		Book book = searchBook(isbn);
		if (book == null)
			throw new LibrarySystemException("No book with isbn " + isbn + " is in the library collection!");
		book.addCopy();
		DataAccess da = new DataAccessFacade();
		da.saveNewBook(book);
		return book;
	}

	public static void main(String[] args) throws LibrarySystemException {

	}
	

	@Override
	public CheckoutRecord checkoutBook(String memberId, String isbn) throws LibrarySystemException {
		LibraryMember member = searchMember(memberId);
		Book book = searchBook(isbn);
		BookCopy bookCopy = searchAvailablBookCopy(isbn);
		
		CheckoutRecordEntry entry = new CheckoutRecordEntry(bookCopy,
									LocalDate.now(),
									LocalDate.now().plusDays(book.getMaxCheckoutLength()));
		CheckoutRecord updatedRecord = addCheckoutRecordEntry(member, entry);
		return updatedRecord;		
	}
	
	public CheckoutRecord getCheckoutRecord(String memberId) {
		DataAccess da = new DataAccessFacade();
		HashMap<String, CheckoutRecord> checkoutRecords = da.readCheckoutRecordMap();
		if(da.isMemberExist(memberId)) {
			return checkoutRecords.get(memberId);
		}
		return null;		
	}
	
	@Override
	public HashMap<String, HashMap<LibraryMember, CheckoutRecordEntry>> searchOverdue(String isbn) throws LibrarySystemException {
		Book book = searchBook(isbn);
		DataAccess da = new DataAccessFacade();
		HashMap<String, CheckoutRecord> checkoutRecordMap = da.readCheckoutRecordMap();	
		HashMap<String, HashMap<LibraryMember, CheckoutRecordEntry>> overdueEntries = 
				new HashMap<String, HashMap<LibraryMember, CheckoutRecordEntry>>();
		int count = 0;
		HashMap<LibraryMember, CheckoutRecordEntry> temp;
		for(Map.Entry<String, CheckoutRecord> record: checkoutRecordMap.entrySet()){
          for(CheckoutRecordEntry entry: record.getValue().getCheckoutRecordEntries()){
     			if(entry.getBookCopy().getBook().getIsbn().toString().equals(isbn)){
     				temp = new HashMap<>();
     				if(entry.getDueDate().isAfter(LocalDate.now())){
     					count ++;
     					temp.put(record.getValue().getLibraryMember(), entry);
     					overdueEntries.put(count+"", temp);
     				}
     			}
          }
        }		
		for(String e: overdueEntries.keySet()){
			System.out.println(e);
		}
		return overdueEntries;
	}

}
