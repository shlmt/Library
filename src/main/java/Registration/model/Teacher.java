package Registration.model;

public class Teacher {
	private String name;
	private String password;
	private String address;
	private String phone;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phoneNum) {
		this.phone = phoneNum;
	}
	 public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
