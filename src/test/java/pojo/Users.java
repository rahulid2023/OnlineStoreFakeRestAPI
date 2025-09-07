package pojo;

public class Users {
	
	private String email;
	private String userName;
	private String password;
    private Name name;
    private Address address;
	private String phone;

    public Users(String email, String userName, String password, Name name, Address address, String phone) {
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
