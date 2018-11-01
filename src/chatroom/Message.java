package chatroom;

public class Message {
	private String from;
	private String to;
	private MessageType msg_type;
	private String info;

	public Message() {
		super();
	}

	public Message(String from, String to, MessageType msg_type, String info) {
		super();
		this.from = from;
		this.to = to;
		this.msg_type = msg_type;
		this.info = info;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public MessageType getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(MessageType msg_type) {
		this.msg_type = msg_type;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
