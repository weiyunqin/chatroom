package chatroom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserServer implements Serializable{
	private static List<User> users = new ArrayList<>();
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	File file = new File("user.dat");
	
	public UserServer() {
		super();
	}
	
	
	public UserServer(List<User> users) {
		super();
		this.users = users;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void writeUser() {
			try {
				oos = new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(users);
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public List<User> readUser() {
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			users = (List<User>) ois.readObject();
			ois.close();
			return users;
		} catch (IOException|ClassNotFoundException e) {
			System.out.println("未找到文件");
			e.printStackTrace();
		}
		return null;
	}
	
	public void addUser(User user) {
		user.setId(users.size()+1);
		users.add(user);
	}
	
	public boolean checkUserIsExist(User user) {
		for(User u : users) {
			if(u.getName().equals(user.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean findUser(User user) {
		for(User u : users) {
			if(u.getName().equals(user.getName()) &&u.getPsw().equals(user.getPsw())) {
				System.out.println("用户名和密码正确");
				return true;
				
			}
		}
		System.out.println("用户名或密码错误");
		return false;
	}
	
}
