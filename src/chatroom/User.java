package chatroom;

import java.io.Serializable;

public class User implements Serializable{
	private String name;
	private String psw;
	private int id;
	
	
	@Override
	public String toString() {
		return "User [name=" + name + ", psw=" + psw + ", id=" + id + "]";
	}
	public User() {
		super();
	}
	public User(String name, String psw, int id) {
		super();
		this.name = name;
		this.psw = psw;
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
