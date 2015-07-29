package lms.business;

public class LibraryMember extends Person{
	private String memberId;
	private String email;
	

	public LibraryMember(String memberId, String firstName, String lastName, String email, String phone, 
				String street, String city, String state, String zip) {
		super(firstName, lastName, phone, new Address(street, city, state, zip) );
		this.memberId = memberId;
		this.email = email;
	}
	
	public String getMemberId() {
		return this.memberId;
	}

	public String getEmail() {
		return this.email;
	}
	public String getPhone(){
		return super.getTelephone();
	}
	public String getFirstName() {
		return super.getFirstName();
	}
	public String getLastName() {
		return super.getLastName();
	}
	public String getTelephone() {
		return super.getTelephone();
	}
	public String getStreet() {
		return super.getAddress().getStreet();
	}
	public String getCity() {
		return super.getAddress().getCity();
	}
	public String getState() {
		return super.getAddress().getState();
	}
	public String getZip() {
		return super.getAddress().getZip();
	}
	
}
