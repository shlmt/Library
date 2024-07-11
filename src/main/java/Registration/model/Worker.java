package Registration.model;

public class Worker {
	private String name;
	private String password;
	private String phone;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
