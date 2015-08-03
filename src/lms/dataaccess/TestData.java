package lms.dataaccess;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.util.converter.LocalDateStringConverter;
import lms.business.Address;
import lms.business.Author;
import lms.business.Book;
import lms.business.CheckoutRecord;
import lms.business.CheckoutRecordEntry;
import lms.business.LibraryMember;

public class TestData {
	// List<LibraryMember> members = new ArrayList<LibraryMember>();
	@SuppressWarnings("serial")
	List<Address> addresses = new ArrayList<Address>() {		{
			add(new Address("101 S. Main", "Fairfield", "IA", "52556"));
			add(new Address("51 S. George", "Georgetown", "MI", "65434"));
			add(new Address("23 Headley Ave", "Seville", "Georgia", "41234"));
			add(new Address("1 N. Baton", "Baton Rouge", "LA", "33556"));
			add(new Address("5001 Venice Dr.", "Los Angeles", "CA", "93736"));
			add(new Address("1435 Channing Ave", "Palo Alto", "CA", "94301"));
			add(new Address("42 Dogwood Dr.", "Fairfield", "IA", "52556"));
			add(new Address("501 Central", "Mountain View", "CA", "94707"));
		}
	};
	
	@SuppressWarnings("serial")
	public List<Author> allAuthors = new ArrayList<Author>() {
		{
			add(new Author("Joe", "Thomas", "641-445-2123", addresses.get(0), "A happy man is he."));
			add(new Author("Sandra", "Thomas", "641-445-2123", addresses.get(0), "A happy wife is she."));
			add(new Author("Nirmal", "Pugh", "641-919-3223", addresses.get(1), "Thinker of thoughts."));
			add(new Author("Andrew", "Cleveland", "976-445-2232", addresses.get(2), "Author of childrens' books."));
			add(new Author("Sarah", "Connor", "123-422-2663", addresses.get(3), "Known for her clever style."));
		}
	};

	// @SuppressWarnings("serial")
	private final List<LibraryMember> allMembers = new ArrayList<LibraryMember>() {
		{

			add(new LibraryMember("001", "Isabella", "Johnson", "isabella.johnson@example.com", "453535", "1107 Primrose Lane",
					"Waumandee", "WI", "54622"));
			add(new LibraryMember("002", "Isabella", "Johnson", "isabella.johnson@example.com", "453535", "4953 Oliver Street",
					"Frisco", "TX", "75034"));
			add(new LibraryMember("003", "Ethan", "Williams", "ethan.williams@example.com", "453535", "3549 Tanglewood Road",
					"Jakson", "MS", "39201"));
			add(new LibraryMember("004", "Emma", "Jones", "emma.jones@example.com", "453535", "4511 Retreat Avenue", "Portland",
					"ME", "04101"));
			add(new LibraryMember("005", "Michael", "Brown", "michael.brown@example.com", "453535", "2654 Shingleton Road",
					"Sister Lakes", "MI", "49045"));
		}

	};

	// Book(int id, String isbn, String title, int maxCheckoutLength,
	// List<Author> authors)
	@SuppressWarnings("serial")
	List<Book> allBooks = new ArrayList<Book>() {
		{
			add(new Book("23-11451", "The Big Fish", 21, Arrays.asList(allAuthors.get(0), allAuthors.get(1))));
			add(new Book("28-12331", "Antartica", 7, Arrays.asList(allAuthors.get(2))));
			add(new Book("99-22223", "Thinking Java", 21, Arrays.asList(allAuthors.get(3))));
			add(new Book("48-56882", "Jimmy's First Day of School", 7, Arrays.asList(allAuthors.get(4))));

		}
	};

	 List<CheckoutRecord> allRecords = new ArrayList<CheckoutRecord>() {
		 {	
//			 add(new CheckoutRecord(
//					 getAllMembers().get(0),
//					 new CheckoutRecordEntry(
//							 getAllBooks().get(0).getCopy(2),
//							 LocalDate.of(2015, 7,1), LocalDate.of(2015, 7, 21)
//						)
//					 )
//				);
//			 
//			 add(new CheckoutRecord(getAllMembers().get(1),
//					 new CheckoutRecordEntry(getAllBooks().get(1).getCopy(2), 
//					 LocalDate.of(2015, 7,12), LocalDate.of(2015, 7, 18))));
//			 
//			 add(new CheckoutRecord(getAllMembers().get(2),
//					 new CheckoutRecordEntry(getAllBooks().get(2).getCopy(2),
//					 LocalDate.of(2015, 7,30), LocalDate.of(2015, 8, 20))));
		 }
	 };
	 
	 List<CheckoutRecordEntry> allEntries = new ArrayList<CheckoutRecordEntry>() {
		 {
			 add(new CheckoutRecordEntry(getAllBooks().get(3).getCopy(1),
					 LocalDate.of(2015, 7,3), LocalDate.of(2015, 7, 24)));
		 }
	 };
	 
	@SuppressWarnings("serial")
	List<User> allUsers = new ArrayList<User>() {
		{
			add(new User("101", "xyz", Auth.LIBRARIAN));
			add(new User("102", "abc", Auth.ADMIN));
			add(new User("103", "111", Auth.BOTH));
		}
	};

	public static void main(String[] args) {
		TestData td = new TestData();
		td.bookData();
		td.userData();
		td.memberData();
		td.checkoutRecordData();
		DataAccess da = new DataAccessFacade();
		System.out.println(da.readBooksMap());
		System.out.println(da.readUserMap());
		System.out.println(da.readMemberMap());
		System.out.println(da.readCheckoutRecordMap());
	}

	// /create books
	public void bookData() {
		allBooks.get(0).addCopy();
		allBooks.get(0).addCopy();
		allBooks.get(1).addCopy();
		allBooks.get(3).addCopy();
		allBooks.get(2).addCopy();
		allBooks.get(2).addCopy();
		DataAccessFacade.loadBookMap(allBooks);
	}

	public void userData() {
		DataAccessFacade.loadUserMap(allUsers);
	}
	
	public void memberData() {
		DataAccessFacade.loadMemberMap(allMembers);
	}

	public void checkoutRecordData() {
//		allRecords.get(0).addCheckoutRecordEntry(allEntries.get(0));
		DataAccessFacade.loadCheckoutRecordMap(allRecords);
	}

	public List<LibraryMember> getAllMembers() {
		return this.allMembers;
	}

	public List<Book> getAllBooks() {
		return this.allBooks;
	}



	// create library members

	// public void libraryMemberData() {

}
