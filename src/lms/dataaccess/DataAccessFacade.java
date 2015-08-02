package lms.dataaccess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import lms.business.Book;
import lms.business.CheckoutRecord;
import lms.business.CheckoutRecordEntry;
import lms.business.LibraryMember;
import lms.business.LibrarySystemException;


@SuppressWarnings("serial")
public class DataAccessFacade implements DataAccess, Serializable {

	enum StorageType {
		BOOKS, MEMBERS, USERS, AUTHORS, RECORD ;
	}

	private static String getPath(){
		if((System.getProperty("os.name")).equals("Linux") || System.getProperty("os.name").equals("Mac OS X")){
			return "/src/lms/dataaccess/storage";
		}
		return "\\src\\lms\\dataaccess\\storage";
	}
	public static final String OUTPUT_DIR = System.getProperty("user.dir") 

			+ getPath();
	public static final String DATE_PATTERN = "MM/dd/yyyy";

	// //specialized lookup methods
	public LibraryMember searchMember(String memberId) {
		 HashMap<String, LibraryMember> memberMap = readMemberMap();
		 LibraryMember m = memberMap.get(memberId);
		 return m;
	}

	public Book searchBook(String isbn) {
		HashMap<String, Book> booksMap = readBooksMap();
		Book b = booksMap.get(isbn);
		return b;
	}
	
	public CheckoutRecord searchCheckoutRecord(String memberId) {
		 HashMap<String, CheckoutRecord> memberRecord = readCheckoutRecordMap();
		 CheckoutRecord m = memberRecord.get(memberId);
		 return m;
	}

	@Override
	public boolean isMemberExist(LibraryMember member) {
		HashMap<String, LibraryMember> memberMap = readMemberMap();
		String memberID = member.getMemberId();
		
		if(memberMap.containsKey(memberID)) {
			return true;
		}
		
		return false;
	}
	
	
	
	public boolean isBookExists(Book book){
		HashMap<String, Book> booksMap =  readBooksMap();
		if(booksMap.containsKey(book.getIsbn())){
			return true;
		}
		return false;
	}

	public boolean isBookExists(String isbn){
		HashMap<String, Book> booksMap =  readBooksMap();
		if(booksMap.containsKey(isbn)){
			return true;
		}
		return false;
	}
	
	public boolean isRecordExists(String memberId){
		HashMap<String, CheckoutRecord> recordMap =  readCheckoutRecordMap();
		if(recordMap.containsKey(memberId)){
			return true;
		}
		return false;
	}
	
	public Auth login(String id, String pwd) {
		HashMap<String, User> userMap = readUserMap();
		if (!userMap.containsKey(id))
			return null;
		User user = userMap.get(id);
		if (!pwd.equals(user.getPassword())) {
			return null;
		}
		return user.getAuthorization();
	}

	
	
	///////save methods
	//saveNewMember
	public void saveNewMember(LibraryMember member) {
		HashMap<String, LibraryMember> memberMap = readMemberMap();
		String id = member.getMemberId();
		memberMap.put(id, member);
		saveToStorage(StorageType.MEMBERS, memberMap);
	}
		
	
	//public void updateMember(LibraryMember member) 
	//save new lendable item
	

	public void saveNewBook(Book book) {
		HashMap<String, Book> bookMap = readBooksMap();
		String isbn = book.getIsbn();
		bookMap.put(isbn, book);
		saveToStorage(StorageType.BOOKS, bookMap);
	}
	
	public void saveNewCheckoutRecord(CheckoutRecord record) {
		HashMap<String, CheckoutRecord> recordMap = readCheckoutRecordMap();
		String id = record.getLibraryMember().getMemberId();
		recordMap.put(id, record);
		saveToStorage(StorageType.RECORD, recordMap);
	}


	@SuppressWarnings("unchecked")
	public HashMap<String, Book> readBooksMap() {
		return (HashMap<String, Book>) readFromStorage(StorageType.BOOKS);
	}

	
	
	
	@SuppressWarnings("unchecked")
	public HashMap<String, LibraryMember> readMemberMap() {
		return (HashMap<String, LibraryMember>)readFromStorage(StorageType.MEMBERS);
	}
	
	

	@SuppressWarnings("unchecked")
	public HashMap<String, User> readUserMap() {
		return (HashMap<String, User>) readFromStorage(StorageType.USERS);
	}

	
	@SuppressWarnings("unchecked")
	public HashMap<String, CheckoutRecord> readCheckoutRecordMap() {
		return (HashMap<String,CheckoutRecord>) readFromStorage(StorageType.RECORD);
	}
	
	/////load methods - these place test data into the storage area
	///// - used just once at startup  
	static void loadMemberMap(List<LibraryMember> memberList) {
		HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
		memberList.forEach(member -> members.put(member.getMemberId(), member));
		saveToStorage(StorageType.MEMBERS, members);
	}

	static void loadBookMap(List<Book> bookList) {
		HashMap<String, Book> books = new HashMap<String, Book>();
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		saveToStorage(StorageType.BOOKS, books);
	}

	static void loadUserMap(List<User> userList) {
		HashMap<String, User> users = new HashMap<String, User>();
		userList.forEach(user -> users.put(user.getId(), user));
		saveToStorage(StorageType.USERS, users);
	}
	
	static void loadCheckoutRecordMap(List<CheckoutRecord> recordList) {
		HashMap<String, CheckoutRecord> records = new HashMap<String, CheckoutRecord>();
		recordList.forEach(record -> records.put(record.getLibraryMember().getMemberId()+"", record));
		
		saveToStorage(StorageType.RECORD, records);
	}
	
	static void saveToStorage(StorageType type, Object ob) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR,
					type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
		}
	}

	static Object readFromStorage(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR,
					type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
		return retVal;
	}

	final static class Pair<S, T> implements Serializable {

		S first;
		T second;

		Pair(S s, T t) {
			first = s;
			second = t;
		}

		@Override
		public boolean equals(Object ob) {
			if (ob == null)
				return false;
			if (this == ob)
				return true;
			if (ob.getClass() != getClass())
				return false;
			@SuppressWarnings("unchecked")
			Pair<S, T> p = (Pair<S, T>) ob;
			return p.first.equals(first) && p.second.equals(second);
		}

		@Override
		public int hashCode() {
			return first.hashCode() + 5 * second.hashCode();
		}

		@Override
		public String toString() {
			return "(" + first.toString() + ", " + second.toString() + ")";
		}

		private static final long serialVersionUID = 5399827794066637059L;
	}

	@Override
	public boolean isMemberExist(String memberId) {
		HashMap<String, LibraryMember> memberMap = readMemberMap();
		if(memberMap.containsKey(memberId)) {
			return true;
		}
		
		return false;
	}


}
