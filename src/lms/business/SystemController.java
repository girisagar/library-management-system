package lms.business;

import java.util.HashMap;
import java.util.List;

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
	 */
	// public void checkoutBook(String memberId, String isbn) throws
	// LibrarySystemException {

	@Override
	public Book searchBook(String isbn) {
		DataAccess da = new DataAccessFacade();
		return da.searchBook(isbn);
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

	public boolean addBookCopy(String isbn) throws LibrarySystemException {
		Book book = searchBook(isbn);
		if (book == null)
			throw new LibrarySystemException("No book with isbn " + isbn + " is in the library collection!");
		book.addCopy();
		return true;
	}

	public static void main(String[] args) throws LibrarySystemException {

	}

	@Override
	public void checkoutBook(String memberId, String isbn) throws LibrarySystemException {
		// TODO Auto-generated method stub

	}

}
