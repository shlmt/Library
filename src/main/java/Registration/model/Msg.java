package Registration.model;

public class Msg {
	private String senderName;
	private String text;
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	
		public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Msg(String senderName, String text) {
		super();
		this.senderName = senderName;
		this.text = text;
	}
	
}
