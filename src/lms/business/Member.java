package lms.business;

public class Member {

	private String firstName;
	private String lastName;

	private String state;
	private String city;
	private String phone;

	public Member(String firstName, String lastName, String state, String city,
			String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.state = state;
		this.city = city;
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
