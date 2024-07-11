package Registration.model;

public class Book {
	private String title;
	private String desc;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
		public String getDesc() {
		return desc;
	}
	public void setDesc(String d) {
		this.desc = d;
	}
	public Book(String title, String d) {
		super();
		this.title = title;
		this.desc = d;
	}
	
}
